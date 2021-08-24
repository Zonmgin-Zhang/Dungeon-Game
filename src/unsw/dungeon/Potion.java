package unsw.dungeon;

import java.util.Timer;
import java.util.TimerTask;

public class Potion extends Entity {
	
	/**
	 * Constructor for the invincibility class
	 * @param x The x-coord of the potion
	 * @param y The y-coord
	 */
	public Potion(int x, int y) {
		super(x, y);
	}
	
    public void usePotion(Player player) {
		System.out.println("Player is now invincible!");
		Timer timer = new Timer();
		TimerTask task = new TimerTask() {
			public void run() {
                System.out.println("Player is now vulnerable!");
                player.setPotion(null);
			}
		};
		timer.schedule(task, 5000);
    }
}