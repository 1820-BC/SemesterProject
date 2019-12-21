package Functions;


import Multiplayer.MultiplayerIO;


//a static wrapper class for the multiplayer IO. Not used because BoardIO took this job over
//Kept just in case it is required
public class Save {

    static MultiplayerIO c;


    public static void save(MultiplayerIO socketSituation){
        c=socketSituation;
    }

    public static MultiplayerIO get(){
        return c;
    }

}
