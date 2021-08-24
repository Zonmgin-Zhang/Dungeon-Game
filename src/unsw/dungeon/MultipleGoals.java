package unsw.dungeon;

import java.util.ArrayList;

public class MultipleGoals implements Goal{

    private Dungeon dungeon;
    private ArrayList<Goal> goals;
    private String operand;

    public MultipleGoals(Dungeon dungeon, String operand) {
        this.dungeon = dungeon;
        this.goals = new ArrayList<>();
        this.operand = operand;
    }

    @Override
    public Boolean checkStatus() {
        return false;
    }

    public void addGoal(Goal goal) {
        this.goals.add(goal);
    }
    
}