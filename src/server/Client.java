package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.UUID;

public class Client {

    // TODO: Complete Client

    private Socket socket;
    private BufferedReader inputReader;
    private PrintWriter outWriter;
    private String uuid;
    private String name;

    Client(Socket socket, String name) {
        this.socket = socket;
        try {
            this.inputReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            this.outWriter = new PrintWriter(socket.getOutputStream(), true);
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.uuid = UUID.randomUUID().toString();
        this.name = name;
    }

    public BufferedReader getInputReader() {
        return inputReader;
    }

    public PrintWriter getOutWriter() {
        return outWriter;
    }

    public String getUuid() {
        return uuid;
    }

    public Socket getSocket() {
        return socket;
    }

    public String getName() {
        return name;
    }
}
