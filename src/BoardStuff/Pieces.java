package BoardStuff;

import java.util.HashMap;
import java.util.Map;

import BoardStuff.PieceTypes.*;

//A class for accessing piece parameters
//used with piecetypes
public class Pieces{

    private static Map<PieceTypes, Integer> moves = Map.of(
            PieceTypes.INFANTRY, 1,
            PieceTypes.CALVERY, 3,
            PieceTypes.ARCHER, 1,
            PieceTypes.TREBUCHET, 1,
            PieceTypes.FACTORY, 0,
            PieceTypes.BRIDGE,0,
            PieceTypes.CLOSEDDRAWBRIDGE,0,
            PieceTypes.OPENDRAWBRIDGE,0,
            PieceTypes.WALL,0,
            PieceTypes.CAPITAL,0
    );
    private static Map<PieceTypes, Integer> shoots = Map.of(
            PieceTypes.INFANTRY, 2,
            PieceTypes.CALVERY, 1,
            PieceTypes.ARCHER, 4,
            PieceTypes.TREBUCHET, 4,
            PieceTypes.FACTORY,0,
            PieceTypes.CLOSEDDRAWBRIDGE,0,
            PieceTypes.OPENDRAWBRIDGE,0,
            PieceTypes.WALL,0,
            PieceTypes.CAPITAL,0
    );
    public static int getMove(PieceTypes p){
        return moves.getOrDefault(p, 0);
    }

    public static int getShoot(PieceTypes p){
        return shoots.getOrDefault(p, 0);
    }



}