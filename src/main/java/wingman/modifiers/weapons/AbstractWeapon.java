package wingman.modifiers.weapons;

import protobuf.SendMsg;
import protobuf.message.MessagePusher;
import protobuf.message.messageClass.OutCreateBulletMessage;
import tank.TankWorld;
import tank.TankWorldHelper;
import wingman.GameWorld;
import wingman.WingmanWorld;
import wingman.game.Bullet;
import wingman.game.PlayerShip;
import wingman.game.Ship;
import wingman.modifiers.AbstractGameModifier;
import java.util.Observer;

/*Weapons are fired by motion controllers on behalf of players or ships
 * They observe motions and are observed by the Game World
 */
public abstract class AbstractWeapon extends AbstractGameModifier {
    public int reload = 5;
    protected Bullet[] bullets;
    protected int direction;
    boolean friendly;
    int lastFired = 0, reloadTime;
    TankWorld tankWorld;

    public AbstractWeapon() {
        this(WingmanWorld.getInstance());
    }

    public AbstractWeapon(Observer world) {
        super();
        this.addObserver(world);
    }

    public void fireWeapon(Ship theShip) {
        if (theShip instanceof PlayerShip) {
            direction = 1;
        } else {
            direction = -1;
        }
    }

    public void fireWeapon(Ship theShip, TankWorld tankWorld){
        if (theShip instanceof PlayerShip) {
            direction = 1;
        } else {
            direction = -1;
        }
    }

    /* read is called by Observers when they are notified of a change */
    public void read(Object theObject) {
        GameWorld world = (GameWorld) theObject;
        world.addBullet(bullets);
        this.tankWorld=(TankWorld)theObject;
        //ArrayList<Bullet> bullets=world.getBullet();
        //广播一次，通知客户端增加一颗子弹


        SendMsg sendMsg=new SendMsg();
        if (bullets.length == 1) {
           /*sendMsg.sendMessage(tankWorld,"^",bullets[bullets.length - 1].getOwner().getName() ,bullets[bullets.length - 1].getLocationPoint().x,bullets[bullets.length - 1].getLocationPoint().y ,direction , bullets[bullets.length - 1].BulletID,0,0,0,0);*/
            OutCreateBulletMessage.CreateBulletMessage createBulletMessage = TankWorldHelper.getCreateBulletMessage(bullets[bullets.length - 1].getOwner().getName(), bullets[bullets.length - 1].getLocationPoint().x, bullets[bullets.length - 1].getLocationPoint().y, direction, bullets[bullets.length - 1].BulletID);
            MessagePusher.getInstance().pushMessageForUsers(tankWorld.getUsers(),createBulletMessage);

        } else if (bullets.length == 2) {
            for (int i = 2; i > 0; i--) {
                /*sendMsg.sendMessage(tankWorld,"^",bullets[bullets.length - i].getOwner().getName() ,bullets[bullets.length - i].getLocationPoint().x,bullets[bullets.length - i].getLocationPoint().y ,direction ,bullets[bullets.length - i].BulletID,0,0,0,0);*/
                OutCreateBulletMessage.CreateBulletMessage createBulletMessage = TankWorldHelper.getCreateBulletMessage(bullets[bullets.length - 1].getOwner().getName(), bullets[bullets.length - 1].getLocationPoint().x, bullets[bullets.length - 1].getLocationPoint().y, direction, bullets[bullets.length - 1].BulletID);
                MessagePusher.getInstance().pushMessageForUsers(tankWorld.getUsers(),createBulletMessage);
            }
        }
    }

    public void remove() {
        this.deleteObserver(WingmanWorld.getInstance());
    }

    public Bullet[] getBullets() {
        return bullets;
    }

    public void setBullets(Bullet[] bullets) {
        this.bullets = bullets;
    }
}
