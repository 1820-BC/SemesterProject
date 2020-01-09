package Spaces;


import BoardStuff.TerrainTypes;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

import java.io.FileNotFoundException;

public class Forests extends Type {
    public Forests() throws FileNotFoundException {
        super();
        super.texture="\u001B[32m|*";//"*|()|*";
        super.setImage("forest");
    }
    @Override
    public String getLand() {
        return "forests";
    }

    @Override
    public TerrainTypes getTerrainType() {
        return TerrainTypes.FORESTS;
    }


    @Override
    public Image getImage() {
        return new Image("/Textures/forest.png");
    }
}
