package protobuf.messageDispather;

import org.apache.log4j.Logger;
import protobuf.messageManager.classContext.HandlerfClassContext;

import java.lang.reflect.Method;


/**
 * @Author longzhu
 * version 1.0
 * 2019/4/28 21:40
 * 消息事件
 */
public class MessageEvent {
    private static Logger logger=Logger.getLogger(MessageEvent.class);
    /**消息拥有者（userId）*/
    private long identyId;
    /**房间号*/
    private int groupNum;
    /**类*/
    private Class clazz;
    /**方法*/
    private Method method;
    /**参数类型*/
    private Class<?>[] paraTypes;
    /**数据*/
    private Object message;

    public MessageEvent(long identyId,int groupNum,Class clazz,Method method,Class<?>[] paraTypes,Object message){
        this.identyId=identyId;
        this.groupNum=groupNum;
        this.clazz=clazz;
        this.method=method;
        this.paraTypes=paraTypes;
        this.message=message;
    }

    public MessageEvent(long identyId,int groupNum, HandlerfClassContext context,Object message){
        this.identyId=identyId;
        this.groupNum=groupNum;
        this.clazz=context.getClazz();
        this.method=context.getMethod();
        this.paraTypes=context.getParaTypes();
        this.message=message;
    }
    public void actionEvent(){
        try {
            Class clazz=this.clazz;
            Method method=this.method;
            method.setAccessible(true);
            Class<?>[] paraTypes=this.getParaTypes();
            Object[] paras=new Object[paraTypes.length];
            paras[0]=this.identyId;
            paras[1]=this.message;
            clazz.getMethod(method.getName(),paraTypes);
            method.invoke(clazz.newInstance(),paras);
            logger.error("线程"+Thread.currentThread()+"执行事件");
        } catch (Exception e) {
           e.printStackTrace();
        }
    }

    public long getIdentyId() {
        return identyId;
    }

    public void setIdentyId(long identyId) {
        this.identyId = identyId;
    }

    public int getGroupNum() {
        return groupNum;
    }

    public void setGroupNum(int groupNum) {
        this.groupNum = groupNum;
    }

    public Class getClazz() {
        return clazz;
    }

    public void setClazz(Class clazz) {
        this.clazz = clazz;
    }

    public Method getMethod() {
        return method;
    }

    public void setMethod(Method method) {
        this.method = method;
    }

    public Class<?>[] getParaTypes() {
        return paraTypes;
    }

    public void setParaTypes(Class<?>[] paraTypes) {
        this.paraTypes = paraTypes;
    }

    public Object getMessage() {
        return message;
    }

    public void setMessage(Object message) {
        this.message = message;
    }
}
