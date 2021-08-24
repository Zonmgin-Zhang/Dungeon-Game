package unsw.dungeon;

public class Laser extends Entity {
	
	private int id;

	/**
	 * Constructor for the invincibility class
	 * @param x The x-coord of the potion
	 * @param y The y-coord
	 */
	public Laser(int x, int y, int id) {
		super(x, y);
		this.id = id;
	}

	public int getId() {
		return this.id;
	}
}