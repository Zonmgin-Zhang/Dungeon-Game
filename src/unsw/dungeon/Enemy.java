package unsw.dungeon;

import java.util.Timer;
import java.util.TimerTask;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;


/**
 * An implementation of the Enemy entity given in the spec
 *
 */
public class Enemy extends Entity implements MoveEntity {

    private Dungeon dungeon;
    private Player player;
    private TimerTask timer;
    private int startingX;
    private int startingY;
    private BooleanProperty fired;

    public Enemy(Dungeon dungeon, int x, int y) {
        super(x, y);
        this.dungeon = dungeon;
        this.player = dungeon.getPlayer();
        this.timer = movementTime();
        this.startingX = x;
        this.startingY = y;
        this.fired = new SimpleBooleanProperty(false);
    }

    @Override
    public boolean moveUp() {
        int wallCheck = 2;
        if (checkSpace(getX(), getY() - 1, dungeon.getEntList(), this, "up") == false) {
            y().set(getY() - 1);
            return true;
        }
        while (checkSpace(getX(), getY() - wallCheck, dungeon.getEntList(), this, "up") == true) {
            wallCheck++;
        }
        y().set(getY() - wallCheck);
        return false;
    }

    @Override
    public boolean moveDown() {
        int wallCheck = 2;
        if (checkSpace(getX(), getY() + 1, dungeon.getEntList(), this, "down") == false) {
            y().set(getY() + 1);
            return true;
        }
        while (checkSpace(getX(), getY() + wallCheck, dungeon.getEntList(), this, "down") == true) {
            wallCheck++;
        }
        y().set(getY() + wallCheck);
        return false;
    }

    @Override
    public boolean moveLeft() {
        int wallCheck = 2;
        if (checkSpace(getX() - 1, getY(), dungeon.getEntList(), this, "left") == false) {
            x().set(getX() - 1);
            return true;
        }
        while (checkSpace(getX() - wallCheck, getY(), dungeon.getEntList(), this, "left") == true) {
            wallCheck++;
        }
        x().set(getX() - wallCheck);
        return false;
    }

    public boolean moveRight() {
        int wallCheck = 2;
        if (checkSpace(getX() + 1, getY(), dungeon.getEntList(), this, "right") == false) {
            x().set(getX() + 1);
            return true;
        }
        while (checkSpace(getX() + wallCheck, getY(), dungeon.getEntList(), this, "right") == true) {
            wallCheck++;
        }
        x().set(getX() + wallCheck);
        return false;
    }

    public TimerTask getTime() {
        return timer;
    }

    public void cancelTime() {
        this.timer.cancel();
    }

    public boolean moveToPlayer() {
        int deltaX = player.getX() - getX();
        int deltaY = player.getY() - getY();

        if (deltaX > 0) {
            moveRight();
        }
        if (deltaX < 0) {
            moveLeft();
        }
        if (deltaY > 0) {
            moveDown();
        }
        if (deltaY < 0) { 
            moveUp();
        }
        return false;
    }

    public TimerTask movementTime() {
        Timer timer = new Timer();

        TimerTask enemyMove = new TimerTask() {
            @Override
            public void run() {
            moveToPlayer();
            }
        };
        // must check if goal is finished 
        timer.schedule(enemyMove, 3000, 750); 
        return enemyMove;
    }

    public void restartPosition() {
        x().set(startingX);
        y().set(startingY);
        this.timer.cancel();
        movementTime();
    }

	public BooleanProperty getFire() {
		return fired;
	}

	public void openFire() {
		fired.setValue(true);
	}

	public boolean isFired() {
		return fired.getValue();
	}

}
