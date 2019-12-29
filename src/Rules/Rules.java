package Rules;

import BoardStuff.BoardIO;
import BoardStuff.PieceTypes;
import BoardStuff.Space;
import Pieces.Piece;

public class Rules {

    private static int turnSinceLastBuild=0;
    private static int turnSinceLastDrawbridge=0;
    private static int numberOfFactories=0;

    public static void addTurnSinceBuild(){
        turnSinceLastBuild--;
    }
    public static void addTurnsSinceLastDrawbridge(){
        turnSinceLastDrawbridge++;
    }
    public static void resetTurnsSinceLastBuild(){
        turnSinceLastBuild=REFERENCE.MAX_TURNS_BETWEEN_BUILDS-numberOfFactories;
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
        if(space.getPieceType()==PieceTypes.EMPTY){
            return false;
        }
        return true;
    }

    //discerns whether the bullet may continue moving through a space
    public static boolean canShootThrough(Space space, Piece attacker, Piece defender){
        if(!BoardIO.teamCheck(attacker)){
            System.out.println("canShootThrough 1");
            return false;
        }
        else if(BoardIO.teamCheck(defender)&&defender.getPieceType()!=PieceTypes.EMPTY){
            System.out.println("canShootThrough 2");
            return false;
        }
        //defender is passable (enemy check only must happen once
        else if(REFERENCE.UNKILLABLE.contains(defender.getType())){
            System.out.println("canShootThrough 3");
            return false;
        }
        else {
            System.out.println("canShootThrough OKAY");
            return true;
        }
    }

    //dicerns whether space can be moved into by piece
    public static boolean canMoveInto(Space space, Piece piece){
        // if moving onto allowed spaces
        if(space.getPiece().getType()==PieceTypes.OPENDRAWBRIDGE||space.getPiece().getType()==PieceTypes.BRIDGE||space.getPiece().getType()==PieceTypes.EMPTY){
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
    public static boolean canBuildIn(int y,Space space,PieceTypes piece){
        //must build in your sector
        if(!BoardIO.correctTeamFromSector(y)){
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
    public static boolean readyForBuild() {
        if(getTurnSinceLastBuild()==0){
            return true;
        }
        return false;
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

    //getters

    public static int getTurnSinceLastBuild(){
        int moves=turnSinceLastBuild;
        if(moves<=0){
            return 0;
        }
        return moves;
    }


}
