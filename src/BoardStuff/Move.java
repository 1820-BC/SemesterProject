package BoardStuff;



//Move class that stores all move parameters and can be ran in BoardIO
public class Move {
    private int x;
    private int y;
    public Vector vector=new Vector();
    Moves type=Moves.MOVE;
    PieceTypes piece;
    PieceTypes newPieceType;

    public Move(int x, int y){
        vector=new Vector();
        piece=BoardIO.getPieceAt(x,y);
        System.out.println("Piece:" +piece);
        this.x=x;
        this.y=y;
    }
    public Move(){
        vector=new Vector();
        piece=PieceTypes.EMPTY;
    }


    public void setNewPieceType(PieceTypes p){
        newPieceType=p;
    }

    public Vector getVector() {
        return vector;
    }

    public void setVector(int x, int y){
        vector.setVals(x,y);
    }
    public void setVectorSize(PieceTypes o){vector.setFromPiece(o);}
    public void setType(Moves type) {
        this.type = type;
    }

    public void setVector(PieceTypes type) {
        vector.setFromPiece(type);
    }

    public String getType() {
        switch (type) {
            case MOVE:
                return "MOVE";
            case SHOOT:
                return "SHOOT";
            case BUILD:
                return "BUILD";
        }
        return "NO MOVE";
    }
    public Moves getPT(){
        return this.type;
    }

    public String toString(){
        return getType()+": "+"__("+x+","+y+")"+"->"+vector.toString();
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getdY() {
        return vector.getdY();
    }
    public int getdX(){
        return vector.getdX();
    }

    public PieceTypes getNewPieceType() {
        return newPieceType;
    }
    //    public



}
