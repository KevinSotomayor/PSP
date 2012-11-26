package serpis.psp;
import java.io.IOException;
import java.net.*;

public class UDPServer {
	private static DatagramSocket datagramsocket;
	private static DatagramPacket datagramPacket;

	public static void main(String[] args) throws IOException {
		byte[] buf = new byte[2048];
		int puerto = 10001;

		datagramPacket = new DatagramPacket(buf, buf.length);
        datagramsocket = new DatagramSocket(puerto);
        datagramsocket.receive(datagramPacket);
        String data = new String(datagramPacket.getData(), 0, datagramPacket.getLength());

   	    System.out.println("El mensaje es: " + data + " El puerto es: " + datagramPacket.getPort() + 
        		" La direccion ip es: " + datagramPacket.getAddress());
   	    
        /**************************** Preguntar la diferencia*************************/
        System.out.println("El mensaje es: " + datagramPacket.getData().toString() + 
        		" Su longitud es: " + datagramPacket.getLength() + " El puerto es: "
        		+ datagramPacket.getPort() + " La direccion ip es: " + datagramPacket.getAddress());
	}
}