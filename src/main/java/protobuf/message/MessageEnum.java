package protobuf.message;

/**
 * Created by longzhu on 2019/4/26 16:13
 */
public enum MessageEnum {
    TestMessage(1,"测试消息"),
    LoginMessage(2,"登录消息"),
    ReqNetWorkGameMessage(3,"请求联网对战"),
    RemoveBulletMessage(4,"移除子弹"),
    SmallExplosionMessage(5,"小爆炸"),
    BulletInfoMessage(6,"子弹信息"),
    UserTankMessage(7,"用户坦克信息"),
    AiTankMessage(8,"ai坦克信息"),
    TankDisappearMessage(9,"坦克消失信息"),
    BigExplosionMessage(10,"大爆炸"),
    RemoveBreakableWallMessage(11,"移除墙壁"),
    BreakableWallMessage(12,"可打破的墙壁"),
    SmallAnimationMessage(13,""),
    RemoveSmallExplosionMessage(14,""),
    BigAnimationMessage(15,""),
    RemoveBigExplosionMessage(16,""),
    RemovePowerUpMessage(17,""),
    CreateBulletMessage(18,""),
    TurnLeftMessage(19,""),
    TurnRightMessage(20,""),
    TurnUpMessage(21,""),
    TurnDownMessage(22,""),
    TurnFiringMessage(23,""),
    ReleaseLeftMessage(24,""),
    ReleaseRightMessage(25,""),
    ReleaseUpMessage(26,""),
    ReleaseDownMessage(27,""),
    ReleaseFiringMessage(28,""),
    ResRandomMapMessage(29,""),
    GameFinishedAndGameWonMessage(30,""),
    GameFinishedAndNotGameWonMessage(31,""),
    ;




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
