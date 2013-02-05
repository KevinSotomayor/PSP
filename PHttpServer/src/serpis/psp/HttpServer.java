package serpis.psp;

import java.io.*;
import java.net.*;


public class HttpServer {

	public static void main(String[] args) throws IOException, InterruptedException{
		
		
		final int port = 8080;
		ServerSocket serverSocket = new ServerSocket(port);

		while (true) {
			Socket socket = serverSocket.accept();

			SimpleServer.process(socket);

			//Runnable runnable = new ThreadServer(socket);
			//Thread thread = new Thread(runnable);
			//thread.start();
		}

		//serverSocket.close();
		
		
		//multiHilo();
		//monoHilo();
		
	
	}
	/*
	//prueba para ver si se crean los hilos multiples.
	public static void multiHilo() throws IOException, InterruptedException{
		
		//se crea el socket del servidor
		 serverSocket = new ServerSocket(port);
		
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
	
	public static void monoHilo() throws IOException, InterruptedException{
	    serverSocket = new ServerSocket(port);
		
		//bucle infinito
		while (true){
	          // Se espera y acepta un nuevo cliente
			Socket socket = serverSocket.accept();
			
			SimpleServer.process(socket);

			}
	}*/
}
