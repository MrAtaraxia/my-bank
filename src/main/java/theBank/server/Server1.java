package theBank.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import theBank.*;

public class Server1 {
    private ServerSocket serverSocket;

    public void start(int port) throws IOException {
        serverSocket = new ServerSocket(port);
        while (true)
            new ClientHandler(serverSocket.accept()).start();
    }

    public void stop() throws IOException {
        serverSocket.close();
    }

    private static class ClientHandler extends Thread {
        private Socket clientSocket;
        private PrintWriter out;
        private BufferedReader in;

        public ClientHandler(Socket socket) {
            this.clientSocket = socket;
        }

        public void run() {
            try {
				out = new PrintWriter(clientSocket.getOutputStream(), true);
	            in = new BufferedReader(
	                    new InputStreamReader(clientSocket.getInputStream()));
            } catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

            String inputLine;
            try {
				while ((inputLine = in.readLine()) != null) {
				    if ("quit".equalsIgnoreCase(inputLine)) {
				        out.println("bye");
				        break;
				    }
					Main myMain = null;
					try {
						myMain = new Main(clientSocket.getInputStream(), clientSocket.getOutputStream());
					} catch (Exception e) {
						e.printStackTrace();
					}
					
					try {
						myMain.run();
					} catch (Exception e) {
						e.printStackTrace();
					}
					
				    out.println(inputLine);
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            try {
				in.close();
                out.close();
				clientSocket.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
    }
    public static void main(String[] args) {
    	Server1 server = new Server1();
    	try {
    		System.out.println("Server Started");
			server.start(7777);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}