package protobuf;

import io.netty.channel.ChannelHandlerContext;
import tank.TankWorld;

import java.util.ArrayList;

public class SendMsg {
    public static ChannelHandlerContext channelHandlerContext;
    public SendMsg(ChannelHandlerContext channelHandlerContext) {
        this.channelHandlerContext = channelHandlerContext;
    }

    public SendMsg() {
    }

    //协议格式：sign+ PlayerId + X + Y+ direction+live+health+score+strength+respawnCounter
    public void sendMessage(TankWorld tankWorld, String sign, String playerId, int... intArrays) {
        ArrayList<ChannelHandlerContext> channelHandlerContextsArrayList=tankWorld.getChannelHandlerContextsArrayList();
        if (sign.equals("playerId")) {//这里分配playerid
            for (int i = 0; i < channelHandlerContextsArrayList.size(); i++) {
                if (playerId.equals("0") && i == 0) {
                    channelHandlerContextsArrayList.get(i).write(resp(sign, playerId, intArrays));
                    channelHandlerContextsArrayList.get(i).flush();
                } else if (playerId.equals("1") && i == 1) {
                    channelHandlerContextsArrayList.get(i).write(resp(sign, playerId, intArrays));
                    channelHandlerContextsArrayList.get(i).flush();
                }
            }
        } else {
            //广播消息
            for (int i = 0; i < channelHandlerContextsArrayList.size(); i++) {
                channelHandlerContextsArrayList.get(i).write(resp(sign, playerId, intArrays));
                channelHandlerContextsArrayList.get(i).flush();
            }
        }
    }

    private ServerProtocolProto.ServerProtocol resp(String sign, String playerId, int... intArrays) {
        ServerProtocolProto.ServerProtocol.Builder builder = ServerProtocolProto.ServerProtocol.newBuilder();
        builder.setSign(sign);
        builder.setPlayerId(playerId);
        for (int i = 0; i < intArrays.length; i++) {
            if (i == 0) builder.setX(intArrays[i]);
            if (i == 1) builder.setY(intArrays[i]);
            if (i == 2) builder.setDirection(intArrays[i]);
            if (i == 3) builder.setLives(intArrays[i]);
            if (i == 4) builder.setHealth(intArrays[i]);
            if (i == 5) builder.setScore(intArrays[i]);
            if (i == 6) builder.setStrength(intArrays[i]);
            if (i == 7) builder.setRespawnCounter(intArrays[7]);
        }
        return builder.build();
    }
}
