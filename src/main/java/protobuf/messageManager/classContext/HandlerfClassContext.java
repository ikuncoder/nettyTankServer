package protobuf.messageManager.classContext;

import java.lang.reflect.Method;

/**
 * @Author longzhu
 * version 1.0
 * 2019/4/27 15:45
 */
public class HandlerfClassContext {
    /**类*/
    private Class clazz;
    /**方法*/
    private Method method;
    /**参数类型*/
    private Class<?>[] paraTypes;

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

    public Class getClazz() {
        return clazz;
    }

    public void setClazz(Class clazz) {
        this.clazz = clazz;
    }
}
