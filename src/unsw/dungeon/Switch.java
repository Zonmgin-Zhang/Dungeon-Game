package unsw.dungeon;

public class Switch extends Entity{
    
    private boolean hasBoulder;

    public Switch(int x, int y) {
        super(x,y);
        this.hasBoulder = false;
    }

    public boolean checkBoulder() {
        return this.hasBoulder;
    }

    public void yesBoulder() {
        this.hasBoulder = true;
    }

    public void noBoulder() {
        this.hasBoulder = false;
    }

}