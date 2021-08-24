package unsw.dungeon;
import java.util.List;

import javafx.application.Platform;

public interface MoveEntity {

    // If these values return false, then the move was a success
    public abstract boolean moveUp(); 
    public abstract boolean moveDown();
    public abstract boolean moveLeft();
    public abstract boolean moveRight();

    public default boolean checkSpace(int xCheck, int yCheck, List<Entity> entList, Object obj, String movement) {
        for (Entity entCheck : entList) {
            if (entCheck != null) { 
                if (entCheck.getX() == xCheck && entCheck.getY() == yCheck) {
                    if (obj.getClass().equals(Boulder.class) && 
                    entCheck.getClass().equals(Switch.class) == false && 
                    entCheck.getClass().equals(Wall.class) == false && 
                    entCheck.getClass().equals(Boulder.class) == false) { // check at start to check if boulder is off switch
                        Boulder b = (Boulder) obj;
                        if (b.hasSwitch()) b.offSwitch();
                        System.out.println("off");
                    }
                    if ((obj.getClass().equals(Player.class) || obj.getClass().equals(Boulder.class) || obj.getClass().equals(Enemy.class)) && 
                        entCheck.getClass().equals(Wall.class)) /* player/boulder/enemy to wall collision */ 
                        return true;
                    if (obj.getClass().equals(Enemy.class) && entCheck.getClass().equals(Boulder.class)) return true; /* Enemy to boulder collision */
                    if (obj.getClass().equals(Player.class) && entCheck.getClass().equals(Boulder.class)) { /* player to boulder collision */
                        Boulder boulder = (Boulder) entCheck;
                        if (movement.equals("up")) return boulder.moveUp();
                        if (movement.equals("down")) return boulder.moveDown();
                        if (movement.equals("left")) return boulder.moveLeft();
                        if (movement.equals("right")) return boulder.moveRight();
                    }
                    if (obj.getClass().equals(Boulder.class) && entCheck.getClass().equals(Boulder.class)) /* boulder to boulder collision */
                        return true;
                    if (obj.getClass().equals(Player.class) && entCheck.getClass().equals(Sword.class)) { /* player equipped with sword */
                        Player player = (Player) obj;
                        Sword sword = (Sword) entCheck;
                        player.setSword(sword);
                        sword.setInvisible();
                        player.getDunEntList().remove(sword);
                        return false;
                    }
                    if (obj.getClass().equals(Player.class) && entCheck.getClass().equals(Door.class)) {
                        Player player = (Player) obj;
                        Door door = (Door) entCheck;
                        if (door.getStatus().equals("open")) {
                            return false;
                        } 
                        if (!player.hasKey()) {
                            return true;
                        }
                        if (player.hasKey()) {
                            door.openAttempt(player.getKey());
                            if (door.getStatus().equals("open")) {
                                player.keyUsed();
                                door.openDoor();
                                return false; 
                            }
                            else return true;
                        }
                    }
                    //add by tarry deal wiht potion
                    if (obj.getClass().equals(Player.class) && entCheck.getClass().equals(Potion.class)) { 
                        Player player = (Player) obj;
                        Potion potion = (Potion) entCheck;
                        player.setPotion(potion);
                        potion.setInvisible();
                        potion.usePotion(player);
                        return false;
                    }
                
                    if (obj.getClass().equals(Player.class) && entCheck.getClass().equals(Key.class)) { 
                        Player player = (Player) obj;
                        Key key = (Key) entCheck;
                        if(player.hasKey()) {
                            return true;
                        } else {
                            player.setKey(key);
                            key.setInvisible();
                            player.getDunEntList().remove(key);
                            return false;
                        }

                    }
                    
                    if (obj.getClass().equals(Player.class) && entCheck.getClass().equals(Stop.class)) { 
                        Player player = (Player) obj;
                        Stop stop = (Stop) entCheck;
                        player.setStop(stop);
                        stop.setInvisible();
                        stop.useStop(player);
                        return false;

                    }

                    if (obj.getClass().equals(Player.class) && entCheck.getClass().equals(Laser.class)) { 
                        System.out.println("Player Killed");
                        Platform.exit();
                        return true;

                    }

                    if (obj.getClass().equals(Enemy.class) && entCheck.getClass().equals(Laser.class)) { 
                        Enemy enemy = (Enemy) obj;
                        System.out.println("Enemy Fired");
                        enemy.openFire();
                        enemy.cancelTime();
                        return false;

                    }

                    if (obj.getClass().equals(Player.class) && entCheck.getClass().equals(Treasure.class)) {
                        Player player = (Player) obj;
                        Treasure tr = (Treasure) entCheck;
                        tr.setInvisible();
                        player.getDunEntList().remove(tr);
                        return false;
                    }
                    if (
                        (obj.getClass().equals(Player.class) && entCheck.getClass().equals(Enemy.class)) || 
                        (obj.getClass().equals(Enemy.class) && entCheck.getClass().equals(Player.class))
                        ) /* player to enemy collision */
                        {
                        Player player = null;
                        Enemy enemy = null;

                        if (obj.getClass().equals(Player.class)) {
                            player = (Player) obj;
                            enemy = (Enemy) entCheck;
                        } else {
                            player = (Player) entCheck;
                            enemy = (Enemy) obj;
                        }

                        if (player.hasSword()) {
                            if(player.SwordUseTimes() > 0){
                                //System.out.println("1");
                                player.useSword();
                                //System.out.println("2");
                                enemy.cancelTime();
                                //System.out.println("3");
                                enemy.setInvisible();
                                //System.out.println("4");
                                player.getDungeon().removeEnemy(enemy);
                                //System.out.println("5");
                                System.out.println("Enemy Killed");
                                return false;
                            }
                            
                        } else if (player.hasStop()) {
                            enemy.cancelTime();
                            System.out.println("Enemy Stopped");
                            return false;
                            

                        //addition by tarry player with potion
                        } else if (player.hasPotion()) {
                            enemy.cancelTime();
                            enemy.setInvisible();
                            player.getDungeon().removeEnemy(enemy);
                            System.out.println("Enemy Killed");
                            return false;
                            
                        

                        } else {
                            player.restartPosition(); // for testing purposes
                            // What to actually implement:
                            // exit the game
                            System.out.println("Player Killed");
                            enemy.cancelTime();
                            Platform.exit();
                            return true;
                        }
                    }
                    
                    if (
                        (obj.getClass().equals(Player.class) && entCheck.getClass().equals(Hound.class)) || 
                        (obj.getClass().equals(Hound.class) && entCheck.getClass().equals(Player.class))
                        ) /* player to enemy collision */
                        {
                            return true;
                    }
                    
                    if (obj.getClass().equals(Player.class) && entCheck.getClass().equals(Portal.class)) { // player to portal collision 
                        Player player = (Player) obj;
                        Portal portal = (Portal) entCheck;
                        portal.PlayerSwitch(player);
                    }
                    
                    if (
                        (obj.getClass().equals(Player.class) && entCheck.getClass().equals(Laser.class)) && 
                        (obj.getClass().equals(Enemy.class)))
                        {
                        return true;
                    }
                    if (obj.getClass().equals(Boulder.class) && entCheck.getClass().equals(Switch.class)) { // boulder to switch collision
                        Boulder b = (Boulder) obj;
                        Switch s = (Switch) entCheck;
                        s.yesBoulder();
                        b.onSwitch(s);
                        System.out.println("on");
                        return false;
                    }
                    if (obj.getClass().equals(Player.class) && entCheck.getClass().equals(Exit.class)) {
                        Player player = (Player) obj;
                        Exit exit = (Exit) entCheck;
                        exit.completedGoal();
                        // Player player = (Player) obj;
                        if (player.getDungeon().checkGoal()) {
                            // expect game to close in javafx
                            // remove goal from dungeon 
                            System.out.println("Game finished");
                            return false;
                        }

                        // For operand "AND", if game not complete and you attempt to remove exit, add it back
                        // do something if goals are multi
                        //if (goal.getGoalName().equals("exit")) return false; // edit this
                    }
                    if (obj.getClass().equals(Player.class) && entCheck.getClass().equals(Portal.class)) { // player to portal collision 
                        Player player = (Player) obj;
                        Portal portal = (Portal) entCheck;
                        portal.PlayerSwitch(player);
                    }
                    if (obj.getClass().equals(Player.class) && entCheck.getClass().equals(FakeWall.class)) { //player to FakeWall collision 
                        Player player = (Player) obj;
                        FakeWall fakeWall = (FakeWall) entCheck;
                        if (player.hasSword()) {
                            player.useSword();
                            fakeWall.setInvisible();
                            System.out.println("The fake Wall was damaged");
                            return false;
                        } else {
                            return true;
                        }
                    }
                }
            }

        }

        if (obj.getClass().equals(Boulder.class)) { // check at end if object is boulder
            Boulder b = (Boulder) obj;
            if (b.hasSwitch()) b.offSwitch();
            System.out.println("off");
        }

        return false;
    }
    

}