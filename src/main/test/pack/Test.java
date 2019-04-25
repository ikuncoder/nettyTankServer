package pack;

import protobuf.common.ProtocolField;
import protobuf.common.byteArrayTools.FieldWrapper;
import protobuf.message.messageBody.OutTestMessage;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * Created by longzhu on 2019/4/25 11:36
 */
public class Test {
    private static ConcurrentMap<Integer,Class<?>> protocolMap=new ConcurrentHashMap<>();
    public static void main(String[] args) {
        /*Reflections reflections = new Reflections("protobuf.message");
        Set<Class<?>> annotated = reflections.getTypesAnnotatedWith(Protocol.class);
        for(Class clazz:annotated){
            Protocol annotations=(Protocol)clazz.getDeclaredAnnotation(Protocol.class);
            int messageId=annotations.CMD();
            protocolMap.putIfAbsent(messageId,clazz);
        }*/
        Field[] fields = OutTestMessage.TestMessage.class.getDeclaredFields();
        List<FieldWrapper> resultList = new ArrayList<>();
        for (Field value : fields) {
            if(value.isAnnotationPresent(ProtocolField.class)){
                FieldWrapper fieldWrapper=new FieldWrapper();
                fieldWrapper.setField(value);
                resultList.add(fieldWrapper);
            }
        }
        System.out.println("============");
    }
}
