package Pieces;

import BoardStuff.PieceTypes;
import javafx.scene.image.Image;

//An abstraction of a piece class that can be placed or removed from the board and requires a cost
public abstract class Piece{

    private int player; //1 or 2
    private String textureP1;
    private String textureP2;
    private String texture;
    public String getTexture(){
        return texture;
    }

    protected void setPlayer(int player) throws Exception {
        if(player!=1&&player!=2){
            throw new Exception("Invalid Player Exception");
        }
        else if(player==1){
            texture=textureP1;
            return;
        }
        texture=textureP2;
    }
    //
    public abstract String type();

    public abstract PieceTypes getType();

    public abstract PieceTypes getPieceType();

    public abstract Image getImage();


}


