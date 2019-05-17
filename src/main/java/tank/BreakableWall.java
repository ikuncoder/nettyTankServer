package tank;

import protobuf.SendMsg;
import protobuf.message.MessagePusher;
import protobuf.message.messageClass.OutBreakableWallMessage;
import protobuf.message.messageClass.OutRemoveBreakableWallMessage;
import wingman.game.BackgroundObject;
import wingman.game.GameObject;

import java.awt.*;
import java.awt.image.ImageObserver;


public class BreakableWall extends BackgroundObject {
    TankWorld tankWorld;
    int timer = 400;
    public SendMsg sendMsg=new SendMsg();
    public BreakableWall(int x, int y,TankWorld tankWorld) {
        super(new Point(x * 32, y * 32), new Point(0, 0), TankWorld.sprites.get("wall2"));
        this.tankWorld=tankWorld;
    }

    //You need to fill in here
    public boolean collision(GameObject otherObject) {//������ӵ��Ļ���Ҫ������ʧ����
        if (location.intersects(otherObject.getLocation())) {
            if (otherObject instanceof TankBullet||otherObject instanceof AiTankBullet) {
                /*sendMsg.sendMessage(tankWorld,"RemoveBreakableWall",0+"",this.getLocationPoint().x ,this.getLocationPoint().y, 0,0,0,0,0,0);*/
                OutRemoveBreakableWallMessage.RemoveBreakableWallMessage removeBreakableWallMessage = TankWorldHelper.getRemoveBreakableWallMessage(this.getLocationPoint().x, this.getLocationPoint().y);
                MessagePusher.getInstance().pushMessageForUsers(tankWorld.getUsers(),removeBreakableWallMessage);
                this.show = false;
            }
            return true;
        }
        return false;
    }

    //You need to fill in here
    public void draw(Graphics g, ImageObserver obs) {
        if (show)
            super.draw(g, obs);
        else {
            this.timer--;
            if (this.timer < 0) {
                this.timer = 400;
                this.show = true;
              /*sendMsg.sendMessage(tankWorld,"BreakableWall", 0+"",this.getLocationPoint().x ,this.getLocationPoint().y ,0,0,0,0,0,0);*/
                OutBreakableWallMessage.BreakableWallMessage breakableWallMessage = TankWorldHelper.getBreakableWallMessage(this.getLocationPoint().x, this.getLocationPoint().y);
                MessagePusher.getInstance().pushMessageForUsers(tankWorld.getUsers(),breakableWallMessage);
            }
        }
    }
}