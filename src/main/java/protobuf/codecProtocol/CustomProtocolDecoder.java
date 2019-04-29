package protobuf.codecProtocol;

import com.google.protobuf.MessageLite;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import protobuf.message.MessageEnum;
import protobuf.message.messageClass.OutReqLoginMessage;
import protobuf.message.messageClass.OutTestMessage;

import java.util.List;

/**
 * Created by longzhu on 2019/4/26 16:47
 * 自定义解码器
 *  协议格式为[[int:data长度][int:协议号][byte[]:data]]
 */
public class CustomProtocolDecoder extends ByteToMessageDecoder {
    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        while(in.readableBytes()>8){  //如果可读长度小于包头从长度，退出
            in.markReaderIndex();
            int bodyLength=in.readInt();  //data长度
            int messageId=in.readInt();   //协议号
            //如果可读长度小于body长度，恢复读指针，退出
            if(in.readableBytes()<bodyLength){
                in.resetReaderIndex();
                return;
            }
            //读取body
            ByteBuf bodyByteBuf=in.readBytes(bodyLength);
            byte[] array;
            int offset;

            int readableLen=bodyByteBuf.readableBytes();
            if(bodyByteBuf.hasArray()){
                array=bodyByteBuf.array();
                offset=bodyByteBuf.arrayOffset()+bodyByteBuf.readerIndex();
            }else{
                array=new byte[readableLen];
                bodyByteBuf.getBytes(bodyByteBuf.readerIndex(),array,0,readableLen);
                offset=0;
            }

            //反序列化
            MessageLite result=decodeBody(messageId,array,offset,readableLen);
            if(result==null){
                break;
            }
            out.add(result);
        }
    }

    public MessageLite decodeBody(int messageId,byte[] array,int offset,int length)throws Exception{
        if(messageId== MessageEnum.TestMessage.getMessageId()){
            return OutTestMessage.TestMessage.getDefaultInstance().getParserForType().parseFrom(array,offset,length);
        }else if(messageId==MessageEnum.LoginMessage.getMessageId()){
            return OutReqLoginMessage.LoginMessage.getDefaultInstance().getParserForType().parseFrom(array,offset,length);
        }
        return null;
    }
}
