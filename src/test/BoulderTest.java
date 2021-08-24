package test;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import unsw.dungeon.Dungeon;
import unsw.dungeon.Boulder;
import unsw.dungeon.Door;
import unsw.dungeon.Goal;
import unsw.dungeon.Player;
import unsw.dungeon.Sword;
import unsw.dungeon.Wall;

public class BoulderTest {
	private static Dungeon dungeon;
	private static Player player;

	@BeforeEach
	void init() {
        dungeon = new Dungeon(10, 10);
        player = new Player(dungeon, 1, 1);
		dungeon.addEntity(player);
		dungeon.setPlayer(player);
	}

	@Test
	void boulderPush() {

		Boulder boulder = new Boulder(dungeon, 2, 1);
        dungeon.addEntity(boulder);

		assertEquals(boulder.getX(), 2);
		assertEquals(boulder.getY(), 1);

		player.moveRight();
		assertEquals(player.getX(), 2);
		assertEquals(player.getY(), 1);

		assertEquals(boulder.getX(), 3);
		assertEquals(boulder.getY(), 1);

	}

	/*@Test
	void boulderPushSwitch() {
		Boulder boulder = new Boulder(dungeon, 2, 1);
		Switch switchEntity = new Switch(3, 1);
        dungeon.addEntity(boulder);
        dungeon.addEntity(switchEntity);

		assertEquals(boulder.getX(), 2);
		assertEquals(boulder.getY(), 1);

		player.moveRight();
		assertEquals(boulder.getX(), 3);
		assertEquals(boulder.getY(), 1);

		assertEquals(switchEntity.getX(), 3);
		assertEquals(switchEntity.getY(), 1);

		assertTrue(switchEntity.getActivated());
	} */

	@Test
	void boulderPushDoor() {
		Boulder boulder = new Boulder(dungeon, 2, 1);
		Door door = new Door(3, 1, 0);
        dungeon.addEntity(boulder);
        dungeon.addEntity(door);

		assertEquals(boulder.getX(), 2);
		assertEquals(boulder.getY(), 1);

		player.moveRight();
		assertEquals(player.getX(), 2);
		assertEquals(player.getY(), 1);

		assertEquals(boulder.getX(), 3);
		assertEquals(boulder.getY(), 1);
    }
    
	@Test
	void boulderPushExit() {
		Boulder boulder = new Boulder(dungeon, 2, 1);
		Goal exit = new Goal(3, 1, "exit");
        dungeon.addEntity(boulder);
        dungeon.addEntity(exit);

		assertEquals(boulder.getX(), 2);
		assertEquals(boulder.getY(), 1);

		player.moveRight();
		assertEquals(player.getX(), 2);
		assertEquals(player.getY(), 1);

		assertEquals(boulder.getX(), 3);
		assertEquals(boulder.getY(), 1);
    }

	@Test
	void boulderPushItems() {
		// Test a sword
		Boulder boulder = new Boulder(dungeon, 2, 1);
		Sword sword = new Sword(3, 1);
        dungeon.addEntity(boulder);
        dungeon.addEntity(sword);

		assertEquals(boulder.getX(), 2);
		assertEquals(boulder.getY(), 1);

		player.moveRight();
		assertEquals(boulder.getX(), 3);
		assertEquals(boulder.getY(), 1);

		assertTrue(dungeon.getEntList().contains(sword));
	}

	@Test
	void boulderPushWall() {
        Boulder boulder = new Boulder(dungeon, 2, 1);
        Wall wall = new Wall(3, 1);
        dungeon.addEntity(boulder);
        dungeon.addEntity(wall);

		assertEquals(boulder.getX(), 2);
		assertEquals(boulder.getY(), 1);

		player.moveRight();
		assertEquals(player.getX(), 1);
		assertEquals(player.getY(), 1);

		assertEquals(boulder.getX(), 2);
		assertEquals(boulder.getY(), 1);
	}

	@Test
	void boulderPushBoulder() {
		Boulder boulder1 = new Boulder(dungeon, 2, 1);
		Boulder boulder2 = new Boulder(dungeon, 4, 1);
        dungeon.addEntity(boulder1);
        dungeon.addEntity(boulder2);

		assertEquals(boulder1.getX(), 2);
		assertEquals(boulder1.getY(), 1);

		player.moveRight();
		assertEquals(player.getX(), 2);
		assertEquals(player.getY(), 1);

		assertEquals(boulder1.getX(), 3);
		assertEquals(boulder1.getY(), 1);

		player.moveRight();
		assertEquals(player.getX(), 2);
		assertEquals(player.getY(), 1);

		assertEquals(boulder2.getX(), 4);
		assertEquals(boulder2.getY(), 1);

	}
}
