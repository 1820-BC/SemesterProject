//The main method for this game
//test comment for pull

import BoardStuff.Board;
import BoardStuff.BoardIO;

import Scenes.*;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import java.awt.*;
import java.io.FileInputStream;
import javafx.stage.Screen;

//Main method that begins to run everything
public class main extends Application {

    //initializing the necessary pieces for the GUI
    Stage stage;


    @Override
    public void start(Stage s) throws Exception{
        double screenWidth=Screen.getPrimary().getVisualBounds().getWidth();
        double screenHeight=Screen.getPrimary().getVisualBounds().getHeight()-40;
        stage=s;
        stage.setTitle("Triumphous");

        //initialize scenes and other requirements
        //BoardIO.setUp();
        OpeningScene.openingSceneInit(stage,screenWidth,screenHeight);
        SettingsScene.settingsSceneInit(stage,screenWidth,screenHeight);
        GameSetupScene.gameSetupSceneInit(stage,screenWidth,screenHeight);
        GameSetupSceneClient.setupGameSetupSceneClient(stage,screenWidth,screenHeight);
        PlayersScene.setupPlayersScene(stage,screenWidth,screenHeight);
        //JoinScene.joinSceneInit(stage,screenWidth,screenHeight);
        MoveScene.setupMoveScene(stage,screenWidth,screenHeight);
        ParamsScene.setUpParamsScene(stage,screenWidth,screenHeight);
        ClientReadyScene.initializeClientReadyScene(stage,screenWidth,screenHeight);

//        stage.setScene(OpeningScene.openingScene(stage));//OpeningScene.openingScene(stage));
        stage.setScene(GameSetupScene.GameSetupScene());
        stage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }




}


