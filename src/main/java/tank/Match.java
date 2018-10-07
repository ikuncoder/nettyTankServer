package tank;
import io.netty.channel.ChannelHandlerContext;
import java.util.ArrayList;
import java.util.Random;

public class Match implements Runnable {
    //房间号
    static int groupNum;

    @Override
    public void run() {
        while(true){
            //每三秒检测一次
            try{
                Thread.sleep(3000);
            }catch (Exception e){
                System.out.println(e.getStackTrace());
            }
            ArrayList<ChannelHandlerContext> channelHandlerContextsArrayList=GameCenter.channelHandlerContextArrayList;
            if(channelHandlerContextsArrayList.size()>=2){
                int groupCount=channelHandlerContextsArrayList.size()/2;//房间数量
                //为每一个组成队伍的玩家启动一个房间
                for(int i=0;i<groupCount;i++){
                    ArrayList<ChannelHandlerContext> tempChannelHandlerContextsArrayList=new ArrayList<>();
                    tempChannelHandlerContextsArrayList.add(channelHandlerContextsArrayList.get(2*i));
                    tempChannelHandlerContextsArrayList.add(channelHandlerContextsArrayList.get(2*i+1));
                    //随机地图
                    Random random = new Random();
                    int mapNum = random.nextInt(3);
                    TankWorld tankWorld=new TankWorld(groupNum++,tempChannelHandlerContextsArrayList,mapNum);
                    GameCenter.tankWorldArrayList.add(tankWorld);
                    //新建一条线程
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            tankWorld.tankWorkStart(tankWorld);
                        }
                    }).start();
                }
                for(int i=0;i<groupCount;i++){
                    GameCenter.channelHandlerContextArrayList.remove(i);//通道1
                    GameCenter.channelHandlerContextArrayList.remove(i);//通道2
                }
            }
        }
    }
}
