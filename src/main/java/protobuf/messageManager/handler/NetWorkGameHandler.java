package protobuf.messageManager.handler;

import io.netty.channel.ChannelHandlerContext;
import org.apache.log4j.Logger;
import protobuf.message.messageClass.OutReqNetWorkGameMessage;
import protobuf.messageManager.annotation.MessageHandler;
import protobuf.messageManager.annotation.MethodInvode;
import protobuf.user.User;
import protobuf.user.UserManager;
import tank.TankWorld;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @Author longzhu
 * version 1.0
 * 2019/5/1 13:22
 */
@MessageHandler
public class NetWorkGameHandler {
    private static Logger logger = Logger.getLogger(NetWorkGameHandler.class);
    /**
     * 网络对战的房间集合
     */
    public volatile static CopyOnWriteArrayList<TankWorld> tankWorlds = new CopyOnWriteArrayList<>();
    /**
     * 请求网络对战的数量(还没组队的)
     */
    private static CopyOnWriteArrayList<ChannelHandlerContext> channelHandlerContexts = new CopyOnWriteArrayList<>();
    /**
     * 现在的房间数量
     */
    private static int groupNum;

    @MethodInvode
    public void reqNetWorkHandler(long userId, OutReqNetWorkGameMessage.NetWorkGameMessage message) {
        logger.error("netWork success");
        ChannelHandlerContext channelHandlerContext = UserManager.getInstance().userContext.get(userId);
        User user = UserManager.getInstance().userMap.get(userId);
        if (channelHandlerContext == null) {
            logger.error("error should not happen:ChannelHandlerContext does not exit in UserManager.userContext");
            return;
        }
        if (user == null) {
            logger.error("error should not happen:User does not exit in UserManager.userMap");
            return;
        }
        channelHandlerContexts.add(channelHandlerContext);
        if (channelHandlerContexts.size() >= 2) {
            int groupCount = channelHandlerContexts.size() / 2;
            ArrayList<ChannelHandlerContext> removeLists = new ArrayList<>();
            for (int i = 0; i < groupCount; i++) {
                ArrayList<ChannelHandlerContext> tempChannelHandlerContextsArrayList = new ArrayList<>();
                tempChannelHandlerContextsArrayList.add(channelHandlerContexts.get(2 * i));
                tempChannelHandlerContextsArrayList.add(channelHandlerContexts.get(2 * i + 1));
                removeLists.add(channelHandlerContexts.get(2 * i));
                removeLists.add(channelHandlerContexts.get(i * i + 1));
                //随机地图
                Random random = new Random();
                int mapNum = random.nextInt(3);
                int groupId = groupNum + 1;
                user.setGroupNum(groupId);
                TankWorld tankWorld = new TankWorld(groupId, tempChannelHandlerContextsArrayList, mapNum);
                tankWorlds.add(tankWorld);
                tankWorld.tankWorkStart(tankWorld);
            }
            channelHandlerContexts.remove(removeLists);
        }
    }
}
