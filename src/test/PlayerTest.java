package test;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import unsw.dungeon.Dungeon;
import unsw.dungeon.Player;
import unsw.dungeon.Wall;
import unsw.dungeon.Goal;

class PlayerTest {

	private Player player;
	private Dungeon dungeon;

	@BeforeEach
	void init() {
        dungeon = new Dungeon(10, 10);
        player = new Player(dungeon, 1, 1);
		dungeon.addEntity(player);
		dungeon.setPlayer(player);
	}

	@Test
	void movePLayer() {
        dungeon = new Dungeon(10, 10);
        player = new Player(dungeon, 1, 1);
        dungeon.addEntity(player);
        dungeon.setPlayer(player);

        assertEquals(player.getX(), 1);
        assertEquals(player.getY(), 1);
    
        player.moveLeft();
        assertEquals(player.getX(), 0);
        assertEquals(player.getY(), 1);
    
        player.moveUp();
        assertEquals(player.getX(), 0);
        assertEquals(player.getY(), 0);
    
        player.moveRight();
        assertEquals(player.getX(), 1);
        assertEquals(player.getY(), 0);
    
        player.moveDown();
        assertEquals(player.getX(), 1);
        assertEquals(player.getY(), 1);
    
	}

	@Test
	void outOfBoundsMovement() {
		assertEquals(player.getX(), 1);
		assertEquals(player.getY(), 1);

		player.moveLeft();

		assertEquals(player.getX(), 0);
		assertEquals(player.getY(), 1);

		// Coordinates should not change
		player.moveLeft();
		assertEquals(player.getX(), 0);
		assertEquals(player.getY(), 1);

    }

    @Test
	void moveWall() {
        Wall wall = new Wall(2, 1);
		dungeon.addEntity(wall);
		player.moveRight();
		player.moveDown();
		wall.getX();
		assertEquals(player.getX(), 1);
		assertEquals(player.getY(), 2);
    }

    @Test
	void ArriveExit() {
        Goal exit = new Goal(2, 1, "exit");
		dungeon.addGoal(exit);
        player.moveRight();
		
		assertEquals(player.getX(), 2);
		assertEquals(player.getY(), 1);

        assertEquals("exit", exit.getGoalName());
    }
}
