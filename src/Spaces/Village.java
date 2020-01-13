package Spaces;

import BoardStuff.TerrainTypes;
import javafx.scene.image.Image;

import java.io.FileNotFoundException;

public class Village extends Type {


    public Village() throws FileNotFoundException {
        super();
        super.texture="\u001B[34m~~";
        super.setImage("village");
    }
    @Override
    public String getLand() {
        return "village";
    }

    @Override
    public TerrainTypes getTerrainType() {
        return TerrainTypes.VILLAGE;
    }

    @Override
    public Image getImage() {
        return new Image("/Textures/village.png");
    }


}
