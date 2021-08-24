package unsw.dungeon;

public class Exit extends Entity implements Goal{

    private Boolean status;

    public Exit(int x,int y) {
        super(x,y);
        this.status = false;
    }

    @Override
    public Boolean checkStatus() {
        return status;
    }

    public void completedGoal() {
        this.status = true;
    }
    
}