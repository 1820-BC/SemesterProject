package Scenes;

import BoardStuff.BoardIO;
import BoardStuff.Players;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;

public class VictoryScene {
    private static Button returnToHome;
    private static Label win;
    private static ImageView image;
    private static int a=1;
    private static int numAni=5;
    private static VBox box;
    static Scene scene;
    private static Timeline fiveSecondsWonder = new Timeline(new KeyFrame(Duration.seconds(0.1), new EventHandler<ActionEvent>() {

        @Override
        public void handle(ActionEvent event) {

//            incrementA();Image image = new Image("my/res/flower.png", 100, 100, false, false);
            image.setImage(new Image("/Textures/Pieces/RedWin"+a+".png"));

        }

    }));
    private static void incrementA(){
        if(a==numAni){
            a=1;
        }
        else {
            a++;
        }

    }


    public static void setupVictoryScene(Stage stage, double width, double height){
        fiveSecondsWonder.setCycleCount(Timeline.INDEFINITE);
//        image=new Label();
        image=new ImageView();
        win=new Label();
//        image.setMinSize(100,100);
        image.setFitHeight(100);
        image.setFitWidth(100);
        returnToHome=new Button("RETURN TO HOME");
        returnToHome.setOnAction(e->{
            stop();
            stage.setScene(OpeningScene.openingScene(stage));
        });
        box=new VBox(30);
        box.getChildren().addAll(win,image,returnToHome);
        scene=new Scene(box,width,height);
        scene.getStylesheets().add("/Graphics/VictoryScene.css");
    }
    public static Scene getScene(){
        String message;
        if(BoardIO.getWinner()== Players.ME){
            message="You Won!";
            box.setId("boxWin");
            win.setId("labWin");
        }
        else{
            message="You Lost";
            box.setId("boxLoss");
            win.setId("labLoss");
        }
        win.setText(message);
        fiveSecondsWonder.play();
        return scene;
    }
    public static void stop(){
        fiveSecondsWonder.stop();
    }
}



