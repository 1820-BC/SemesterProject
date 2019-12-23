package Pieces;

import BoardStuff.PieceTypes;
import javafx.scene.image.Image;

public class Capital extends Piece {

    public Capital(Teams team) {
        super(team);
    }

    @Override
    public String type() {
        return "capital";
    }

    @Override
    public PieceTypes getType() {
        return PieceTypes.CAPITAL;
    }

    @Override
    public PieceTypes getPieceType() {
        return PieceTypes.CAPITAL;
    }

    @Override
    public Image getImage() {
        return new Image("Textures/Pieces/"+team+"Capital.png");
    }

}
