package test;

import java.util.ArrayList;
import java.util.Arrays;

import huston.MCA;
import huston.MapContainer;
import huston.Particle;
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
		testenSIE();
		// --- Testing a robot starting in the left corner, measuring facing up
		// --- Note: Test with kicking out INFINITY!
		ArrayList<SensorData> mock = new ArrayList<>();
		
		//Messung 1
		mock.add(new SensorData(0, 575));
		mock.add(new SensorData(-90, 25));
		mock.add(new SensorData(90, 25));
		
		mca.recalculateParticles(mock);
		mca.doResampling();
		mc.repaint();
		moveTest();

		mc.repaint();

		//Messung 2
		jan();
		mock.clear();
		mock.add(new SensorData(0, 525));
		mock.add(new SensorData(-90, 25));
		mock.add(new SensorData(90, 75));
		
		mca.recalculateParticles(mock);
		mca.doResampling();
		
		mc.repaint();
		
		System.out.println("DONE TESTING");
		
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
	
	public void moveTest() 
	{
		jan();
		mca.moveParticles(50);
		mc.repaint();
		jan();
		System.out.println(Arrays.toString(mca.getParticle().toArray()));
//		mca.moveParticles(50);
//		jan();
//		mca.moveParticles(50);
		
	}
	
	public void testenSIE() 
	{
		System.out.println("TESTEN SIE TESTENSIE!!!!");
		mca.start();
		mca.addParticle(new Particle(25, 75, 90));
		mc.repaint();
	}
	
}
