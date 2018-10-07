package tank;
import io.netty.channel.ChannelHandlerContext;
import protobuf.ClientProtocolProto;
import protobuf.ReceiveMsg;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class GameCenter {
    //后期优化，因为GameCenter是单例模式，所以下面不必设为静态变量，可以设为成员变量
    public static int flag;
    public static volatile ArrayList<ChannelHandlerContext> channelHandlerContextArrayList = new ArrayList<>();
    public static ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
    public static ArrayList<TankWorld> tankWorldArrayList=new ArrayList<>();
    private static int i=0;
    //单例模式
    private GameCenter(){}
    private static GameCenter gameCenter=null;
    public static GameCenter getInstance(){
        if(gameCenter==null){
            gameCenter=new GameCenter();
        }
        return gameCenter;
    }

    //每一个连接都要进入这个方法
    public void connetEnter(ChannelHandlerContext ctx){
        channelHandlerContextArrayList.add(ctx);
        //建立一条线程处理匹配问题
        i++;
        //保证只执行一次
        //优化猜想：这里也不用新建立一条线程，直接调用方法就可以了
        if(i==1){
            //玩家每3s匹配一次
            new Thread(new Match()).start();
        }
    }
    public void receiveMsgHandler(ChannelHandlerContext ctx, Object msg){
        //接收到的消息放进线程池进行处理
        ClientProtocolProto.ClientProtocol req = (ClientProtocolProto.ClientProtocol) msg;
        //后期优化猜想：这里可以不用新建立的线性池，因为netty原本就有线程池
        cachedThreadPool.execute(new ReceiveMsg(req));
    }
}
