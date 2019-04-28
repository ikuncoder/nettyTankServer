package protobuf.message;

/**
 * Created by longzhu on 2019/4/26 16:13
 */
public enum MessageEnum {
    TestMessage(1,"测试消息"),
    LoginMessage(2,"登录消息");





    private int messageId;
    private String messageName;
    MessageEnum(int messageId,String messageName){
        this.messageId=messageId;
        this.messageName=messageName;
    }

    public int getMessageId() {
        return messageId;
    }

    public void setMessageId(int messageId) {
        this.messageId = messageId;
    }

    public String getMessageName() {
        return messageName;
    }

    public void setMessageName(String messageName) {
        this.messageName = messageName;
    }
}
