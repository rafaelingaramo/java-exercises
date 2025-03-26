package networking.udp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;
import java.nio.charset.StandardCharsets;

public class UdpSocketClient {
    public static void main(String[] args) throws IOException {
        new UdpSocketClient().run();
    }

    public void run() throws IOException {
        System.out.println("Waiting for user input");
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try (DatagramSocket socket = new DatagramSocket()) {
            byte[] buf;
            InetAddress address = InetAddress.getByName("127.0.0.1");
            while(true) {
                String nextLine = reader.readLine();
                buf = nextLine.getBytes(StandardCharsets.UTF_8);
                DatagramPacket packet = new DatagramPacket(buf, buf.length, address, 4445);
                socket.send(packet);
                buf = new byte[256];
                DatagramPacket response = new DatagramPacket(buf, buf.length);
                socket.receive(response);
                System.out.println("Response from server: " + new String(response.getData()).substring(0, response.getLength()));

                if (nextLine.equals("Bye.")) {
                    break;
                }
            }
        }
    }
}
