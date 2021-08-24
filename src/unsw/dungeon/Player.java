package unsw.dungeon;

import java.util.List;

/**
 * The player entity
 * 
 * @author Robert Clifton-Everest
 *
 */
public class Player extends Entity implements MoveEntity {

    private Dungeon dungeon;
    private Sword sword;
    private int startingX;
    private int startingY;
    private Key key;
    private Potion potion;
    private Stop stop;

    /**
     * Create a player positioned in square (x,y)
     * @param x
     * @param y
     */
    public Player(Dungeon dungeon, int x, int y) {
        super(x, y);
        this.dungeon = dungeon;
        this.sword = null;
        this.potion = null;
        this.key = null;
        this.stop = null;
        this.startingX = x;
        this.startingY = y;
    }

    @Override
    public boolean moveUp() {
        if (getY() > 0 && checkSpace(getX(), getY() - 1, this.dungeon.getEntList(), this, "up") == false) {
            y().set(getY() - 1);
            return false;
        }
        return true;
    }

    @Override
    public boolean moveDown() {
        if (getY() < dungeon.getHeight() - 1 && checkSpace(getX(), getY() + 1, this.dungeon.getEntList(), this, "down") == false) {
            y().set(getY() + 1);
            return false;
        }
        return true;
    }

    @Override
    public boolean moveLeft() {
        if (getX() > 0 && checkSpace(getX() - 1, getY(), this.dungeon.getEntList(), this, "left") == false) {
            x().set(getX() - 1);
            return false;
        }
        return true;
    }

    @Override
    public boolean moveRight() {
        if (getX() < dungeon.getWidth() - 1 && checkSpace(getX() + 1, getY(), this.dungeon.getEntList(), this, "right") == false) {
            x().set(getX() + 1);
            return false;
        }
        return true;
    }

    /**
     * Checks if player has sword.
     * @return True if player has sword, false otherwise.
     */
    public boolean hasSword() {
        if (sword == null) return false;
        if (sword.getDurability() == 0) return false;
        return true;
    }

    public void useSword() {
        this.sword.reduceDurability();
    }

    public int SwordUseTimes() {
        return sword.getDurability();
    }

    public void setSword(Sword sword) {
        this.sword = sword;
    }

    public boolean hasKey() {
        if (this.key == null) return false;
        return true;
    }

    public void setKey(Key newKey) {
        this.key = newKey;
    }
    
    public Key getKey() {
        return this.key;
    }

    public void keyUsed() {
        this.key = null;
    }

    public boolean hasStop() {
        if (this.stop == null) return false;
        return true;
    }

    public void setStop(Stop newStop) {
        this.stop = newStop;
    }

    public boolean hasPotion() {
        if (this.potion == null) return false;
        return true;
    }

    public void setPotion(Potion newpotion) {
        this.potion = newpotion;
    }

    public Dungeon getDungeon() {
        return this.dungeon;
    }
    
    public void restartPosition() {
        x().set(startingX);
        y().set(startingY);
    }

    public void setX(int x) {
        x().set(x);
    }

    public void setY(int y) {
        y().set(y);
    }

    public List<Entity> getDunEntList() {
        return this.dungeon.getEntList();
    }
}
