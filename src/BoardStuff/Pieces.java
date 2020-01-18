package BoardStuff;

import java.util.Map;
import Pieces.*;

//A class for accessing piece parameters
//used with piecetypes
public class Pieces{

    private static Map<PieceTypes, Integer> moves = Map.of(
            PieceTypes.INFANTRY, 2,
            PieceTypes.CALVERY, 4,
            PieceTypes.ARCHER, 1,
            PieceTypes.TREBUCHET, 1,
            PieceTypes.FACTORY, 0,
            PieceTypes.BARGE, 1,
            PieceTypes.BATTLE_SHIP,1,
//            PieceTypes.OPENDRAWBRIDGE,0,
            PieceTypes.WALL,0,
            PieceTypes.CAPITAL,0
    );
    private static Map<PieceTypes, Integer> shoots = Map.of(
            PieceTypes.INFANTRY, 1,
            PieceTypes.CALVERY, 1,
            PieceTypes.ARCHER, 3,
            PieceTypes.TREBUCHET, 2,
            PieceTypes.FACTORY,0,
            PieceTypes.BARGE, 0,
            PieceTypes.BATTLE_SHIP,4,
//            PieceTypes.OPENDRAWBRIDGE,0,
            PieceTypes.WALL,0,
            PieceTypes.CAPITAL,0
    );
    public static int getMove(PieceTypes p){
        return moves.getOrDefault(p, 0);
    }

    public static int getShoot(PieceTypes p){
        return shoots.getOrDefault(p, 0);
    }


    public static Piece getPieceFrom(PieceTypes p,Teams team) {
//        setPiece(new Infantry());
//        System.out.println("p: "+p);


        if (p == PieceTypes.INFANTRY) {
            return new Infantry(team);
        }
        else if(p==PieceTypes.EMPTY){
            return new EmptyPiece();
        }
        else if(p==PieceTypes.ARCHER){
            return new Archer(team);
        }
        else if(p==PieceTypes.CALVERY){
            return new Calvery(team);
        }
        else if(p==PieceTypes.TREBUCHET){
            return new Trebuchet(team);
        }
        else if(p==PieceTypes.FACTORY){
            return new Factory(team);
        }
        else if(p==PieceTypes.WALL){
            return new Wall(team);
        }
        else if(p==PieceTypes.BATTLE_SHIP){
            return new BattleShip(team);
        }
        else if(p==PieceTypes.BARGE){
            return new Barge(team);
        }
        else if(p==PieceTypes.CAPITAL){
            return new Capital(team);
        }
        return null;
    }
}