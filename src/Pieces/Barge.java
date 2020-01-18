package Pieces;

import BoardStuff.PieceTypes;
import javafx.scene.image.Image;

public class Barge extends Piece {

    Piece heldPiece=new EmptyPiece();



    public Barge(Teams team){
        super(team);
    }

    @Override
    public String type() {
        return "Barge";
    }

    @Override
    public PieceTypes getType() {
        return PieceTypes.BARGE;
    }

    @Override
    public PieceTypes getPieceType() {

        return PieceTypes.BARGE;
    }


    @Override
    public Image getImage() {
        System.out.println(team);
        Image i=new Image("/Textures/Pieces/"+team+"Barge.png");
        return i;
    }
    @Override
    public void setHolding(Piece p){
        heldPiece=p;
    }
    public Piece disembark(){
        Piece p=heldPiece;
        heldPiece=new EmptyPiece();
        return p;
    }
}
