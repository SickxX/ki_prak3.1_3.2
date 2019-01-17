package frankieTheTankie;

import java.io.DataInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.tree.ExpandVetoException;

import lejos.utility.Delay;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

public class RobotClient
{
	private static final String IP = "10.0.1.45";
	public static void main(String[] args) throws Exception 
	{	
		RobotClient client = new RobotClient();
		try 
		{
			client.connect();	
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}	
	}
 
	public void connect() throws IOException
	{		
		System.out.println("Init");
		CommandCenter commandCenter = new CommandCenter();
		
		int port = 8083;
		java.net.Socket socket = new java.net.Socket(IP, port);
		System.out.println("Connected!");

		while (true) 
		{		
			try
			{
				String command = read(socket);
				if(command.equals("Sensors"))
				{
					String msg = commandCenter.colorScan();
					msg += " ";
					msg += commandCenter.distanceScan();
					write(socket, msg);
				}
				else if(command.equals("Distance"))
				{
					write(socket, commandCenter.distanceScan());
				}
				else if(command.equals("Kill"))
				{
					commandCenter.onKill();
					write(socket, "ACK");
					socket.close();
					break;
				}
				else
				{
					String[] commandSplit = command.split(" ");
					command = commandSplit[0];
					int value = Integer.parseInt(commandSplit[1]);
					if(command.equals("Forward"))
					{
						commandCenter.forward(value);
					}
					else if(command.equals("TurnLeft"))
					{
						commandCenter.turnLeft(value);
					}
					else if(command.equals("TurnRight"))
					{
						commandCenter.turnRight(value);
					}
					else if(command.equals("Look"))
					{
						commandCenter.lookAround(value);
					}
					write(socket, "ACK");
				}
			} catch(Exception e) {
				write(socket, "FIN");
				commandCenter.onKill();
				socket.close();
				break;
			}
		}
	}

	public String read(java.net.Socket socket) throws IOException
	{
		BufferedReader br =  new BufferedReader(new InputStreamReader(socket.getInputStream()));
		char[] buffer = new char[200];
		int bSize = br.read(buffer,0,200);
		String msg = new String(buffer,0,bSize);
		return msg;
	}

	public void write(java.net.Socket socket, String msg) throws IOException
	{
		PrintWriter pw = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
		pw.print(msg);
		pw.flush();
	}


}