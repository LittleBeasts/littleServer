package server;

import java.net.Socket;

public class Client{

    // TODO: Complete Client

    private Socket socket;

    Client(Socket socket){
        this.socket = socket;
    }

    public Socket getSocket() {
        return socket;
    }
}
