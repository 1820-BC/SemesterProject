package Multiplayer;

import BoardStuff.Move;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

//A HostIO class that allows for host functionality
public class HostIO extends MultiplayerIO{

    private ServerSocket server;
    private Socket IOsocket;
    UpdationThread T;
//    private WaitScreenUpdationThread thread;

    @Override
    public void reset(){
        try {
            server.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public HostIO() throws IOException {
        server = new ServerSocket(4444);
        host=true;

    }

    public void sendMessage(String message){
        os.println(message);
    }

    public String getMessage() throws IOException {
        return in.readLine();
    }

    public void beginConnectionProcedure() throws IOException {
        IOsocket=server.accept();
        os=new PrintStream(IOsocket.getOutputStream());
        in=new BufferedReader(new InputStreamReader(IOsocket.getInputStream()));
    }
//
//    @Override
//    public Move getMovePlayer1() {
//        try {
//            return player1.remove();
//        }
//        catch(Exception e){
//            return null;
//        }
//    }



    public void sendWholeCanvas(String wholeBoard){
        os.println(wholeBoard);

    }

    @Override
    public void sendOkay() {
        os.println("RUN GAME");
    }

    @Override
    public void runUpdationThread() {
        T=new UpdationThread(IOsocket,in,os);
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

    public void sendName() throws FileNotFoundException {
//        sent=true;
        Scanner s=new Scanner(new File("src/Settings/name.txt"));
        String name=s.nextLine();
        os.println(name);
    }
//    public String getMessage();
}
