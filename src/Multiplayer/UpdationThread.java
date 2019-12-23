package Multiplayer;

import BoardStuff.BoardIO;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.util.Duration;

import java.io.BufferedReader;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;

public class UpdationThread {

    Socket s;
    BufferedReader in;
    PrintStream out;

    public UpdationThread(Socket s, BufferedReader dis, PrintStream dos) {
        this.s = s;
        in = dis;
        out = dos;
    }

    public void start() {
        Timeline fiveSecondsWonder = new Timeline(new KeyFrame(Duration.seconds(5), new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                System.out.println("this is called every 5 seconds on UI thread");
            }
        }));
        fiveSecondsWonder.setCycleCount(Timeline.INDEFINITE);
        fiveSecondsWonder.play();
    }


}
