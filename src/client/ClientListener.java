package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

import static protocol.Message.decodeMessage;

public class ClientListener extends Thread {

    private BufferedReader in;
    private Socket socket;

    ClientListener(Socket socket) throws IOException {
        this.socket = socket;
        this.in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        this.start();
    }

    @Override
    public void run() {
        while (true) {
            try {
                if (in.ready()) {
                    String resp = in.readLine();
                    System.out.println(decodeMessage(resp));
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


}
