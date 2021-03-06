package Scenes;

import BoardStuff.*;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.InputEvent;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import Rules.*;
import java.io.IOException;
import java.util.Timer;


public class MoveScene {
    //#region
//    static private boolean done;
    static private Scene scene;
    static private Button up, down, left, right, dru, drd, dlu, dld;
    static private TextField xF, yF;
    static private ImageView image;
    static private Move currentMove;
    //    static private Button arrowBack,arrowFront;
    static private ListView<String> moves;
    static private Button sendOrders, submitOrder, movement,shot;
    static private Stage stage;
    static private ScrollPane pane;
//    static private TextField xIn,yIn;
    static private boolean multiplayer;
    static private AnchorPane anchor;
    static private FlowPane flower;
    static private BorderPane primaryPane;
    static private Label MoveNum, moveType;
    static private VBox vbox,movesVBox,infoVBox;
    static private int movesIn = 0;
    static private String message = "YOU HAVE ";
    static private String message2 = "MOVES QUEUED";
    static private PieceTypes type;
    static private int selectedX;
    static private int selectedY;
    static private int squareSize=30;
    static private Button build;
    static private Label color, enemy;
    static private Label movesTillBuild;
    static private Stage playStage;
//    static private TimerThread clock;
    //#endregion
    public static void setupMoveScene(Stage stage, double width, double height) {
        playStage=stage;
        //#region

        up = new Button();
        down = new Button();
        left = new Button();
        right = new Button();
        dru = new Button();
        drd = new Button();
        dlu = new Button();
        dld = new Button();

        int x = 70;
        int y = 70;
        up.setPrefSize(x, y);
        down.setPrefSize(x, y);
        left.setPrefSize(x, y);
        right.setPrefSize(x, y);
        dlu.setPrefSize(x, y);
        dru.setPrefSize(x, y);
        dld.setPrefSize(x, y);
        drd.setPrefSize(x, y);
        moveOrShoot();
        //#endregion






        color=new Label();
        enemy=new Label();
        movesTillBuild=new Label();
        movesTillBuild.setText("Moves Till Build: "+Rules.getTurnSinceLastBuild());

        moveType=new Label("DIRECTED TO: NO MOVE");
        MoveNum = new Label(message + movesIn + message2);
        Image im = new Image("Textures/noMove.png");
        image = new ImageView(im);
        xF = new TextField();
        xF.setAlignment(Pos.CENTER);
        xF.setPromptText("X Position");
        yF = new TextField();
        yF.setAlignment(Pos.CENTER);
        yF.setPromptText("Y Position");
        currentMove=new Move();
        anchor = new AnchorPane(up, down, left, right, dru, drd, dlu, dld, image);
        double dim = 300;
        image.setFitHeight(dim / 2 - 20);
        image.setFitWidth(dim / 2 - 20);
        image.preserveRatioProperty();
        anchor.setMinSize(dim, dim);
        anchor.setId("ButtonPane");

        //#region
        AnchorPane.setBottomAnchor(down, 0.);
        AnchorPane.setLeftAnchor(down, dim / 2);
        AnchorPane.setRightAnchor(down, dim / 2);
        AnchorPane.setLeftAnchor(up, dim / 2);
        AnchorPane.setRightAnchor(up, dim / 2);
        AnchorPane.setTopAnchor(up, 0.);
        AnchorPane.setBottomAnchor(left, dim / 2);
        AnchorPane.setLeftAnchor(left, 0.);
        AnchorPane.setTopAnchor(left, dim / 2);
        AnchorPane.setBottomAnchor(right, dim / 2);
        AnchorPane.setRightAnchor(right, 0.);
        AnchorPane.setTopAnchor(right, dim / 2);
        //--- --- ---
//            AnchorPane.setBottomAnchor(dru, 0.);
        AnchorPane.setLeftAnchor(dru, dim);
        AnchorPane.setRightAnchor(dru, 10.);
        AnchorPane.setTopAnchor(dru, 10.);

        AnchorPane.setLeftAnchor(dlu, 10.);
        AnchorPane.setRightAnchor(dlu, dim);
        AnchorPane.setTopAnchor(dlu, 10.);

        AnchorPane.setLeftAnchor(drd, dim);
        AnchorPane.setRightAnchor(drd, 10.);
        AnchorPane.setBottomAnchor(drd, 10.);

        AnchorPane.setLeftAnchor(dld, 10.);
        AnchorPane.setRightAnchor(dld, dim);
        AnchorPane.setBottomAnchor(dld, 10.);

        AnchorPane.setLeftAnchor(image, dim / 2 - 20);
        AnchorPane.setTopAnchor(image, dim / 2 - 20);


        //#endregion
//        Label l=new Label();
//        pane.setId("mapTerminal");

        moves=new ListView<String>();
        movesIn=0;
        MoveNum=new Label("Moves in Queue: 0");
        sendOrders=new Button("  SUBMIT  ");
        sendOrders.setId("ordersButton");
        movesVBox=new VBox(20);
        movesVBox.getChildren().addAll(movesTillBuild,sendOrders,moves);
        movesVBox.setAlignment(Pos.CENTER);


        pane = new ScrollPane();
        pane.setContent(BoardIO.getCanvas());
        pane.setPadding(new Insets(0,10,0,10));


        submitOrder=new Button("ENTER");
        submitOrder.setId("ordersButton");
        movement=new Button("MOVEMENT");
        shot=new Button("SHOT");
        build=new Button("BUILD");
        movement.setId("move");
        shot.setId("shot");
        build.setId("build");

        flower=new FlowPane();
        flower.setHgap(10);
        flower.getChildren().addAll(movement,shot,build);

        vbox = new VBox(20);
        vbox.setAlignment(Pos.CENTER);
        vbox.getChildren().addAll(moveType,flower,anchor,submitOrder);
//        vbox.setPadding(new Insets(0,10,0,0));

        infoVBox=new VBox(20);
        infoVBox.getChildren().setAll(color,enemy);

        primaryPane = new BorderPane();
        primaryPane.setPadding(new Insets(20,10,20,10));

        primaryPane.setCenter(pane);

        primaryPane.setLeft(movesVBox);
        primaryPane.setRight(vbox);
        primaryPane.setBottom(infoVBox);

        scene = new Scene(primaryPane, width, height);
        scene.getStylesheets().addAll("Graphics/ControlBoard.css");
        //#region

        //#endregion
        movement.setOnAction(e->{
            currentMove.setType(Moves.MOVE);
            moveType.setText("DIRECTED TO: MOVE");
            moveOrShoot();
        });
        shot.setOnAction(e->{
            currentMove.setType(Moves.SHOOT);
            moveType.setText("DIRECTED TO: SHOOT");
            moveOrShoot();
        });
        build.setOnAction(e->{
            currentMove.setType(Moves.BUILD);
            moveType.setText("DIRECTED TO: BUILD");
            build();
        });
        submitOrder.setOnAction(e->{
            FunctionForEntering(im);
        });

        sendOrders.setOnAction(e->{
//            if(multiplayer)
            BoardIO.run(BoardIO.getQueue().getMovePlayer1(),multiplayer);
            moves.getItems().remove(0);
            movesIn++;
        });

        EventHandler<KeyEvent> handler= keyEvent -> {
            if(keyEvent.getCode()== KeyCode.W){
                selectedY-=1;
                prepareForMove(selectedX,selectedY);
            }
            else if(keyEvent.getCode()==KeyCode.S){
                selectedY+=1;
                prepareForMove(selectedX,selectedY);
            }
            else if(keyEvent.getCode()==KeyCode.D){
                selectedX+=1;
                prepareForMove(selectedX,selectedY);
            }
            else if(keyEvent.getCode()==KeyCode.A){
                selectedX-=1;
                prepareForMove(selectedX,selectedY);
            }
            else if(keyEvent.getCode()==(KeyCode.F)){
                FunctionForEntering(im);
            }
            else if(keyEvent.getCode()==KeyCode.E){
                currentMove.setType(Moves.MOVE);
                moveType.setText("DIRECTED TO: MOVE");
            }
            else if(keyEvent.getCode()==KeyCode.Q){
                BoardIO.run(BoardIO.getQueue().getMovePlayer1(),multiplayer);
                moves.getItems().remove(0);
            }



        };
        scene.addEventHandler(KeyEvent.KEY_PRESSED,handler);
    }

    private static void FunctionForEntering(Image im) {
        if(currentMove.getPT()==null){
            return;
        }
        BoardIO.getBoard().setPointer(currentMove.getX(),currentMove.getY());
        if(currentMove.getPT()==Moves.BUILD){
            if(!Rules.readyForBuild()){
                return;
            }
            if(currentMove.getNewPieceType()==PieceTypes.FACTORY){
                if(Rules.getNumberOfFactories()==REFERENCE.MAX_TURNS_BETWEEN_BUILDS-REFERENCE.EFFECT_OF_FACTORY){
//            System.out.println("death of factory");
                    return;
                }
                Rules.addFactory();
            }
            Rules.resetTurnsSinceLastBuild();
        }
        currentMove.setVectorSize(BoardIO.getPieceAt());
        BoardIO.getQueue().addToQueue(currentMove);
        moves.getItems().add(currentMove.toString());
//        Moves m=currentMove.getPT();
        currentMove=new Move(selectedX,selectedY);
//        currentMove.setType(m);
        moveType.setText("DIRECTED TO: Move");
        moveOrShoot();
        image.setImage(im);
        movesIn=moves.getItems().size();
        movesIn--;
        MoveNum.setText("Moves in Queue: "+movesIn);
        movesTillBuild.setText("Seconds Till Build: "+Rules.getTurnSinceLastBuild());

    }


    public static Scene getScene(boolean multi) {
//        BoardIO.setupForMoveSelection();
        multiplayer=multi;
        color.setText("Your Color Is: "+BoardIO.getTeam());
        enemy.setText("Your Enemy Is: "+PlayersScene.getName());
        if(multiplayer) {
            BoardIO.getIO().runUpdationThread();
        }
        color.setId(BoardIO.getTeam().toString());
        enemy.setId(BoardIO.getOtherTeam().toString());
        BoardIO.getCanvas().setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                selectedX=(int) (mouseEvent.getX()/squareSize)-1;
                selectedY=(int) (mouseEvent.getY()/squareSize)-1;
                prepareForMove(selectedX,selectedY);
            }
        });
//        clock=new TimerThread();
        TimerThread.run();
//        BoardIO.getIO().setActionListener();
        return scene;
    }

    private static void prepareForMove(int selectedX, int selectedY) {
        if(!BoardIO.pointerPossible(selectedX,selectedY)){
            return;
        }
        BoardIO.setyACT(selectedY);
        BoardIO.setxACT(selectedX);
        BoardIO.drawRectAround();
//                System.out.println("selX: "+selectedX);
//                System.out.println("selY:"+selectedY);
        currentMove=new Move(selectedX,selectedY);
        currentMove.setVectorSize(BoardIO.getPieceAt());
        image.setImage(new Image("Textures/noMove.png"));


    }

    private static void moveOrShoot(){
        up.setId("up");
        down.setId("down");
        left.setId("left");
        right.setId("right");
        dru.setId("dru");
        drd.setId("drd");
        dlu.setId("dlu");
        dld.setId("dld");
        up.setOnAction(e->{
            currentMove.setVector(0,1);
            image.setImage(new Image("Textures/up.png"));
        });
        down.setOnAction(e->{
            currentMove.setVector(0,-1);
            image.setImage(new Image("Textures/down.png"));
        });
        left.setOnAction(e->{
            currentMove.setVector(-1,0);
            image.setImage(new Image("Textures/left.png"));
        });
        right.setOnAction(e->{
            currentMove.setVector(1,0);
            image.setImage(new Image("Textures/right.png"));
        });
        dru.setOnAction(e->{
            currentMove.setVector(1,1);
            image.setImage(new Image("Textures/dru.png"));

        });
        drd.setOnAction(e->{
            currentMove.setVector(1,-1);
            image.setImage(new Image("Textures/drd.png"));
        });
        dlu.setOnAction(e->{
            currentMove.setVector(-1,1);
            image.setImage(new Image("Textures/dlu.png"));
        });
        dld.setOnAction(e->{
            currentMove.setVector(-1,-1);
            image.setImage(new Image("Textures/dld.png"));

        });
    }
    private static void build(){
        String t=BoardIO.getStringTeam();
        up.setId(t+"infantry");
        down.setId(t+"factory");
        left.setId(t+"trebuchet");
        right.setId(t+"wall");
        dru.setId(t+"archer");
        drd.setId(t+"calvery");
        dlu.setId(t+"battleship");
        dld.setId(t+"barge");
        up.setOnAction(e->{
            currentMove.setNewPieceType(PieceTypes.INFANTRY);
            image.setImage(new Image("/Textures/Pieces/"+t+"Infantry.png"));
        });
        down.setOnAction(e->{
            currentMove.setNewPieceType(PieceTypes.FACTORY);
            image.setImage(new Image("/Textures/Pieces/"+t+"Factory.png"));
        });
        left.setOnAction(e->{
            currentMove.setNewPieceType(PieceTypes.TREBUCHET);
            image.setImage(new Image("/Textures/Pieces/"+t+"Trebuchet.png"));
        });
        right.setOnAction(e->{
            currentMove.setNewPieceType(PieceTypes.WALL);
            image.setImage(new Image("/Textures/Pieces/"+t+"Wall.png"));
        });
        dru.setOnAction(e->{
            currentMove.setNewPieceType(PieceTypes.ARCHER);
            image.setImage(new Image("/Textures/Pieces/"+t+"Archer.png"));

        });
        drd.setOnAction(e->{
            currentMove.setNewPieceType(PieceTypes.CALVERY);
            image.setImage(new Image("/Textures/Pieces/"+t+"Calvery.png"));
        });
        dlu.setOnAction(e->{
            currentMove.setNewPieceType(PieceTypes.BATTLE_SHIP);
            image.setImage(new Image("/Textures/Pieces/"+t+"Battleship.png"));
        });
        dld.setOnAction(e->{
            currentMove.setNewPieceType(PieceTypes.BARGE);
            image.setImage(new Image("/Textures/Pieces/"+t+"Barge.png"));

        });
    }

    public static void end() {
        playStage.setScene(VictoryScene.getScene());
    }
    public static void endClock(){
        TimerThread.stop();
    }
    public static void updateClock() {
        movesTillBuild.setText("Seconds Till Build: "+Rules.getTurnSinceLastBuild());

    }
}
