import lejos.hardware.motor.Motor;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.hardware.sensor.EV3UltrasonicSensor;
import lejos.remote.ev3.RMISampleProvider;
import lejos.remote.ev3.RemoteEV3;
import lejos.robotics.SampleProvider;
import lejos.robotics.chassis.Chassis;
import lejos.robotics.chassis.Wheel;
import lejos.robotics.chassis.WheeledChassis;
import lejos.robotics.navigation.MovePilot;
import lejos.utility.Delay;

public class CommandCenter {

	private Wheel wheel1, wheel2;
	private MovePilot mp;
	private Chassis chassis;
	private EV3ColorSensor colorSensor;
	private SampleProvider colorProvider, sp;
	private RMISampleProvider penis = null;
	private float[] colorSample;
	private RemoteEV3 eve3 = null;
	private EV3UltrasonicSensor us;
	private int it;
	private float[] sample;
	private float distanceValue;

	public CommandCenter() {

		this.wheel1 = WheeledChassis.modelWheel(Motor.A, 56).offset(-50);
		this.wheel2 = WheeledChassis.modelWheel(Motor.D, 56).offset(50);
		chassis = new WheeledChassis(new Wheel[] {wheel1, wheel2},WheeledChassis.TYPE_DIFFERENTIAL);
		mp = new MovePilot(chassis);

		us = new EV3UltrasonicSensor(SensorPort.S4);
		sp = us.getDistanceMode();
		distanceValue = (float) 0.000;
		it = 100;


		colorSensor = new EV3ColorSensor(SensorPort.S2);
		colorProvider = colorSensor.getRGBMode();
		colorSample = new float[colorProvider.sampleSize()];


	}



	public String scan() {

		System.out.println("Scanning...");
		String rgb= "empty";
		boolean c = true;
		while(c) {
			colorProvider.fetchSample(colorSample, 0);
			rgb = "R: " + colorSample[0]+ " G: " + colorSample[1] +" B: " + colorSample[2];


			sample = new float[sp.sampleSize()];
			sp.fetchSample(sample, 0);
			distanceValue = (float) sample[0];
			rgb = rgb + "; Distance: " + distanceValue *100 + "\n";

			Delay.msDelay(500);


			c = false;
		}
		System.out.println("Scan finished!");
		return rgb;
	}
	public void forward(int distance) {
		mp.travel(-distance);
		stop();
	}
	public void backward(int distance) {
		mp.travel(distance);
		stop();
	}
	public void turnRight(int angle) {
		mp.setAngularSpeed(100);
		mp.rotate(angle);
		stop();
	}
	public void turnLeft(int angle) {
		mp.setAngularSpeed(100);
		mp.rotate(-angle);
		stop();
	}
	public void stop() {
		mp.stop();
	}
}