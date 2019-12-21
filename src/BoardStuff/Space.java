package BoardStuff;

import Pieces.*;
import Spaces.Type;
import javafx.scene.effect.Blend;
import javafx.scene.image.Image;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

//space class that contains both piece and type of each space on the board
//Allows for easier accessing and recieving of board params
public class Space {

    private Piece piece;

    private Type type;


    public Space(Type t, Piece piece) throws FileNotFoundException {
        this.piece=piece;
        this.type=t;

    }

    public Piece getPiece() {
        return piece;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public void setPiece(Piece piece) {
        this.piece = piece;
    }
    public void setPiece(PieceTypes p){
//        setPiece(new Infantry());
//        System.out.println("p: "+p);
        if (p == PieceTypes.INFANTRY) {
            piece = new Infantry();
        }
        else if(p==PieceTypes.EMPTY){
            piece=new EmptyPiece();
        }
        else if(p==PieceTypes.ARCHER){
            piece=new Archer();
        }
        else if(p==PieceTypes.CALVERY){
            piece=new Calvery();
        }
        else if(p==PieceTypes.TREBUCHET){
            piece=new Trebuchet();
        }
    }


    public PieceTypes getPieceType() {
        return piece.getPieceType();
    }

    public Image getTexturePiece() {
        return piece.getImage();
    }

    public Image getTextureTerrain(){
        return type.getImage();
    }
}
