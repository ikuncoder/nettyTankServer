package protobuf.message;

import protobuf.common.byteArrayTools.Codecable;

/**
 * Created by longzhu on 2019/4/24 19:17
 */
public abstract class BaseMessage extends Codecable {
    public abstract int getCMD();
}
