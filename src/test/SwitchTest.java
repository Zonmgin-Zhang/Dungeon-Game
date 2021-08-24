//package test;


//import unsw.dungeon.*;

/*class SwitchTest {

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
	void boulderActivate() {
		Boulder boulder = new Boulder(dungeon, 1, 2);
		dungeon.addEntity(boulder);
		Switch sw = new Switch(1, 3);
		dungeon.addEntity(sw);

		assertFalse(sw.getActivated());
		player.moveDown();
		assertTrue(sw.getActivated());
	}

	@Test
	void boulderDeactivate() {
		Boulder boulder = new Boulder(dungeon, 1, 2);
		dungeon.addEntity(boulder);
		Switch sw = new Switch(1, 2);
		dungeon.addEntity(sw);

		assertTrue(sw.getActivated());
		player.moveDown();
		assertFalse(sw.getActivated());
	}

	@Test
	void boulderPushThrough() {
		Boulder boulder = new Boulder(dungeon, 1, 2);
		dungeon.addEntity(boulder);
		Switch sw = new Switch(1, 3);
		dungeon.addEntity(sw);

		assertFalse(sw.getActivated());
		player.moveDown();
		assertTrue(sw.getActivated());
		player.moveDown();
		assertFalse(sw.getActivated());

	}

	@Test
	void otherEntityFail() {
		Switch sw = new Switch(1, 2);
		dungeon.addEntity(sw);
		assertFalse(sw.getActivated());
		player.moveDown();
		assertFalse(sw.getActivated());
	}
}*/
