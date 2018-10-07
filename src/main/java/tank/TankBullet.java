package tank;

import wingman.game.Bullet;

import java.awt.*;
import java.awt.image.ImageObserver;

public class TankBullet extends Bullet {
    public TankBullet(Point location, Point speed, int strength, Tank owner,TankWorld tankWorld) {
        this(location, speed, strength, 0, owner,tankWorld);
    }

    public TankBullet(Point location, Point speed, int strength, int offset, Tank owner,TankWorld tankWorld) {
        super(location, speed, strength, new Simple2DMotion(owner.direction + offset,tankWorld), owner);
        this.setImage(TankWorld.sprites.get("bullet"));
    }


    public void draw(Graphics g, ImageObserver obs) {
        if (show) {
            g.drawImage(img, location.x, location.y, null);
        }
    }
}
