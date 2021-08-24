package unsw.dungeon;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;

public class Door extends Entity {
	private int id;
	private String status;
	private BooleanProperty open;
	
	/**
	 * Constructor for the door class
	 * @param x The door's x-coord
	 * @param y The door's y-coord
	 * @param id The door's ID
	 */
	public Door (int x, int y, int id) {
		super(x, y);
		this.id = id;
		this.status = "closed"; // becomes "open" when key opening is successful
		this.open = new SimpleBooleanProperty(false);
	}
		/**
	 * Getter for linking id
	 * @return Returns the linking id
	 */
	public int getId() {
		return id;
	}

	public String getStatus() {
		return status;
	}

    /**
	 * Check if player's key matches door id
	 * if it does, then unlock door
	 */
    public Boolean openAttempt(Key key){
        if (key.getId() == this.id) {
			this.status = "open";
            return true;
        }
		return false;
	}

	public BooleanProperty getOpen() {
		return open;
	}

	public void openDoor() {
		open.setValue(true);
	}

	public boolean isOpen() {
		return open.getValue();
	}
}
