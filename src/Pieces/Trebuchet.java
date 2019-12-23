package Pieces;

import BoardStuff.PieceTypes;
import javafx.scene.image.Image;

public class Trebuchet extends Piece {


    public Trebuchet(Teams team) {
        super(team);
    }

    @Override
    public String type() {
        return "Trebuchet";
    }

    @Override
    public PieceTypes getType() {
        return PieceTypes.TREBUCHET;
    }

    @Override
    public PieceTypes getPieceType() {
        return PieceTypes.TREBUCHET;
    }

    @Override
    public Image getImage() {
        return new Image("/Textures/Pieces/"+team+"Trebuchet.png");
    }
}
