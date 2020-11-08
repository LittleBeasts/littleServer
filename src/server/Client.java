package server;

import java.io.*;
import java.net.Socket;
import java.util.UUID;

public class Client{

    // TODO: Complete Client

    private Socket socket;
    private BufferedReader inputReader;
    private PrintWriter outWriter;
    private String uuid;

    Client(Socket socket){
        this.socket = socket;
        try {
            this.inputReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            this.outWriter = new PrintWriter(socket.getOutputStream(), true);
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.uuid = UUID.randomUUID().toString();
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
}
