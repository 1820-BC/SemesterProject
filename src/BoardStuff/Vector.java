package BoardStuff;


//vector contains dx, dy, scaler
//it is used to make the move function more general and allow more easy movements
public class Vector {

    int dX;
    int dY;
    int scaler;


    public Vector(){
        dX=1;
        dY=1;
        scaler=2;
    }

    public Vector(int dX,int dY){
        this.dY=dY;
        this.dX=dX;
        this.scaler=2;
    }

    public void setdX(int dX) {
        this.dX = dX;
    }

    public void setdY(int dY) {
        this.dY = dY;
    }

    public void setScaler(int scaler) {
        this.scaler = scaler;
    }

    public int getScaler() {
        return scaler;
    }

    public int getdX(){
        return dX*scaler;
    }

    public int getdY() {
        return dY*scaler;
    }

    public void movePointer(Board b) {
        for (int x : Functions.Functions.range(scaler)){
            b.movePointer(dX, dY);
        }
    }


    public void setVals(int x, int y) {
        dX=x;
        dY=y;
    }

    public void setFromPiece(PieceTypes p) {
        scaler=Pieces.getMove(p);
    }
    public String toString(int x,int y){
        return ""+"("+(dX*scaler+x)+" , "+(-dY*scaler+y)+")";
    }
    public String toString(){
        if(dX==1){
            if(dY==0){
                return "RIGHT";
            }
            else if(dY==-1){
                return "DOWN-RIGHT";
            }
            else{
                return "UP-RIGHT";
            }
        }
        else if(dX==0){
            if(dY==-1){
                return "DOWN";
            }
            else{
                return "UP";
            }
        }
        else{
            if(dY==0){
                return "LEFT";
            }
            else if(dY==-1){
                return "DOWN-LEFT";
            }
            else{
                return "UP-LEFT";
            }
        }
    }
    public int getDirX(){
        return dX;
    }
    public int getDirY(){
        return dY;
    }
}
