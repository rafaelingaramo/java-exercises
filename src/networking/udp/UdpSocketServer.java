package networking.udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.nio.charset.StandardCharsets;

public class UdpSocketServer {
    public static void main(String[] args) throws IOException {
        new UdpSocketServer().run();
    }

    public void run() throws IOException {
        System.out.println("Accepting new connections");
        try(DatagramSocket socket = new DatagramSocket(4445)) {
            while (true) {
                byte[] buf = new byte[256];
                DatagramPacket packet = new DatagramPacket(buf, buf.length);
                socket.receive(packet);

                byte[] data = packet.getData();
                String request = new String(data, StandardCharsets.UTF_8).substring(0, packet.getLength());
                System.out.println("Data received from client: " + request);

                InetAddress address = packet.getAddress();
                int port = packet.getPort();
                buf = ("Echo: " + request).getBytes();
                DatagramPacket response = new DatagramPacket(buf, buf.length, address, port);
                socket.send(response);

                if (request.equals("Bye.")) {
                    break;
                }
            }
        }
    }
}
