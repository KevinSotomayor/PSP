package serpis.psp;

import java.io.*;
import java.net.*;
import java.util.*;

public class HttpServer {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws IOException{
		final String newLine = "\r\n";
		final int port = 8080;
		final String fileNameError404 = "fileError404.html"; 
		final String response200 = "HTTP/1.0 200 OK";
		final String response404 = "HTTP/1.0 404 NOT FOUND";
		
		ServerSocket serverSocket = new ServerSocket(port);
		
		Socket socket = serverSocket.accept();
		
		Scanner scanner = new Scanner(socket.getInputStream());
	
		String fileName = "index.html";
		
		while (true){//devuelve true si hay otra linea de entrada en el scanner. ->scanner.hasNextLine()
			//Leemos cada linea.
			String line = scanner.nextLine();//devuelve la linea
			//if(line.startsWith("GET"))
				
			
			System.out.println(line);
			if(line.equals(""))
				break;
		}
		
		//Comprobamos que existe el fichero instanciandolo y comprobando.
		File file= new File(fileName);
		String responseFileName = file.exists() ? fileName : fileNameError404;
		//Si existe el filename, y sino el de error.
		
		String response = file.exists() ? response200 : response404;
		/*if(!file.exists())
			fileName = fileNameError404;*/
		
		/*El servidor tiene que devolver un html cuando el navegador se lo consulte, y tienen que decirnos
		 * http/1.1 200 ok 
		 * <cr><lf>
		 * 
		 * 
		 * crear un html con un encabezado como el de arriba si el navegador al hacer la petición del archivo index.html
		 * de lo contrario si el navegador pide un archivo que no existe en el sistema de archivos, el navegador
		 * presentará un mensaje de not found., el navegador no, la consola. flujo de entrada, el archivo.
		 * flujo de salida, la consola.
		 * 
		 * 
		 * clase view que trabaje con dos flujos, uno de entrada en el programa  y un flujo de salida, por en medio transpforma
		 * la petición. toma como flujo de entrada el archivo, y toma como flujo de salida, la consola
		 * 
		 * simplicidad: leer en un sitio, y escribir en otro.
		 * 
		 * 
		 * y si no se encuentra la pagina un:
		 * http/1.1 404 not found
		 * <cr> <lf>
		 * 
		 * obtener el nombre del archivo de la cadena, con un indexoff
		 * 
		 * indexOff(cadena, ' ')
		 * 
		 * trozo(5, 10);
		 * 
		 * ESCRIBIR METODOS PARA: WriteHeader y demás que se irán viendo cuando se escriba el codigo
		 */
	
		
		
		String header  = response + newLine + newLine;
		byte[] headerBuffer = header.getBytes(); //se guarda un byte[]
		
		OutputStream outputStream = socket.getOutputStream();
		outputStream.write(headerBuffer);//Escribir todos los bytes de ese byte[]
		
		final int bufferSize = 2048;
		byte[] buffer = new byte[bufferSize];
		
		FileInputStream fileInputStream = new FileInputStream(fileName);

		int count;
		while((count = fileInputStream.read(buffer))!=-1){
			outputStream.write(buffer, 0, count);
			//Lee del archivo y escribe en el flujo de salida.
		}
	/*	PrintWriter printWriter = new PrintWriter(outputStream, true);
		printWriter.print("HTTP/1.0 404 NOT FOUND" + newLine);
		printWriter.print(newLine);
		
		printWriter.flush();
		
		printWriter.close();*/
		fileInputStream.close();
		
		socket.close();	
		serverSocket.close();

	}
}
