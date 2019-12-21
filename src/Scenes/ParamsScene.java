package Scenes;

import BoardStuff.BoardIO;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.FileNotFoundException;

public class ParamsScene {
    private static Slider droplets; //roughly 2/3rds of the largest landscape side
    private static Slider surfaceTension; //or radius, roughly 1/10 of largest landscape side
    private static Slider elasticity; //elasticity is among that the surface tension can vary, creates less square landscape, should be roughly half the radius

    private static Slider flowForce; //The max length of rivers, equal to ~4/9 longest side length
    private static Slider minPrecipitaion; //the minimum number of river starters, equal to one third of longest side length
    private static Slider rainfallVariation; //the variation from that minimum, about a third of minPrecipitation
    private static Slider riverness; //the range from 50 of the likely hood that a river will go one way over the other. See .txt file of world generation description. 30 is recommended
    private static Slider size;
    private static Scene scene;
    private static VBox box;
    private static FlowPane sizePane, dropletsPane, surfaceTensionPane,  elasticityPane, flowForcePane,minPrecipitaionPane,  rainfallVariationPane,rivernessPane;
    private static Label sizeL,dropletsL, surfaceTensionL,  elasticityL, flowForceL,minPrecipitaionL,  rainfallVariationL,rivernessL, sizeULabel,dropletsULabel, surfaceTensionULabel,  elasticityULabel, flowForceULabel,minPrecipitaionULabel,  rainfallVariationULabel,rivernessULabel;
    private static Button back, backWOGen;


    public static void setUpParamsScene(Stage stage, double width,double height){
        //#group
        droplets =new Slider();
        droplets.setMajorTickUnit(1);
        droplets.setMinorTickCount(0);
        droplets.setBlockIncrement(1);
//        droplets.
        droplets.setSnapToTicks(true);
        dropletsPane=new FlowPane();
        dropletsPane.setHgap(20);
        setUpSlider(droplets);
        droplets.setId("droplets");
        dropletsULabel=new Label("droplets:");
        dropletsL=new Label(Integer.toString((int)droplets.getValue()));
        dropletsPane.getChildren().addAll(dropletsULabel,droplets,dropletsL);
        dropletsPane.setAlignment(Pos.CENTER_RIGHT);
        surfaceTension=new Slider();
        surfaceTension.setMajorTickUnit(1);
        surfaceTension.setMinorTickCount(0);
        surfaceTension.setBlockIncrement(1);
//        surfaceTension.
        surfaceTension.setSnapToTicks(true);
        surfaceTensionPane=new FlowPane();
        surfaceTensionPane.setHgap(20);
        setUpSlider(surfaceTension);
        surfaceTension.setId("surfaceTension");
        surfaceTensionULabel=new Label("surfaceTension:");
        surfaceTensionL=new Label(Integer.toString((int)surfaceTension.getValue()));
        surfaceTensionPane.getChildren().addAll(surfaceTensionULabel,surfaceTension,surfaceTensionL);
        surfaceTensionPane.setAlignment(Pos.CENTER_RIGHT);
        elasticity=new Slider();
        elasticity.setMajorTickUnit(1);
        elasticity.setMinorTickCount(0);
        elasticity.setBlockIncrement(1);
//        elasticity.
        elasticity.setSnapToTicks(true);
        elasticityPane=new FlowPane();
        elasticityPane.setHgap(20);
        setUpSlider(elasticity);
        elasticity.setId("elasticity");
        elasticityULabel=new Label("elasticity:");
        elasticityL=new Label(Integer.toString((int)elasticity.getValue()));
        elasticityPane.getChildren().addAll(elasticityULabel,elasticity,elasticityL);
        elasticityPane.setAlignment(Pos.CENTER_RIGHT);
        flowForce=new Slider();
        flowForce.setMajorTickUnit(1);
        flowForce.setMinorTickCount(0);
        flowForce.setBlockIncrement(1);
//        flowForce.
        flowForce.setSnapToTicks(true);
        flowForcePane=new FlowPane();
        flowForcePane.setHgap(20);
        setUpSlider(flowForce);
        flowForce.setId("flowForce");
        flowForceULabel=new Label("flowForce:");
        flowForceL=new Label(Integer.toString((int)flowForce.getValue()));
        flowForcePane.getChildren().addAll(flowForceULabel,flowForce,flowForceL);
        flowForcePane.setAlignment(Pos.CENTER_RIGHT);
        minPrecipitaion=new Slider();
        minPrecipitaion.setMajorTickUnit(1);
        minPrecipitaion.setMinorTickCount(0);
        minPrecipitaion.setBlockIncrement(1);
//        minPrecipitaion.
        minPrecipitaion.setSnapToTicks(true);
        minPrecipitaionPane=new FlowPane();
        minPrecipitaionPane.setHgap(20);
        setUpSlider(minPrecipitaion);
        minPrecipitaion.setId("minPrecipitaion");
        minPrecipitaionULabel=new Label("minPrecipitaion:");
        minPrecipitaionL=new Label(Integer.toString((int)minPrecipitaion.getValue()));
        minPrecipitaionPane.getChildren().addAll(minPrecipitaionULabel,minPrecipitaion,minPrecipitaionL);
        minPrecipitaionPane.setAlignment(Pos.CENTER_RIGHT);
        rainfallVariation=new Slider();
        rainfallVariation.setMajorTickUnit(1);
        rainfallVariation.setMinorTickCount(0);
        rainfallVariation.setBlockIncrement(1);
//        rainfallVariation.
        rainfallVariation.setSnapToTicks(true);
        rainfallVariationPane=new FlowPane();
        rainfallVariationPane.setHgap(20);
        setUpSlider(rainfallVariation);
        rainfallVariation.setId("rainfallVariation");
        rainfallVariationULabel=new Label("rainfallVariation:");
        rainfallVariationL=new Label(Integer.toString((int)rainfallVariation.getValue()));
        rainfallVariationPane.getChildren().addAll(rainfallVariationULabel,rainfallVariation,rainfallVariationL);
        rainfallVariationPane.setAlignment(Pos.CENTER_RIGHT);
        riverness=new Slider();
        riverness.setMajorTickUnit(1);
        riverness.setMinorTickCount(0);
        riverness.setBlockIncrement(1);
//        riverness.
        riverness.setSnapToTicks(true);
        rivernessPane=new FlowPane();
        rivernessPane.setHgap(20);
        setUpSlider(riverness);
        riverness.setId("riverness");
        rivernessULabel=new Label("riverness:");
        rivernessL=new Label(Integer.toString((int)riverness.getValue()));
        rivernessPane.getChildren().addAll(rivernessULabel,riverness,rivernessL);
        rivernessPane.setAlignment(Pos.CENTER_RIGHT);
        size=new Slider();
        size.setMajorTickUnit(1);
        size.setMinorTickCount(0);
        size.setBlockIncrement(1);
//        size.
        size.setSnapToTicks(true);
        sizeULabel=new Label("size:");
        sizePane=new FlowPane();
        sizePane.setHgap(20);
        setUpSlider(size);
        size.setId("size");
        sizeL=new Label(Integer.toString((int)riverness.getValue()));
        sizePane.getChildren().addAll(sizeULabel,size,sizeL);
        sizePane.setAlignment(Pos.CENTER_RIGHT);
        backWOGen=new Button("BACK");
        //#endgroup
        back=new Button("RETURN AND GENERATE");
        box=new VBox(10);
        box.setAlignment(Pos.CENTER_RIGHT);
        box.setPadding(new Insets(80,80,80,80));
        box.getChildren().addAll(sizePane,dropletsPane, surfaceTensionPane, elasticityPane, flowForcePane, minPrecipitaionPane, rainfallVariationPane, rivernessPane,back,backWOGen);
        scene=new Scene(box,width,height);
        scene.getStylesheets().add("Graphics/ParamsScene.css");
        droplets.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number t1) {
                dropletsL.setText(String.format("%d", t1.intValue()));
            }
        });
        riverness.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number t1) {
                rivernessL.setText(String.format("%d", t1.intValue()));
            }
        });
        surfaceTension.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number t1) {
                surfaceTensionL.setText(String.format("%d", t1.intValue()));
            }
        });
        minPrecipitaion.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number t1) {
                minPrecipitaionL.setText(String.format("%d", t1.intValue()));
            }
        });
        elasticity.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number t1) {
                elasticityL.setText(String.format("%d", t1.intValue()));
            }
        });
        size.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number t1) {
                sizeL.setText(String.format("%d", t1.intValue()));
            }
        });
        rainfallVariation.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number t1) {
                rainfallVariationL.setText(String.format("%d", t1.intValue()));
            }
        });
        flowForce.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number t1) {
                flowForceL.setText(String.format("%d", t1.intValue()));
            }
        });
        back.setOnAction(e->{
            stage.setScene(GameSetupScene.GameSetupScene());
            try {
                BoardIO.setUpCanvasWithParams( (int)size.getValue(), (int)droplets.getValue(),(int) surfaceTension.getValue(), (int)flowForce.getValue(), (int) rainfallVariation.getValue(), (int) minPrecipitaion.getValue());
                BoardIO.setUpCanvas();
            } catch (FileNotFoundException ex) {
                ex.printStackTrace();
            }
        });
        backWOGen.setOnAction(e->stage.setScene(GameSetupScene.GameSetupScene()));

    }


    public static void setUpSlider(Slider s){
        s.setBlockIncrement(1);
        s.setMax(100);
        s.isSnapToTicks();
    }


    public static Scene getParamsScene(){

        return scene;
    }


    public static int getDroplets() {
        return (int) droplets.getValue();
    }

    public static int getElasticity() {
        return (int) elasticity.getValue();
    }

    public static int getFlowForce() {
        return (int) flowForce.getValue();
    }

    public static int getMinPrecipitaion() {
        return (int) minPrecipitaion.getValue();
    }

    public static int getRainfallVariation() {
        return (int) rainfallVariation.getValue();
    }

    public static int getRiverness() {
        return (int) riverness.getValue();
    }

    public static int getSurfaceTension() {
        return (int) surfaceTension.getValue();
    }

    public static int getSide() {
        return (int) size.getValue();
    }
}
