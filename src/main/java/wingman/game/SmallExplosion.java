package wingman.game;

import protobuf.SendMsg;
import protobuf.message.MessagePusher;
import protobuf.message.messageClass.OutRemoveSmallExplosionMessage;
import protobuf.message.messageClass.OutSmallAnimationMessage;
import tank.TankWorld;
import tank.TankWorldHelper;
import wingman.GameWorld;
import wingman.WingmanWorld;

import java.awt.*;



/* Small explosions happen whenever an enemy dies */
public class SmallExplosion extends BackgroundObject {

	public SendMsg sendMsg=new SendMsg();
     static Image animation[] = new Image[]{WingmanWorld.sprites.get("explosion1_1"),
            WingmanWorld.sprites.get("explosion1_2"),
            WingmanWorld.sprites.get("explosion1_3"),
            WingmanWorld.sprites.get("explosion1_4"),
            WingmanWorld.sprites.get("explosion1_5"),
            WingmanWorld.sprites.get("explosion1_6"),
            WingmanWorld.sprites.get("explosion1_7")};
    int timer;
    int frame;

    public SmallExplosion(Point location) {
        super(location, animation[0]);
        timer = 0;
        frame = 0;
        GameWorld.sound.play("Resources/snd_explosion2.wav");
    }

    public void update(int w, int h, TankWorld tankWorld) {
        super.update(w, h);
        timer++;
        if (timer % 5 == 0) {
            frame++;
            if (frame < 6) {
            	this.img = animation[frame];
                /*sendMsg.sendMessage(tankWorld,"smallAnimation", 0+"" ,this.getLocationPoint().x ,this.getLocationPoint().y ,frame,0,0,0,0,0);*/
                OutSmallAnimationMessage.SmallAnimationMessage smallAnimationMessage = TankWorldHelper.getSmallAnimationMessage(this.getLocationPoint().x, this.getLocationPoint().y, frame);
                MessagePusher.getInstance().pushMessageForUsers(tankWorld.getUsers(),smallAnimationMessage);
            }
            else {
            	this.show = false;
            	/*sendMsg.sendMessage(tankWorld,"RemoveSmallExplosion",0+"",this.getLocationPoint().x ,this.getLocationPoint().y,0,0,0,0,0,0);*/
                OutRemoveSmallExplosionMessage.RemoveSmallExplosionMessage removeSmallExplosionMessage = TankWorldHelper.getRemoveSmallExplosionMessage(this.getLocationPoint().x, this.getLocationPoint().y);
                MessagePusher.getInstance().pushMessageForUsers(tankWorld.getUsers(),removeSmallExplosionMessage);
            }
                
        }

    }

    public boolean collision(GameObject otherObject) {
        return false;
    }
}
