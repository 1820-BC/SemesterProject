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
        Timeline fiveSecondsWonder = new Timeline(new KeyFrame(Duration.seconds(1), new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                if(!BoardIO.checkForWinCondition())
                    BoardIO.updateOponentBoard();
                    BoardIO.resetMoveMessage();
                try {
                        BoardIO.getUpdate();
                    } catch (IOException epps) {
                        epps.printStackTrace();
                    }

                }

        }));
        fiveSecondsWonder.setCycleCount(Timeline.INDEFINITE);
        fiveSecondsWonder.play();
    }


}
