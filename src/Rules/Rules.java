package Rules;

import BoardStuff.BoardIO;
import BoardStuff.PieceTypes;
import BoardStuff.Space;
import Pieces.Piece;

public class Rules {

    private static int turnSinceLastBuild=REFERENCE.MAX_TURNS_BETWEEN_BUILDS;
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

    //if either of the below methods are false, the thing is not killed. However, if canShootThrough is true, the bullet continues to move


    //does the piece die or does the bullet keep moving, checked after canShootThrough
    public static boolean canKill(Space space, Piece attacker, Piece defender){
        if(defender.getPieceType()==PieceTypes.EMPTY){
            return false;
        }
        return true;
    }

    //discerns whether the bullet may continue moving through a space
    public static boolean canShootThrough(Space space, Piece attacker, Piece defender){
        if(!BoardIO.teamCheck(attacker)){
            return false;
        }
        //defender is kilable (enemy check only must happen once
        else if(REFERENCE.UNKILLABLE.contains(defender.getType())){
            return false;
        }
        //can not go through factory, wall, closed draw bridge, or village types
        else if(space.getPieceType()==PieceTypes.WALL||space.getPieceType()==PieceTypes.FACTORY||space.getPieceType()==PieceTypes.CLOSEDDRAWBRIDGE||space.getPieceType()==PieceTypes.VILLAGE){
            return false;
        }
        return true;
    }

    //dicerns whether space can be moved into by piece
    public static boolean canMoveInto(Space space, Piece piece){
        // if moving onto allowed spaces
        if(space.getPiece().getType()!=PieceTypes.OPENDRAWBRIDGE||space.getPiece().getType()!=PieceTypes.BRIDGE||space.getPiece().getType()==PieceTypes.EMPTY){
            if(space.getType().equals("rivers")){
                return false;
            }
            return true;
        }

        return false;
    }
    //the effect of a move
    public static void effectOfMoveInto(Space space, Piece piece){
        if(space.getPiece().getType()==PieceTypes.FACTORY){
            addFactory();
        }
        return;
    }

    //whether something can be built in a location
    public static boolean canBuildIn(Space space,PieceTypes piece){
        if(turnSinceLastBuild<REFERENCE.MAX_TURNS_BETWEEN_BUILDS-numberOfFactories){
            return false;
        }
        //can not build on non-empty spaces
        if(space.getPiece().getType()!=PieceTypes.EMPTY){
            return false;
        }
        //can not build in water
        if(space.getType().getLand().equals("rivers")){
            return false;
        }
        return true;
    }
    //The effect of a build
    public static void effectOfBuildOn(Space spaceFromPointer, PieceTypes newPieceType) {
        addFactory();
    }

    public static void effectOfPieceChange(Space spaceFromPointer, Piece pieceFrom) {
        if(spaceFromPointer.getPiece().getType()==PieceTypes.FACTORY){
            reduceFactory();
        }
    }
}
