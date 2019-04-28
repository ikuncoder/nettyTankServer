package protobuf.user;

import io.netty.channel.ChannelHandlerContext;
import org.apache.log4j.Logger;
import protobuf.message.messageClass.OutReqLoginMessage;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * @Author longzhu
 * version 1.0
 * 2019/4/27 23:59
 */
public class UserManager {
    private static Logger logger=Logger.getLogger(UserManager.class);
    /**key：userId*/
    public ConcurrentMap<Long, ChannelHandlerContext> userContext=new ConcurrentHashMap<>();
    public ConcurrentMap<Long, User> userMap=new ConcurrentHashMap<>();
    private UserManager(){}
    private static UserManager instance;
    public static UserManager getInstance(){
        if(instance==null){
            synchronized (UserManager.class){
                if(instance==null){
                    instance=new UserManager();
                }
            }
        }
        return instance;
    }

    /**
     * 将用户和ChannelHandlerContext对应起来
     * @param ctx
     * @param msg
     */
    public void insertUserContext(ChannelHandlerContext ctx, Object msg){
        if(!(msg instanceof OutReqLoginMessage.ReqLoginMessage)){
            return;
        }
        long userId=((OutReqLoginMessage.ReqLoginMessage) msg).getUserId();
        userContext.put(userId,ctx);
        insertUserMap(userId);
    }

    /**
     * 创建user
     * @param userId
     */
    public void insertUserMap(long userId){
        if(userMap.containsKey(userId)){
            return;
        }
        User user=new User(userId);
        userMap.put(userId, user);
    }

    public User getUserByChannelHandlerContext(ChannelHandlerContext ctx){
        if(!userContext.containsValue(ctx)){
            logger.debug("userContext缓存中不存在相应的ChannelHandlerContext");
        }
        long resultUserId=0;
        for(Map.Entry<Long,ChannelHandlerContext> map:userContext.entrySet()){
            ChannelHandlerContext channelHandlerContext=map.getValue();
            if(channelHandlerContext.equals(ctx)){
                resultUserId=map.getKey();
            }
        }
        return userMap.get(resultUserId);
    }
}
