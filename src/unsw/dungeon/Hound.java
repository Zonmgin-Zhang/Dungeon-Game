package unsw.dungeon;


/**
 * An implementation of the Enemy entity given in the spec
 *
 */
public class Hound extends Entity {

    private int id;
    private int startingX;
    private int startingY;

    public Hound(int x, int y, int id) {
        super(x, y);
        this.startingX = x;
        this.startingY = y;
        this.id = id;
    }

    public void restartPosition() {
        x().set(startingX);
        y().set(startingY);
    }
    
    public int getId() {
		return this.id;
	}

}
