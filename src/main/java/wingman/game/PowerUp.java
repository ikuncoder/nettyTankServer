package wingman.game;

import protobuf.SendMsg;
import tank.TankWorld;
import wingman.WingmanWorld;
import wingman.modifiers.AbstractGameModifier;
import wingman.modifiers.motions.SimpleMotion;
import wingman.modifiers.weapons.AbstractWeapon;

import java.awt.*;
import java.util.Observable;

/* PowerUp extends ship so that it can hold a weapon to give to player*/
public class PowerUp extends Ship {
    public SendMsg sendMsg=new SendMsg();

    public PowerUp(Ship theShip) {
        super(theShip.getLocationPoint(), theShip.getSpeed(), 1, WingmanWorld.sprites.get("powerup"));
        this.motion = new SimpleMotion();
        this.motion.addObserver(this);
        this.weapon = theShip.getWeapon();
    }

    public PowerUp(int location, int health, AbstractWeapon weapon) {
        this(new Point(location, -100), health, weapon);
        this.motion = new SimpleMotion();
        this.motion.addObserver(this);
        this.weapon = weapon;
    }

    public PowerUp(Point location, int health, AbstractWeapon weapon) {
        super(new Point(location), new Point(0, 2), health, WingmanWorld.sprites.get("powerup"));
        this.motion = new SimpleMotion();
        this.motion.addObserver(this);
        this.weapon = weapon;
    }

    @Override
    public void update(Observable o, Object arg) {
        AbstractGameModifier modifier = (AbstractGameModifier) o;
        modifier.read(this);
    }

    public void die() {
        this.show = false;
        weapon.deleteObserver(this);
        motion.deleteObserver(this);
        WingmanWorld.getInstance().removeClockObserver(motion);
    }
    public void die(TankWorld tankWorld){
        this.show = false;
        sendMsg.sendMessage(tankWorld,"Removepowerup" ,0+"",this.getLocationPoint().x
                ,this.getLocationPoint().y ,0,0,0,0,0,0);
        weapon.deleteObserver(this);
        motion.deleteObserver(this);
        WingmanWorld.getInstance().removeClockObserver(motion);
    }

}
