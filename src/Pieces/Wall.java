package Pieces;

import BoardStuff.PieceTypes;
import javafx.scene.image.Image;

public class Wall extends Piece {

    public Wall(Teams team){
        super(team);
    }

    @Override
    public String type() {
        return "Wall";
    }

    @Override
    public PieceTypes getType() {
        return PieceTypes.WALL;
    }

    @Override
    public PieceTypes getPieceType() {

        return PieceTypes.WALL;
    }

    @Override
    public Image getImage() {
        System.out.println(team);
        Image i=new Image("/Textures/Pieces/"+team+"Wall.png");
        return i;
    }
}
