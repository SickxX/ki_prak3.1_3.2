package huston;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

import huston.Robot.SensorData;
import math.Utils;
import test.RobotTest;

public class RobotServer 
{

	private static RobotServer server;
	private MapContainer mc;

	public static void main(String[] args) throws IOException 
	{
		// instance of map and graphics and stuff
		server = new RobotServer();
	}

	public RobotServer(boolean t)
	{
		mc = new MapContainer();
		mc.getMCA();

		RobotTest test = new RobotTest(mc.getMCA(), mc);

		test.testPerformance();
//		test.testenSIE();


	}

	public RobotServer()
	{
		mc = new MapContainer();

		//Conection
		try {
			connect();
		}catch(IOException e){
			e.printStackTrace(); 
		}
	}




	public void connect() throws IOException 
	{
		System.out.println("Starting");


		int port = 8081;

		java.net.ServerSocket serverSocket = new java.net.ServerSocket(port);
		
		System.out.println("Waiting...");
		java.net.Socket client = waitForLogin(serverSocket);
		System.out.println("Connected");
		try{
			

			//1. Partikel erzeugen
			mc.getMCA().start();
//			mc.getMCA().addParticle(new Particle(25, 75, 90));
			mc.repaint();
			System.out.println("---------Startet MCA");
			delay(1000);
			//2. Measure
//			mc.getMCA().recalculateParticles(measure(client, 5));
//			mc.repaint();
//			mc.getMCA().doResampling();
//			mc.repaint();
//			move(client, 500);
//			move(client, 500);
//			move(client, 500);
//			move(client, 500);
			
			System.out.println("---------Measure");
			delay(1000);
			//3. Action
			int minMovementDistance = 30;
			ArrayList<SensorData> data;
			for(int i = 0; i < 20 ; i++) 
			{
				data = measure(client,5);
				System.out.println("BLABLA" + data.get(0).getDistance());
				if(data.get(0).getDistance() > minMovementDistance && data.get(0).getRotation() == 0) {
					
					doResample(data);
					move(client,minMovementDistance * 10);
//					doResample(data);
				} else 
				{
					float maxDist = 0;
					double rotation = 0;
					for(SensorData sd : data) {
						if( maxDist < sd.getDistance() && sd.getRotation() != 0)
							maxDist = sd.getDistance();
							rotation = sd.getRotation();
					}
					turn(client, (int) rotation);
					
				}



			}
			//3. 
			//3. 
			call(client, "Kill");
			System.out.println("KILLED");

		} catch(Exception e)	{
			e.printStackTrace();
			write(client, "Kill");
			client.close();
			serverSocket.close();
		}

	}
	
	private void movement() {
		
	}
	
	
	
	

	private void doResample(ArrayList<SensorData> data) {
		mc.getMCA().recalculateParticles(data);
		mc.repaint();

		jan();

		mc.getMCA().doResampling();
		mc.repaint();
	}

	private boolean coinFlip() {
		return ThreadLocalRandom.current().nextDouble() > 0.5;
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

	public ArrayList<SensorData> measure(java.net.Socket client, int samplesize) throws Exception
	{
		ArrayList<SensorData> data = new ArrayList<>();

		double step = 0.0;
		if(samplesize % 2 == 1) {
			step = (double)(samplesize - 1) / 2;
			call(client, "Look 0");
			String c = call(client, "Distance");
			String[] cData = c.split(" ");
			if(Utils.parseFloat(cData[1]) >0 ) 
				data.add(new SensorData(0, Utils.parseFloat(cData[1])));				
		} else {
			step = (double)(samplesize / 2);
		}

		int i = -90;
		int j = 90;
		int run = (int) step;
		while(run > 0) {
			call(client, "Look " + i);

			String c = call(client, "Distance");
			String[] cData = c.split(" ");

			//um INFINITY auszuschlie�en eine If anweisung schreiben und die Messdaten einfach nicht verwenden
			// sollte das nicht aufgrund unserer implementierung der SensorData auch funktionieren, wenn wir 
			// keine Daten haben?
			if(Utils.parseFloat(cData[1]) >0 )
				data.add(new SensorData(i, Utils.parseFloat(cData[1])));				
			call(client, "Look " + j);
			c = call(client, "Distance");
			cData = c.split(" ");
			if(Utils.parseFloat(cData[1]) >0 )
				data.add(new SensorData(j, Utils.parseFloat(cData[1])));				
			i += 90 / run;
			j -= 90 / run;
			run -= 1;
		}
		for (SensorData sensorData : data) {
			System.out.println(sensorData.toString());
		}
		

		return data;

		//		nextStep(client, data);
	}

	public void move(java.net.Socket client, int distance) throws Exception
	{
		call(client, "Forward " + distance);
		mc.getMCA().moveParticles(distance / 10);
		//mc.getMCA().recalculateParticles(measure(client, 5));
		mc.repaint();
		mc.getMCA().doResampling();
		mc.repaint();
		jan();
	}

	public void turn(java.net.Socket client, int theta) throws Exception
	{
		call(client, "Turn " + theta);
		mc.getMCA().turnParticles(theta);
		//measure(client, 3);
	}

	public void delay(long delay)
	{
		try {
			Thread.sleep(delay);
		}
		catch(Exception e)
		{}
	}

	public void jan()
	{
		delay(1000);
	}

	public void nextStep(java.net.Socket client ,ArrayList<SensorData> data) {
		SensorData bestValue = new SensorData(0,0);
		for(SensorData d: data) {
			if(d.getDistance() > bestValue.getDistance()) {
				bestValue = d;
			}
		}
		if ( bestValue.getDistance() < 15) {
			try {
				turn(client, 180);
				measure(client, 3);
			} catch (Exception e) {
			}
		}else{
			try {
				turn(client,(int)bestValue.getRotation());
				move(client, 15);
				measure(client,3);
			} catch (Exception e) {
			}
		}	
	}
}