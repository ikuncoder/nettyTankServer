package protobuf;

import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import protobuf.common.byteArrayTools.Codecable;
import protobuf.message.messageBody.OutTestMessage;

/**
 * @author lishikun
 * @version 1.0
 * @date 2018年9月05日
 */
@Sharable
public class SubReqServerHandler extends ChannelInboundHandlerAdapter {
    private SubReqServerHandler(){}
    private static SubReqServerHandler subReqServerHandler=null;
    public static SubReqServerHandler getInstance(){
        if(subReqServerHandler==null){
            subReqServerHandler=new SubReqServerHandler();
        }
        return subReqServerHandler;
    }
    @Override
    public void channelActive(ChannelHandlerContext ctx) {
        //GameCenter.getInstance().connetEnter(ctx);
        OutTestMessage.TestMessage testMessage=new OutTestMessage.TestMessage();
        //ctx.write(codecable);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        Codecable codecable=(Codecable)msg;
        System.out.println("server:"+codecable);
        ctx.write(codecable);
        //GameCenter.getInstance().receiveMsgHandler(ctx, msg);
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
