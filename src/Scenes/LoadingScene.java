package Scenes;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class LoadingScene {
    private static Label loading;
    private static ProgressBar bar;
    private static Scene LoadingScene;
    private static VBox box;
    private static double barCurr=0;
    private static double change;

    public static void setupLoadingScene(Stage stage, double width, double height,int num){
        change=100/num;
        loading = new Label();
        loading.setText("LOADING");

        bar=new ProgressBar();
        bar.setProgress(0);


        box=new VBox(50);
        box.getChildren().add(loading);
        box.getChildren().add(bar);
        box.setAlignment(Pos.CENTER);


        LoadingScene=new Scene(box,width,height);
        LoadingScene.getStylesheets().add("/Graphics/LoadingScene.css");
    }
    public static void increaseProgressBar(){
        barCurr+=change;
        bar.setProgress(barCurr);

    }
    public static Scene getScene(){
        return LoadingScene;
    }


}
