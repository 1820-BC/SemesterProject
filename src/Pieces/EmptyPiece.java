package Pieces;

import BoardStuff.PieceTypes;
import javafx.scene.image.Image;

public class EmptyPiece extends Piece {

    public EmptyPiece(){
        super(Teams.Red);
    }
    @Override
    public String type() {
        return null;
    }

    @Override
    public PieceTypes getType() {
        return PieceTypes.EMPTY;
    }

    @Override
    public PieceTypes getPieceType() {

        return PieceTypes.EMPTY;
    }

    @Override
    public Image getImage() {
        return new Image("/Textures/Pieces/Null.png");
    }
}
