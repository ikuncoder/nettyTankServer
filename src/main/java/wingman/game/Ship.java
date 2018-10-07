package wingman.game;

import tank.TankWorld;
import wingman.WingmanWorld;
import wingman.modifiers.motions.MotionController;
import wingman.modifiers.weapons.AbstractWeapon;

import java.awt.*;



/* Ships are things that have weapons and health */
public class Ship extends MoveableObject {
    protected AbstractWeapon weapon;
    protected int health;
    protected Point gunLocation;

    public Ship(Point location, Point speed, int strength, Image img) {
        super(location, speed, img);
        this.strength = strength;
        this.health = strength;
        this.gunLocation = new Point(15, 20);
    }

    public Ship(int x, Point speed, int strength, Image img) {
        this(new Point(x, -90), speed, strength, img);
    }

    public AbstractWeapon getWeapon() {
        return this.weapon;
    }

    public void setWeapon(AbstractWeapon weapon) {
        this.weapon.remove();
        this.weapon = weapon;
    }

    public void damage(int damageDone) {
        this.health -= damageDone;
        if (health <= 0) {
            this.die();
        }
        return;
    }

    public void damage(int damageDone, TankWorld tankWorld){
        this.health -= damageDone;
        if (health <= 0) {
            this.die(tankWorld);
        }
        return;
    }

    public void die() {
        this.show = false;//设置tank消失
        SmallExplosion explosion = new SmallExplosion(new Point(location.x, location.y));
        WingmanWorld.getInstance().addBackground(explosion);
        weapon.deleteObserver(this);
        motion.deleteObserver(this);
        WingmanWorld.getInstance().removeClockObserver(motion);
    }

    public void die(TankWorld tankWorld){
        this.show = false;//设置tank消失
        SmallExplosion explosion = new SmallExplosion(new Point(location.x, location.y));
        WingmanWorld.getInstance().addBackground(explosion);
        weapon.deleteObserver(this);
        motion.deleteObserver(this);
        WingmanWorld.getInstance().removeClockObserver(motion);
    }

    public void collide(GameObject otherObject) {
    }

    public void fire() {
        weapon.fireWeapon(this);
    }

    public int getHealth() {
        return health;
    }

    /* some setters and getters!*/
    public void setHealth(int health) {
        this.health = health;
    }

    public MotionController getMotion() {
        return this.motion;
    }

    public void setMotion(MotionController motion) {
        this.motion = motion;
    }

    public Point getGunLocation() {
        return this.gunLocation;
    }
}