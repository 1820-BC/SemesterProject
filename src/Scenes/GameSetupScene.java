package Scenes;

import BoardStuff.Board;
import BoardStuff.BoardIO;
import BoardStuff.GameInteraction;
import Multiplayer.HostIO;
import Multiplayer.UpdationThread;
import Pieces.Teams;
import javafx.scene.Group;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.NoSuchElementException;
//import BoardGame.boardIO;

public class GameSetupScene {

    static BorderPane main;
    static VBox control;
    static Button setParamsExact;
    static FlowPane sav,lod;
    static TextField inSave;
    static ComboBox<String> inLoad;
    static Button generate,save,load,back,start;
    static Slider rivers;
    static Slider size;
    static Label riversLabel,sizelabel;
    static ScrollPane container;

    static Scene game_setup_scene;
    static Canvas c;
    static Stage stage;
    static double width,height;

   // static BoardIO board;

    public static void gameSetupSceneInit(Stage st, double screenWidth, double screenHeight) {

        height=screenHeight;
        width=screenWidth;
        //setup layouts
        main=new BorderPane();
        main.setPadding(new Insets(10,60,10,10));
        control=new VBox(10);
        sav=new FlowPane();
        lod=new FlowPane();
        container=new ScrollPane();
        stage=st;
        setParamsExact=new Button("SET EXACT PARAMETERS");
        //setup nodes
        generate=new Button("GENERATE");
        save=new Button("SAVE");
        load=new Button("LOAD");
        back=new Button("BACK");
        start=new Button("START GAME");





        c=BoardIO.getCanvas();

        save.setId("EnterButton");
        load.setId("EnterButton");
        //button function setup

        generate.setOnAction(e->{
            int s=(int)size.getValue()+1;

            try {
                BoardIO.generateBoardFromVariables((int)rivers.getValue(),s,s);
                BoardIO.setUpCanvas();
                c=BoardIO.getCanvas();
            } catch (FileNotFoundException ex) {
                System.out.println("ERROR IN GENERATE BUTTON ON GAMESETUPSCENE");
            }
            sendCanvas();
        }
        );
        load.setOnAction(e->{

                    try {
                        BoardIO.generateBoardFromFile(inLoad.getValue());
                        BoardIO.setUpCanvas();


                    } catch (FileNotFoundException ex) {
                        System.out.println("ooff");
                    }
                    sendCanvas();
                }
                );
        save.setOnAction(e->{


                try{
                    if(BoardIO.getBoard().getBoard().isEmpty()) {
                        throw new Exception();
                    }

                    FileInputStream f = new FileInputStream("D://BoardGame/src/SavedMaps/" + inSave.getText() + ".txt");
                    inSave.setText("File Already Exists");

                }
                catch(IOException ex){
                    try{
                        BoardIO.saveBoardToFile(inSave.getText());
                        inLoad.getItems().add(inSave.getText()+".txt");
                    }
                    catch(IOException exe){
                        inSave.setText("Invalid File");
                    }
                    catch(NoSuchElementException nu){
                        inSave.setText("Nothing In File");
                    }
                    catch(NullPointerException nup){

                    }

                } catch (Exception ex) {
                    inSave.setText("Board non-Existent");
                }


        });
        start.setOnAction(e-> {
            boolean runningLigit=false;
            if((!BoardIO.hasConnection())&&runningLigit){
                return;
            }
            BoardIO.getIO().sendOkay();
            stage.setScene(MoveScene.getScene());
            BoardIO.beginUpdationThread();

        });
        back.setOnAction(e->stage.setScene(OpeningScene.openingScene(stage)));

        setParamsExact.setOnAction(e->{
            stage.setScene(ParamsScene.getParamsScene());

        });


        size=new Slider();
        rivers=new Slider();




        rivers.setMax(80);
        size.setMax(80);
        rivers.setId("waterSlider");
        size.setId("sizeSlider");
        size.setBlockIncrement(2);
        size.setMin(1);
        rivers.setBlockIncrement(1);


        riversLabel=new Label("Rivers");
        sizelabel=new Label("Size");

        inLoad=new ComboBox<>();


        inSave=new TextField();

        inSave.setPromptText("File Name");


        lod.setHgap(10);
        sav.setHgap(10);
        lod.getChildren().addAll(inLoad,load);
        sav.getChildren().addAll(inSave,save);

        control.setAlignment(Pos.CENTER);
        control.getChildren().addAll(riversLabel,rivers,sizelabel,size,generate,sav, lod,start,back,setParamsExact);
        //wrapperPane=new Group();
        //wrapperPane.getChildren().add(c);
        container.setContent(c);

        container.setId("mapTerminal");

        main.setCenter(container);
        main.setRight(control);

        game_setup_scene=new Scene(main,screenWidth,screenHeight);

        game_setup_scene.getStylesheets().add("Graphics/GameSetupScene.css");




    }

    public static void sendCanvas() {
        if(BoardIO.hasConnection()){
            BoardIO.sendBoardThroughIO();
        }
    }


    public static Scene GameSetupScene(){
//        BoardIO.getIO().runWaitScreenUpdationThread();
        BoardIO.setPlayer(Teams.Blue);
        File f = new File("D://BoardGame/src/SavedMaps/");
        if(f.listFiles()==null){
            return game_setup_scene;
        }
        inLoad.getItems().clear();
        for(File s:f.listFiles()){
            inLoad.getItems().add(s.getName());
        }



        return game_setup_scene;
    }


}
