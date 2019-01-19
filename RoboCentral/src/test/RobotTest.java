package test;

import java.util.ArrayList;

import huston.MCA;
import huston.MapContainer;
import huston.Robot.SensorData;

public class RobotTest 
{
	private MCA mca;
	private MapContainer mc;
	
	public RobotTest(MCA mca, MapContainer mc)
	{
		System.out.println("Thx for choosing the Testing Engine Provided by the fabulous Yannick1!");
		this.mca = mca;
		this.mc = mc;
	}
	
	public void testPerformance()
	{
		mca.start();
		mc.repaint();
		// --- Testing a robot starting in the left corner, measuring facing up
		// --- Note: Test with kicking out INFINITY!
		ArrayList<SensorData> mock = new ArrayList<>();
		
		mock.add(new SensorData(0, 25));
		mock.add(new SensorData(-90, 25));
//		mock.add(new SensorData(90, Float.MAX_VALUE));
		
		jan();
		mca.recalculateParticles(mock);
		mc.repaint();
		
	}
	
	public void jan()
	{
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
