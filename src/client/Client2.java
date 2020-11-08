package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client2 {
    private Socket clientSocket;
    private PrintWriter out;
    private BufferedReader in;

    public void startConnection(String ip, int port) throws IOException {
        clientSocket = new Socket(ip, port);
        out = new PrintWriter(clientSocket.getOutputStream(), true);
        in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
    }

    public String sendMessage(String msg) throws IOException {
        out.println(msg);
        String resp = in.readLine();
        return resp;
    }

    public void stopConnection() throws IOException {
        in.close();
        out.close();
        clientSocket.close();
    }

    public static void main(String[] args) throws IOException {
        Client2 client = new Client2();
        client.startConnection("127.0.0.1", 9999);
        String response = client.sendMessage("hello server");
        System.out.println(response);
        Scanner scanner = new Scanner(System.in);
        while (true){
            String in = scanner.nextLine();
            System.out.println("Eingabe: " + in);
            response = client.sendMessage(in);
            System.out.println(response);
        }
    }
}
