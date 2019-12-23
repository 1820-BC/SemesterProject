package Rules;

import BoardStuff.PieceTypes;
import BoardStuff.Space;
import Pieces.Piece;

public class Rules {

    private static int turnSinceLastBuild=0;
    private static int turnSinceLastDrawbridge=0;
    private static int numberOfFactories=1;

    public static void addTurnSinceBuild(){
        turnSinceLastBuild++;
    }
    public static void addTurnsSinceLastDrawbridge(){
        turnSinceLastDrawbridge++;
    }
    public static void resetTurnsSinceLastBuild(){
        turnSinceLastBuild=0;
    }
    public static void addFactory(){
        numberOfFactories++;
    }
    public static void reduceFactory(){
        numberOfFactories--;
    }
    //discerns whether item can be shot
    public static boolean canShootAt(Space space, Piece attacker, Piece defender){
        return true;
    }

    //dicerns whether space can be moved into by piece
    public static boolean canMoveInto(Space space, Piece piece){
        if(space.getType().getLand().equals("rivers"))
        return true;
    }
    //the effect of a move
    public static void effectOfMoveInto(Space space, Piece piece){
        return;
    }

    //whether something can be built in a location
    public static boolean canBuildIn(Space space,PieceTypes piece){
        return true;
    }
    //The effect of a build
    public static void effectOfBuildOn(Space spaceFromPointer, PieceTypes newPieceType) {
    }
}
