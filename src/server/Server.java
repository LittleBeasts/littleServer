package server;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Server {

    private List<Room> roomList;
    private LandingRoom landingRoom;

    Server() {
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

    public void addRoom(String roomName) {
        this.roomList.add(new Room(roomName));
    }

    public List<Room> getRoomList() {
        return roomList;
    }

    public LandingRoom getLandingRoom() {
        return landingRoom;
    }

    public static void main(String[] args) throws IOException {
        Server server = new Server();

    }
}
