package unsw.dungeon;


public class BoulderGoal implements Goal{

    private Dungeon dungeon;

    public BoulderGoal(Dungeon dungeon) {
        this.dungeon = dungeon;
    }

    @Override
    public Boolean checkStatus() {
        return checkSwitches();
    }

    public Boolean checkSwitches() {
        int switchCount = 0;
        for (Entity e : this.dungeon.getEntList()) {
            if (e.getClass().equals(Switch.class)) {
                Switch s = (Switch) e;
                if (s.checkBoulder() == false) switchCount++;
            }
        }
        if (switchCount == 0) return true;
        else return false;
    }
}