package tank;

import wingman.GameClock;
import wingman.game.PlayerShip;

import java.awt.Graphics2D;
import java.awt.Point;
import java.util.ArrayList;

public class AiTankHandler implements Runnable {
	private TankWorld tankWorld;
	private Point mapsize ;
	TankLevel level;
	public ArrayList<PlayerShip> aiplayer;
	public Graphics2D g2;
	public GameClock clock = new GameClock();

	public AiTankHandler(TankWorld tankWorld){
		this.tankWorld=tankWorld;
		level=new TankLevel("Resources/level"+tankWorld.mapNum+".txt",tankWorld);
		mapsize = new Point(level.w * 32, level.h * 32);
		aiplayer = tankWorld.getAiPlayer();
		g2 = tankWorld.createGraphics2D(mapsize.x, mapsize.y);
		level.addObserver(tankWorld);
		clock.addObserver(level);
	}

	@Override
	public void run() {
		Thread thread = Thread.currentThread();
		//new KeSarStart().Start();
		while (true) {
			if(!tankWorld.gameFinished){
				try {
					thread.sleep(3000);//1s刷新一次
				} catch (InterruptedException e) {
					System.out.println(e.getStackTrace());
				}
				PlayerShip p3 = aiplayer.get(0);
				PlayerShip p4=aiplayer.get(1);
				p3.update(mapsize.x, mapsize.y,tankWorld);
				p4.update(mapsize.x, mapsize.y,tankWorld);
			}else{
				break;//相当于退出线程
			}
		}
	}
}
