import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class GameThread extends Thread {
	protected Socket clientSocket;
	private char[]b = {'E','E','E','E','E','E','E','E', 'E'};
	public GameThread(Socket socket) {
		this.clientSocket = socket;
	}
	public void run() {
		try{
		PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
	      BufferedReader keyboard = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
	      while(true){
	    	  //System.out.println("here");
	    	  String  message = "Enter two positive integers in the range 1 to 3 inclusive for the position of an empty cell: ";
	    	  out.println(message);
	    	  int i,j;
	    	  i = Integer.parseInt(keyboard.readLine());
	          j = Integer.parseInt(keyboard.readLine());
	          if(i*3+j < 0 || i*3+j > 9 || b[i*3+j] != 'E') {
	        	  out.println("Error");
	        	  out.println("Enter input again\n");
	        	  continue;
	          }
			  b[i*3+j] = 'X';
			  winner(b);
			  b[computePosition(i, j, b)] = 'O';
			  out.println("Computer will Play now: ");
			  out.print(b);
			  out.println("\n");
			  out.flush();
			  winner(b);
	      }
	     
	      
	      //out.print(b);
	      //out.flush();
	     

	      //System.out.println(s);
	      
	      /*while(!s.equals("bye")){
	    	  System.out.print("Enter a line for the server:  ");
	    	  s = keyboard.nextLine();
	    	  char b[] = {'E', 'X'};
	    	  //out.println(s + "\n"+ b);
	    	  out.write(b);
	    	  out.flush();
	    	  s = in.readLine();
	    	  System.out.println(s);
	      }*/
	      //out.close();
	      //clientSocket.close();
	      
	    } catch (IOException e) {
	        System.out.println("Error: " + e);
	    }
	}
	private static int computePosition(int row, int column, char b[]) {
		  int position = 4;
		  
		  if(b[position] == 'E') {
			  return position;
		  } else {
			  //check all corners. if all corners are empty then return corner's position
			  if(b[0] == 'E' && b[2] == 'E' && b[6] == 'E' && b[8] == 'E') {
				  return 0;
			  }
			  
			  return findPositionWhereCountISTwo(b);
		  }
	}

	private static int findPositionWhereCountISTwo(char b[]) {
		  
		  //first row
		  boolean defenceRequired = false;
		  int defencePosition = 0;
		  int count = 0, j = 0, count0 = 0;
		  boolean dontCheck = false;
		  for(int i = 0; i < 3; i++) {
			  if(b[i] == 'X') {
				  count++;
			  } else {
				  if(b[i] == 'E') {
					  j = i;
				  } else if(b[i] == 'O') {
					  dontCheck = true;
					  count0++;
				  }
			  }
		  }
		  if(count0 == 2 && b[j] == 'E') {
			  return j;
		  }
		  if(count == 2 && !dontCheck) {
			  
			  defencePosition = j;
			  defenceRequired = true;
		  }
		  
		//second row
		  count = 0; j = 0; count0 = 0;
		  dontCheck = false;
		  for(int i = 3; i < 6; i++) {
			  if(b[i] == 'X') {
				  count++;
			  } else {
				  if(b[i] == 'E') {
					  j = i;
				  } else if(b[i] == 'O') {
					  dontCheck = true;
					  count0++;
				  }
			  }
		  }
		  if(count0 == 2 && b[j] == 'E') {
			  return j;
		  }
		  if(count == 2 && !dontCheck) {
			  
			  defencePosition = j;
			  defenceRequired = true;
		  }
		  
		//third row
		  count = 0; j = 0; count0 = 0;
		  dontCheck = false;
		  for(int i = 6; i < 9; i++) {
			  if(b[i] == 'X') {
				  count++;
			  } else {
				  if(b[i] == 'E') {
					  j = i;
				  } else if(b[i] == 'O') {
					  dontCheck = true;
					  count0++;
				  }
			  }
		  }
		  if(count0 == 2 && b[j] == 'E') {
			  return j;
		  }
		  if(count == 2 && !dontCheck) {
			  
			  defencePosition = j;
			  defenceRequired = true;
		  }
		  
		//first column
		  count = 0; j = 0; count0 = 0;
		  dontCheck = false;
		  for(int i = 0; i < 9; i += 3) {
			  if(b[i] == 'X') {
				  count++;
			  } else {
				  if(b[i] == 'E') {
					  j = i;
				  } else if(b[i] == 'O') {
					  dontCheck = true;
					  count0++;
				  }
			  }
		  }
		  if(count0 == 2 && b[j] == 'E') {
			  return j;
		  }
		  if(count == 2 && !dontCheck) {
			  
			  defencePosition = j;
			  defenceRequired = true;
		  }
		  
		//second column
		  count = 0; j = 0; count0 = 0;
		  dontCheck = false;
		  for(int i = 1; i < 9; i += 3) {
			  if(b[i] == 'X') {
				  count++;
			  } else {
				  if(b[i] == 'E') {
					  j = i;
				  } else if(b[i] == 'O') {
					  dontCheck = true;
					  count0++;
				  }
			  }
		  }
		  if(count0 == 2 && b[j] == 'E') {
			  return j;
		  }
		  if(count == 2 && !dontCheck) {
			  
			  defencePosition = j;
			  defenceRequired = true;
		  }
		  
		//third column
		  count = 0; j = 0; count0 = 0;
		  dontCheck = false;
		  for(int i = 2; i < 9; i += 3) {
			  if(b[i] == 'X') {
				  count++;
			  } else {
				  if(b[i] == 'E') {
					  j = i;
				  } else if(b[i] == 'O') {
					  dontCheck = true;
					  count0++;
				  }
			  }
		  }
		  if(count0 == 2 && b[j] == 'E') {
			  return j;
		  }
		  if(count == 2 && !dontCheck) {
			  
			  defencePosition = j;
			  defenceRequired = true;
		  }
		  
		  //-ve slope
		  count = 0; j = 0; count0 = 0;
		  dontCheck = false;
		  for(int i = 0; i < 9; i += 4) {
			  if(b[i] == 'X') {
				  count++;
			  } else {
				  if(b[i] == 'E') {
					  j = i;
				  } else if(b[i] == 'O') {
					  dontCheck = true;
					  count0++;
				  }
			  }
		  }
		  if(count0 == 2 && b[j] == 'E') {
			  return j;
		  }
		  if(count == 2 && !dontCheck) {
			  
			  defencePosition = j;
			  defenceRequired = true;
		  }
		  
		  //+ve slope
		  count = 0; j = 0; count0 = 0;
		  dontCheck = false;
		  for(int i = 6; i > 1; i = i-2) {
			  if(b[i] == 'X') {
				  count++;
			  } else {
				  if(b[i] == 'E') {
					  j = i;
				  } else if(b[i] == 'O') {
					  dontCheck = true;
					  count0++;
				  }
			  }
		  }
		  if(count0 == 2 && b[j] == 'E') {
			  return j;
		  }
		  if(count == 2 && !dontCheck) {
			  
			  defencePosition = j;
			  defenceRequired = true;
		  }
		  
		  if(defenceRequired) {
			  return defencePosition;
		  }
		  
		  //means play to win
		  //check first row
		  if(containsOnly("" + b[0] + b[1] + b[2], "OE")) {
			  /*for(int i = 0; i < 3;i++) {
				  if(b[i] == 'E') {
					  return i;
				  }
			  }*/
			  if(b[0] == 'E') return 0;
			  if(b[2] == 'E') return 2;
			  if(b[1] == 'E') return 1;
		  }
		  
		//check second row
		  if(containsOnly("" + b[3] + b[4] + b[5], "OE")) {
			  /*for(int i = 3; i < 6;i++) {
				  if(b[i] == 'E') {
					  return i;
				  }
			  }*/
			  if(b[3] == 'E') return 3;
			  if(b[5] == 'E') return 5;
			  if(b[4] == 'E') return 4;
		  }
		  
		//check third row
		  if(containsOnly("" + b[6] + b[7] + b[8], "OE")) {
			  /*for(int i = 6; i < 9;i++) {
				  if(b[i] == 'E') {
					  return i;
				  }
			  }*/
			  if(b[6] == 'E') return 6;
			  if(b[8] == 'E') return 8;
			  if(b[7] == 'E') return 7;
		  }
		  
		//check first column
		  if(containsOnly("" + b[0] + b[3] + b[6], "OE")) {
			  /*for(int i = 0; i < 9; i += 3) {
				  if(b[i] == 'E') {
					  return i;
				  }
			  }*/
			  if(b[0] == 'E') return 0;
			  if(b[6] == 'E') return 6;
			  if(b[3] == 'E') return 3;
		  }
		  
		//check second column
		  if(containsOnly("" + b[1] + b[4] + b[7], "OE")) {
			  /*for(int i = 1; i < 9; i += 3) {
				  if(b[i] == 'E') {
					  return i;
				  }
			  }*/
			  if(b[1] == 'E') return 1;
			  if(b[7] == 'E') return 7;
			  if(b[4] == 'E') return 4;
		  }
		  
		//check third row
		  if(containsOnly("" + b[2] + b[5] + b[8], "OE")) {
			  /*for(int i = 2; i < 3;i++) {
				  if(b[i] == 'E') {
					  return i;
				  }
			  }*/
			  if(b[2] == 'E') return 2;
			  if(b[8] == 'E') return 8;
			  if(b[5] == 'E') return 5;
		  }
		  
		//check -ve slope
		  if(containsOnly("" + b[0] + b[4] + b[8], "OE")) {
			  /*for(int i = 0; i < 9; i += 4) {
				  if(b[i] == 'E') {
					  return i;
				  }
			  }*/
			  if(b[0] == 'E') return 0;
			  if(b[8] == 'E') return 8;
			  if(b[4] == 'E') return 4;
		  }
		  
		//check +ve slope
		  if(containsOnly("" + b[2] + b[4] + b[6], "OE")) {
			  /*for(int i = 6; i > 1; i -= 2) {
				  if(b[i] == 'E') {
					  return i;
				  }
			  }*/
			  if(b[2] == 'E') return 2;
			  if(b[6] == 'E') return 6;
			  if(b[4] == 'E') return 4;
		  }
		  
		  int i = 0;
		  while(b[i] != 'E') {
			  i++;
		  }
		  return i;
	}

	private static boolean containsOnly(String str, String certain){
		  return str.matches("[" + certain + "]*");
	}

	private static void winner(char b[]){
		    boolean win = false;
		    if(isWin('O', b) ||isWin('X', b))
		      win = true;
		    else if(isFull(b)){
		      System.out.println("It is a tie");
		//      display();
		      System.exit(0);
		    }
		    if(win){
//		      display();
		      System.exit(0);
		    }
		  }
		  
		  private static boolean isFull(char b[]){
		    for(int i = 0; i < 3; i++)
		      for(int j = 0; j < 3; j++)
		        if(b[i + 3 + j] == 'E')
		          return false;
		    return true;
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
}
