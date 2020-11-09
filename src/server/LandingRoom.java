package server;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class LandingRoom extends Thread {

    private Server server;
    private Room room;

    LandingRoom(Server server) {
        super("Lobby");
        this.room = new Room("62eaf943-8637-4dab-932c-cdb46128fa4d");
        this.room.stopThread();
        this.server = server;
    }

    public List<Room> getRoomList() {
        return this.server.getRoomList();
    }

    public void registerClientInLobby(Client client) {
        this.room.registerClient(client);
        PrintWriter outWriter = client.getOutWriter();
        // TODO: Protokoll gedöns für Raumliste hier
        outWriter.println("Welcome to the Lobby!");
    }

    @Override
    public void run() {
        // TODO: Lässt nur raumverwaltung & Chat zu
        System.out.println("Landing Room Thread running");
        int cint = 100000;
        while (true) {
            List<Client> clientList = this.room.getClientList();
            if (cint++ % 400000000 == 0) {
                // System.out.println(clientList.size());
                for (Client client : clientList) {
                    //System.out.println("checking client: " + client.getUuid());
                    try {
                        //System.out.println(client.getInputReader().ready());
                        if (client.getInputReader().ready()) {
                            String message = client.getInputReader().readLine();
                            System.out.println(message);
                            broadcastMessage(message, client);
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    private void broadcastMessage(String message, Client sender) {
        System.out.println("Broadcasting: " + message);
        for (Client client :
                this.room.getClientList()) {
            if (!client.getUuid().equals(sender.getUuid()))
                client.getOutWriter().println(sender.getUuid() + ": " + message);
        }
    }
}
