package BoardStuff;

import Rules.Rules;
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
import Pieces.*;
import Pieces.Piece;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

//a class implementation for accessing and operating on a single board across the scenes
//Essential class for operation of game
//Contains most important methods
public class BoardIO {
    private static boolean addPieces=false;
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
    private static String moves="";
    private static Teams player;
    private static String stringPlayer;


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
            addToBoard(0, 0, PieceTypes.ARCHER,Teams.Blue);
            addToBoard(1, 1, PieceTypes.TREBUCHET,Teams.Blue);
            addToBoard(2, 2, PieceTypes.CALVERY,Teams.Blue);
            addToBoard(3, 3, PieceTypes.FACTORY,Teams.Blue);
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
            c.setHeight(squareSize * (b.getSize()+2));
            c.setWidth(squareSize * (b.getSize()+2));
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
        drawLines();

    }

    public static void drawLines(){
        GraphicsContext g=c.getGraphicsContext2D();
        g.setStroke(Color.RED);
        g.strokeLine(squareSize+2,(c.getHeight())/2,c.getWidth()-squareSize,(c.getHeight())/2);
        //blue
        g.setStroke(Color.BLUE);
        g.strokeLine(squareSize+2,(c.getHeight())/2+2,c.getWidth()-squareSize,(c.getHeight())/2+2);
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

    public static boolean notOnTeam(Move movePlayer1){
        if(b==null){
            return false;
        }
        b.setPointer(movePlayer1.getX(), movePlayer1.getY());
        Piece p=b.getPieceFromPointer();
        return p.getTeam()!=player&&p.type()!=null;
    }

    public static void run(Move movePlayer1) {
//        System.out.println(movePlayer1.getX());
//        System.out.println(movePlayer1.getY());
////        System.out.println(movePlayer1.getdX());
////        System.out.println(movePlayer1.getdY());
//        System.out.println(movePlayer1);
        if(notOnTeam(movePlayer1)){
            return;
        }
        if (movePlayer1.getPT() == Moves.MOVE) {
            System.out.println("move");
            runMovement(movePlayer1);
            updateForMovement(movePlayer1);
        }
        else if(movePlayer1.getPT()==Moves.SHOOT){
            System.out.println("shoot");

            runShot(movePlayer1);
            updateForShot(movePlayer1);
        }
        else if(movePlayer1.getPT()==Moves.BUILD){
            System.out.println("build");
            runBuild(movePlayer1);

        }
        drawLines();
    }
    private static void updateForMovement(Move move){
        Rules.addTurnSinceBuild();

    }
    private static void updateForShot(Move move){
        Rules.addTurnSinceBuild();
    }
    private static void updateForBuild(){
        Rules.resetTurnsSinceLastBuild();
    }
    private static void runBuild(Move movePlayer1){

        PieceTypes type=movePlayer1.getNewPieceType();
        System.out.println(type);
        b.setPointer(movePlayer1.getX(),movePlayer1.getY());
//        if(b.getPieceTypeFromPointer()==PieceTypes.EMPTY){
        if (!Rules.canBuildIn(movePlayer1.getY(),b.getSpaceFromPointer(),movePlayer1.getNewPieceType())) {
            return;
        }
        b.setPieceFromPointer(type,player);
        if(type==PieceTypes.FACTORY){
            Rules.addFactory();
        }
        moves+="-"+movePlayer1.getX()+","+movePlayer1.getY()+","+ type+","+player;
        redrawSquare(movePlayer1.getX(), movePlayer1.getY());
    }
    private static void runShot(Move movePlayer1){
        int x=movePlayer1.getX();
        int y=movePlayer1.getY();
        b.setPointer(x,y);
        Piece attacker=b.getPieceFromPointer();
        int dX=movePlayer1.getVector().getDirX();
        int dY=-movePlayer1.getVector().getDirY();
        for(int apple=0;apple<Pieces.getShoot(attacker.getPieceType());apple++){
            System.out.println(dX);
            System.out.println(dY);
            b.movePointer(dX,dY);
            System.out.println(b.getSpaceFromPointer());
            if(!Rules.canShootThrough(b.getSpaceFromPointer(),attacker,b.getPieceFromPointer())){
                return;
            }
            if(!Rules.canKill(b.getSpaceFromPointer(),attacker,b.getPieceFromPointer())){
                System.out.println("Can Not Kill");
                continue;
            }
            System.out.println("SLAY"+b.getXFromPointer()+b.getYFromPointer());
            b.setPieceFromPointer(PieceTypes.EMPTY,Teams.Red);
            moves+="-"+b.getXFromPointer()+","+b.getYFromPointer()+","+PieceTypes.EMPTY+","+Teams.Red;
            redrawSquare(b.getXFromPointer(),b.getYFromPointer());
            break;

        }
        System.out.println("-----");
    }
    private static void runMovement(Move movePlayer1){
        if (movePlayer1 == null) {
            return;
        }
        b.setPointer(movePlayer1.getX(), movePlayer1.getY());
        Piece piece = b.getPieceFromPointer();
        movePlayer1.setVector(piece.getPieceType());

        b.movePointer(movePlayer1.getdX(), -movePlayer1.getdY());
        if (!Rules.canMoveInto(b.getSpaceFromPointer(),piece)) {
            return;
        }
        Rules.effectOfMoveInto(b.getSpaceFromPointer(),piece);

        getBoard().setPointer(movePlayer1.getX(), movePlayer1.getY());


        System.out.println(movePlayer1.getdX());
        moves += "-" + movePlayer1.getX() + "," + movePlayer1.getY() + ",EMPTY"+",Red";
        getBoard().setPieceFromPointer(PieceTypes.EMPTY, Teams.Red);

        getBoard().movePointer(movePlayer1.getdX(), -movePlayer1.getdY());
        moves += "-" + b.getX() + "," + b.getY() + "," + piece.getPieceType()+","+piece.getTeam();
//        System.out.println(moves);
        getBoard().setPieceFromPointer(piece.getPieceType(),piece.getTeam());
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
//        getBoard().setPieceFromPointer(p,team);
    }
    public static void addToBoard(int x, int y, PieceTypes p, Teams team){
        getBoard().setPointer(x, y);
        getBoard().setPieceFromPointer(p,team);
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
        drawLines();
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

    public static void setSquare(String part, String part1, String part2,String part3) {
        int x=Integer.parseInt(part);
        int y=Integer.parseInt(part1);
        b.setPointer(x,y);
        Rules.effectOfPieceChange(b.getSpaceFromPointer(),Pieces.getPieceFrom(PieceTypes.valueOf(part2),Teams.valueOf(part3)));
        b.setPieceFromPointer(PieceTypes.valueOf(part2),Teams.valueOf(part3));

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
        addToBoard(0,0,PieceTypes.CAPITAL,Teams.Red);
        addToBoard(b.getSize()-1,b.getSize()-1,PieceTypes.CAPITAL,Teams.Blue);
    }

    public static void sendBoardThroughIO(){
        io.sendWholeCanvas(b.getEncodedBoard());
    }

    public static void beginUpdationThread() {
        io.runUpdationThread();

    }

    public static void resetMoveMessage() {
        moves="";
    }

    public static void setPlayer(Teams teams){
        player=teams;
        stringPlayer=teams.toString();
    }

    public static Teams getTeam() {
        return player;
    }

    public static String getStringTeam(){
        return stringPlayer;
    }

    public static boolean teamCheck(Piece attacker) {
        return attacker.getTeam()==player;
    }

    public static Teams getTeamFromSector(double y){
        System.out.println(y+">="+b.getDoubleSize()/2);
        if(y>=b.getDoubleSize()/2){
            return Teams.Blue;
        }
        return Teams.Red;
    }

    public static boolean correctTeamFromSector(int y) {
        return getTeamFromSector(y)==player;
    }
}
