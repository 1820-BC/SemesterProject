package Pieces;

import BoardStuff.PieceTypes;
import javafx.scene.image.Image;

public class Infantry extends Piece {
    public Infantry(){
        super();
    }

    @Override
    public String type() {
        return "Infantry";
    }

    @Override
    public PieceTypes getType() {
        return PieceTypes.INFANTRY;
    }

    @Override
    public PieceTypes getPieceType() {
        return PieceTypes.INFANTRY;
    }

    @Override
    public Image getImage() {
        return new Image("/Textures/Pieces/Infantry.png");
    }
}
