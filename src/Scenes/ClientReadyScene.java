package Scenes;

import BoardStuff.BoardIO;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

//import java.awt.*;

public class ClientReadyScene {

    private static ScrollPane CanvasPane;

    private static Label text;
    private static FlowPane flower;
    private static VBox vbox;
    private static Button refresh;
    private static Scene scene;
    private static Stage Stage;
    public static void initializeClientReadyScene(Stage stage,double width, double height){
        Stage=stage;
        CanvasPane=new ScrollPane();
//        lis=new ListView();
        text=new Label("Current Map");
        refresh=new Button("REFRESH MAP");

        CanvasPane.setContent(BoardIO.getCanvas());
        refresh.setOnAction(e->{
            try {
                if(BoardIO.getIO().recWholeCanvas()){
                    stage.setScene(MoveScene.getScene(true));
                    BoardIO.beginUpdationThread();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            BoardIO.drawBoardNew();

        });
        CanvasPane.setMaxSize(1000,1000);
        CanvasPane.setFitToHeight(true);
        CanvasPane.setFitToHeight(true);
        flower=new FlowPane();
        flower.setHgap(20);
        flower.getChildren().addAll(CanvasPane);
        flower.setAlignment(Pos.CENTER);
        vbox=new VBox(20);
        vbox.setAlignment(Pos.CENTER);
        vbox.getChildren().addAll(text,flower,refresh);
        scene = new Scene(vbox,width,height);
        scene.getStylesheets().add("/Graphics/ControlBoard.css");

    }

    public static Scene getClientReadyScene(){
//        BoardIO.getIO().runWaitScreenUpdationThread();
        return scene;
    }


    public static void nextScene() {
        Stage.setScene(MoveScene.getScene(true));
    }
}
