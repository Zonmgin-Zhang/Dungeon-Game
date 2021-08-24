package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import unsw.dungeon.*;

class EnemyTest {

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
	void testEnemyMovement() {
        Enemy enemy = new Enemy(dungeon, 5, 5);
        dungeon.addEntity(enemy);
		assertEquals(enemy.getX(), 5);
		assertEquals(enemy.getY(), 5);

		enemy.moveRight();

		assertEquals(enemy.getX(), 6);
        assertEquals(enemy.getY(), 5);
        
		enemy.moveLeft();

		assertEquals(enemy.getX(), 5);
        assertEquals(enemy.getY(), 5);

        enemy.moveUp();

		assertEquals(enemy.getX(), 5);
        assertEquals(enemy.getY(), 4);
        
        enemy.moveDown();

		assertEquals(enemy.getX(), 5);
		assertEquals(enemy.getY(), 5);
    
	}

	@Test
	void testEnemyCollideBoulder() {
        Enemy enemy = new Enemy(dungeon, 5, 5);
        dungeon.addEntity(enemy);
        Boulder boulder = new Boulder(dungeon, 6, 5);
        dungeon.addEntity(boulder);
		assertEquals(enemy.getX(), 5);
		assertEquals(enemy.getY(), 5);

		// enemy jumps through boulder to avoid the enemy from stopping movement
		enemy.moveRight();
		assertEquals(enemy.getX(), 7);
		assertEquals(enemy.getY(), 5);

	}

	@Test
	void testEnemyCollideDoor() {
        Enemy enemy = new Enemy(dungeon, 5, 5);
        dungeon.addEntity(enemy);
        Door door = new Door(6, 5, 1);
        dungeon.addEntity(door);

		assertEquals(enemy.getX(), 5);
		assertEquals(enemy.getY(), 5);

		enemy.moveRight();
		assertEquals(enemy.getX(), 5);
		assertEquals(enemy.getY(), 5);


		enemy.moveRight();
		assertEquals(enemy.getX(), 5);
		assertEquals(enemy.getY(), 5);
	}

	/*@Test
	void testEnemyApproach() {

        Enemy enemy = new Enemy(dungeon, 5, 5);
		dungeon.addEntity(enemy);
		assertEquals(enemy.getX(), 5);
		assertEquals(enemy.getY(), 5);

		player.moveRight();
		System.out.printf("x:%d y:%d\n", enemy.getX(), enemy.getY());
		assertEquals(player.getX(), 2);
		assertEquals(player.getY(), 1);
		assertEquals(enemy.getX(), 4);
		assertEquals(enemy.getY(), 5);
	}*/

	@Test
	void testEnemyDeathSword() {
        Enemy enemy = new Enemy(dungeon, 2, 1);
        dungeon.addEntity(enemy);
        Sword sword= new Sword(0, 0);
        dungeon.addEntity(sword);

		player.setSword(sword);

		player.moveRight();

		assertFalse(dungeon.getEntList().contains(enemy));
	}

	/*@Test
	void testEnemyDeathPotion() {
        Enemy enemy = new Enemy(dungeon, 2, 1);
        dungeon.addEntity(enemy);
        Potion potion = new Potion(0, 0);
		player.pickUp(potion);

		player.moveRight();
		assertFalse(dungeon.getEntities().contains(enemy));
	}*/

	@Test
	void testPlayerDeathPlayerMove() {
        Enemy enemy = new Enemy(dungeon, 2, 1);
        dungeon.addEntity(enemy);
		assertEquals(player.getX(), 1);
		assertEquals(player.getY(), 1);
		player.moveRight();
		assertEquals(player.getX(), 1);
		assertEquals(player.getY(), 1);
	}


}
