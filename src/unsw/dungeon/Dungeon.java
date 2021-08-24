 
/**
 *
 */
package unsw.dungeon;

import java.util.ArrayList;
import java.util.List;

/**
 * A dungeon in the interactive dungeon player.
 *
 * A dungeon can contain many entities, each occupy a square. More than one
 * entity can occupy the same square.
 *
 * @author Robert Clifton-Everest
 *
 */
public class Dungeon {

    private int width, height;
    private String goalStatus;
    private List<Entity> entities;
    private Player player;
    private Goal goal;


    public Dungeon(int width, int height) {
        this.width = width;
        this.height = height;
        this.entities = new ArrayList<>();
        this.player = null;
        this.goal = null;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public Player getPlayer() {
        return player;
    }

    public List<Entity> getEntList() {
        return entities;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public void addEntity(Entity entity) {
        entities.add(entity);
    }

    public String getGoalStatus() {
        return this.goalStatus;
    }

    public void setGoal(Goal goal) {
        this.goal = goal;
    }

    public void setGoalStatus(String status) {
        this.goalStatus = status;
    } 

    public boolean checkGoal() {
        return goal.checkStatus();
    }

    public boolean hasGoal(){
        if (this.goal != null) return false;
        return true;
    }

    public void removeEnemy(Enemy enemy) {
        entities.remove(enemy);
        enemy.getTime().cancel();
    }

    public void cancelEnemyTimer() {
        for (Entity e : this.getEntList()) {
            if (e.getClass().equals(Enemy.class)) {
                Enemy enemy = (Enemy) e;
                enemy.getTime().cancel();
            }
        }
    }

}
