package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client1 extends Thread {

    private Socket clientSocket;
    private PrintWriter out;
    private BufferedReader in;

    public void startConnection(String ip, int port) throws IOException {
        clientSocket = new Socket(ip, port);
        out = new PrintWriter(clientSocket.getOutputStream(), true);
        in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
    }

    public void sendMessage(String msg) throws IOException {
        out.println(msg);
        // String resp = in.readLine();
        // return resp;
    }

    public void stopConnection() throws IOException {
        in.close();
        out.close();
        clientSocket.close();
    }

    public static void main(String[] args) throws IOException {
        Client1 client = new Client1();
        client.startConnection("127.0.0.1", 9999);
        client.sendMessage("hello server");

        System.out.println(client.in.readLine());
        //System.out.println(response);
        Scanner scanner = new Scanner(System.in);
        client.runnable.run();
        while (true) {
            System.out.println("Waiting for input...");
            client.sendMessage(scanner.nextLine());
            //System.out.println(response);
        }
    }

    Runnable runnable = () ->{ while (true) {
        try {
            if (this.in.ready()) {
                String resp = in.readLine();
                System.out.println(resp);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }};
}

