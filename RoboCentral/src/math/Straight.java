package math;

import java.util.function.Function;

public class Straight 
{
	private Vector position;
	private Vector direction;
	
	public Function<Double, Vector> calculate = input -> calculate(input);
	
	public Straight(Vector pos, Vector dir)
	{
		position = pos;
		direction = dir;
	}
	
	
	public Vector getPosition() {
		return position;
	}


	public void setPosition(Vector position) {
		this.position = position;
	}


	public Vector getDirection() {
		return direction;
	}


	public void setDirection(Vector direction) {
		this.direction = direction;
	}

	public Vector calculate(double x)
	{
		return calculate(this, x);
	}
	
	
	
	// --- Statics
	public static Vector calculate(Straight s, double distance)
	{
		return new Vector(s.getPosition().getX() + distance * s.getDirection().getX(), 
				s.getPosition().getY() + distance * s.getDirection().getY());
	}
	
	public static Vector intersects(Straight s, Straight t)
	{
		Vector v1 = s.calculate(0);
		Vector v2 = s.calculate(1);
		Vector v3 = t.calculate(0);
		Vector v4 = t.calculate(1);
		
		double x1 = v1.getX();
		double y1 = v1.getY();
		
		double x2 = v2.getX();
		double y2 = v2.getY();
		
		double x3 = v3.getX();
		double y3 = v3.getY();
		
		double x4 = v4.getX();
		double y4 = v4.getY();
		
		// --- Formel von http://en.wikipedia.org/wiki/Line-line_intersection
		// --- Zähler 
		double zx = ((x1 * y2 - y1 * x2) * (x3-x4)) - ((x1 - x2) * (x3 * y4 - y3 * x4));
	    double zy = ((x1 * y2 - y1 * x2) * (y3-y4)) - ((y1 - y2) * (x3 * y4 - y3 * x4));
	     
		// --- Nenner
	    double n = ((x1 - x2) * (y3 - y4)) - ((y1 - y2) * (x3 - x4));
	   
		return new Vector(zx/n, zy/n);
	}
	
	public boolean isOnPositive(Vector v)
	{
		boolean result = false;
		Vector end = calculate(1000);
		
		if(position.getX() <= end.getX())
		{
			result = position.getX() <= v.getX() && v.getX() <= end.getX();
		}
		else
		{
			result = position.getX() >= v.getX() && v.getX() >= end.getX();
		}
		
		if(result && position.getY() <= end.getY())
		{
			result = position.getY() <= v.getY() && v.getY() <= end.getY();
		}
		else
		{
			result = position.getY() >= v.getY() && v.getY() >= end.getY();
		}
		
		return result;
	}
}