package protobuf.common.byteArrayTools;

/**
 * Created by longzhu on 2019/4/24 20:57
 */
import lombok.AllArgsConstructor;
import lombok.Data;

import java.lang.reflect.Field;
@Data
@AllArgsConstructor
public class FieldWrapper {
    /**
     * 上下行数据属性
     */
    private Field field;
    /**
     * 上下行数据属性上的注解
     */
    private CodecProprety codecProprety;

    public FieldWrapper(){}

    public FieldWrapper(Field field,CodecProprety codecProprety){
        this.field=field;
        this.codecProprety=codecProprety;
    }

    public Field getField() {
        return field;
    }

    public void setField(Field field) {
        this.field = field;
    }

    public CodecProprety getCodecProprety() {
        return codecProprety;
    }

    public void setCodecProprety(CodecProprety codecProprety) {
        this.codecProprety = codecProprety;
    }
}
