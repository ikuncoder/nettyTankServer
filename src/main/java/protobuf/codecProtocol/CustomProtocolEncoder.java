package protobuf.codecProtocol;

import com.google.protobuf.MessageLite;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import protobuf.message.MessageEnum;
import protobuf.message.messageClass.*;

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
        }else if(msg instanceof OutReqLoginMessage.LoginMessage){
            messageId=MessageEnum.LoginMessage.getMessageId();
        }else if(msg instanceof OutReqNetWorkGameMessage.NetWorkGameMessage){
            messageId=MessageEnum.ReqNetWorkGameMessage.getMessageId();
        }else if(msg instanceof OutRemoveBulletMessage.RemoveBulletMessage){
            messageId = MessageEnum.RemoveBulletMessage.getMessageId();
        }else if(msg instanceof OutSmallExplosionMessage.SmallExplosionMessage){
            messageId=MessageEnum.SmallExplosionMessage.getMessageId();
        }else if(msg instanceof OutBulletInfoMessage.BulletInfoMessage){
            messageId=MessageEnum.BulletInfoMessage.getMessageId();
        }else if(msg instanceof OutUserTankMessage.UserTankMessage){
            messageId=MessageEnum.UserTankMessage.getMessageId();
        }else if(msg instanceof OutAiTankMessage.AiTankMessage){
            messageId=MessageEnum.AiTankMessage.getMessageId();
        }else if(msg instanceof OutTankDisappearMessage.TankDisappearMessage){
            messageId=MessageEnum.TankDisappearMessage.getMessageId();
        }else if(msg instanceof OutBigExplosionMessage.BigExplosionMessage){
            messageId=MessageEnum.BigExplosionMessage.getMessageId();
        }else if(msg instanceof OutRemoveBreakableWallMessage.RemoveBreakableWallMessage){
            messageId=MessageEnum.RemoveBreakableWallMessage.getMessageId();
        }else if(msg instanceof OutBreakableWallMessage.BreakableWallMessage){
            messageId=MessageEnum.BreakableWallMessage.getMessageId();
        }else if(msg instanceof OutSmallAnimationMessage.SmallAnimationMessage){
            messageId=MessageEnum.SmallAnimationMessage.getMessageId();
        }else if(msg instanceof OutRemoveSmallExplosionMessage.RemoveSmallExplosionMessage){
            messageId=MessageEnum.RemoveSmallExplosionMessage.getMessageId();
        }else if(msg instanceof OutBigAnimationMessage.BigAnimationMessage){
            messageId=MessageEnum.BigAnimationMessage.getMessageId();
        }else if(msg instanceof OutRemoveBigExplosionMessage.RemoveBigExplosionMessage){
            messageId=MessageEnum.RemoveBigExplosionMessage.getMessageId();
        }else if(msg instanceof OutRemovePowerUpMessage.RemovePowerUpMessage){
            messageId=MessageEnum.RemovePowerUpMessage.getMessageId();
        }else if(msg instanceof OutCreateBulletMessage.CreateBulletMessage){
            messageId=MessageEnum.CreateBulletMessage.getMessageId();
        }else if(msg instanceof OutTurnLeftMessage.TurnLeftMessage){
            messageId=MessageEnum.TurnLeftMessage.getMessageId();
        }else if(msg instanceof OutTurnRightMessage.TurnRightMessage){
            messageId=MessageEnum.TurnRightMessage.getMessageId();
        }else if(msg instanceof OutTurnUpMessage.TurnUpMessage){
            messageId=MessageEnum.TurnUpMessage.getMessageId();
        }else if(msg instanceof OutTurnDownMessage.TurnDownMessage){
            messageId=MessageEnum.TurnDownMessage.getMessageId();
        }else if(msg instanceof OutTurnFiringMessage.TurnFiringMessage){
            messageId=MessageEnum.TurnFiringMessage.getMessageId();
        }else if(msg instanceof OutReleaseLeftMessage.ReleaseLeftMessage){
            messageId=MessageEnum.ReleaseLeftMessage.getMessageId();
        }else if(msg instanceof OutReleaseRightMessage.ReleaseRightMessage){
            messageId=MessageEnum.ReleaseRightMessage.getMessageId();
        }else if(msg instanceof OutReleaseUpMessage.ReleaseUpMessage){
            messageId=MessageEnum.ReleaseUpMessage.getMessageId();
        }else if(msg instanceof OutReleaseDownMessage.ReleaseDownMessage){
            messageId=MessageEnum.ReleaseDownMessage.getMessageId();
        }else if(msg instanceof OutReleaseFiringMessage.ReleaseFiringMessage){
            messageId=MessageEnum.ReleaseFiringMessage.getMessageId();
        }else if(msg instanceof OutResRandomMapMessage.ResRandomMapMessage){
            messageId=MessageEnum.ResRandomMapMessage.getMessageId();
        }else if(msg instanceof OutgameFinishedAndGameWonMessage.gameFinishedAndGameWonMessage){
            messageId=MessageEnum.GameFinishedAndGameWonMessage.getMessageId();
        }else if(msg instanceof OutgameFinishedAndNotGameWonMessage.gameFinishedAndNotGameWonMessage){
            messageId=MessageEnum.GameFinishedAndNotGameWonMessage.getMessageId();
        }
        return messageId;
    }
}
