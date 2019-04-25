package protobuf.message.manager;

import org.reflections.Reflections;
import protobuf.common.Protocol;

import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * Created by longzhu on 2019/4/25 15:43
 * 此类的目的是管理被Protocol注解修饰的类
 * ps:扫描类的工具引入了一个开源的项目Reflections
 */
public class ProtocolAnnotationManager {
    private ConcurrentMap<Integer,Class<?>> protocolMap=new ConcurrentHashMap<>();
    private static ProtocolAnnotationManager instance;
    private ProtocolAnnotationManager(){
    }
    public static ProtocolAnnotationManager getInstance(){
        if(instance==null){
            synchronized (ProtocolAnnotationManager.class){
                if(instance==null){
                    instance=new ProtocolAnnotationManager();
                }
            }
        }
        return instance;
    }

    public void init(){
        Reflections reflections = new Reflections("protobuf.message");
        Set<Class<?>> annotated = reflections.getTypesAnnotatedWith(Protocol.class);
        for(Class clazz:annotated){
            Protocol annotations=(Protocol)clazz.getDeclaredAnnotation(Protocol.class);
            int messageId=annotations.CMD();
            protocolMap.putIfAbsent(messageId,clazz);
        }
    }

    public ConcurrentMap<Integer, Class<?>> getProtocolMap() {
        return protocolMap;
    }

    public void setProtocolMap(ConcurrentMap<Integer, Class<?>> protocolMap) {
        this.protocolMap = protocolMap;
    }
}
