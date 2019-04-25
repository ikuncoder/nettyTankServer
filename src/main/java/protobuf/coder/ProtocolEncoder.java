package protobuf.coder;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import protobuf.common.byteArrayTools.Codecable;
import protobuf.common.byteArrayTools.PayloadEncoder;

/**
 * Created by longzhu on 2019/4/23 16:02
 * 编码器
 * 协议的格式：byte[2]:消息的长度+byte[2]:协议号+byte[]：消息体
 */
public class ProtocolEncoder extends MessageToByteEncoder<Codecable>{
    @Override
    protected void encode(ChannelHandlerContext ctx, Codecable msg, ByteBuf out) throws Exception {
        byte[] body = PayloadEncoder.getPayload(msg);
        int bodyLength=body.length;
        byte[] header = new byte[4];
        //body的长度
        /*header[0] =(byte)(bodyLength & 0xff);
        header[1] =(byte)((bodyLength>>8) & 0xff);*/
        //协议的id
        int messageId = msg.getCMD();
        /*header[2] = (byte)(messageId & 0xff);
        header[3] = (byte)((messageId>>8) & 0xff);*/
        //写出
        out.writeByte(bodyLength);
        out.writeByte(messageId);
        out.writeBytes(body);
    }
}
