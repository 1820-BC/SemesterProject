package BoardStuff;

import Pieces.EmptyPiece;
import Pieces.*;
import Scenes.ParamsScene;
import Spaces.*;
import Functions.Functions;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

//Low Level Process Board Class That Allows for Low Level Running
public class Board {

    ArrayList<ArrayList<Space>> board=new ArrayList();

    //Parameters for Generation and setting params
    private int droplets=30; //roughly 2/3rds of the largest landscape side
    private int surfaceTension=3; //or radius, roughly 1/10 of largest landscape side
    private int elasticity=2; //elasticity is among that the surface tension can vary, creates less square landscape, should be roughly half the radius

    private int flowForce=15; //The max length of rivers, equal to ~4/9 longest side length
    private int minPrecipitaion=10; //the minimum number of river starters, equal to one third of longest side length
    private int rainfallVariation=3; //the variation from that minimum, about a third of minPrecipitation
    private int riverness=45; //the range from 50 of the likely hood that a river will go one way over the other. See .txt file of world generation description. 30 is recommended

    private int numberOfVillages; //this number represents the number of villages generated in the board. It is used to generate a number of village droplets
    private int sizeOfVillages; // radius of village area that is used during droplets
    private double villageDensity; // The likely hood that a village is generated for each spot within a village radius. Domain of value is 1 to 100

    int sizeX;
    int sizeY;
    int[] pointer=new int[2];



    //sets board parameters using only x, y, and minprecipitation. The other values are calculated through formulas acquired empirically
    public void setParams(int minPrecipitation, int x, int y){
        sizeX=x;
        sizeY=y;
        droplets=x*x*x/6+1;
        surfaceTension=x/7+3;
        elasticity=surfaceTension/2+1;
        flowForce=4*x/9;
        rainfallVariation=minPrecipitation/4;
        this.minPrecipitaion=minPrecipitation;
        this.numberOfVillages=(int)Math.sqrt(x-20)/2;
        this.sizeOfVillages=x/2;
        this.villageDensity=5;
    }
    public void setParamsFromVals(int side, int droplets,int surfaceTension,int flowForce, int rainfallVariation, int minPrecipitaion,int numVillages,int villageDensity,int sizeOfVillages){
        this.sizeY=side;
        this.sizeX=side;
        this.droplets=droplets;
        this.surfaceTension=surfaceTension;
        this.flowForce=flowForce;
        this.rainfallVariation=rainfallVariation;
        this.minPrecipitaion=minPrecipitaion;
        ;this.numberOfVillages=numVillages;
        this.sizeOfVillages=sizeOfVillages;
        this.villageDensity=villageDensity;
    }




    /*
    This constructor generates a world through a procedural generation algorithm that was designed specifically for this project
    For more information on the algorithm, see to the file "Documentation"
     */



    //============================= Constructors and Helper Methods ================================\\

    public void setupBoard(String bd) throws FileNotFoundException {

        String line;
        String[] list;
        ArrayList<Space> row=new ArrayList<>();

        line=bd;
        System.out.println(line);
        list=line.split("==");
        for(String string3:list){
            ;
            String[] string2=string3.split("-");
            for(String string:string2) {
                if (string.equals("mountains")) {
                    row.add(new Space(new Mountains(), new EmptyPiece()));
                } else if (string.equals("fields")) {
                    row.add(new Space(new Fields(), new EmptyPiece()));
                } else if (string.equals("forests")) {
                    row.add(new Space(new Forests(), new EmptyPiece()));
                } else if (string.equals("rivers")) {
                    row.add(new Space(new Rivers(), new EmptyPiece()));
                } else {
                    row.add(new Space(new Bridges(), new EmptyPiece()));
                }


            }
            board.add((ArrayList<Space>)row.clone());
            row.clear();
            //System.out.println(board.size());
        }
        sizeX=board.size();
        sizeY=board.size();
        System.out.println("Board Size: "+board.size());
    }
    public Board(){
        return;
    }

    public Board(int minPrec,int sizeX,int sizeY) throws FileNotFoundException {
        setParams(minPrec, sizeX, sizeY);
        setupSelf();
        addTowns(sizeX);;
        addCapitals(sizeX);
    }
    public Board(int side, int droplets,int surfaceTension,int flowForce, int rainfallVariation, int minPrecipitaion,int numVillages,int villageDensity,int numberOfVillages) throws FileNotFoundException {
        setParamsFromVals(side, droplets, surfaceTension, flowForce,  rainfallVariation, minPrecipitaion,numVillages,villageDensity,numberOfVillages);
        setupSelf();
        addTowns(side);
        addCapitals(side);
    }
    public void setupSelf() throws FileNotFoundException {
        pointer[0] = 0;
        pointer[1] = 0;
        ArrayList<Space> placeHolder = new ArrayList(sizeX);
        board=new ArrayList(sizeY);
        for (int a=0;a<sizeY;a++) { //Map is initialized. O(x*y)
            for (int b=0;b<sizeY;b++) {

                placeHolder.add(new Space(new Forests(), new EmptyPiece()));
            }
            board.add((ArrayList<Space>) placeHolder.clone());
            placeHolder.clear();
        }
        int x;
        int y;
        ArrayList<int[]> drops = new ArrayList();
        ArrayList<Droplet> ds = new ArrayList();
        for (int a=0;a<=droplets;a++) {
            x = (int) (Math.random() * sizeX);
            y = (int) (Math.random() * sizeY);
            Droplet d = new Droplet();
            board.get(y).set(x, new Space(d, new EmptyPiece()));
            int[] i = {x, y}; //setting up a save for later referral when placing land types
            ds.add(d);
            drops.add(i);
        }
        System.out.println("A");
        x = -1;
        y = -1;
        for (ArrayList<Space> l : board) {
            y++;
            x = -1;
            for (Space s : l) {
                x++;
                int i = -1;
                for (int[] lP : drops) {
                    i++;
                    if (Math.abs(x - lP[0]) - (int) (Math.random() * elasticity + 1) <= surfaceTension) {
                        if (Math.abs(y - lP[1]) - (int) (Math.random() * elasticity + 1) <= surfaceTension) { //if the point is in range of a droplet, then figure out the seed and set the type
                            if (ds.get(i).getLand() == "mountains") {
                                board.get(y).set(x, new Space(new Mountains(), new EmptyPiece()));
                            } else if (ds.get(i).getLand() == "fields") {
                                board.get(y).set(x, new Space(new Fields(), new EmptyPiece()));
                            } else {
                                board.get(y).set(x, new Space(new Forests(), new EmptyPiece()));
                            }
                            break;
                        }
                    }
                }

            }

        }
        System.out.println('B');
        //now to add the rivers. Rivers start from a random edge piece and wind through the landscape until it either reaches the side or
        //hits the maximum specified under "flow force"
        //the variable "industriality" specifies the number of bridges as a percent of their creation. The second water in a chain is always a bridge
        //the variable "min percipitation" specifies the number of rivers.
        //the variable "rainfall variation" is the possible deviation from this number, with percipitation being the minimum
        int numberOfRivers = minPrecipitaion + (int) (Math.random() * (rainfallVariation + 1));

        for (int i : Functions.range(numberOfRivers)) {

            setPointer((int) (Math.random() * sizeX), (int) (Math.random() * sizeY));
            int a = (int) (Math.random() * riverness) + 50; //This means that each river will be slightly different. The riverness changes the possible range of this value
            for (int i2 : Functions.range(flowForce)) {

                //place a river
                int random = (int) (Math.random() * 101);

                board.get(pointer[1]).set(pointer[0], new Space(new Rivers(), new EmptyPiece()));

                int rand = (int) (Math.random() * 101);
                if (Functions.isEven(i2)) {
                    if (rand <= a) {
                        setPointer(pointer[0] + 1, pointer[1]);
                    } else {
                        setPointer(pointer[0] - 1, pointer[1]);
                    }
                } else {
                    if (rand <= a) {
                        setPointer(pointer[0], pointer[1] + 1);
                    } else {
                        setPointer(pointer[0], pointer[1] - 1);
                    }
                }
            }
        }
        System.out.println("Board Size: "+board.size());
    }


    private void addTowns(int sizeX){
        ArrayList<int[]> villages=new ArrayList<>();
        for(int i=0;i<numberOfVillages;i++){
            int x=(int)(Math.random()*(sizeX-2)+1);
            int y=(int)(Math.random()*(sizeX-2)+1);
//            System.out.println("X VAL FOR VILLAGE:"+x);
//            System.out.println("Y VAL FOR VILLAGE"+y);
            try {
                villages.add(new int[] {x,y});
                board.get(x).set(y,new Space(new Village(),new EmptyPiece()));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

        }
        for(int[] location:villages){
            for(int x=(-sizeOfVillages/2);x<sizeOfVillages/2;x++){
                for(int y=(-sizeOfVillages/2);y<sizeOfVillages/2;y++){
                    if(location[0]-x<0||location[1]-y<0||location[0]-x>=sizeX||location[1]-y>=sizeX||board.get(location[0]-x).get(location[1]-y).getType().getLand().equals("rivers")){
                        continue;
                    }
                    double randVar=Math.random();
                    if(randVar<=villageDensity/100){
                        try {
                            board.get(location[0]-x).set(location[1]-y,new Space(new Village(),new EmptyPiece()));
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }



    }


    private void addCapitals(int side){
        setPointer(0,0);
        try {
            setSpaceFromPointer(new Space(new Fields(),new Capital(Teams.Red)));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        setPointer(side-1,side-1);
        try {
            setSpaceFromPointer(new Space(new Fields(),new Capital(Teams.Blue)));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /*
    This constructor allows for a board to be generated from a save file of a world.
    It runs by splitting each line of the world save file and, depending on the type stored there, adds something to the board

     */
    public Board(String name) throws FileNotFoundException {
        board.clear();
        Scanner s=new Scanner(new File("./src/BoardStuff/SavedMaps/" + name));
        String line;
        String[] list;
        ArrayList<Space> row=new ArrayList<>();

        line=s.nextLine();
        System.out.println(line);
        list=line.split("==");
        for(String string3:list){
            ;
            String[] string2=string3.split("-");
            for(String string:string2) {
                if (string.equals("mountains")) {
                    row.add(new Space(new Mountains(), new EmptyPiece()));
                } else if (string.equals("fields")) {
                    row.add(new Space(new Fields(), new EmptyPiece()));
                } else if (string.equals("forests")) {
                    row.add(new Space(new Forests(), new EmptyPiece()));
                } else if (string.equals("rivers")) {
                    row.add(new Space(new Rivers(), new EmptyPiece()));
                } else {
                    row.add(new Space(new Bridges(), new EmptyPiece()));
                }


        }
        board.add((ArrayList<Space>)row.clone());
        row.clear();
        //System.out.println(board.size());
        }

        s.reset();
        s.close();

        sizeX=board.size();
        sizeY=board.size();
        addCapitals(sizeX);
    }


    //Saves board to txt file name
    //Will return true is successful and false if file already exists
    public boolean saveToFile(String name) throws IOException {
        File f=new File("./src/BoardStuff/SavedMaps/" + name);
        f.createNewFile();
        if(sizeX==0){
            throw new IOException();
        }
        BufferedWriter writer = new BufferedWriter(new FileWriter(f));
        for (ArrayList<Space> a : board) {
            for (Space s : a) {
                writer.write(s.getType().getLand() + "-");
            }
            writer.write("==");



        }
        writer.close();
        return true;

    }

    //============================ Comms ======================================\\

    public String getEncodedBoard(){
        String writer = new String();
        for (ArrayList<Space> a : board) {
            for (Space s : a) {
                writer+=(s.getType().getLand() + "-");
            }
            writer+="==";
        }
//        writer.close();
        return writer;
    }

    //============================= Low Level Operations ================================\\



    //moves the pointer to a specified location
    //will return false and do nothing if pointer goes out of bounds
    public boolean setPointer(int x, int y){
        if(x>=sizeX||y>=sizeY||x<0||y<0){
//            System.out.println("oof");
            return false;
        }

        pointer[0]=x;
        pointer[1]=y;
        return true;
    }
    public int getXFromPointer(){
        return pointer[0];
    }
    public int getYFromPointer(){
        return pointer[1];
    }
    //moves the pointer by dX and dY in their repspective directions. Runs like setPointer in terms of returns
    public boolean movePointer(int dX,int dY){
        return setPointer(pointer[0]+dX,pointer[1]+dY);
    }


    //gets space at pointer
    public Space getSpaceFromPointer(){
        return board.get(pointer[1]).get(pointer[0]);
    }

    //gets type at pointer
    public Type getTypeFromPointer(){
        return board.get(pointer[1]).get(pointer[0]).getType();
    }

    public PieceTypes getPieceTypeFromPointer(){
        return board.get(pointer[1]).get(pointer[0]).getPieceType();
    }

    //gets piece at pointer
    public Piece getPieceFromPointer(){
        return board.get(pointer[1]).get(pointer[0]).getPiece();
    }

    //sets type at pointer
    public void setTypeFromPointer(Type t){
        board.get(pointer[1]).get(pointer[0]).setType(t);
    }

    //sets piece at pointer
    public void setPieceFromPointer(PieceTypes p,Teams team){
//        System.out.println(p);
        if(p==PieceTypes.EMPTY){
            board.get(pointer[1]).get(pointer[0]).remove(PieceTypes.ARCHER);
            board.get(pointer[1]).get(pointer[0]).remove(PieceTypes.INFANTRY);
            board.get(pointer[1]).get(pointer[0]).remove(PieceTypes.CALVERY);
            board.get(pointer[1]).get(pointer[0]).remove(PieceTypes.TREBUCHET);
            board.get(pointer[1]).get(pointer[0]).remove(PieceTypes.BARGE);
            board.get(pointer[1]).get(pointer[0]).remove(PieceTypes.BATTLE_SHIP);
            board.get(pointer[1]).get(pointer[0]).remove(PieceTypes.WALL);
            board.get(pointer[1]).get(pointer[0]).remove(PieceTypes.FACTORY);


//            board.get(pointer[1]).get(pointer[0]).remove(PiececTypes.);
        }
        board.get(pointer[1]).get(pointer[0]).setPiece(p,team);//.setPiece(p);
    }

    public void setSpaceFromPointer(Space s){board.get(pointer[1]).set(pointer[0],s);}

    //prints board in terminal - useful for debugging
    public void print(){
        for(ArrayList<Space> a:board){

            for(Space s:a){
                System.out.print(s.getType().getTexture()+"|");
            }
            System.out.println("");
        }
    }

    public String getX() {
        return ""+pointer[0];
    }

    public String getY() {
        return ""+pointer[1];
    }

    //============================= Interaction ================================\\


    public void setPiece(int x, int y,Piece piece){
        board.get(y).get(x).setPiece(piece);
    }


    public void setType(int x, int y, Type type){
        board.get(y).get(x).setType(type);
    }


    public int getSize(){
        return sizeX;
    }

    public double getDoubleSize(){ return sizeX; }

    public ArrayList<ArrayList<Space>> getBoard() {
        return board;
    }

    public PieceTypes On(Teams team) {
        if(team==Teams.Red){
            setPointer(0,0);
            return getPieceTypeFromPointer();
        }
        setPointer(sizeX-1,sizeX-1); //check this thing for other error!!!
//        System.out.println(getPieceTypeFromPointer());
        return getPieceTypeFromPointer();
    }

    public Teams onTeam(Teams team) {
        if(team==Teams.Red){
            setPointer(0,0);
            return getPieceFromPointer().getTeam();
        }
        setPointer(sizeX,sizeX); //check this thing for other error!!!
        return getPieceFromPointer().getTeam();
    }


    // public void movePiece(Vector V, int x, int y){
   //     setPointer(x,y);
//
  //  }
//



}
