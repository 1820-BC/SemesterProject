package Multiplayer;

import BoardStuff.BoardIO;
import BoardStuff.Move;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintStream;
import java.util.LinkedList;
import java.util.Queue;

//general MultiplayerIO class that allows for multiplayer. It is an abstraction of the other multiplayer classes that allows for better cross class functionality
public abstract class MultiplayerIO {

      static boolean host;
      static String port;

      protected PrintStream os;
      protected BufferedReader in;

//      private MultiplayerIO(){return;}

//      public MultiplayerIO() {

//      }

//      protected MultiplayerIO(String ip, int port){
//
//      }

      protected void setHost(){
          host=true;
//          port=new HostIO(IP);
      }
      protected void setClient(){

          host=false;
//          port=new ClientIO(IP);
      }

      public boolean isHost(){
          return host;
      }

//      public abstract Move getMovePlayer1();





    public void sendAction(String moves){
        os.println(moves);
    }
    public void recieveAction() throws IOException {
        if(!in.ready()){
            return;
        }
        String updater=in.readLine();
//        System.out.println(updater);
        if(updater==(null)){
//            System.out.println("nullified");
            return;
        }
        if(updater.equals("WIN")){
//            System.out.println("AWIN");
            BoardIO.endSystemForEnemyWin();
        }
        if(updater.equals("")){
            return;
        }
        else {
//            System.out.println(updater);
            updater = updater.substring(1);
            String[] updates = updater.split("-");
            for (String thingToUpdate : updates) {
                String[] parts = thingToUpdate.split(",");
                BoardIO.setSquare(parts[0], parts[1], parts[2],parts[3]);
            }
        }
    }


    public void sendWholeCanvas(String wholeBoard){
        os.println(wholeBoard);
    }

    public boolean recWholeCanvas() throws IOException {
        String board=in.readLine();
        if(board.equals("RUN GAME")){
            return true;
        }
        BoardIO.drawNewBoardFromString(board);

        return false;
    }

    public abstract void sendOkay();

    public abstract void runUpdationThread();

    public abstract void endUpdationThread();

    public abstract void sendForWin();

    public abstract void reset();

//    public abstract void setActionListener();
}

