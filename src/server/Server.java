package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Server {
//    private ServerSocket serverSocket;
//    private Socket clientSocket;
//    private PrintWriter out;
//    private BufferedReader in;
//
//    public void start(int port) throws IOException {
//        serverSocket = new ServerSocket(port);
//        clientSocket = serverSocket.accept();
//        System.out.println(clientSocket.toString());
//        out = new PrintWriter(clientSocket.getOutputStream(), true);
//        in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
//        String greeting = in.readLine();
//        if ("hello server".equals(greeting)) {
//            out.println("hello client");
//        }
//        else {
//            out.println("unrecognised greeting");
//        }
//    }
//
//    public void stop() throws IOException {
//        in.close();
//        out.close();
//        clientSocket.close();
//        serverSocket.close();
//    }

    private List<Room> roomList;
    private LandingRoom landingRoom;

    Server(){
        roomList = new ArrayList<>();
        try {
            SocketThread socketThread = new SocketThread(9999, this);
            socketThread.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.landingRoom = new LandingRoom(this);
        this.landingRoom.start();
    }

    public void addRoom(String roomName){
        this.roomList.add(new Room(roomName));
    }

    public List<Room> getRoomList() {
        return roomList;
    }

    public LandingRoom getLandingRoom() {
        return landingRoom;
    }

    public static void main(String[] args) throws IOException {
        Server server=new Server();

    }
}
