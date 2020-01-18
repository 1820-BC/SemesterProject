package Scenes;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class InstructionsScene {

    private static ScrollPane scrollPane;
    private static Label label;
    private static VBox box;
    private static Label title;
    private static Scene scene;
    private static String message;
    private static Button back;


    public static void setUpInstructionScene(Stage stage, double width, double height){
        scrollPane = new ScrollPane();
        label=new Label();
        label.setMaxWidth(900);
        label.setWrapText(true);
        message="";
        try {
            File directory = new File("");

            Scanner s=new Scanner(new FileInputStream(new File(directory.getAbsolutePath() + "/src/Scenes/dir.txt")));

            while(s.hasNext()){
                message+=s.nextLine()+"\n";
            }
            label.setText(message);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        back=new Button("BACK");
        back.setOnAction(e->{
            stage.setScene(OpeningScene.openingScene(stage));
        });
        back.setPrefSize(50,50);
        scrollPane.setContent(label);


        title=new Label("HOW TO PLAY");
        title.setId("title");
//        title.setId("title");
        box=new VBox(10);
        box.getChildren().addAll(title,scrollPane,back);
        box.setAlignment(Pos.CENTER);
        box.setPadding(new Insets(100,100,100,100));
        scene=new Scene(box,width,height);
        scene.getStylesheets().add("/Graphics/Instructions.css");

    }

    public static Scene getScene(){
        return scene;
    }


}
