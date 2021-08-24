package unsw.dungeon;

import java.io.File;
import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class MenuController {

    @FXML
    private ImageView imageBg;

    @FXML
    private Button playButton;
    
    @FXML
    private MenuButton dunEditButton;

    @FXML
    private MenuItem standardBut;
    
    @FXML
    private MenuItem bouldersBut;

    @FXML
    private MenuItem advancedBut;

    private String stage = "standard.json";

    @FXML
    public void handlePlay(ActionEvent event) throws IOException {
        Stage mazeStage = (Stage) playButton.getScene().getWindow();
        //Button backButton = new Button("Back");
        // backButton.setAlignment(Pos.BOTTOM_RIGHT);
        mazeStage.setTitle("EasyPass Dungeon");
        DungeonControllerLoader dungeonLoader = new DungeonControllerLoader(stage);
        DungeonController controller = dungeonLoader.loadController();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("DungeonView.fxml"));
        loader.setController(controller);
        Parent root = loader.load();
        Scene scene = new Scene(root);
        root.requestFocus();
        mazeStage.setScene(scene);
        mazeStage.show();
    }

    @FXML
    public void handleDun() {

    }

    @FXML
    public void changeStandardDun() {
        stage = "standard.json";
        // make standard text bold
    }

    @FXML 
    public void changeBouldersDun() {
        stage = "boulders.json";
        // make boulders text bold
    }
    
    @FXML
    public void changeAdvancedDun() {
        stage = "advanced.json";
        // make advanced text bold
    }

    @FXML
    public void initialize() {
        Image image = new Image((new File("images/fb.jpg")).toURI().toString());
        imageBg.setImage(image);
        
    }


}