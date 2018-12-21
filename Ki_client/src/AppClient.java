import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

public class AppClient {

	public static void main(String[] args)throws Exception {
	
		AppClient client = new AppClient();
		try {
			client.connect();
		}catch(IOException e){
			e.printStackTrace();
		}
	}
	
	
	public void connect() throws IOException {

		String ip = "192.168.137.254" ;//EV3
		int port = 5000;
		java.net.Socket socket = new java.net.Socket(ip,port); // verbindet sich mit dem Server
		String send = "Start";
		write(socket, send);
		
		byte[] data = new byte[500];
		InputStream in = socket.getInputStream();
		
		while ( in.read(data, 0, 500) != -1) {
//			System.out.println("StreamSize " + socket.getInputStream().read());
			System.out.println(new String(data));
//			String read = read(socket);
//			System.out.println(read);
		}
		
//		String read = read(socket);
//		System.out.println(read);
//		try {
//			Thread.sleep(3000);
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		read = read(socket);
//		System.out.println(read);
	}
	
	
	public void write(java.net.Socket socket, String msg)throws IOException {
		PrintWriter printwriter = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
		printwriter.print(msg);
		printwriter.flush();
	}
	
	
	public String read(java.net.Socket socket) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		char[] buffer = new char[500];
		int bSize = br.read(buffer,0,500);
		String msg = new String(buffer,0,bSize);
		return msg;
	}
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
//	     Socket sock = new Socket("192.168.137.96", 5000);       
//	     String message1 = "Accept Best Wishes, MrBimmler";
//	 
//	     OutputStream ostream = sock.getOutputStream();                 
//	     DataOutputStream dos = new DataOutputStream(ostream);
//	     dos.writeBytes(message1);                                                         
//	     dos.close();                            
//	     ostream.close();   
//	     sock.close();
				
				
	

}
