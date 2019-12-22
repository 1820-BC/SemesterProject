package Scenes;

import BoardStuff.BoardIO;
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
    private static ListView lis;
    private static Label text;
    private static FlowPane flower;
    private static VBox vbox;
    private static Button refresh;
    private static Scene scene;
    public static void initializeClientReadyScene(Stage stage,double width, double height){

        CanvasPane=new ScrollPane();
        lis=new ListView();
        text=new Label("Players and Map");
        refresh=new Button("REFRESH MAP");

        CanvasPane.setContent(BoardIO.getCanvas());

        refresh.setOnAction(e->{
            try {
                BoardIO.getIO().recWholeCanvas();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });

        flower=new FlowPane();
        flower.setHgap(20);
        flower.getChildren().addAll(CanvasPane,lis);
        vbox=new VBox(20);
        vbox.getChildren().addAll(text,flower,refresh);
        scene = new Scene(vbox,height,width);


    }

    public static Scene getClientReadyScene(){
        return scene;
    }


}
