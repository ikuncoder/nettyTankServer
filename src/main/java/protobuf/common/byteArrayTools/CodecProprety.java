package protobuf.common.byteArrayTools;

/**
 * Created by longzhu on 2019/4/24 20:56
 */
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
public @interface CodecProprety {
    /**
     * 属性顺序
     * @return
     */
    int order();

    /**
     * 数据长度。解码时用，除了简单数据类型之外才起作用（如：String）。
     * @return
     */
    int length() default 0;
}
