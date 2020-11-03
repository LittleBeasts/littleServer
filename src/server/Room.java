package server;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Room implements Runnable {

    private List<Client> clientList;
    private String uuid;
    private String roomName;

    Room(String roomName){
        this.clientList = new ArrayList<>();
        this.roomName = roomName;
        this.uuid = UUID.randomUUID().toString();
    }

    public void registerClient(Client client){
        clientList.add(client);
    }

    public void removeClient(Client client){
        clientList.remove(client);
    }

    public void sendMessage() throws IOException {
        System.out.println("Message sent");
        for (Client client: this.clientList
             ) {
            PrintWriter out = new PrintWriter(client.getSocket().getOutputStream(), true);
            out.println("HELLO THERE");
        }
    }

    public List<Client> getClientList() {
        return clientList;
    }

    public String getUuid() {
        return uuid;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setClientList(List<Client> clientList) {
        this.clientList = clientList;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    @Override
    public void run() {
        // TODO: Timeslotted Client Message Listening
    }
}
