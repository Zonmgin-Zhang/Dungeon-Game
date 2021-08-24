package unsw.dungeon;

public class Boulder extends Entity implements MoveEntity{

    private Dungeon dungeon;
    private Switch switchBut;

    public Boulder(Dungeon dungeon, int x, int y) {
        super(x, y);
        this.dungeon = dungeon;
        this.switchBut = null;
    }

    public void onSwitch(Switch switchBut) {
        this.switchBut = switchBut;
    }

    public void offSwitch() {
        this.switchBut.noBoulder();
        this.switchBut = null;
    }

    public boolean hasSwitch() {
        if (this.switchBut == null) return false;
        else return true;
    }

    @Override
    public boolean moveUp() {
        if (getY() > 0 && checkSpace(getX(), getY() - 1, this.dungeon.getEntList(), this, "nil") == false) {
            y().set(getY() - 1);
            return false;
        }
        return true;
    }

    @Override
    public boolean moveDown() {
        if (getY() < dungeon.getHeight() - 1 && checkSpace(getX(), getY() + 1, this.dungeon.getEntList(), this, "nil") == false) {
            y().set(getY() + 1);
            return false;
        }
        return true;
    }

    @Override
    public boolean moveLeft() {
        if (getX() > 0 && checkSpace(getX() - 1, getY(), this.dungeon.getEntList(), this, "nil") == false) {
            x().set(getX() - 1);
            return false;
        }
        return true;
    }

    @Override
    public boolean moveRight() {
        if (getX() < dungeon.getWidth() - 1 && checkSpace(getX() + 1, getY(), this.dungeon.getEntList(), this, "nil") == false) {
            x().set(getX() + 1);
            return false;
        }
        return true;
    }

}