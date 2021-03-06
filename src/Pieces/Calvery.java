package Pieces;

import BoardStuff.PieceTypes;
import javafx.scene.image.Image;

public class Calvery extends Piece{


    public Calvery(Teams team) {
        super(team);
    }

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
        return new Image("Textures/Pieces/"+team+"Calvery.png");
    }
}
