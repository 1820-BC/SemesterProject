package Spaces;

import BoardStuff.TerrainTypes;

public class Bridges extends Type {
    public Bridges(){
        super();
        super.texture="\033[0;37m||";
    }
    @Override
    public String getLand() {
        return "bridge";
    }

    @Override
    public TerrainTypes getTerrainType() {
        return null;
    }

}
