package serpis.psp;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;
import java.util.Scanner;

public class TCPServer{
	public static void main(String[] args) throws IOException {
		
		//HolaMundo();
		pingPong();
		//connectAndWrite();
	}
	
	private static void pingPong() throws IOException{
		int port = 10001;
		String mensaje;

			ServerSocket serverSocket = new ServerSocket(port);
			Socket socket = serverSocket.accept();

			BufferedReader bufferedReader = new BufferedReader( new InputStreamReader ( socket.getInputStream() ) );
			PrintWriter printWriter = new PrintWriter ( socket.getOutputStream() );
		
			mensaje = bufferedReader.readLine();//Leyendo mensaje
			System.out.println("Mensaje recibido: " + mensaje);
			System.out.println("Enviando mensaje modificado: "+mensaje.toLowerCase());
		
			System.out.println("Mensaje enviado.");//Enviando mensaje
			printWriter.println(mensaje.toLowerCase());//Aqui lo envia.
	
			printWriter.close();
			bufferedReader.close();
			socket.close();
			serverSocket.close();
		

		
	}
	
	private static void connectAndWrite() throws IOException{
		int port = 10001;
		String mensaje;

			ServerSocket serverSocket = new ServerSocket(port);
			Socket socket = serverSocket.accept();

			PrintWriter printWriter = new PrintWriter ( socket.getOutputStream(), true );
	
			mensaje = "Prueba del Servidor. -"  + new Date();
			System.out.println("Mensaje enviado: " + mensaje);
	
			printWriter.println(mensaje);
			
			printWriter.close();
			socket.close();
			serverSocket.close();
		
		
	}
	
	private static void HolaMundo()throws IOException {
		
		int port = 10001;
		ServerSocket serverSocket = new ServerSocket(port);
		
		Socket socket = serverSocket.accept();
		
		Scanner scanner = new Scanner(socket.getInputStream());
		
		String line = scanner.nextLine();//aqui lee el mensaje
		System.out.println("Line = " + line);//aqui imprime el mensaje
		
		socket.close();
		serverSocket.close();

	}
}