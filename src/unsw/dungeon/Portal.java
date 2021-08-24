package unsw.dungeon;

public class Portal extends Entity {
	
	private int id;
	private Dungeon dungeon;
	
	public Portal (int x, int y, int id, Dungeon dungeon) {
		super(x, y);
		this.id = id;
		this.dungeon = dungeon;
	}
	
	/**
	 * Getter of linking id
	 * @return Linking id
	 */
	public int getId() {
		return id;
	}

	/**
	 * Setter for linking id
	 * @param id Linking id
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * Sends player to the other portal on the other side
	 * @param p The player entering the portal
	 */
	public void PlayerSwitch(Player p) {
		for (Entity e : dungeon.getEntList()) {
			if (e.getClass().equals(Portal.class) && !e.equals(this)) {
				Portal portal = (Portal) e;
				if (portal.getId() == this.getId()) {
					p.setX(portal.getX());
					p.setY(portal.getY());
					break;
				}
			}
		}	
	}

}