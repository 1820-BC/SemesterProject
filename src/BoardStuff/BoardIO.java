package BoardStuff;

import Multiplayer.HostIO;
import Multiplayer.MultiplayerIO;
import Multiplayer.UpdationThread;
import javafx.event.EventHandler;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

import java.awt.desktop.SystemEventListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Stack;

import Pieces.Piece;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

//a class implementation for accessing and operating on a single board across the scenes
//Essential class for operation of game
//Contains most important methods
public class BoardIO {
    private static boolean addPieces=true;
    private static int xACT=0;
    private static int yACT=0;
    private static Board b = new Board();
    private static Canvas c=new Canvas(10,10);
    //ublic static Canvas cP=new Canvas(10,10);
   // private static GraphicsContext gP=cP.getGraphicsContext2D();
    private static GraphicsContext g=c.getGraphicsContext2D();
    private static int squareSize=30;
    private static int prevX,prevY;
    private static MultiplayerIO io;
    private static String moves;

    //generates the board from a file fileName.txt
    //returns true is successful and false if not
    public static void generateBoardFromFile(String fileName) throws FileNotFoundException {
        b = new Board(fileName);

    }


    public static void saveBoardToFile(String fileName) throws IOException {
        b.saveToFile(fileName);
    }

    //generates board from the parameters given
    public static void generateBoardFromVariables(int riverness, int x, int y) throws FileNotFoundException {
        b = new Board(riverness, x, y);
    }


    public static void setUpCanvas(){

        if(addPieces) {
            addToBoard(0, 0, PieceTypes.ARCHER);
            addToBoard(1, 1, PieceTypes.INFANTRY);
            addToBoard(2, 2, PieceTypes.CALVERY);
            addToBoard(3, 3, PieceTypes.TREBUCHET);
        }
        drawBoardNew();
    }

    public static void setUpCanvasWithParams(int side, int droplets, int  surfaceTension, int flowForce, int  rainfallVariation,int  minPrecipitaion) throws FileNotFoundException {
        b=new Board(side, droplets, surfaceTension, flowForce,  rainfallVariation, minPrecipitaion);

    }



    private static void selectFromLocation(double x, double y) {

    }
    public static void drawBoardNew(){
        if(b.getSize()==0){
            c.setHeight(80*squareSize);
            c.setWidth(80*squareSize);
        }
        else {
            c.setHeight(squareSize * b.getSize());
            c.setWidth(squareSize * b.getSize());
        }

        g.clearRect(0,0,c.getWidth(),c.getHeight());

        int y=0;
        int x;

        for(ArrayList<Space> lis:b.getBoard()){
            GraphicsContext g=c.getGraphicsContext2D();
            y++;
            x=0;
            for(Space space:lis){

                x++;
//                System.out.println(x);
//                System.out.println(y);
                g.drawImage(space.getTextureTerrain(),x*squareSize,y*squareSize);
                g.drawImage(space.getTexturePiece(),x*squareSize,y*squareSize);
//                System.out.println(space.getPiece());

            }
        }
        g.save();
    }
    public static PieceTypes getType(){
        return b.getPieceTypeFromPointer();
    }

    public static Canvas getCanvas(){
        return c;
    }


    public static Board getBoard() {
        return b;
    }

    public static void run(Move movePlayer1) {
//        System.out.println(movePlayer1.getX());
//        System.out.println(movePlayer1.getY());
////        System.out.println(movePlayer1.getdX());
////        System.out.println(movePlayer1.getdY());
//        System.out.println(movePlayer1);
        if (movePlayer1.getPT() == Moves.MOVE) {
            runMovement(movePlayer1);
        }
        else{
            runShot(movePlayer1);
        }
    }
    private static void runShot(Move movePlayer1){
        //stuff
    }
    private static void runMovement(Move movePlayer1){
        if (movePlayer1 == null) {
            return;
        }
        b.setPointer(movePlayer1.getX(), movePlayer1.getY());
        Piece piece = b.getPieceFromPointer();
        movePlayer1.setVector(piece.getPieceType());

        b.movePointer(movePlayer1.getdX(), -movePlayer1.getdY());
        if ((b.getPieceTypeFromPointer() != PieceTypes.EMPTY && b.getPieceTypeFromPointer() != PieceTypes.OPENDRAWBRIDGE)) {
            return;
        } else if (b.getSpaceFromPointer().getType().getLand().equals("rivers")) {
            return;
        }
        getBoard().setPointer(movePlayer1.getX(), movePlayer1.getY());


        System.out.println(movePlayer1.getdX());
        moves += "-" + movePlayer1.getX() + "," + movePlayer1.getY() + ",EMPTY";
        getBoard().setPieceFromPointer(PieceTypes.EMPTY);

        getBoard().movePointer(movePlayer1.getdX(), -movePlayer1.getdY());
        moves += "-" + b.getX() + "," + b.getY() + "," + piece.getPieceType();
        getBoard().setPieceFromPointer(piece.getPieceType());
        redrawSquare(movePlayer1.getX(), movePlayer1.getY());
        redrawSquare(movePlayer1.getX() + movePlayer1.getdX(), -movePlayer1.getdY() + movePlayer1.getY());
//        hearAction();

    }
    public static PieceTypes getPieceAt(int x, int y) {
        getBoard().setPointer(x,y);
//        getBoard().getPieceFromPointer();
        return getBoard().getPieceTypeFromPointer();
    }

    public static PieceTypes getPieceAt(){
        return getPieceAt(xACT,yACT);
    }

    public static void addToBoard(PieceTypes p){
        getBoard().setPointer(xACT,yACT);
        getBoard().setPieceFromPointer(p);
    }
    public static void addToBoard(int x, int y,PieceTypes p){
        getBoard().setPointer(x, y);
        getBoard().setPieceFromPointer(p);
    }

    public static void setxACT(int x){
        xACT=x;
    }

    public static void setyACT(int yACT) {
        BoardIO.yACT = yACT;
    }

    public static void drawRectAround() {
//        g.setStroke();
//        drawBoardNew();
        redrawSquare(prevX,prevY);
        g.setStroke(Color.RED);
        g.setLineWidth(1);
        g.strokeRect(xACT*squareSize+squareSize+5,yACT*squareSize+squareSize+5,squareSize-10,squareSize-10);
        prevX=xACT;
        prevY=yACT;
    }
    public static void redrawSquare(int x, int y){
        g.clearRect(x*squareSize+squareSize,y*squareSize+squareSize,squareSize,squareSize);
        b.setPointer(x,y);
//        b.getSpaceFromPointer().getTextureTerrain();
        g.drawImage(b.getSpaceFromPointer().getTextureTerrain(),x*squareSize+squareSize,y*squareSize+squareSize);
        g.drawImage(b.getSpaceFromPointer().getTexturePiece(),x*squareSize+squareSize,y*squareSize+squareSize);
        g.save();
    }

    public static boolean pointerPossible(int x, int y){
        if(x<getBoard().sizeX&&x>=0&&y>=0&&y<getBoard().sizeY){
            return true;
        }
        return false;
    }


    public static void updateOponentBoard() {
        io.sendAction(moves);
    }

    public static void getUpdate() throws IOException {
        io.recieveAction();
    }

    public static boolean checkForWinCondition() {
        return false;
    }

    public static void setSquare(String part, String part1, String part2) {
        int x=Integer.parseInt(part);
        int y=Integer.parseInt(part1);
        b.setPointer(x,y);
        b.setPieceFromPointer(PieceTypes.valueOf(part2));
        redrawSquare(x,y);
    }

    public static void setIO(MultiplayerIO hostIO) {
        io=hostIO;
    }

    public static boolean hasConnection() {
        return io != null;

    }


    public static MultiplayerIO getIO() {
        return io;
    }


    public static void drawNewBoardFromString(String board) {
        b=new Board();
        try {
            b.setupBoard(board);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void sendBoardThroughIO(){
        io.sendWholeCanvas(b.getEncodedBoard());
    }

    public static void beginUpdationThread() {
        io.runUpdationThread();

    }
}
