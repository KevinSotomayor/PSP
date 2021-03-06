package serpis.psp;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class PruebaStream {

	/**
		copia fichero1 en fichero2
	 */
	public static void main(String[] args) throws IOException {
		//FileInputStream leer con .read
		//FileOutputStream escribe con write	
		if (args.length != 2){
			System.out.println("Uso: PruebaStream source destination");
			return;
		}
		
		String inputFileName = args[0];
		String outputFileName = args[1];
		//se lanza desde la carpeta bin, lo cual hay que poner los archivos en esta carpeta.
		
		System.out.println("Copiando...");
		System.out.println("inputFileName: " + inputFileName);
		System.out.println("outputFileName: " + outputFileName);
		
		FileInputStream fileInputStream = new FileInputStream(inputFileName);
		FileOutputStream fileOutputStream = new FileOutputStream(outputFileName);
		
		
		final int bufferSize = 2048;
		byte[] buffer = new byte[bufferSize];
		
		int count = 0;
		while ((count = fileInputStream.read(buffer, 0, bufferSize)) != -1 )
			fileOutputStream.write(buffer, 0, count);
			
		fileInputStream.close();
		fileOutputStream.close();
	}

}
