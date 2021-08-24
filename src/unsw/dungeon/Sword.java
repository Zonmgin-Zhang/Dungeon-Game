package unsw.dungeon;


public class Sword extends Entity {
	private int durability;

    /**
	 * Sword Constructor when coords and current dungeon is given
	 * @param x The x-coordinate of entity.
	 * @param y The y-coordinate of entity.
	 */
	public Sword(int x, int y) {
		super(x, y);
		this.durability = 5;
	}
	
	/**
	 * Reducing the durability of the sword
	 */
	public void reduceDurability() {
		this.durability--;
	}
	
	/**
	 * Get the durability of the sword
	 * @return Durability of the sword
	 */
	public int getDurability() {
		return durability;
	}

	public void clearDurability(){
		this.durability = 0;
	}



}

