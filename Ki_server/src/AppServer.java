import java.io.DataInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

import lejos.utility.Delay;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

public class AppServer {

	public static void main(String[] args)throws Exception {
		
		AppServer server = new AppServer();
		try {
			server.connect();
			
		}catch (IOException e){
			e.printStackTrace();
		}
		
	}
	
	
	public void connect() throws IOException{
		
		int port = 5000;
		java.net.ServerSocket serverSocket = new java.net.ServerSocket(port);
		System.out.println("server is ready");
		java.net.Socket client = waitForLogin(serverSocket);
		String msg = readMsg(client);
		System.out.println(msg);
		writeMsg(client, msg);
		Delay.msDelay(3000);
		
		CommandCenter cc = new CommandCenter();

		msg = cc.scan();
		writeMsg(client, msg);
//		Delay.msDelay(300);
		cc.forward(500);
		msg = cc.scan();
		writeMsg(client, msg);
		cc.turnLeft(90);
		cc.turnRight(270);
		cc.forward(500);
		msg = cc.scan();
		writeMsg(client, msg);
		cc.turnLeft(180);
		msg = cc.scan();
		writeMsg(client, msg);
	}
	
	
	
	public java.net.Socket waitForLogin(java.net.ServerSocket serverSocket)throws IOException{
		java.net.Socket socket = serverSocket.accept();
		return socket;
	}
	public String readMsg(java.net.Socket socket) throws IOException{
		BufferedReader br =  new BufferedReader(new InputStreamReader(socket.getInputStream()));
		char[] buffer = new char[200];
		int bSize = br.read(buffer,0,200);
		String msg = new String(buffer,0,bSize);
		return msg;
	}
	public void writeMsg(java.net.Socket socket, String msg) throws IOException{
		PrintWriter pw = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
		pw.print(msg);
		pw.flush();
		

		
		
//	     ServerSocket sersock = new ServerSocket(5000); 
//	     System.out.println("server is ready");  //  message to know the server is running
//	 
//	     Socket sock = sersock.accept();               
//	                                                                                          
//	     InputStream istream = sock.getInputStream();  
//	     DataInputStream dstream = new DataInputStream(istream);
//	 
//
//		String message2 = dstream.readLine();
//	     System.out.println(message2);
//	     dstream .close(); istream.close(); sock.close(); sersock.close();

	}

}
