package unsw.dungeon;

public class TreasureGoal implements Goal{

    private Dungeon dungeon;

    public TreasureGoal(Dungeon dungeon) {
        this.dungeon = dungeon;
    }

    @Override
    public Boolean checkStatus() {
        int treasureCount = 0;
        for (Entity e : this.dungeon.getEntList()) {
            if (e.getClass().equals(Treasure.class)) treasureCount++;
        }
        if (treasureCount == 0) return true;
        return false;
    }
    
}