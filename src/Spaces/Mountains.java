package Spaces;

import BoardStuff.TerrainTypes;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

import java.io.FileNotFoundException;

public class Mountains extends Type {


    public Mountains() throws FileNotFoundException {
        super();
        super.texture = "\u001B[30m/\\";//"/\\()/\\";
        super.setImage("mountains");
    }

    @Override
    public String getLand() {
        return "mountains";
    }

    @Override
    public TerrainTypes getTerrainType() {
        return TerrainTypes.MOUNTAINS;
    }


    @Override
    public Image getImage() {
        return new Image("/Textures/mountains.png");
    }
}
