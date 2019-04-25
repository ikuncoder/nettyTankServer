package protobuf.coder;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import protobuf.common.byteArrayTools.Codecable;
import protobuf.common.byteArrayTools.PayloadDecoder;
import protobuf.message.manager.ProtocolAnnotationManager;

import java.util.List;

/**
 * Created by longzhu on 2019/4/24 21:39
 * 解码器
 * 协议的格式：byte[2]:消息的长度+byte[2]:协议号+byte[]：消息体
 */
public class ProtocolDecoder extends ByteToMessageDecoder{
    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        //获取包头中body的长度
        int bodyLength=in.readInt();
        //获取消息号
        int messageId=in.readInt();
        //如果可读长度小于body长度，恢复读指针，退出。
        if(in.readableBytes()<bodyLength){
            in.resetReaderIndex();
            return;
        }
        ByteBuf bodyBytebuf = in.readBytes(bodyLength);
        byte[] bodyBytes=bodyBytebuf.array();
        Class clazz= ProtocolAnnotationManager.getInstance().getProtocolMap().get(messageId);
        Codecable message=PayloadDecoder.resolve(bodyBytes,clazz);
        out.add(message);
    }
}
