package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import unsw.dungeon.Dungeon;
import unsw.dungeon.Door;
import unsw.dungeon.Key;
import unsw.dungeon.Player;

class DoorTest {

	private Dungeon dungeon;
	private Player player;

	@BeforeEach
	void init() {
        dungeon = new Dungeon(10, 10);
        player = new Player(dungeon, 1, 1);
		dungeon.addEntity(player);
		dungeon.setPlayer(player);
	}

	@Test
	void playerMoveDoorNoKey() {
		Door door = new Door(2, 1, 1);
        dungeon.addEntity(door);

		player.moveRight();
		assertEquals("closed", door.getStatus());
		assertEquals(player.getX(), 1);
		assertEquals(player.getY(), 1);
	}

	@Test
	void playerMoveDoorWrongKey() {
        Door door = new Door(3, 1, 1);
        dungeon.addEntity(door);

        Key key = new Key(2, 1, 1);
        dungeon.addEntity(key);

		// Pick up
		player.moveRight();
		player.setKey(key);

		assertEquals(player.getX(), 2);
		assertEquals(player.getY(), 1);

		assertTrue(player.hasKey());

		player.moveRight();
		assertFalse(door.openAttempt(key));
		assertEquals("closed", door.getStatus());
		assertEquals(player.getX(), 2);
		assertEquals(player.getY(), 1);
	}

	@Test
	void playerMoveDoorRightKey() {
        Door door = new Door(3, 1, 1);
		dungeon.addEntity(door);
		assertEquals(door.getId(), 1);

        Key key = new Key(2, 1, 1);
        dungeon.addEntity(key);

		// Pick up
		player.moveRight();
		player.setKey(key);

		assertEquals(player.getX(), 2);
		assertEquals(player.getY(), 1);

		assertTrue(player.hasKey());

		// Open door
		player.moveRight();
		assertTrue(door.openAttempt(key));
		assertEquals("open", door.getStatus());

		assertEquals(player.getX(), 3);
		assertEquals(player.getY(), 1);
    }

	@Test
	void keyCarryMaxOne() {
        Key key1 = new Key(2, 1, 1);
		dungeon.addEntity(key1);

        Key key2 = new Key(3, 1, 2);
        dungeon.addEntity(key2);

		player.moveRight();
		assertFalse(player.hasKey());

		player.moveRight();
		assertTrue(player.hasKey());

	}

}