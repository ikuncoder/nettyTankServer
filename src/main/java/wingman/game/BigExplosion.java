package wingman.game;

import protobuf.SendMsg;
import tank.TankWorld;
import wingman.GameWorld;

import java.awt.*;



/* BigExplosion plays when player dies*/
public class BigExplosion extends BackgroundObject {

	public SendMsg sendMsg=new SendMsg();
	
    static Image animation[] = new Image[]{GameWorld.sprites.get("explosion2_1"),
            GameWorld.sprites.get("explosion2_2"),
            GameWorld.sprites.get("explosion2_3"),
            GameWorld.sprites.get("explosion2_4"),
            GameWorld.sprites.get("explosion2_5"),
            GameWorld.sprites.get("explosion2_6"),
            GameWorld.sprites.get("explosion2_7")};
    int timer;
    int frame;

    public BigExplosion(Point location) {
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
            if (frame < 7) {
            	this.img = animation[frame];
                sendMsg.sendMessage(tankWorld,"bigAnimation", 0+"" ,this.getLocationPoint().x ,this.getLocationPoint().y ,frame,0,0,0,0,0);
            }
            else {
            	this.show = false;
            	sendMsg.sendMessage(tankWorld,"RemoveBigExplosion",0+"",this.getLocationPoint().x,this.getLocationPoint().y ,0,0,0,0,0,0);
            }
                
            
        }
    }

    public boolean collision(GameObject otherObject) {
        return false;
    }
}
