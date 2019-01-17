package math;

public class Vector 
{
	private double x, y, angle;
	
	public Vector(double x, double y)
	{
		this.x = x;
		this.y = y;
		angle = 0;
	}
	
	public Vector(double x, double y, double theta)
	{
		this(x, y);
		rotate(theta);
		angle = theta;
	}
	
	public void rotate(double theta)
	{
		double x1 = x * (Math.cos(theta)) - y * (Math.sin(theta));
		double y1 = x * (Math.sin(theta)) + y * (Math.cos(theta));
		x = Math.round(x1);
		y = Math.round(y1);
	}
	
	public String toString()
	{
		return "x: " + x + " y: " + y;
	}

	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}

	public double getAngle() {
		return angle;
	}

	public void setAngle(double angle) {
		this.angle = angle;
	}
	
	public static double distance(Vector a, Vector b)
	{
		return Math.sqrt(Math.pow(b.getX() - a.getX(), 2) + Math.pow(b.getY() - a.getY(), 2));
	}
}
