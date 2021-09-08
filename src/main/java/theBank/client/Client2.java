package theBank.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Client2 {
    private Socket clientSocket;
    private PrintWriter out;
    private BufferedReader in;

    public void startConnection(String ip, int port) throws UnknownHostException, IOException {
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
    	Client1 client = new Client1();
        client.startConnection("127.0.0.1", 7777);
        Scanner scanner = new Scanner(System.in);
        String theInput = "";
        while(true) {
        	theInput = scanner.nextLine();
	        String response = client.sendMessage(theInput);
	        //assertEquals("hello client", response);
	    	System.out.println("SERVERS RESPONSE" + response);
        }
    }
}