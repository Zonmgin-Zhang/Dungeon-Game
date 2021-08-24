package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import unsw.dungeon.Dungeon;
import unsw.dungeon.Enemy;
//import unsw.dungeon.Exit;
import unsw.dungeon.Player;
//import unsw.dungeon.Switch;
//import unsw.dungeon.Treasure;
import unsw.dungeon.Goal;
//import unsw.dungeon.MultipleGoals;
//iimport unsw.dungeon.SingularGoal;

public class GoalTest {

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
	void dungeonSetGoal() {
		Goal goal = new Goal(2, 2, null);
		dungeon.addGoal(goal);
		assertEquals(goal, dungeon.getGoals());
	}

	@Test
	void simpleGoalBoulder() {
        /*Switch sw = new Switch(2, 2);
        dungeon.addEntity(sw);
		*/
		Goal goal = new Goal(2, 2, "boulders");
		dungeon.addGoal(goal);
		
		assertEquals("boulders", goal.getGoalName());
	}

	@Test
	void simpleGoalEnemy() {
        Enemy enemy1 = new Enemy(dungeon, 2, 2);
        dungeon.addEntity(enemy1);
        Enemy enemy2 = new Enemy(dungeon, 3, 3);
        dungeon.addEntity(enemy2);

		Goal goal = new Goal(3, 3, "enemies");

		dungeon.addGoal(goal);

		assertEquals("enemies", goal.getGoalName());
	}

	@Test
	void simpleGoalExit_playerActivated() {

		Goal goal = new Goal(0, 0, "exit");
		dungeon.addGoal(goal);

		assertEquals("exit", goal.getGoalName());
	}

	@Test
	void simpleGoalTreasure() {

		Goal goal = new Goal(2, 1, "treasure");

		dungeon.addGoal(goal);
		player.moveRight();

		assertEquals("treasure", goal.getGoalName());
	}


	@Test
	void compositeGoalOR() {

		Goal goal1 = new Goal(1, 2,  "treasure");
		Goal goal2 = new Goal(1, 2,  "Exit");
		Goal goal3 = new Goal(1, 2, "OR");

		dungeon.addGoal(goal1);
		dungeon.addGoal(goal2);
		dungeon.addGoal(goal3);
		dungeon.getGoals();

		assertFalse(dungeon.goalsCompleted());

		player.moveDown();
		assertTrue(dungeon.goalsCompleted());
	}

	@Test
	void simpleCompositeGoalAND() {

		Goal goal1 = new Goal(1, 1, "boulder");
		Goal goal2 = new Goal(1, 2, "exit");
		Goal goal3 = new Goal(1, 2, "AND");

		dungeon.addGoal(goal1);
		dungeon.addGoal(goal2);
		dungeon.addGoal(goal3);
		dungeon.getGoals();

        /*Switch sw = new Switch(1, 1);
		dungeon.addEntity(sw);
		
		
		Boulder boulder = new Boulder(dungeon, 1, 1);
		dungeon.addEntity(boulder);
		*/
		player.moveDown();
		assertTrue(dungeon.goalsCompleted());
	}
}
