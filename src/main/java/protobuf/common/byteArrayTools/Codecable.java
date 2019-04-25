package protobuf.common.byteArrayTools;

/**
 * Created by longzhu on 2019/4/24 20:54
 */
import lombok.Data;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Data
public abstract class Codecable {

    public static List<FieldWrapper> resolveFileldWrapperList(Class clazz){
        Field[] fields = clazz.getDeclaredFields();
        List<FieldWrapper> fieldWrapperList = new ArrayList<>();
        for (Field field : fields) {
            CodecProprety codecProprety = field.getAnnotation(CodecProprety.class);
            if (codecProprety == null) {
                continue;
            }
            FieldWrapper fw = new FieldWrapper(field, codecProprety);
            fieldWrapperList.add(fw);
        }

        Collections.sort(fieldWrapperList, new Comparator<FieldWrapper>() {
            @Override
            public int compare(FieldWrapper o1, FieldWrapper o2) {
                return o1.getCodecProprety().order() - o2.getCodecProprety().order();
            }
        });

        return fieldWrapperList;
    }


    public abstract List<FieldWrapper> getFieldWrapperList();

   // public abstract int getCMD();
}