import java.io.*;
import java.net.*;
import java.util.Scanner;

public class MyClient {
  private static Scanner keyboard;

public static void main(String[] args){
    keyboard = new Scanner(System.in);
    int ip;
    char []b = {'E', 'E', 'E',           //E means the cell is empty. 
				 'E', 'E', 'E', 
	             'E', 'E', 'E'};
    try {//Keep this number. For the pace server this is the number we must use.
        ip = 16790; //("vulcan.seidenberg.pace.edu");
        InetAddress host = InetAddress.getByName( "localhost");
        Socket clientSocket = new Socket(host, ip);
        PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
        BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        
        while(true){
        	String msg = null;
        	while(true) {
        		msg = in.readLine();
        		if(msg != null && !msg.trim().equals("")) {
        			break;
        		}
        	}
        	System.out.println(msg);
        	
        	int i = keyboard.nextInt() -1;
        	int j = keyboard.nextInt() -1;
        	out.println(i);
        	out.println(j);
        	msg = in.readLine();
        	if(msg.equalsIgnoreCase("Error")) {
        		System.out.println(msg);
        		System.out.println(in.readLine());
        	} else {
        		System.out.println(msg);
            	in.read(b);
            	display(b);
            	System.out.println(in.readLine());
        	}
        	
        	if(isWin('O',b) ||isWin('X',b)) {
        		break;
        	}
        }
        
        
        //String s = in.readLine();
       
        
        /*while(!msg.equals("bye")){
        	System.out.print("Enter a line or bye to quit for the client:  ");
        	msg = keyboard.nextLine();
        	out.println(msg);
        	s = in.readLine();
        	char []b = null;
        	in.read(b);
        	System.out.println("array"+b);
        	if(s != null )
        	System.out.println(s);
        	if(msg.equals("bye")){        		
        		System.exit(0);
        	}
        }*/
       
        in.close();
      clientSocket.close();
    }catch (IOException e) {
      System.out.println("Error: " + e);
      System.exit(0);
    }   
  }

private static boolean isWin(char xo, char[] b){
    if((b[0] == xo && b[1] == xo && b[2] == xo)||
        (b[3] == xo && b[4] == xo && b[5] == xo) ||
        (b[6] == xo && b[7] == xo && b[8] == xo) ||
        (b[0] == xo && b[3] == xo && b[6] == xo) ||
        (b[1] == xo && b[4] == xo && b[7] == xo) ||
        (b[2] == xo && b[5] == xo && b[8] == xo) ||
        (b[0] == xo && b[4] == xo && b[8] == xo) ||
        (b[2] == xo && b[4] == xo && b[6] == xo)){
      System.out.println(xo + " is the winner.");
     // display();
      return true;
    }
    return false;
  }

private static void display(char[] b){
    for(int i = 0; i < b.length; i++){
	  if(i == 3 || i == 6)
	    System.out.println();
		System.out.print(b[i] + "	");
	  }
	System.out.println();
}
}
