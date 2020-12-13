package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketThread extends Thread {
    //This Thread accepts clients

    private ServerSocket serverSocket;
    private Server server;

    SocketThread(int port, Server server) throws IOException {
        serverSocket = new ServerSocket(port);
        this.server = server;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Socket clientSocket = this.serverSocket.accept();
                Client client = new Client(clientSocket, "User");
                PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                // TODO: Protocol Gedöns here
                server.getLandingRoom().registerClientInLobby(client);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
