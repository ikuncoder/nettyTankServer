package protobuf.messageManager;

import com.google.protobuf.MessageLite;
import org.reflections.Reflections;
import protobuf.messageManager.annotation.MessageHandler;
import protobuf.messageManager.annotation.MethodInvode;
import protobuf.messageManager.classContext.HandlerfClassContext;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @Author longzhu
 * version 1.0
 * 2019/4/27 15:27
 */
public class MessageCenter {
    public static Map<Class<?>, HandlerfClassContext> classHandlerfClassContextMap=new HashMap<>();

    private MessageCenter(){}
    private static MessageCenter instance;
    public static MessageCenter getInstance(){
        if(instance==null){
            synchronized (MessageCenter.class){
                if(instance==null){
                    instance=new MessageCenter();
                }
            }
        }
        return instance;
    }

    public void init(){
        Reflections reflections = new Reflections("protobuf.messageManager");
        Set<Class<?>> annotated = reflections.getTypesAnnotatedWith(MessageHandler.class);
        for(Class<?> clazzs:annotated){
            Method[] methods=clazzs.getMethods();
            for(Method method:methods){
               Annotation annotation= method.getDeclaredAnnotation(MethodInvode.class);
               if(annotation!=null){
                   HandlerfClassContext classContext=new HandlerfClassContext();
                   classContext.setClazz(clazzs);
                   classContext.setMethod(method);
                   method.setAccessible(true);
                   Class<?>[] paraClasses=method.getParameterTypes();
                   classContext.setParaTypes(paraClasses);
                   for(Class value:paraClasses){
                       if(MessageLite.class.isAssignableFrom(value)){
                           classHandlerfClassContextMap.put(value,classContext);
                       }
                   }
               }
            }
        }
    }


    public static void main(String[] args){
        MessageCenter.getInstance().init();
        System.out.println(1);
    }
}
