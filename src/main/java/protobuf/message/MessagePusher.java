package protobuf.message;

import com.google.protobuf.MessageLite;
import io.netty.channel.ChannelHandlerContext;
import org.apache.log4j.Logger;
import protobuf.user.User;
import protobuf.user.UserManager;

import java.util.List;

/**
 * @Author longzhu
 * version 1.0
 * 2019/4/29 22:36
 * 消息推送器
 */
public class MessagePusher {
    private static Logger logger=Logger.getLogger(MessagePusher.class);
    private MessagePusher(){}
    private static MessagePusher instance;
    public static MessagePusher getInstance(){
        if(instance==null){
            synchronized (MessagePusher.class){
                if(instance==null){
                    instance=new MessagePusher();
                }
            }
        }
        return instance;
    }

    /**向一个用户推送消息*/
    public void pushMessage(User user, MessageLite message){
        /*logger.error("send user:"+user+"+message:"+message);*/
        ChannelHandlerContext channelHandlerContext= UserManager.getInstance().userContext.get(user.getUserId());
        if(channelHandlerContext==null){
            logger.error("error should not happen:ChannelHandlerContext which in User is not found");
            return;
        }
        if(message==null){
            logger.error("message should not empty");
            return;
        }
        channelHandlerContext.writeAndFlush(message);
    }

    /**向集合里面的用户推送消息*/
    public void pushMessageForUsers(List<User> users,MessageLite message){
        if(users==null){
            return ;
        }
        for(User user:users){
            pushMessage(user,message);
        }
    }
}
