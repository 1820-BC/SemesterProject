public class resourceCost {
    public int lumber;
    public int metal;
    public int stone;
    public resourceCost(int lumber, int metal, int stone){
        setResources(lumber,metal,stone);
    }
    public resourceCost(resourceCost r){
        setResources(r.getLumber(),r.getMetal(),r.getStone());
    }
    public int getLumber(){
        return lumber;
    }
    public int getMetal(){
        return metal;
    }
    public int getStone(){
        return stone;
    }

    public void setLumber(int lumber) {
        this.lumber = lumber;
    }


    public void setMetal(int metal) {
        this.metal = metal;
    }

    public void setStone(int stone) {
        this.stone = stone;
    }
    public void setResources(int lumber, int metal, int stone){
        this.lumber=lumber;
        this.metal=metal;
        this.stone=stone;
    }
    public void setResources(resourceCost r){
        setResources(r.getLumber(),r.getMetal(),r.getStone());
    }
}

