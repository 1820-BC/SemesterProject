package Multiplayer;

import BoardStuff.Move;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;


//ClientIO class that allows for client functionality
public class ClientIO extends MultiplayerIO{

     Socket socket;

     boolean sent;


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



//    @Override
//    public Move getMovePlayer1() {
//         null;
//    }

//    public void sendMove();

    public void addToQueue(Move move){
       player1.add(move);
    }

    @Override
    public void sendOkay() {
        return;
    }

    @Override
    public void runUpdationThread() {
        UpdationThread T=new UpdationThread(socket,in,os);
        T.start();
    }

    @Override
    public void runWaitScreenUpdationThread() {
        WaitScreenUpdationThread thread=new WaitScreenUpdationThread(socket,in,os);
        thread.start();
    }

    @Override
    public void stopWaitScreenUpdationThread() {
        return;
    }


}
