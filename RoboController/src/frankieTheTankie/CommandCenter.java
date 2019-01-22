package frankieTheTankie;

import lejos.hardware.motor.Motor;
import lejos.hardware.motor.NXTRegulatedMotor;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.hardware.sensor.EV3UltrasonicSensor;
import lejos.remote.ev3.RMISampleProvider;
import lejos.remote.ev3.RemoteEV3;
import lejos.robotics.SampleProvider;
import lejos.robotics.Servo;
import lejos.robotics.chassis.Chassis;
import lejos.robotics.chassis.Wheel;
import lejos.robotics.chassis.WheeledChassis;
import lejos.robotics.navigation.MovePilot;
import lejos.utility.Delay;

public class CommandCenter
{	
	private Wheel wheel1, wheel2;
	private NXTRegulatedMotor neck;
	private MovePilot movePilot;
	private Chassis chassis;
	private EV3ColorSensor colorSensor;
	private SampleProvider colorProvider, sampleProvider;
	private RMISampleProvider rmsporv = null;
	private float[] colorSample;
	private RemoteEV3 eve3 = null;
	private EV3UltrasonicSensor ultraSonicSensor;
	private int it;
	private float[] sample;
	private float distanceValue;
	private int neckPosition = 0;
 
	public CommandCenter() 
	{
		//TeamRot: (Motor.B, 40).offset(80)
		//TeamGelb: (Motor.B, 56).offset(60)
		this.wheel1 = WheeledChassis.modelWheel(Motor.C, 56).offset(-60);
		this.wheel2 = WheeledChassis.modelWheel(Motor.B, 56).offset(60);
		this.neck = Motor.A;
		chassis = new WheeledChassis(new Wheel[] {wheel1, wheel2}, WheeledChassis.TYPE_DIFFERENTIAL);
		movePilot = new MovePilot(chassis);

		ultraSonicSensor = new EV3UltrasonicSensor(SensorPort.S4);
		sampleProvider = ultraSonicSensor.getDistanceMode();
		distanceValue = (float) 0.000;
		it = 100;

		colorSensor = new EV3ColorSensor(SensorPort.S3);
		colorProvider = colorSensor.getRGBMode();
		colorSample = new float[colorProvider.sampleSize()];
	}

	
	
	
	public String colorScan() 
	{
		System.out.println("Scanning...");
		String rgb= "empty";
		
		colorProvider.fetchSample(colorSample, 0);
		rgb = "R: " + colorSample[0]+ " G: " + colorSample[1] +" B: " + colorSample[2];

		Delay.msDelay(500);

		System.out.println("Scan finished!");
		return rgb;
	}
	
	public String distanceScan()
	{
		sample = new float[sampleProvider.sampleSize()];
		sampleProvider.fetchSample(sample, 0);
		distanceValue = (float) sample[0];
		return "Distance: " + distanceValue *100;

	}
	/**
	 * - oder nicht - je nach gruppe
	 * @param distance
	 */
	public void forward(int distance) 
	{
		distance *= .95;
		movePilot.travel(-distance);
		stop();
	}
	
	public void backward(int distance) 
	{
		distance *= .95;
		movePilot.travel(-distance);
		stop();
	}
	
	public void turnRight(int angle) 
	{
		movePilot.setAngularSpeed(100);
		movePilot.rotate(-angle);
		stop();
	}
	
	public void turnLeft(int angle) 
	{
		movePilot.setAngularSpeed(100);
		movePilot.rotate(angle);
		stop();
	}	
	
	public void turn(int angle) 
	{
		movePilot.setAngularSpeed(100);
		movePilot.rotate(angle);
		stop();
	}
	
	public void stop() 
	{
		movePilot.stop();
	}
	
	public void lookAround(int position)
	{
		position = position > 90 ? 90 : position;
		position = position < -90 ? -90 : position;
		
		neck.rotate(position - neckPosition);
		neckPosition = position;
	}
	
	public void onKill()
	{
		lookAround(0);
	}
}