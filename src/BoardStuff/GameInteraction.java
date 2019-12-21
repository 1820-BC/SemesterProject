package BoardStuff;

import Multiplayer.MultiplayerIO;

import javax.print.attribute.standard.QueuedJobCount;
import java.util.LinkedList;
import java.util.Queue;

//not useful class that is kept for possible implementation later
public class GameInteraction {


    static MultiplayerIO IO;

    public static void setIO(MultiplayerIO Io){
        IO=Io;
    }
    public static MultiplayerIO getIO(){
        return IO;
    }




}
