package serpis.psp;

import java.io.*;
import java.net.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HttpServer {

	private static ServerSocket serverSocket;
	private static Socket socket;
	private static final String newLine = "\r\n";
	private static Scanner scanner;

	public static void main(String[] args) throws IOException{

		/*ThreadServer threadServer = new ThreadServer();
		threadServer.start();*/

		monoHilo();
		
	
	}


	public static void monoHilo() throws IOException{
		final int port = 8080;
		serverSocket = new ServerSocket(port);

		while (true){

	    socket = serverSocket.accept();
		
		String fileName = getFileName(socket.getInputStream());
		writeHeader( socket.getOutputStream(),  fileName);
		writeFile( socket.getOutputStream(),  fileName);
		socket.close();	
		
		
		}
		//serverSocket.close();
	}
	
	//TO-DO implementar correctamente
	private static String getFileName(InputStream inputStream){

		scanner = new Scanner(inputStream);
	
		//String fileName = "index.html";
		String fileName = "";

		while (true){
			String line = scanner.nextLine();
			if(line.startsWith("GET")){ //GET  /otro.html HTTP/1.1
					int count = 5;
					while(line.charAt(count) != ' ')//Mientra no haya un espacio que siga a�adiendo caracteres.
						fileName += line.charAt(count++);

			}

			System.out.println(line);
			if(line.equals(""))
				break;
		}
		
		return fileName;
	}
	
	//Escribe la cabecera o no en funcion de si existe o no el archivo.
	private static void writeHeader(OutputStream outputStream, String fileName) throws IOException{
		final String response200 = "HTTP/1.0 200 OK";
		final String response404 = "HTTP/1.0 404 NOT FOUND";
		File file = new File(fileName);
		String response = file.exists() ? response200 : response404;

		
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
