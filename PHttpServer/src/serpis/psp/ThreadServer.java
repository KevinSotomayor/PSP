package serpis.psp;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.*;
import java.util.Scanner;

public class ThreadServer implements Runnable  {
	private final String newLine = "\r\n";
	final String defaultFileName = "index.html";
	private final String fileNameError404 = "fileError404.html"; 
	private final String response200 = "HTTP/1.0 200 OK";
	private final String response404 = "HTTP/1.0 404 NOT FOUND";

	private Socket socket;
	private InputStream inputStream;
	private OutputStream outputStream;
	private String fileName;
	private Boolean fileExists;
	
	public ThreadServer(Socket socket){
		this.socket = socket;
	}

	
	public void run(){
		System.out.println(Thread.currentThread().getName() + " inicio.");
		
					try {
					inputStream = socket.getInputStream();
					outputStream = socket.getOutputStream();
					getFileName();
					getFileExists();
					writeHeader();
					writeFile();
					socket.close();	
					
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} 
					System.out.println(Thread.currentThread().getName() + " fin.");

		}
	
	private void getFileName(){

		Scanner scanner = new Scanner(inputStream);
		String fileName = "";

		while (true){
			String line = scanner.nextLine();
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
		System.out.println(Thread.currentThread().getName() + " fileName="+fileName);
		
	}
	
	private void getFileExists() {
		File file = new File(fileName);
		fileExists = file.exists();
	}

	private void writeHeader() throws IOException{
		String response = fileExists ? response200 : response404;
		
		String header  = response + newLine + newLine;
		byte[] headerBuffer = header.getBytes(); 
		
		outputStream.write(headerBuffer);
		
	}
	
	
	private  void writeFile() throws IOException, InterruptedException{

		String responseFileName = fileExists  ? fileName : fileNameError404;		
		
		final int bufferSize = 2048;
		byte[] buffer = new byte[bufferSize];
		
		FileInputStream fileInputStream = new FileInputStream(responseFileName);

		int count;
		while((count = fileInputStream.read(buffer))!=-1){
			System.out.println(Thread.currentThread().getName() + ".");
		    Thread.sleep(1000);
		
			outputStream.write(buffer, 0, count);
		}
		
		fileInputStream.close();
		
	}


}
