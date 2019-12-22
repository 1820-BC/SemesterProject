package Multiplayer;

import BoardStuff.BoardIO;

import java.io.BufferedReader;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;

public class UpdationThread extends Thread {

    Socket s;
    BufferedReader in;
    PrintStream out;

    public UpdationThread(Socket s, BufferedReader dis, PrintStream dos) {
        this.s = s;
        in = dis;
        out = dos;
    }

    @Override
    public void run() {
        while(!BoardIO.checkForWinCondition()){
            BoardIO.updateOponentBoard();
            try {
                BoardIO.getUpdate();
            } catch (IOException epps) {
                epps.printStackTrace();
            }

        }
    }


}
