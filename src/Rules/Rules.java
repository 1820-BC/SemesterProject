package Rules;

import BoardStuff.BoardIO;
import BoardStuff.PieceTypes;
import BoardStuff.Space;
import BoardStuff.TerrainTypes;
import Pieces.Piece;
import Pieces.Teams;

public class Rules {

    private static int turnSinceLastBuild=0;
    private static int turnSinceLastDrawbridge=0;
    private static int numberOfFactories=0;
    private static int amountOfFactories=0;

    public static void addTurnSinceBuild(){
        turnSinceLastBuild-=1;
    }
    public static void addTurnsSinceLastDrawbridge(){
        turnSinceLastDrawbridge++;
    }
    public static void resetTurnsSinceLastBuild(){
        turnSinceLastBuild=REFERENCE.MAX_TURNS_BETWEEN_BUILDS-numberOfFactories;
    }
    public static void addFactory(){
        numberOfFactories+=REFERENCE.EFFECT_OF_FACTORY;
        amountOfFactories++;
    }
    public static void reduceFactory(){
        numberOfFactories-=REFERENCE.EFFECT_OF_FACTORY;
        amountOfFactories--;
    }

    //if either of the below methods are false, the thing is not killed. However, if canShootThrough is true, the bullet continues to move


    //does the piece die or does the bullet keep moving, checked after canShootThrough
    public static boolean canKill(Space space, Piece attacker, Piece defender){
        System.out.println(space.getPieceType());
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
//        else if(BoardIO.)
        else if(BoardIO.teamCheck(defender)&&defender.getPieceType()!=PieceTypes.EMPTY){
            System.out.println("canShootThrough 2");
            return false;
        }
        else if(space.getType().getLand().equals("village")){
            return false;
        }
        //defender is passable (enemy check only must happen once
        else if(REFERENCE.UNKILLABLE.contains(defender.getType())&&attacker.getPieceType()!=PieceTypes.TREBUCHET){
            System.out.println("canShootThrough 3");
            return false;
        }
        else if (attacker.getPieceType()==PieceTypes.TREBUCHET&&REFERENCE.KILLIBLEBYTREBUCHET.contains(defender.getType())){
            System.out.println("canShootThrough OKAY BY TREBUCHET: "+defender.getType());
            return true;
        }
        else {
            System.out.println("canShootThrough OKAY");
            return true;
        }
    }

    //dicerns whether space can be moved into by piece
    public static boolean canMoveInto(Space space, Piece piece,boolean multiplayer){
        // if moving onto allowed spaces
        if (space.getPieceType() == PieceTypes.CAPITAL) {
            if(multiplayer){
                BoardIO.win();
            }
            else{
                BoardIO.softwin();
            }
            return false;
        }
        if(space.getPiece().getType()==PieceTypes.EMPTY){
            System.out.println("EMPTY");
            if(space.getType().getLand().equals("rivers")){
//                System.out.println("move to rivers");
                if(piece.getPieceType()==PieceTypes.BARGE||piece.getPieceType()==PieceTypes.BATTLE_SHIP){
//                    System.out.println("YOLOOYY");
                    return true;
                }
                return false;
            }
            if(piece.getPieceType()==PieceTypes.BARGE||piece.getPieceType()==PieceTypes.BATTLE_SHIP){
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

    public static int getNumberOfFactories(){
        return numberOfFactories;
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
            System.out.println("rivers");
            if(piece==PieceTypes.BARGE||piece==PieceTypes.BATTLE_SHIP){
                return true;
            }
            return false;
        }
        if(piece==PieceTypes.BARGE||piece==PieceTypes.BATTLE_SHIP){
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
        moves=Math.round(moves);
        return moves;
    }



    public static int effectOfTerrainOnShot(Space s){
        TerrainTypes t=s.getTerrainType();
        if(t==TerrainTypes.FORESTS){
            return REFERENCE.EFFECT_OF_FORESTS_ON_SHOT;
        }
        else if(t==TerrainTypes.MOUNTAINS){
            return REFERENCE.EFFECT_OF_MOUNTAINS_ON_SHOT;
        }
        else if(t==TerrainTypes.RIVERS){
            return REFERENCE.EFFECT_OF_WATER_ON_SHOT;
        }
        else{
            return REFERENCE.EFFECT_OF_PLAINS_ON_SHOT;
        }
    }
    public static int effectOfTerrainOnMove(Space s){
        TerrainTypes t=s.getTerrainType();
        if(t==TerrainTypes.FORESTS){
            return REFERENCE.EFFECT_OF_FORESTS_ON_MOVE;
        }
        else if(t==TerrainTypes.MOUNTAINS){
            return REFERENCE.EFFECT_OF_MOUNTAINS_ON_MOVE;
        }
        else if(t==TerrainTypes.RIVERS){
            return REFERENCE.EFFECT_OF_WATER_ON_MOVE;
        }
        else{
            return REFERENCE.EFFECT_OF_PLAINS_ON_MOVE;
        }
    }

    public static boolean winState(int newX, int newY, Teams player) {
        if(player==Teams.Blue){
            if(newX==0&&newY==0){
                return true;
            }
            return false;
        }
        else{
            if(newX==BoardIO.getBoard().getSize()&&newY==BoardIO.getBoard().getSize()){
                return true;
            }
            return false;
        }

    }
}
