package unsw.dungeon;

import java.io.FileNotFoundException;
import java.io.FileReader;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

/**
 * Loads a dungeon from a .json file.
 *
 * By extending this class, a subclass can hook into entity creation. This is
 * useful for creating UI elements with corresponding entities.
 *
 * @author Robert Clifton-Everest
 *
 */
public abstract class DungeonLoader {

    private JSONObject json;

    public DungeonLoader(String filename) throws FileNotFoundException {
        json = new JSONObject(new JSONTokener(new FileReader("dungeons/" + filename)));
    }

    /**
     * Parses the JSON to create a dungeon.
     * @return
     */
    public Dungeon load() {
        int width = json.getInt("width");
        int height = json.getInt("height");

        Dungeon dungeon = new Dungeon(width, height);

        JSONObject jGoalConds = json.getJSONObject("goal-condition");
        String goal = jGoalConds.getString("goal");
        if (goal.equals("exit")) {
            dungeon.setGoalStatus("singleExit");
            //Exit exit = (Exit) blah;
        } else if (goal.equals("enemies")) {
            dungeon.setGoalStatus("singleEnemies");
            EnemyGoal eg = new EnemyGoal(dungeon);
            dungeon.setGoal(eg);
        } else if (goal.equals("boulders")) {
            dungeon.setGoalStatus("singleBoulders");
            // do something
        } else if (goal.equals("treasure")) {
            dungeon.setGoalStatus("singleTreasure");
            TreasureGoal tg = new TreasureGoal(dungeon);
            dungeon.setGoal(tg);
        } else {
            String operator = goal; // will be "AND" or "OR"
            dungeon.setGoalStatus(operator);
            JSONArray jSubGoalArr = jGoalConds.getJSONArray("subgoals");
            for (int i = 0; i < jSubGoalArr.length(); i++) {
                JSONObject jSubGoal = jSubGoalArr.getJSONObject(i);
                String subGoal = jSubGoal.getString("goal");
                if (subGoal.equals("exit")) {
                    //Exit exit = (Exit) eCheck;
                    // do something with exit
                } else if (subGoal.equals("enemies")) {
                    //EnemyGoal eg = new EnemyGoal(dungeon);
                    // do something with eg
                } else if (subGoal.equals("boulders")) {
                    // do something
                } else if (subGoal.equals("treasure")) {
                    // do something
                }
            }
        }

        JSONArray jsonEntities = json.getJSONArray("entities");

        for (int i = 0; i < jsonEntities.length(); i++) {
            loadEntity(dungeon, jsonEntities.getJSONObject(i));
        }
        return dungeon;
    }

    private void loadEntity(Dungeon dungeon, JSONObject json) {
        String type = json.getString("type");
        int x = json.getInt("x");
        int y = json.getInt("y");
        int id;

        Entity entity = null;
        switch (type) {
        case "player":
            Player player = new Player(dungeon, x, y);
            dungeon.setPlayer(player);
            onLoad(player);
            entity = player;
            break;
        case "wall":
            Wall wall = new Wall(x, y);
            onLoad(wall);
            entity = wall;
            break;
        case "fakeWall":
            FakeWall fakeWall = new FakeWall(x, y);
            onLoad(fakeWall);
            entity = fakeWall;
            break;
        case "boulder":
            Boulder boulder = new Boulder(dungeon, x, y);
            onLoad(boulder);
            entity = boulder;
            break;
        case "exit":
            Exit exit = new Exit(x, y);
            onLoad(exit);
            entity = exit;
            if (dungeon.getGoalStatus().equals("singleExit")) dungeon.setGoal(exit);
            break;
        case "enemy":
            Enemy enemy = new Enemy(dungeon, x, y);
            onLoad(enemy);
            entity = enemy;
            if (dungeon.getGoalStatus().equals("singleEnemies") && dungeon.hasGoal() == false) {
                EnemyGoal eg = new EnemyGoal(dungeon);
                dungeon.setGoal(eg);
            }
            break;
        case "hound":
            id = json.getInt("id");
            Hound hound = new Hound(x, y, id);
            onLoad(hound);
            entity = hound;
            break;
        case "treasure":
            Treasure treasure = new Treasure(x, y);
            onLoad(treasure);
            entity = treasure;
            if (dungeon.getGoalStatus().equals("singleTreasure") && dungeon.hasGoal() == false) {
                TreasureGoal tg = new TreasureGoal(dungeon);
                dungeon.setGoal(tg);
            }
            break;
        case "stop":
            Stop stop = new Stop(x,y);
            onLoad(stop);
            entity = stop;
            break;        
        
        case "laser":
            id = json.getInt("id");
            Laser laser = new Laser(x,y,id);
            onLoad(laser);
            entity = laser;
            break;        
        

        case "portal":
            id = json.getInt("id");
            Portal portal = new Portal(x,y,id,dungeon);
            onLoad(portal);
            entity = portal;
            break;   
        case "sword":
            Sword sword = new Sword(x, y);
            onLoad(sword);
            entity = sword;
            break;
        case "switch":
            Switch switchBut = new Switch(x, y);
            onLoad(switchBut);
            entity = switchBut;
            // edit how to add and remove goals
            if (dungeon.getGoalStatus().equals("singleBoulders") && dungeon.hasGoal() == false) {
                BoulderGoal bg = new BoulderGoal(dungeon);
                dungeon.setGoal(bg);
            }
            break;

        case "key":
        	id = json.getInt("id");
        	Key key = new Key(x,y,id);
        	onLoad(key);
        	entity = key;
        	break;
        case "door":
        	id = json.getInt("id");
        	Door door = new Door(x,y,id);
        	onLoad(door);
        	entity = door;
        	break;

        case "invincibility":
            Potion  potion = new Potion(x,y);
            onLoad(potion);
            entity = potion;
            break;        
        }
        dungeon.addEntity(entity);
    }
    public abstract void onLoad(Player player);

    public abstract void onLoad(Enemy enemy);

    public abstract void onLoad(Wall wall);
    
    public abstract void onLoad(FakeWall fakeWall);

    public abstract void onLoad(Boulder boulder);

    public abstract void onLoad(Exit exit);

    public abstract void onLoad(Treasure treasure);
    
    public abstract void onLoad(Sword sword);

    public abstract void onLoad(Switch switchBut);

    public abstract void onLoad(Key key);

    public abstract void onLoad(Door door);

    public abstract void onLoad(Potion potion);
    
    public abstract void onLoad(Stop stop);

    public abstract void onLoad(Laser laser);

    public abstract void onLoad(Hound hound);

    public abstract void onLoad(Portal portal);
  

    // TODO Create additional abstract methods for the other entities

}
