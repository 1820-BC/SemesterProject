package Scenes;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class OpeningScene {
    static Scene titlePage;
    static VBox titleLayoutOrd2;
    static Button host,join,single_player, settings, quit;
    static BorderPane titleLayoutOrd1;
    static Label title,version;
    static String backdrop="back2";
    static double width,height;
    static FlowPane paney;

    public static void openingSceneInit(Stage stage,double w, double h) {

        width=w;
        height=h;


        host = new Button("HOST");
        join = new Button("JOIN");
        single_player=new Button("SINGLE PLAYER");
        settings = new Button("OPTIONS");
        quit = new Button("QUIT");
        title = new Label("Triumphus");
        title.getStyleClass().add("titleText");

        version = new Label("Version Alpha 1.2.0");
        version.getStyleClass().add("infoText");


        //title screen button setup
        quit.setOnAction(e -> stage.close());
        settings.setOnAction(e->stage.setScene(SettingsScene.settingsScene(stage)));
        single_player.setOnAction((e->stage.setScene(GameSetupScene.GameSetupScene(false))));
        host.setOnAction(e->stage.setScene(PlayersScene.getPlayersScene()));
        join.setOnAction(e->stage.setScene(GameSetupSceneClient.GSSC()));
        //layout management

        //Vbox
        paney=new FlowPane();
        paney.setHgap(20);
        paney.setAlignment(Pos.TOP_RIGHT);
        paney.getChildren().addAll(host,join);

        titleLayoutOrd2 = new VBox(30);
        titleLayoutOrd2.getChildren().addAll(title,paney,single_player,settings, quit);
        titleLayoutOrd2.setAlignment(Pos.TOP_RIGHT);

        //boarder pane
        titleLayoutOrd1 = new BorderPane();

        titleLayoutOrd1.setPadding(new Insets(-25, 300, 0, 0));
        titleLayoutOrd1.setCenter(titleLayoutOrd2);


        titleLayoutOrd1.setBottom(version);
        //scene setup
        titlePage = new Scene(titleLayoutOrd1, width, height);

        titleLayoutOrd1.getStylesheets().add("Graphics/General.css");
        titleLayoutOrd1.getStyleClass().add(backdrop);

    }

    public static Scene openingScene(Stage stage){
        return titlePage;
    }



    //@param: num must be between 1 and 4
    public static void setBackdrop(int num){
        backdrop="back"+num;
        titleLayoutOrd1.getStyleClass().set(0,backdrop);

    }

    public static String getBackdrop(){
        return backdrop;
    }

    public static int getBackdropNumber(){
        char[] c=backdrop.toCharArray();
        return c[c.length-1];
    }
}
