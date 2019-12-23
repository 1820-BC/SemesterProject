package Pieces;

import BoardStuff.PieceTypes;
import javafx.scene.image.Image;

public class Factory extends Piece {
    public Factory(Teams team){
        super(team);
    }

    @Override
    public String type() {
        return "Factory";
    }

    @Override
    public PieceTypes getType() {
        return PieceTypes.FACTORY;
    }

    @Override
    public PieceTypes getPieceType() {
        return PieceTypes.FACTORY;
    }

    @Override
    public Image getImage() {
        return new Image("/Textures/Pieces/"+team+"Factory.png");
    }
}
