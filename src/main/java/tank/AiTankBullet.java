package tank;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.image.ImageObserver;

import wingman.game.Bullet;

public class AiTankBullet extends Bullet{
    public AiTankBullet(Point location, Point speed, int strength, AiTank theTank,TankWorld tankWorld) {
        this(location, speed, strength, 0, theTank,tankWorld);
    }

    public AiTankBullet(Point location, Point speed, int strength, int offset, AiTank owner,TankWorld tankWorld) {
        super(location, speed, strength, new Simple2DMotion(owner.direction + offset,tankWorld), owner);
        this.setImage(TankWorld.sprites.get("bullet"));
    }

    public void draw(Graphics g, ImageObserver obs) {
        if (show) {
            g.drawImage(img, location.x, location.y, null);
        }
    }
}
