package unsw.dungeon;


public class Key extends Entity {


    private int id;

    /**
	 * Constructor for the key class
	 * @param x The x-coord of the key
	 * @param y The y-coord
	 * @param id The key's id
	 */

    public Key(int x, int y, int id) {
        super(x, y);
        this.id = id;
    }

    /**
	 * Getter for key's id
	 * @return Returns the key id
	 */
	public int getId() {
		return id;
	}
}