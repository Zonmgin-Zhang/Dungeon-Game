package unsw.dungeon;

import java.util.Timer;
import java.util.TimerTask;

public class Stop extends Entity {
	
	/**
	 * Constructor for the invincibility class
	 * @param x The x-coord of the potion
	 * @param y The y-coord
	 */
	public Stop(int x, int y) {
		super(x, y);
	}
	
    public void useStop(Player player) {
		System.out.println("Enemy will stop moving when they touch you");
		Timer timer = new Timer();
		TimerTask task = new TimerTask() {
			public void run() {
                System.out.println("You lose your stop power!!");
                player.setStop(null);
			}
		};
		timer.schedule(task, 10000);
    }
}
