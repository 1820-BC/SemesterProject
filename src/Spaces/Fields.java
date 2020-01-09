package Spaces;

import BoardStuff.TerrainTypes;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

import java.io.FileNotFoundException;

/*
public static final String ANSI_BLACK = "\u001B[30m";
public static final String ANSI_RED = "\u001B[31m";
public static final String ANSI_GREEN = "\u001B[32m";


 */
public class Fields extends Type{
    public Fields() throws FileNotFoundException {
        super();
        super.texture="\u001B[33m__";//"__()__";
        super.setImage("plains");
    }
    @Override
    public String getLand() {
        return "fields";
    }

    @Override
    public TerrainTypes getTerrainType() {
        return TerrainTypes.PLAINS;
    }


    @Override
    public Image getImage() {
        return new Image("/Textures/plains.png");
    }
}
