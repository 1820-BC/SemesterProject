package Multiplayer;

import BoardStuff.Move;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

//A HostIO class that allows for host functionality
public class HostIO extends MultiplayerIO{

    private ServerSocket server;
    private Socket IOsocket;




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
        os=new PrintWriter(IOsocket.getOutputStream());
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


    public void addToQueue(Move move){
        player1.add(move);
    }





//    public String getMessage();
}
