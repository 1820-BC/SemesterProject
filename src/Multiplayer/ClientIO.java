package Multiplayer;

import BoardStuff.Move;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;


//ClientIO class that allows for client functionality
public class ClientIO extends MultiplayerIO{

     Socket socket;

     boolean sent;

     UpdationThread T;

     public void reset(){
         try {
             socket.close();
         } catch (IOException e) {
             e.printStackTrace();
         }
     }


    public ClientIO(String IP) throws IOException {
        super();
        host=false;
        socket=new Socket(IP,4444);
        os=new PrintStream(socket.getOutputStream());
        in=new BufferedReader(new InputStreamReader(socket.getInputStream()));

    }

    public ClientIO() {

        return;
    }

    public void setUp(String IP,int port) throws IOException{
        socket=new Socket(IP,port);
        try {
            os=new PrintStream(socket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            in=new BufferedReader(new InputStreamReader(socket.getInputStream()));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    public void sendName() throws FileNotFoundException {
        sent=true;
        Scanner s=new Scanner(new File("src/Settings/name.txt"));
        String name=s.nextLine();
        os.println(name);
    }
    public String getMessage() throws IOException {
        return in.readLine();
    }



//    @Override
//    public Move getMovePlayer1() {
//         null;
//    }

//    public void sendMove();



    @Override
    public void sendOkay() {
        return;
    }

    @Override
    public void runUpdationThread() {
        T=new UpdationThread(socket,in,os);
        T.start();
    }

    @Override
    public void endUpdationThread() {
        T.end();
    }

    @Override
    public void sendForWin(){
        os.println("WIN");
    }


}
