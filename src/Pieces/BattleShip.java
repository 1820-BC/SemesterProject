package Pieces;

import BoardStuff.PieceTypes;
import javafx.scene.image.Image;

public class BattleShip extends Piece {

    public BattleShip(Teams team){
        super(team);
    }

    @Override
    public String type() {
        return "Battleship";
    }

    @Override
    public PieceTypes getType() {
        return PieceTypes.BATTLE_SHIP;
    }

    @Override
    public PieceTypes getPieceType() {

        return PieceTypes.BATTLE_SHIP;
    }

    @Override
    public Image getImage() {
        System.out.println(team);
        Image i=new Image("/Textures/Pieces/"+team+"Battleship.png");
        return i;
    }
}
