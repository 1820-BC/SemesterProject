package BoardStuff;

import Pieces.*;
import Spaces.Type;
import javafx.scene.image.Image;

import java.io.FileNotFoundException;
import java.util.ArrayList;

//space class that contains both piece and type of each space on the board
//Allows for easier accessing and recieving of board params
public class Space {

    private ArrayList<Piece> pieces;

    private Type type;

    private Piece secondaryPiece;

    public Space(Type t, Piece piece) throws FileNotFoundException {
        pieces=new ArrayList<>();
        this.pieces.add(piece);
        this.type=t;

    }

    public Piece getPiece() {
        return pieces.get(0);
    }
    public ArrayList getPieceList(){
        return pieces;
    }
    public void addPiece(Piece piece){
    pieces.add(piece);
    }

    public void remove(PieceTypes p){
        for(Piece pp:(ArrayList<Piece>)pieces){
            if(pp.getType()==p){
                pieces.remove(pp);
                break;
            }
        }
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public void setPiece(Piece piece) {
        this.pieces.set(0,piece);
    }
    public void setPiece(PieceTypes p,Teams team){
        Piece piece=null;

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
        else if(p==PieceTypes.BATTLE_SHIP){
            piece=new BattleShip(team);
        }
        else if(p==PieceTypes.BARGE){
            piece=new Barge(team);
        }
        else if(p==PieceTypes.CAPITAL){
            piece=new Capital(team);
        }
        if(pieces.size()>0&&pieces.get(0).getType()==PieceTypes.EMPTY){
            pieces.set(0,piece);
        }
        else {
            pieces.add(piece);
        }
    }



    public PieceTypes getPieceType() {

        return pieces.get(0).getPieceType();
    }

    public Image getTexturePiece() {
        return pieces.get(0).getImage();
    }

    public Image getTextureTerrain(){
        return type.getImage();
    }

    public TerrainTypes getTerrainType() {
        return type.getTerrainType();
    }
}
