package Scenes;


import BoardStuff.BoardIO;
import Multiplayer.HostIO;
import Multiplayer.MultiplayerIO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;
import Functions.Save;
import Multiplayer.ClientIO;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.io.IOException;
import java.util.ArrayList;
//import javafx.stage.Stage;

public class PlayersScene {

    private static Button getPlayers,back;
    private static VBox players;
    private static VBox buttons;
    private static ListView<String> lis;
    private static ArrayList<String> connected;
    private static FlowPane flower;
    private static Scene scene;

    public static void setupPlayersScene(Stage stage,double width,double height) throws IOException {
        HostIO host=new HostIO();
        flower=new FlowPane();
        flower.setHgap(50);
        flower.setAlignment(Pos.CENTER);
        getPlayers=new Button("ACCEPT PLAYERS");
        back=new Button("BACK");
        players=new VBox(30);
        players.setAlignment(Pos.CENTER);
        buttons=new VBox(30);
        connected=new ArrayList<>();
        lis=new ListView();
//        ListView<String> list = new ListView<String>();

        ObservableList<String> items = FXCollections.observableArrayList(connected);
        lis.setItems(items);

        getPlayers.setOnAction(e->{
            try {
                host.beginConnectionProcedure();
                String name=host.getMessage();
                items.add(name);
                BoardIO.setIO(host);
            } catch (IOException ex) {
                ex.printStackTrace();
            }

        });
        back.setOnAction(e->{
            stage.setScene(GameSetupScene.GameSetupScene());
        });
        players.getChildren().addAll(lis);
        buttons.getChildren().addAll(getPlayers,back);
        flower.getChildren().addAll(players,buttons);
        scene=new Scene(flower,width,height);
        flower.setId("backgroundForPlayerScene");
        scene.getStylesheets().add("Graphics/General.css");
    }


    public static Scene getPlayersScene() {
        return scene;
    }
}
