package protobuf.messageManager.handler;

import protobuf.message.messageClass.OutTestMessage;
import protobuf.messageManager.annotation.MessageHandler;
import protobuf.messageManager.annotation.MethodInvode;

/**
 * @Author longzhu
 * version 1.0
 * 2019/4/27 15:16
 */
@MessageHandler
public class TestMessageHandler {
    @MethodInvode
    public void reqTestMessageHandler(long userId,OutTestMessage.TestMessage message){
        System.out.println(message);
    }
}
