package protobuf.codecProtocol;

import com.google.protobuf.MessageLite;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import protobuf.message.MessageEnum;
import protobuf.message.messageClass.*;

import java.util.List;

/**
 * Created by longzhu on 2019/4/26 16:47
 * 自定义解码器
 *  协议格式为[[int:data长度][int:协议号][byte[]:data]]
 */
public class CustomProtocolDecoder extends ByteToMessageDecoder {
    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        while(in.readableBytes()>=8){  //如果可读长度小于包头从长度，退出
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
            out.add(result);
        }
    }

    public MessageLite decodeBody(int messageId,byte[] array,int offset,int length)throws Exception{
        if(messageId== MessageEnum.TestMessage.getMessageId()){
            return OutTestMessage.TestMessage.getDefaultInstance().getParserForType().parseFrom(array,offset,length);
        }else if(messageId==MessageEnum.LoginMessage.getMessageId()){
            return OutReqLoginMessage.LoginMessage.getDefaultInstance().getParserForType().parseFrom(array,offset,length);
        }else if(messageId==MessageEnum.ReqNetWorkGameMessage.getMessageId()){
            return OutReqNetWorkGameMessage.NetWorkGameMessage.getDefaultInstance().getParserForType().parseFrom(array,offset,length);
        }else if(messageId==MessageEnum.RemoveBulletMessage.getMessageId()){
            return OutRemoveBulletMessage.RemoveBulletMessage.getDefaultInstance().getParserForType().parseFrom(array,offset,length);
        }else if(messageId==MessageEnum.SmallExplosionMessage.getMessageId()){
            return OutSmallExplosionMessage.SmallExplosionMessage.getDefaultInstance().getParserForType().parseFrom(array,offset,length);
        }else if(messageId==MessageEnum.BulletInfoMessage.getMessageId()){
            return OutBulletInfoMessage.BulletInfoMessage.getDefaultInstance().getParserForType().parseFrom(array,offset,length);
        }else if(messageId==MessageEnum.UserTankMessage.getMessageId()){
            return OutUserTankMessage.UserTankMessage.getDefaultInstance().getParserForType().parseFrom(array,offset,length);
        }else if(messageId==MessageEnum.AiTankMessage.getMessageId()){
            return OutAiTankMessage.AiTankMessage.getDefaultInstance().getParserForType().parseFrom(array,offset,length);
        }else if(messageId==MessageEnum.TankDisappearMessage.getMessageId()){
            return OutTankDisappearMessage.TankDisappearMessage.getDefaultInstance().getParserForType().parseFrom(array,offset,length);
        }else if(messageId==MessageEnum.BigExplosionMessage.getMessageId()){
            return OutBigExplosionMessage.BigExplosionMessage.getDefaultInstance().getParserForType().parseFrom(array,offset,length);
        }else if(messageId==MessageEnum.RemoveBreakableWallMessage.getMessageId()){
            return OutRemoveBreakableWallMessage.RemoveBreakableWallMessage.getDefaultInstance().getParserForType().parseFrom(array,offset,length);
        }else if(messageId==MessageEnum.BreakableWallMessage.getMessageId()){
            return OutBreakableWallMessage.BreakableWallMessage.getDefaultInstance().getParserForType().parseFrom(array,offset,length);
        }else if(messageId==MessageEnum.SmallAnimationMessage.getMessageId()){
            return OutSmallAnimationMessage.SmallAnimationMessage.getDefaultInstance().getParserForType().parseFrom(array,offset,length);
        }else if(messageId==MessageEnum.RemoveSmallExplosionMessage.getMessageId()){
            return OutRemoveSmallExplosionMessage.RemoveSmallExplosionMessage.getDefaultInstance().getParserForType().parseFrom(array,offset,length);
        }else if(messageId==MessageEnum.BigAnimationMessage.getMessageId()){
            return OutBigAnimationMessage.BigAnimationMessage.getDefaultInstance().getParserForType().parseFrom(array,offset,length);
        }else if(messageId==MessageEnum.RemoveBigExplosionMessage.getMessageId()){
            return OutRemoveBigExplosionMessage.RemoveBigExplosionMessage.getDefaultInstance().getParserForType().parseFrom(array,offset,length);
        }else if(messageId==MessageEnum.RemovePowerUpMessage.getMessageId()){
            return OutRemovePowerUpMessage.RemovePowerUpMessage.getDefaultInstance().getParserForType().parseFrom(array,offset,length);
        }else if(messageId==MessageEnum.CreateBulletMessage.getMessageId()){
            return OutCreateBulletMessage.CreateBulletMessage.getDefaultInstance().getParserForType().parseFrom(array,offset,length);
        }else if(messageId==MessageEnum.TurnLeftMessage.getMessageId()){
            return OutTurnLeftMessage.TurnLeftMessage.getDefaultInstance().getParserForType().parseFrom(array,offset,length);
        }else if(messageId==MessageEnum.TurnRightMessage.getMessageId()){
            return OutTurnRightMessage.TurnRightMessage.getDefaultInstance().getParserForType().parseFrom(array,offset,length);
        }else if(messageId==MessageEnum.TurnUpMessage.getMessageId()){
            return OutTurnUpMessage.TurnUpMessage.getDefaultInstance().getParserForType().parseFrom(array,offset,length);
        }else if(messageId==MessageEnum.TurnDownMessage.getMessageId()){
            return OutTurnDownMessage.TurnDownMessage.getDefaultInstance().getParserForType().parseFrom(array,offset,length);
        }else if(messageId==MessageEnum.TurnFiringMessage.getMessageId()){
            return OutTurnFiringMessage.TurnFiringMessage.getDefaultInstance().getParserForType().parseFrom(array,offset,length);
        }else if(messageId==MessageEnum.ReleaseUpMessage.getMessageId()){
            return OutReleaseUpMessage.ReleaseUpMessage.getDefaultInstance().getParserForType().parseFrom(array,offset,length);
        }else if(messageId==MessageEnum.ReleaseDownMessage.getMessageId()){
            return OutReleaseDownMessage.ReleaseDownMessage.getDefaultInstance().getParserForType().parseFrom(array,offset,length);
        }else if(messageId==MessageEnum.ReleaseLeftMessage.getMessageId()){
            return OutReleaseLeftMessage.ReleaseLeftMessage.getDefaultInstance().getParserForType().parseFrom(array,offset,length);
        }else if(messageId==MessageEnum.ReleaseRightMessage.getMessageId()){
            return OutReleaseRightMessage.ReleaseRightMessage.getDefaultInstance().getParserForType().parseFrom(array,offset,length);
        }else if(messageId==MessageEnum.ReleaseFiringMessage.getMessageId()){
            return OutReleaseFiringMessage.ReleaseFiringMessage.getDefaultInstance().getParserForType().parseFrom(array,offset,length);
        }else if(messageId==MessageEnum.ResRandomMapMessage.getMessageId()){
            return OutResRandomMapMessage.ResRandomMapMessage.getDefaultInstance().getParserForType().parseFrom(array,offset,length);
        }else if(messageId==MessageEnum.GameFinishedAndNotGameWonMessage.getMessageId()){
            return OutgameFinishedAndNotGameWonMessage.gameFinishedAndNotGameWonMessage.getDefaultInstance().getParserForType().parseFrom(array,offset,length);
        }else if(messageId==MessageEnum.GameFinishedAndGameWonMessage.getMessageId()){
            return OutgameFinishedAndGameWonMessage.gameFinishedAndGameWonMessage.getDefaultInstance().getParserForType().parseFrom(array,offset,length);
        }
        return null;
    }
}
