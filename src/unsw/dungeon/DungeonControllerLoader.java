package unsw.dungeon;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

import java.io.File;

/**
 * A DungeonLoader that also creates the necessary ImageViews for the UI,
 * connects them via listeners to the model, and creates a controller.
 * @author Robert Clifton-Everest
 *
 */
public class DungeonControllerLoader extends DungeonLoader {

    private List<ImageView> entities;

    //Images
    private Image playerImage;
    private Image wallImage;
    private Image boulderImage;
    private Image exitImage;
    private Image enemyImage;
    private Image treasureImage;
    private Image swordImage;
    private Image switchImage;
    private Image keyImage;
    private Image doorClosedImage;
    private Image doorOpenImage;
    private Image potionImage;
    private Image laserImage;
    private Image stopImage;
    private Image portalImage;
    private Image houndImage;
    private Image fireImage;
    private Image fwImage;

    public DungeonControllerLoader(String filename)
            throws FileNotFoundException {
        super(filename);
        entities = new ArrayList<>();
        playerImage = new Image((new File("images/human_new.png")).toURI().toString());
        wallImage = new Image((new File("images/brick_brown_0.png")).toURI().toString());
        boulderImage = new Image((new File("images/boulder.png")).toURI().toString());
        exitImage = new Image((new File("images/exit.png")).toURI().toString());
        enemyImage = new Image((new File("images/deep_elf_master_archer.png")).toURI().toString());
        treasureImage = new Image((new File("images/gold_pile.png")).toURI().toString());
        swordImage = new Image((new File("images/greatsword_1_new.png")).toURI().toString());
        switchImage = new Image((new File("images/pressure_plate.png")).toURI().toString());
        //addition by tarry
        keyImage = new Image((new File("images/key.png")).toURI().toString());
        doorClosedImage = new Image((new File("images/closed_door.png")).toURI().toString());
        doorOpenImage = new Image((new File("images/open_door.png")).toURI().toString());
        potionImage = new Image((new File("images/brilliant_blue_new.png")).toURI().toString());
        laserImage = new Image((new File("images/laser.png")).toURI().toString());
        fireImage = new Image((new File("images/fire.gif")).toURI().toString());
        portalImage = new Image((new File("images/portal.png")).toURI().toString());
        stopImage = new Image((new File("images/bubbly.png")).toURI().toString());
        houndImage = new Image((new File("images/gnome.png")).toURI().toString());
        fwImage = new Image((new File("images/fakewall.png")).toURI().toString());
    }

    @Override
    public void onLoad(Player player) {
        ImageView view = new ImageView(playerImage);
        addEntity(player, view);
    }

    @Override
    public void onLoad(Enemy enemy) {
        ImageView view1 = new ImageView(enemyImage);
        ImageView view2 = new ImageView(fireImage);
        trackEnemyStatus(enemy, view1, view2);
        addEntity(enemy, view1);
        addEntity(enemy, view2);
    }

    @Override
    public void onLoad(Exit exit) {
        ImageView view = new ImageView(exitImage);
        addEntity(exit, view);
    }

    @Override
    public void onLoad(Treasure treasure) {
        ImageView view = new ImageView(treasureImage);
        addEntity(treasure, view);
    }

    @Override
    public void onLoad(Sword sword) {
        ImageView view = new ImageView(swordImage);
        addEntity(sword, view);
    }

    @Override
    public void onLoad(Boulder boulder) {
        ImageView view = new ImageView(boulderImage);
        addEntity(boulder, view);
    }

    @Override
    public void onLoad(Wall wall) {
        ImageView view = new ImageView(wallImage);
        addEntity(wall, view);
    }

    @Override
    public void onLoad(Switch switchBut) {
        ImageView view = new ImageView(switchImage);
        addEntity(switchBut, view);
    }

    @Override
    public void onLoad(Key key) {
        ImageView view = new ImageView(keyImage);
        addEntity(key, view);
    }
    
    @Override
    public void onLoad(FakeWall fakewall) {
        ImageView view = new ImageView(fwImage);
        addEntity(fakewall, view);
    }
    
    @Override
    public void onLoad(Door door) {
        ImageView view1 = new ImageView(doorClosedImage);
        ImageView view2 = new ImageView(doorOpenImage);
        trackDoorStatus(door, view1, view2);
        addEntity(door, view1);
        addEntity(door, view2);
    }

    @Override
    public void onLoad(Potion potion) {
        ImageView view = new ImageView(potionImage);
        addEntity(potion, view);
    }
    @Override
    public void onLoad(Portal portal) {
        ImageView view = new ImageView(portalImage);
        addEntity(portal, view);
    }

    @Override
    public void onLoad(unsw.dungeon.Stop stop) {
        ImageView view = new ImageView(stopImage);
        addEntity(stop, view);
    }

    @Override
    public void onLoad(Hound hound) {
        ImageView view = new ImageView(houndImage);
        addEntity(hound, view);
    }

    @Override
    public void onLoad(Laser laser) {
        ImageView view = new ImageView(laserImage);
        addEntity(laser, view);
    }

    private void addEntity(Entity entity, ImageView view) {
        trackPosition(entity, view);
        entities.add(view);
    }

    /**
     * Set a node in a GridPane to have its position track the position of an
     * entity in the dungeon.
     *
     * By connecting the model with the view in this way, the model requires no
     * knowledge of the view and changes to the position of entities in the
     * model will automatically be reflected in the view.
     * @param entity
     * @param node
     */
    private void trackPosition(Entity entity, Node node) {
        GridPane.setColumnIndex(node, entity.getX());
        GridPane.setRowIndex(node, entity.getY());
        entity.x().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable,
                    Number oldValue, Number newValue) {
                GridPane.setColumnIndex(node, newValue.intValue());
            }
        });
        entity.y().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable,
                    Number oldValue, Number newValue) {
                GridPane.setRowIndex(node, newValue.intValue());
            }
        });
    }
    private void trackDoorStatus(Door door, Node close, Node open) {
    	open.setVisible(false);
    	door.getOpen().addListener(new ChangeListener<Boolean>() {
			@Override
			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
				if (newValue.booleanValue()) {
					System.out.println("Gets here");
					close.setVisible(false);
					open.setVisible(true);
				} else {
					close.setVisible(true);
					open.setVisible(false);
				}
			}
    		
    	});
    }

    private void trackEnemyStatus(Enemy enemy, Node good, Node fired) {
    	fired.setVisible(false);
    	enemy.getFire().addListener(new ChangeListener<Boolean>() {
			@Override
			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
				if (newValue.booleanValue()) {
					System.out.println("Enemy gonna be fired");
					good.setVisible(false);
					fired.setVisible(true);
				} else {
					good.setVisible(true);
					fired.setVisible(false);
				}
			}
    		
    	});
    }
    
    /**
     * Create a controller that can be attached to the DungeonView with all the
     * loaded entities.
     * @return
     * @throws FileNotFoundException
     */
    public DungeonController loadController() throws FileNotFoundException {
        return new DungeonController(load(), entities);
    }


}
