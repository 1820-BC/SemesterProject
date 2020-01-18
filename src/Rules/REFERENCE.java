package Rules;

import BoardStuff.PieceTypes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;

public class REFERENCE {

    public static final int MAX_TURNS_BETWEEN_BUILDS=0;

    public static final int TURNS_BETWEEN_DRAWBRIDGE=10;
    public static final ArrayList UNKILLABLE =new ArrayList(Arrays.asList(new PieceTypes[]{
            PieceTypes.WALL,
//            PieceTypes.OPENDRAWBRIDGE,
//            PieceTypes.CLOSEDDRAWBRIDGE,
            PieceTypes.BARGE,
            PieceTypes.FACTORY,
            PieceTypes.VILLAGE}));

    public static final ArrayList KILLIBLEBYTREBUCHET=new ArrayList(Arrays.asList(new PieceTypes[]{
            PieceTypes.WALL,
            PieceTypes.FACTORY,
//            PieceTypes.OPENDRAWBRIDGE,
//            PieceTypes.CLOSEDDRAWBRIDGE
    }));
//    public static final int

}
