package serpis.psp;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Date;
import java.util.Scanner;

public class TCPClient {

	public static void main(String[] args) throws UnknownHostException, IOException {
		
		//HolaMundo();
		/* Primer metodo de conexión entre un cliente y un servidor.*/ 
		
		
		pingPong();
		/*Conexión entre cliente y servidor, el cliente le envia un mensaje y el servidor se lo devuelve el mismo, 
		 * pero todo en minúsculas. */
		//mesaneje + mensaje.toLowerCase();
		
		//connectAndRead(args);
		/* Al cliente le debemos pasar como parámetros(separados por espacios) el inetAddress y el puerto al que se
		 * debe conectar.
		 * Debe mostrar por consola todo lo que el servidor escriba.*/
		
		//connectUpvWeb();
	}
	
	
	private static void connectUpvWeb() throws IOException{
		String host ="www.upv.es";
		int port = 80;
		String line1 = "GET /index.html HTTP/1.1";
		String line2 = "";
		
		Socket socket = new Socket(host, port);
		
		OutputStream outputStream = socket.getOutputStream();
		PrintWriter printWriter = new PrintWriter(outputStream, true); //autoflush=true
		
		InputStream inputStream = socket.getInputStream();
		Scanner scanner = new Scanner(inputStream);
		
		printWriter.println(line1);
		printWriter.println(line2);
		
		while( scanner.hasNextLine())
			System.out.println(scanner.nextLine());
		printWriter.close();
		scanner.close();
		socket.close();
		
	}
	
	
	
	
	private static void pingPong() throws IOException{
		
		InetAddress inetAddress = InetAddress.getByName("127.0.0.1"); 		
		int port = 10001;
		String mensaje;
		InputStreamReader inputStreamReader = new InputStreamReader(System.in); 
		BufferedReader escribirTeclado = new BufferedReader(inputStreamReader);
		
			Socket socket = new Socket(inetAddress,port);
	
			OutputStream outputStream = socket.getOutputStream();
			PrintWriter PrintWriter = new PrintWriter(outputStream, true);
			
			InputStream inputStream = socket.getInputStream();
			BufferedReader	bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
			
			System.out.print("Mensaje a enviar: ");
			PrintWriter.println( escribirTeclado.readLine() ); //Enviando mensaje
			
			mensaje = bufferedReader.readLine();//Leyendo mensaje
			System.out.println("Mensaje devuelto por el servidor: " + mensaje);
	
		
			PrintWriter.close();
			bufferedReader.close();
			socket.close();

		}
	
        

	
	private static void connectAndRead(String[] args) throws IOException{
		
		String inetAddress = args[0]; 		
		int port = Integer.parseInt(args[1]);

			Socket socket = new Socket(inetAddress,port);
	
			InputStream inputStream = socket.getInputStream();
			Scanner scanner = new Scanner (inputStream);
			
			while (scanner.hasNextLine())
				System.out.println("Mensje recibido del servidor: " +scanner.nextLine());
			
			socket.close();
	
	}
	               
	
	
	
	private static void HolaMundo()throws UnknownHostException, IOException {
		
		InetAddress inetAddress = InetAddress.getByName("127.0.0.1"); 
		int port = 10001;
		Socket socket = new Socket(inetAddress, port);
		
		OutputStream outputStream = socket.getOutputStream();
		
		PrintWriter printWriter = new PrintWriter(outputStream, true);
		printWriter.println(" Hola desde TCPCLient a las " + new Date());

		
		printWriter.close();
		socket.close();
	}
}
