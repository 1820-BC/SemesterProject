package Multiplayer;

import BoardStuff.BoardIO;
import Scenes.ClientReadyScene;
import Scenes.MoveScene;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.util.Duration;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;

public class WaitScreenUpdationThread {
    Socket s;
    BufferedReader in;
    PrintStream out;
    Timeline updator;

    public WaitScreenUpdationThread(Socket s, BufferedReader dis, PrintStream dos) {
        this.s = s;
        in = dis;
        out = dos;
    }

    public void start() {
        updator = new Timeline(new KeyFrame(Duration.seconds(0.001), new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                try {
                    if(in.readLine().equals("RUN GAME")){
                        ClientReadyScene.nextScene();
                        BoardIO.beginUpdationThread();
                        updator.stop();
                    }
                    else{
                        BoardIO.getIO().recWholeCanvas();
                        BoardIO.drawBoardNew();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                } {


            }

        }}));
        updator.setCycleCount(Timeline.INDEFINITE);
        updator.play();
    }


}


