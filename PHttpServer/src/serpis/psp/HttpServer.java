package serpis.psp;

import java.io.*;
import java.net.*;
import java.util.*;


public class HttpServer {
	private final static int port = 8080;
	private static ServerSocket serverSocket;
	private static Socket socket;
	private static final String newLine = "\r\n";
	private static Scanner scanner;

	public static void main(String[] args) throws IOException{

		//socket = serverSocket.accept();
		//Thread threadServer = new Thread(new ThreadServer(socket));
		//threadServer.start();

		//String threadName = Thread.currentThread().getName();
		//System.out.println("threadName = " + threadName);-> Para saber el nombre del thread.
		
		//serverSocket = new ServerSocket(port);
	    //monoHilo(serverSocket);
		
		
		multiHilo();
		
	
	}
	
	//prueba para ver si se crean los hilos multiples.
	public static void multiHilo() throws IOException{
		
		//se crea el socket del servidor
		serverSocket = new ServerSocket(port);
		
		//bucle infinito
		while (true){
	          // Se espera y acepta un nuevo cliente
			socket = serverSocket.accept();
			
			//Instanciamos la clase para atender al cliente y se lanza un hilo
			Runnable r = new ThreadServer(socket);	
			Thread threadServer = new Thread(r);
			threadServer.start();
			}
	}

	
	

	public static void monoHilo(ServerSocket serverSocket) throws IOException{

		while (true){
		socket = serverSocket.accept();

		String fileName = getFileName(socket.getInputStream());
		//Escribir lineas que indiquen cuando se hacen las peticiones, 
		//Y que pongan la fecha de cuando se ejecuta.
		writeHeader( socket.getOutputStream(),  fileName);
		writeFile( socket.getOutputStream(),  fileName);

		socket.close();
		
		}
		//serverSocket.close();
	}
	
	private static String getFileName(InputStream inputStream){
		final String defaultFileName = "index.html";
			
		scanner = new Scanner(inputStream);
		String fileName = "";

		while (true){
			String line = scanner.nextLine();
			System.out.println(line);
			if(line.startsWith("GET")){ //GET  /otro.html HTTP/1.1
					int count = 5;
					while(line.charAt(count) != ' ')//Mientra no haya un espacio que siga a�adiendo caracteres.
						fileName += line.charAt(count++);
			}
			if(line.equals(""))
				break;
		}
		if(fileName.equals(""))
			fileName = defaultFileName;
		System.out.println(fileName);

		return fileName;
	}
	
	
	//Escribe la cabecera o no en funcion de si existe o no el archivo.
	private static void writeHeader(OutputStream outputStream, String fileName) throws IOException{
		final String response200 = "HTTP/1.0 200 OK";
		final String response404 = "HTTP/1.0 404 NOT FOUND";
		File file = new File(fileName);
		String response = file.exists() ? response200 : response404;

		System.out.println(response);
		String header  = response + newLine + newLine;
		byte[] headerBuffer = header.getBytes(); 
		
		outputStream.write(headerBuffer);
		
	}
	
	
	
	private static void writeFile(OutputStream outputStream, String fileName) throws IOException{
		final String fileNameError404 = "fileError404.html"; 
		
		File file= new File(fileName);
		String responseFileName = file.exists() ? fileName : fileNameError404;		
		
		final int bufferSize = 2048;
		byte[] buffer = new byte[bufferSize];
		
		FileInputStream fileInputStream = new FileInputStream(responseFileName);

		int count;
		while((count = fileInputStream.read(buffer))!=-1)
			outputStream.write(buffer, 0, count);

		fileInputStream.close();
		
	}
}
