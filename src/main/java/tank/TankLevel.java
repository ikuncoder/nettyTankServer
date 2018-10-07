package tank;

import wingman.game.PowerUp;
import wingman.modifiers.AbstractGameModifier;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Observable;
import java.util.Observer;

/*This is where enemies are introduced into the game according to a timeline*/
public class TankLevel extends AbstractGameModifier implements Observer {
    //每一个TankLevel里面包含一个对应的TankWorld
    int start;
    Integer position;
    String filename;
    BufferedReader level;
    public int w, h;
    int endgameDelay = 100;// don't immediately end on game end
    TankWorld tankWorld;

    /*Constructor sets up arrays of enemies in a LinkedHashMap*/
    public TankLevel(String filename,TankWorld tankWorld) {
        super();
        this.tankWorld=tankWorld;
        this.filename = filename;
        String line;
        try {
            level = new BufferedReader(new InputStreamReader(TankWorld.class.getResource(filename).openStream()));
            line = level.readLine();
            w = line.length();
            h = 0;
            while (line != null) {
                h++;
                line = level.readLine();
            }
            level.close();
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    public void read(Object theObject) {
    }

    public void load(TankWorld world) {

        try {
            level = new BufferedReader(new InputStreamReader(TankWorld.class.getResource(filename).openStream()));
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }

        String line;
        try {
            line = level.readLine();
            w = line.length();
            h = 0;
            while (line != null) {
                for (int i = 0, n = line.length(); i < n; i++) {
                    char c = line.charAt(i);
                    
                    if (c == '1') {
                        Wall wall = new Wall(i, h);
                        world.addBackground(wall);
                    }

                    if (c == '2') {
                        BreakableWall wall = new BreakableWall(i, h,tankWorld);
                        world.addBackground(wall);
                    }

                    if (c == '3') {
                        int[] controls = {KeyEvent.VK_A, KeyEvent.VK_W, KeyEvent.VK_D, KeyEvent.VK_S, KeyEvent.VK_SPACE};
                        world.addPlayer(new Tank(new Point(i * 32, h * 32), world.sprites.get("player1"), controls, "1",world));
                    }

                    if (c == '4') {
                        int[] controls = new int[]{KeyEvent.VK_LEFT, KeyEvent.VK_UP, KeyEvent.VK_RIGHT, KeyEvent.VK_DOWN, KeyEvent.VK_ENTER};
                        world.addPlayer(new Tank(new Point(i * 32, h * 32), world.sprites.get("player2"), controls, "2",world));
                    }

                    if (c == '5') {
                        world.addPowerUp(new PowerUp(new Point(i * 32, h * 32), 0, new FancyTankWeapon(world)));
                    }
                    
                    if(c=='6') {
                    	int[] controls= {1,2,3,4,5};
                    	world.addAiPlayer(new AiTank(new Point(i*32, h*32), world.sprites.get("aiplayer1"), controls, "3",world));
                    }
                    
                    if(c=='7') {
                    	int[] controls= {1,2,3,4,5};
                    	world.addAiPlayer(new AiTank(new Point(i*32, h*32), world.sprites.get("aiplayer2"), controls, "4",world));
                    }
                }
                h++;
                line = level.readLine();
            }
            level.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /*Level observes GameClock and updates on every tick*/
    @Override
    public void update(Observable o, Object arg) {
        //TankWorld world = TankWorld.getInstance();
        if (tankWorld.isGameOver()) {
            if (endgameDelay <= 0) {
                tankWorld.removeClockObserver(this);
                tankWorld.finishGame();
                
            } else endgameDelay--;
        }
    }
}
