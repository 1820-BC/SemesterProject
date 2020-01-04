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

    private Piece secondaryPiece;

    public Space(Type t, Piece piece) throws FileNotFoundException {
        this.piece=piece;
        this.type=t;

    }

    public Piece getPiece() {
        if(getAcceptablePieces(piece)){
            return piece;
        }
        return secondaryPiece;
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
    public void setPiece(PieceTypes p,Teams team){
//        setPiece(new Infantry());
//        System.out.println("p: "+p);
        if(getAcceptablePieces(piece)){
            addPieceToSecondary(p,team);
            return;
        }

        if (p == PieceTypes.INFANTRY) {
            piece = new Infantry(team);
        }
        else if(p==PieceTypes.EMPTY){
            piece=new EmptyPiece();
        }
        else if(p==PieceTypes.ARCHER){
            piece=new Archer(team);
        }
        else if(p==PieceTypes.CALVERY){
            piece=new Calvery(team);
        }
        else if(p==PieceTypes.TREBUCHET){
            piece=new Trebuchet(team);
        }
        else if(p==PieceTypes.FACTORY){
            piece=new Factory(team);
        }
        else if(p==PieceTypes.WALL){
            piece=new Wall(team);
        }
        else if(p==PieceTypes.CLOSEDDRAWBRIDGE){
            piece=new drawBridge(team);
        }
        else if(p==PieceTypes.BRIDGE){
            piece=new Bridge(team);
        }
        else if(p==PieceTypes.CAPITAL){
            piece=new Capital(team);
        }
    }

    private boolean getAcceptablePieces(Piece piece) {
            if(piece.getType()==PieceTypes.FACTORY||piece.getType()==PieceTypes.OPENDRAWBRIDGE||piece.getType()==PieceTypes.BRIDGE){
                return true;
            }
            return false;
    }

    public void addPieceToSecondary(PieceTypes p,Teams team){
        if (p == PieceTypes.INFANTRY) {
            secondaryPiece = new Infantry(team);
        }
        else if(p==PieceTypes.EMPTY){
            secondaryPiece=new EmptyPiece();
        }
        else if(p==PieceTypes.ARCHER){
            secondaryPiece=new Archer(team);
        }
        else if(p==PieceTypes.CALVERY){
           secondaryPiece=new Calvery(team);
        }
        else if(p==PieceTypes.TREBUCHET){
            secondaryPiece=new Trebuchet(team);
        }
        if(piece.getType()==PieceTypes.FACTORY) {
            piece.setTeam(BoardIO.getTeam());
        }

    }

    public PieceTypes getPieceType() {
        if(getAcceptablePieces(piece)){
            return secondaryPiece.getPieceType();
        }
        return piece.getPieceType();
    }

    public Image getTexturePiece() {
        return piece.getImage();
    }

    public Image getTextureTerrain(){
        return type.getImage();
    }
}
