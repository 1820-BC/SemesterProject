package Rules;

import BoardStuff.PieceTypes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;

public class REFERENCE {

    public static final int MAX_TURNS_BETWEEN_BUILDS=30;

//    public static final int TURNS_BETWEEN_DRAWBRIDGE=30;
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
    public static final int EFFECT_OF_FACTORY = 3;
    public static final int EFFECT_OF_MOUNTAINS_ON_SHOT = 1;
    public static final int EFFECT_OF_PLAINS_ON_SHOT = 0;
    public static final int EFFECT_OF_FORESTS_ON_SHOT = 0;
    public static final int EFFECT_OF_MOUNTAINS_ON_MOVE= 0;
    public static final int EFFECT_OF_PLAINS_ON_MOVE = 1;
    public static final int EFFECT_OF_FORESTS_ON_MOVE= 0;

//    public static final int

}
