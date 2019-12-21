package Spaces;//type of territory which will include all necessary params for a location on the board including slots

import javafx.scene.image.Image;

import java.io.FileInputStream;
import java.io.FileNotFoundException;


public abstract class Type {
    /*
    * Textures should be in format L()R where L is on the left of unit and R is on the right of the unit
    * L and R may have length 2
    * */
    protected String texture;
    protected Image image;

    public Image getImage() {
        return null;
    }

    public Image getTexture(){
        return image;
    }

    public void setImage(String name) throws FileNotFoundException {
        image=new Image("/Textures/"+name+".png");
    }

    public abstract String getLand();

}
