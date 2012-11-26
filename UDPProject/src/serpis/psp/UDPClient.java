package serpis.psp;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UDPClient {
	private static DatagramSocket datagramSocket;
	private static DatagramPacket datagramPacket;

	public static void main(String[] args) throws IOException {
		String text = "Hola desde udp client";
		byte[] buf = text.getBytes();
		InetAddress inetAddress = InetAddress.getByName("127.0.0.1");
		int port = 10001;
		
		datagramPacket = new DatagramPacket(buf, buf.length, inetAddress, port);
		datagramSocket = new DatagramSocket();
		datagramSocket.send(datagramPacket);
		System.out.println("UDPClient fin.");
		
	}
}