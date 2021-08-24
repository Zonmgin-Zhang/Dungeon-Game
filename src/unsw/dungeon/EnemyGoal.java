package unsw.dungeon;

public class EnemyGoal implements Goal{

    private Dungeon dungeon;

    public EnemyGoal(Dungeon dungeon) {
        this.dungeon = dungeon;

    }

    @Override
    public Boolean checkStatus() {
        int enemyCount = 0;
        for (Entity e : this.dungeon.getEntList()) {
            if (e.getClass().equals(Enemy.class)) 
                enemyCount++;
        }
        if (enemyCount == 0) return true;
        return false;
    }

    
}