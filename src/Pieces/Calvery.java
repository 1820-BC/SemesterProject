package Pieces;

import BoardStuff.PieceTypes;
import javafx.scene.image.Image;

public class Calvery extends Piece{


    @Override
    public String type() {
        return "calvery";
    }

    @Override
    public PieceTypes getType() {
        return PieceTypes.CALVERY;
    }

    @Override
    public PieceTypes getPieceType() {
        return PieceTypes.CALVERY;
    }

    @Override
    public Image getImage() {
        return new Image("Textures/Pieces/Calvery.png");
    }
}
