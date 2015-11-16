import java.io.*;
import java.net.*;
import java.util.Scanner;

public class MyServer {
  private static Scanner keyboard;

public static void main(String[] args) {
	//keyboard = new Scanner(System.in);
	char b[] = new char[] {'E', 'E', 'E',           //E means the cell is empty. 
		   				 'E', 'E', 'E', 
			             'E', 'E', 'E'};
	ServerSocket serverSocket = null;
    try {//Keep this number. For the pace server this is the number we must use.
      serverSocket = new ServerSocket(16790);  
      Socket clientSocket = null;
      while(true) {
    	  try {
              clientSocket = serverSocket.accept();
          } catch (IOException e) {
              System.out.println("I/O error: " + e);
          }
    	  new GameThread(clientSocket).start();
      }
      
      
    } catch (IOException e) {
        System.out.println("Error: " + e);
    }
}
}

