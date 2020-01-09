package Spaces;

import BoardStuff.TerrainTypes;
import javafx.scene.image.Image;

import java.io.FileNotFoundException;


public class Rivers extends Type {

    public Rivers() throws FileNotFoundException {
        super();
        super.texture="\u001B[34m~~";
        super.setImage("water");
    }
    @Override
    public String getLand() {
        return "rivers";
    }

    @Override
    public TerrainTypes getTerrainType() {
        return null;
    }

    @Override
    public Image getImage() {
        return new Image("/Textures/water.png");
    }


}
