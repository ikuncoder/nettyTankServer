package protobuf.codecProtocol;

import com.google.protobuf.MessageLite;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import protobuf.message.MessageEnum;
import protobuf.message.messageClass.OutReqLoginMessage;
import protobuf.message.messageClass.OutTestMessage;

/**
 * Created by longzhu on 2019/4/26 14:59
 * 自定义编解码器、
 * 协议格式为[[int:data长度][int:协议号][byte[]:data]]
 */
public class CustomProtocolEncoder extends MessageToByteEncoder<MessageLite> {
    @Override
    protected void encode(ChannelHandlerContext ctx, MessageLite msg, ByteBuf out) throws Exception {
        byte[] body=msg.toByteArray();
        int bodyLength=body.length;
        int messageId=getMessageId(msg);
        out.writeInt(bodyLength);
        out.writeInt(messageId);
        out.writeBytes(body);
    }

    public int getMessageId(MessageLite msg){
        int messageId=0;
        if(msg instanceof OutTestMessage.TestMessage){
            messageId=MessageEnum.TestMessage.getMessageId();
        }else if(msg instanceof OutReqLoginMessage.ReqLoginMessage){
            messageId=MessageEnum.LoginMessage.getMessageId();
        }
        return messageId;
    }
}
