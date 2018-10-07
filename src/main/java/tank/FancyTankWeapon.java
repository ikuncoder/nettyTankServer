package tank;

import wingman.game.Bullet;
import wingman.game.Ship;
import wingman.modifiers.weapons.AbstractWeapon;

import java.awt.*;

public class FancyTankWeapon extends AbstractWeapon {
    public FancyTankWeapon(TankWorld tankWorld) {
        super(tankWorld);
    }

    public void fireWeapon(Ship theTank,TankWorld tankWorld) {//tank装备上这个武器后，开火就执行这个方法
        super.fireWeapon(theTank);
        Point location = theTank.getLocationPoint();
        Point offset = theTank.getGunLocation();
        location.x += offset.x;
        location.y += offset.y;
        Point speed = new Point(0, -15 * direction);
        int strength = 7;
        reload = 15;

        bullets = new Bullet[2];
        bullets[0] = new TankBullet(location, speed, strength, -5, (Tank) theTank,tankWorld);
        bullets[1] = new TankBullet(location, speed, strength, 5, (Tank) theTank,tankWorld);


        this.setChanged();

        this.notifyObservers();
    }


}
