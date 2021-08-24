package unsw.dungeon;

public class Treasure extends Entity implements Goal {

    private boolean success;

    Treasure(int x, int y) {
        super(x, y);
        success = false;
    }
    
    @Override
    public Boolean checkStatus() {
        return success;
    }

    public void completedGoal() {
        this.success = true;
    }
}