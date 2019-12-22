package Scenes;

import BoardStuff.BoardIO;
import Functions.Save;
import Multiplayer.ClientIO;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.IOException;

public class GameSetupSceneClient {

    static Scene scene;
    static Label text;
    static VBox box;
    static TextField field;
    static Button join,back;
    static String backdrop="back2";

    public static void setupGameSetupSceneClient(Stage stage, double width,double height){

        box=new VBox(15);
        field=new TextField();
        back=new Button("BACK");
        join=new Button("JOIN");
        text=new Label("Game Setup");
        text.setId("title");
        field.setAlignment(Pos.CENTER_RIGHT);


        field.setPromptText("IP ADDRESS");
        back.setOnAction(e-> {
                    stage.setScene(OpeningScene.openingScene(stage));
                }
                );
        join.setOnAction(e->{

                    if(field.getText().equals("")){
                        return;
                    }

                    try {
                        ClientIO clientIO = new ClientIO(field.getText());

                        clientIO.sendName();
                        BoardIO.setIO(clientIO);

                    } catch (IOException ex) {
                        field.setText("Invalid IP");
                        return;
                    }
                    stage.setScene(ClientReadyScene.getClientReadyScene());
//                    Save.save(clientIO);
//                    assert clientIO != null;
                }
                );

        box=new VBox(50);
        box.setPadding(new Insets(0, 250, 0, 800));
        box.setAlignment(Pos.CENTER_RIGHT);
        box.getChildren().addAll(text,field,join,back);
        box.getStylesheets().add("Graphics/General.css");
        box.getStyleClass().add(backdrop);
        scene=new Scene(box,width,height);

    }


    public static Scene GSSC() {

        return scene;

    }





}
