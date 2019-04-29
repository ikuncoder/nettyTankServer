package protobuf;

import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import org.apache.log4j.Logger;
import protobuf.message.messageClass.OutReqLoginMessage;
import protobuf.message.messageClass.OutTestMessage;
import protobuf.messageDispather.Dispather;
import protobuf.user.UserManager;


/**
 * @author lishikun
 * @version 1.0
 * @date 2018年9月05日
 */
@Sharable
public class SubReqServerHandler extends ChannelInboundHandlerAdapter {
    private static Logger logger=Logger.getLogger(SubReqServerHandler.class);
    private SubReqServerHandler(){}
    private static SubReqServerHandler subReqServerHandler;
    public static SubReqServerHandler getInstance(){
        if(subReqServerHandler==null){
            synchronized (SubReqServerHandler.class){
                if(subReqServerHandler==null){
                    subReqServerHandler=new SubReqServerHandler();
                }
            }
        }
        return subReqServerHandler;
    }
    @Override
    public void channelActive(ChannelHandlerContext ctx) {
        //GameCenter.getInstance().connetEnter(ctx);
        //ctx.writeAndFlush(respTestMessage());
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        logger.error("received message"+msg);
        if(msg instanceof OutReqLoginMessage.LoginMessage){
            try {
                loginMessageHandler(ctx, msg);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }else{
            //GameCenter.getInstance().receiveMsgHandler(ctx, msg);
            //System.out.println("server==========="+msg);
            Dispather.getInstance().messageDispather(ctx,msg);
        }
    }

    public void loginMessageHandler(ChannelHandlerContext ctx, Object msg){
        UserManager.getInstance().insertUserContext(ctx,msg);
    }

    private OutTestMessage.TestMessage respTestMessage(){
        OutTestMessage.TestMessage.Builder builder= OutTestMessage.TestMessage.newBuilder();
        builder.setName("serverlsk");
        builder.setAge(22);
        builder.setHigh(168);
        return builder.build();
    }

    private ServerProtocolProto.ServerProtocol resp(String msg) {
        ServerProtocolProto.ServerProtocol.Builder builder = ServerProtocolProto.ServerProtocol.newBuilder();
        builder.setSign("123");
        builder.setPlayerId("1");
        builder.setX(1);
        builder.setY(1);
        builder.setDirection(1);
        builder.setLives(1);
        builder.setHealth(1);
        builder.setScore(1);
        builder.setStrength(1);
        return builder.build();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();// 发生异常，关闭链路
    }
}
