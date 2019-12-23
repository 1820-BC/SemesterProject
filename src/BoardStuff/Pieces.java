package BoardStuff;

import java.util.HashMap;
import java.util.Map;

import BoardStuff.PieceTypes.*;

//A class for accessing piece parameters
//used with piecetypes
public class Pieces{

    private static Map<PieceTypes, Integer> moves = Map.of(
            PieceTypes.INFANTRY, 2,
            PieceTypes.CALVERY, 4,
            PieceTypes.ARCHER, 2,
            PieceTypes.TREBUCHET, 2,
            PieceTypes.FACTORY, 0
    );
    private static Map<PieceTypes, Integer> shoots = Map.of(
            PieceTypes.INFANTRY, 3,
            PieceTypes.CALVERY, 2,
            PieceTypes.ARCHER, 6,
            PieceTypes.TREBUCHET, 4,
            PieceTypes.FACTORY,0
    );
    public static int getMove(PieceTypes p){
        return moves.getOrDefault(p, 0);
    }

    public static int getShoot(PieceTypes p){
        return shoots.getOrDefault(p, 0);
    }



}