package Pieces;

import BoardStuff.PieceTypes;
import javafx.scene.image.Image;

public class Archer extends Piece {
    public Archer(){
        super();
    }

    @Override
    public String type() {
        return "Archer";
    }

    @Override
    public PieceTypes getType() {
        return PieceTypes.ARCHER;
    }

    @Override
    public PieceTypes getPieceType() {

        return PieceTypes.ARCHER;
    }

    @Override
    public Image getImage() {
        Image i=new Image("/Textures/Pieces/Archer.png");
        return i;
    }


}
