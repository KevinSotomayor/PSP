package serpis.psp;

import java.io.*;
import java.net.*;


public class HttpServer {

	public static void main(String[] args) throws IOException, InterruptedException{
		
		SimpleServer.process();
		//multiHilo();
		
	
	}
	
	//prueba para ver si se crean los hilos multiples.
	public static void multiHilo() throws IOException{
		int port = 8080;
		//se crea el socket del servidor
		ServerSocket serverSocket = new ServerSocket(port);
		
		//bucle infinito
		while (true){
	          // Se espera y acepta un nuevo cliente
			Socket socket = serverSocket.accept();
			
			//Instanciamos la clase para atender al cliente y se lanza un hilo
			Runnable r = new ThreadServer(socket);	
			Thread threadServer = new Thread(r);
			threadServer.start();
			}
	}
}
