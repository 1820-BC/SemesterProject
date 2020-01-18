package Rules;

import BoardStuff.BoardIO;
import Scenes.MoveScene;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.util.Duration;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class TimerThread {
    private static Timeline thread;

    //    @Override
    public static void stop(){
        thread.stop();
    }
    public static void run() {
        thread = new Timeline(new KeyFrame(Duration.seconds(1), new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {

                Rules.addTurnSinceBuild();
                MoveScene.updateClock();
            }


        }));
        thread.setCycleCount(Timeline.INDEFINITE);
        thread.play();
    }
}