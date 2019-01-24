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
	
	public void test1()
	{
		testenSIE();
		
		ArrayList<SensorData> mock = new ArrayList<>();
		mca.addParticle(new Particle(75, 75, 90));
		System.out.println("!!!first measuring!!!");
		//Messung 1
		mock.add(new SensorData(0, 525));
		mock.add(new SensorData(-90, 25));
		mock.add(new SensorData(90, 75));
		
		mca.recalculateParticles(mock);
		
	}
	
	public void testPerformance()
	{
		testenSIE();
		jan();
		// --- Testing a robot starting in the left corner, measuring facing up
		// --- Note: Test with kicking out INFINITY!
		ArrayList<SensorData> mock = new ArrayList<>();
		
		System.out.println("!!!first measuring!!!");
		//Messung 1
		mock.add(new SensorData(0, 575));
		mock.add(new SensorData(90, 25));
		mock.add(new SensorData(-90, 25));
		
		System.out.println("before recalculateParticles");
		mca.recalculateParticles(mock);
		System.out.println("before Resampling");
		mca.doResampling();
		System.out.println("after RESAMPLING");
		mc.repaint();
		jan();
		moveTest();
		jan();
		System.out.println("!!!second measuring!!!");
		//Messung 2
		mock.clear();
		mock.add(new SensorData(0, 525));
		mock.add(new SensorData(-90, 25));
		mock.add(new SensorData(90, 75));
		
		mca.recalculateParticles(mock);
		mca.doResampling();
		mc.repaint();
		jan();
		moveTest();
		jan();

		System.out.println("!!!third measuring!!!");
		//Messung 3
		mock.clear();
		mock.add(new SensorData(0, 475));
		mock.add(new SensorData(-90, 75));
		mock.add(new SensorData(90, 25));
		
		mca.recalculateParticles(mock);
		mca.doResampling();
		mc.repaint();
		jan();
		moveTest();
		jan();
		System.out.println("!!!fourth measuring!!!");
		//Messung 4
		mock.clear();
		mock.add(new SensorData(0, 425));
		mock.add(new SensorData(-90, 25));
		mock.add(new SensorData(90, 25));
		
		mca.recalculateParticles(mock);
		mca.doResampling();
		mc.repaint();
		jan();
		moveTest();
		jan();

		System.out.println("!!!fifth measuring!!!");
		//Messung 5
		mock.clear();
		mock.add(new SensorData(0, 375));
		mock.add(new SensorData(-90, 75));
		mock.add(new SensorData(90, 75));
		
		mca.recalculateParticles(mock);
		mca.doResampling();
		mc.repaint();
		jan();
		moveTest();
		jan();
		
		
		System.out.println("!!!sixth measuring!!!");
		//Messung 6
		mock.clear();
		mock.add(new SensorData(0, 325));
		mock.add(new SensorData(90, 25));
		mock.add(new SensorData(-90, 75));
		
		System.out.println("recalc");
		mca.recalculateParticles(mock);
		System.out.println("then resample");
		mca.doResampling();
		System.out.println("then repaint");
		mc.repaint();
		jan();
		moveTest();
		jan();
		
	
		
		System.out.println("!!!Seventh measuring!!!");
		//Messung 7
		mock.clear();
		mock.add(new SensorData(0, 275));
		mock.add(new SensorData(-90, 25));
		mock.add(new SensorData(90, 75));
		
		mca.recalculateParticles(mock);
		mca.doResampling();
		mc.repaint();
		moveTest();
		System.out.println("!!!Eighth measuring!!!");
		//Messung 8
		mock.clear();
		mock.add(new SensorData(0, 225));
		mock.add(new SensorData(-90, 75));
		mock.add(new SensorData(90, 75));
		
		mca.recalculateParticles(mock);
		mca.doResampling();
		mc.repaint();
		jan();
		moveTest();
		jan();
		
		System.out.println("!!!Ninth measuring!!!");
		//Messung 9
		mock.clear();
		mock.add(new SensorData(0, 175));
		mock.add(new SensorData(-90, 25));
		mock.add(new SensorData(90, 25));
		
		mca.recalculateParticles(mock);
		mca.doResampling();
		mc.repaint();
		jan();
		moveTest();
		jan();
		
		System.out.println("!!!Tenth measuring!!!");
		//Messung 10
		mock.clear();
		mock.add(new SensorData(0, 125));
		mock.add(new SensorData(-90, 75));
		mock.add(new  SensorData(90, 75));
		
		mca.recalculateParticles(mock);
		mca.doResampling();
		mc.repaint();
		jan();
		mca.turnParticles(90);
		jan();
		
		System.out.println("!!!Eleventh measuring!!!");
		mock.clear();
		mock.add(new SensorData(0, 75));
		mock.add(new SensorData(-90, 125));
		mock.add(new SensorData(90, 475));
		
		mca.recalculateParticles(mock);
		mca.doResampling();
		mc.repaint();
		moveTest();
		
		System.out.println("!!!Twelveth measuring!!!");
		mock.clear();
		mock.add(new SensorData(0, 25));
		mock.add(new SensorData(-90, 25));
		mock.add(new SensorData(90, 25));
		
		mca.recalculateParticles(mock);
		mca.doResampling();
		mc.repaint();
		
		mca.turnParticles(180);
		
		System.out.println("!!!Thirteenth measuring!!!");
		mock.clear();
		mock.add(new SensorData(0, 125));
		mock.add(new SensorData(-90, 25));
		mock.add(new SensorData(90, 25));
		
		mca.recalculateParticles(mock);
		mca.doResampling();
		mc.repaint();
		moveTest();
		
		System.out.println("!!!Ficekn measuring!!!");
		mock.clear();
		mock.add(new SensorData(0, 75));
		mock.add(new SensorData(-90, 125));
		mock.add(new SensorData(90, 475));
		
		mca.recalculateParticles(mock);
		mca.doResampling();
		mc.repaint();
	
		mca.doResampling();
		mc.repaint();
		System.out.println("||||||||||||DONE TESTING||||||||||||||||");
		
	}
	
	public void jan()
	{
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			
			e.printStackTrace();
		}
	}
	public void turnTest(int theta) {
		mca.turnParticles(theta);
		mc.repaint();
	}
	public void moveTest() 
	{
		mca.moveParticles(50);
		mc.repaint();
		//System.out.println(Arrays.toString(mca.getParticle().toArray()));
	}
	
	public void testenSIE() 
	{
		System.out.println("TESTEN SIE TESTENSIE!!!!");
		mca.start();
	//	mca.addParticle(new Particle(25, 75, 90));
		mc.repaint();
	}
	
}
