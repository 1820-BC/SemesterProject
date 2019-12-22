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
      static Queue<Move> player1=new LinkedList();
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


      public abstract void addToQueue(Move currentMove);


    public void sendAction(String moves){
        os.println(moves);
    }
    public void recieveAction() throws IOException {
        String updater=in.readLine();
        updater=updater.substring(1);
        String[] updates=updater.split("-");
        for(String thingToUpdate:updates){
            String[] parts=thingToUpdate.split(",");
            BoardIO.setSquare(parts[0],parts[1],parts[2]);

        }
    }
    public Move getMovePlayer1(){
        try {
            return player1.remove();
        }
        catch(Exception e){
            return null;
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


//    public abstract void setActionListener();
}

