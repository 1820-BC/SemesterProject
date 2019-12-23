package Pieces;

import BoardStuff.PieceTypes;
import javafx.scene.image.Image;

public class drawBridge extends Piece {

    public drawBridge(Teams team){
        super(team);
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
        System.out.println(team);
        Image i=new Image("/Textures/Pieces/"+team+"DrawBridgeClosed.png");
        return i;
    }
}
