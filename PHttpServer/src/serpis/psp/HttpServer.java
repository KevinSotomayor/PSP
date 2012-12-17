package serpis.psp;

import java.io.*;
import java.net.*;
import java.util.*;

public class HttpServer {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws IOException{
		String newLine = "\r\n";
		
		int port = 8080;
		
		ServerSocket serverSocket = new ServerSocket(port);
		
		Socket socket = serverSocket.accept();
		
		Scanner scanner = new Scanner(socket.getInputStream());
	
		while (true){//devuelve true si hay otra linea de entrada en el scanner. ->scanner.hasNextLine()
			//Leemos cada linea.
			String line = scanner.nextLine();//devuelve la linea
			System.out.println(line);
			if(line.equals(""))
				break;
		}
		
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
		 * 
		 */
		PrintWriter printWriter = new PrintWriter(socket.getOutputStream(), true);
		printWriter.print("HTTP/1.0 404 NOT FOUND" + newLine);
		printWriter.print(newLine);
		
		printWriter.flush();
		
		printWriter.close();
		socket.close();	
		serverSocket.close();

	}
}
