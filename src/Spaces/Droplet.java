package Spaces;

/*
A type class that creates a droplet for world creation
It randomly chooses a seed to plant a certain type of land
 */

import BoardStuff.TerrainTypes;
import javafx.scene.image.Image;

public class Droplet extends Type{

    private String seed;

    public Droplet(){
        super();
        super.texture="??????";
        int a=(int)(Math.random()*3);
        if(a==1){
            seed="mountains";
            return;
        }
        else if(a==2){
            seed="fields";
        }
        else{
            seed="forests";
        }
    }
    @Override
    public String getLand() {
        return seed;
    }

    @Override
    public TerrainTypes getTerrainType() {
        return null;
    }

}
