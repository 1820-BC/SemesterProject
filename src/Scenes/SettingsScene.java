package Scenes;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class SettingsScene {

    static Scene settingsScene;
    static FlowPane d,setter;
    static ComboBox<String> delete;
    static Button back, del, set;
    static VBox settingsMenu;
    static BorderPane settingsMenuOrd1;
    static double width,height;
    static TextField textField;



    public static void settingsSceneInit(Stage stage,double w,double h) {

        width=w;
        height=h;

        settingsMenu=new VBox(30);
        settingsMenu.setAlignment(Pos.CENTER_RIGHT);



        //button and control init
        back=new Button("BACK");
        back.setOnAction(e -> stage.setScene(OpeningScene.openingScene(stage)));
        del=new Button("DELETE");
        del.setId("danger");
        delete=new ComboBox<>();
        set=new Button("SET");
        set.setId("go");
        textField=new TextField();
        textField.setPromptText("Enter New Name");


        del.setOnAction(e->{

            File f=new File("D://BoardGame/src/SavedMaps/"+delete.getValue());
            f.delete();
            delete.getItems().remove(delete.getValue());

        });
        set.setOnAction(e->{
            BufferedWriter b= null;
            try {
                b = new BufferedWriter(new FileWriter(new File("src/Settings/Name.txt")));
//                b.write(textField.getText());
//                b.newLine();
                b.write(textField.getText());
                b.flush();
            } catch (IOException ex) {
                ex.printStackTrace();
            }

        });







        d=new FlowPane();
        d.setHgap(10);
        d.getChildren().addAll(delete,del);
        d.setAlignment(Pos.CENTER_RIGHT);

        setter=new FlowPane();
        setter.setHgap(10);
        setter.getChildren().addAll(textField,set);
        setter.setAlignment(Pos.CENTER_RIGHT);

        settingsMenu.getChildren().addAll(setter,d,back);
        settingsMenu.getStylesheets().add("Graphics/General.css");
        settingsMenu.getStyleClass().add(OpeningScene.getBackdrop());
        settingsMenu.setPadding(new Insets(30,300,30,30));



        settingsScene=new Scene(settingsMenu,width,height);



    }

    public static Scene settingsScene(Stage stage) {
        File f = new File("D://BoardGame/src/SavedMaps/");
        if(f.listFiles()==(null)){
//            System.out.println("YOTE");
            return settingsScene;
        }
        delete.getItems().clear();
        for(File s:f.listFiles()){
            delete.getItems().add(s.getName());
        }
        return settingsScene;
    }


}
