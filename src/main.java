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
        LoadingScene.setupLoadingScene(stage,screenWidth,screenHeight,10);
        stage.setScene(LoadingScene.getScene());
        stage.show();
        //initialize scenes and other requirements
        InstructionsScene.setUpInstructionScene(stage,screenWidth,screenHeight);
        LoadingScene.increaseProgressBar();
        OpeningScene.openingSceneInit(stage,screenWidth,screenHeight);
        LoadingScene.increaseProgressBar();
        SettingsScene.settingsSceneInit(stage,screenWidth,screenHeight);
        LoadingScene.increaseProgressBar();
        GameSetupScene.gameSetupSceneInit(stage,screenWidth,screenHeight);
        LoadingScene.increaseProgressBar();
        GameSetupSceneClient.setupGameSetupSceneClient(stage,screenWidth,screenHeight);
        LoadingScene.increaseProgressBar();
        PlayersScene.setupPlayersScene(stage,screenWidth,screenHeight);
        LoadingScene.increaseProgressBar();
        MoveScene.setupMoveScene(stage,screenWidth,screenHeight);
        LoadingScene.increaseProgressBar();
        ParamsScene.setUpParamsScene(stage,screenWidth,screenHeight);
        LoadingScene.increaseProgressBar();
        ClientReadyScene.initializeClientReadyScene(stage,screenWidth,screenHeight);
        LoadingScene.increaseProgressBar();
        VictoryScene.setupVictoryScene(stage,screenWidth,screenHeight);
        LoadingScene.increaseProgressBar();

//        stage.setScene(OpeningScene.openingScene(stage));//OpeningScene.openingScene(stage));
        stage.setScene(OpeningScene.openingScene(stage));

    }


    public static void main(String[] args) {
        launch(args);
    }




}


