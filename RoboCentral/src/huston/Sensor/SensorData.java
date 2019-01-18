package huston.Sensor;

public class SensorData 
{
	private double rotation;
	private float distance;
	
	public SensorData(double r, float d)
	{
		rotation = r;
		distance = d;
	}

	public double getRotation() {
		return rotation;
	}

	public float getDistance() {
		return distance;
	}
}
