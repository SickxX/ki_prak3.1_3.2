package huston;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;

import huston.Sensor.SensorData;

public class RobotServer 
{
	public static void main(String[] args) throws IOException 
	{
		// instance of map and graphics and stuff
		MapContainer mp = new MapContainer();
		mp.getMCA();
		//Conection
//		RobotServer server = new RobotServer();
//		try {
//			server.connect();
//		}catch(IOException e){
//			e.printStackTrace();
//		}
	}
 
	public void connect() throws IOException 
	{
		System.out.println("Starting");
		int port = 8083;
		
		java.net.ServerSocket serverSocket = new java.net.ServerSocket(port);
		java.net.Socket client = waitForLogin(serverSocket);
		System.out.println("Connected");
		try{
			call(client, "Sensors");
			call(client, "Forward 500");
			call(client, "Sensors");
			call(client, "TurnLeft 90");
			call(client, "TurnRight 270");
			call(client, "Look 90");
			call(client, "Look -180");
			call(client, "Sensors");
			call(client, "Forward 500");
			call(client, "Sensors");
			call(client, "TurnLeft 180");
			call(client, "Sensors");
			call(client, "Kill");
			
		} catch(Exception e)	{
			e.printStackTrace();
			write(client, "Kill");
			client.close();
			serverSocket.close();
		}
			
	}
	
	public String call(java.net.Socket socket, String command) throws Exception
	{
		write(socket, command);
		String answer = read(socket);
		if(answer.equals("ACK")) // Success!
		{
			//Do absolutely nothing
			Thread.sleep(1000);
		}
		else if(answer.equals("FIN")) // Failure
		{
			throw new Exception("Clientside Error by " + command);
		}
		else // SensorData
		{
			System.out.println(answer);			
		}
		return answer;
	}

	public java.net.Socket waitForLogin(java.net.ServerSocket serverSocket) throws IOException
	{
		java.net.Socket socket = serverSocket.accept();
		return socket;
	}
	
	public void write(java.net.Socket socket, String msg) throws IOException
	{
		PrintWriter printwriter = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
		printwriter.print(msg);
		printwriter.flush();
	}

	public String read(java.net.Socket socket) throws IOException 
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		char[] buffer = new char[500];
		int bSize = br.read(buffer, 0, 500);
		String msg = new String(buffer, 0, bSize);
		return msg;
	}
	
	public void measure(java.net.Socket client, int samplesize) throws Exception
	{
		ArrayList<SensorData> data = new ArrayList<>();
		
		double step = 0.0;
		if(samplesize % 2 == 1) {
			step = (double)(samplesize - 1) / 2;
			call(client, "Look 0");
			data.add(new SensorData(0, Float.parseFloat(call(client, "Distance"))));
		} else {
			step = (double)(samplesize / 2);
		}
		
		int i = -90;
		int j = 90;
		int run = (int) step;
		while(run > 0) {
			call(client, "Look " + i);
			data.add(new SensorData(i, Float.parseFloat(call(client, "Distance"))));
			call(client, "Look " + j);
			data.add(new SensorData(j, Float.parseFloat(call(client, "Distance"))));
			i += 90 / run;
			j -= 90 / run;
			run -= 1;
		}
		
		// TODO: MCA ausführen
	}
	
	public void move(java.net.Socket client)
	{}
}