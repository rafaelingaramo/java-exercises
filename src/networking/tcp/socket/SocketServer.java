package networking.tcp.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketServer {
    public static void main(String[] args) throws IOException {
        new SocketServer().run();
    }

    public void run() throws IOException {
        try (ServerSocket server = new ServerSocket(9999)) {
            Socket client = server.accept();
            PrintWriter out = new PrintWriter(client.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));

            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                out.println("ServerSays: " + inputLine);
                if (inputLine.equals("Bye.")) {
                    break;
                }
            }
        }
    }
}
