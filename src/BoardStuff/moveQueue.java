package BoardStuff;

import java.util.LinkedList;
import java.util.Queue;

public class moveQueue {

    static Queue<Move> player1=new LinkedList();

    public static Move getMovePlayer1(){
        try {
            return player1.remove();
        }
        catch(Exception e){
            return null;
        }
    }

    public static void addToQueue(Move move){
        player1.add(move);
    }




}
