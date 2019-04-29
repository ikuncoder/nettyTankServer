package protobuf.messageDispather;

import io.netty.channel.ChannelHandlerContext;
import org.apache.log4j.Logger;
import protobuf.messageManager.MessageCenter;
import protobuf.messageManager.classContext.HandlerfClassContext;
import protobuf.user.User;
import protobuf.user.UserManager;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * @Author longzhu
 * version 1.0
 * 2019/4/27 17:23
 * 分发器
 * ps:一个房间(tankWorld)一条线程处理
 */
public class Dispather {
    private static Logger logger=Logger.getLogger(Dispather.class);
    int core = Runtime.getRuntime().availableProcessors();
    private EventThread[] eventThread = new EventThread[core * 2];

    private Dispather() {
        init();
    }

    private static Dispather instance;

    public static Dispather getInstance() {
        if (instance == null) {
            synchronized (Dispather.class) {
                if (instance == null) {
                    instance = new Dispather();
                }
            }
        }
        return instance;
    }

    public void messageDispather(ChannelHandlerContext ctx,Object msg) {
        HandlerfClassContext context = MessageCenter.getInstance().classHandlerfClassContextMap.get(msg.getClass());
        User user= UserManager.getInstance().getUserByChannelHandlerContext(ctx);
        if(user==null){
           logger.error("user不在缓存里面");
            return;
        }
        MessageEvent event=new MessageEvent(user.getUserId(),user.getGroupNum(),context,msg);
        EventThread thread=getThread(user.getGroupNum());
        thread.queue.add(event);
       /* try {
            Class clazz=context.getClazz();
            Method method=context.getMethod();
            method.setAccessible(true);
            Class<?>[] paraTypes=context.getParaTypes();
            Object[] paras=new Object[paraTypes.length];
            paras[0]=user.getUserId();
            paras[1]=msg;
            clazz.getMethod(method.getName(),paraTypes);
            method.invoke(clazz.newInstance(),paras);
        } catch (Exception e) {
            logger.debug(e);
        }*/
    }

    public EventThread getThread(int groupId){
        int threadIndex=groupId%eventThread.length;//这里后期可以优化
        return eventThread[threadIndex];
    }

    class EventThread extends Thread {
        public BlockingQueue<MessageEvent> queue = new ArrayBlockingQueue(100);
        @Override
        public void run() {
            for (; ; ) {
                try {
                    MessageEvent messageEvent=queue.take();
                    messageEvent.actionEvent();
                } catch (Exception e) {
                    System.out.println(e);
                }
            }
        }
    }

    public void init() {
        for (int i = 0; i < eventThread.length; i++) {
            eventThread[i]=new EventThread();
            eventThread[i].start();
        }
    }

    public static void main(String[] args) {
        Dispather.getInstance().init();
        logger.error("hh");
    }
}
