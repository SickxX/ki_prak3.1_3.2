package huston;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.color.ColorSpace;
import java.util.ArrayList;

import huston.Robot.SensorData;
import math.Straight;
import math.Utils;
import math.Vector;

public class Particle {

	public static final int TOTAL_PARTICLES = 50000;
	
	protected double x,y;
	protected double angle;
	private double probability;
	private double error;

	public Particle(double x, double y) {
		this.x = x;
		this.y = y;
		probability = 1;
	}
	public Particle(double x, double y, double angle) {
		this(x,y);
		this.angle = angle;
	}

	public Particle(double x, double y, double angle, double prob) {
		this(x, y);
		this.angle = angle;
		this.probability = prob;
	}
	
	public double getAngle() {
		return angle;
	}
	public void setAngle(double angle) {
		this.angle = angle;
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
	public double getError()
	{
		return error;
	}
	
	
	public String toString()
	{
		return "Partikel: " + "x:" + x + ", y:" + y + ", Angel: " + angle + ", probability:" + probability; 
	}
	
	public double getProbabitlity() {
		return probability;
	}
	
	public void draw(Graphics g, float scale)
	{	
		//RED
		if (probability > 0.95 && probability <= 1 )	 
		{
		g.setColor(new Color(1, 0, 0, (float)(probability)));
		g.fillOval((int)getX(),(int) getY(), 4, 4);
		}//BLUE
//		else
//			g.setColor(new Color(0, 0, 1, (float)(probability)));
//			g.fillOval((int)getX(),(int) getY(), 4, 4);
}

	/**
	 * Recalculates based on Scanned data
	 * @param data The Scan result
	 * @param map The Map
	 */
	public void recalculate(ArrayList<SensorData> data, Map map)
	{
		error = 0;
//		System.out.println(this);
		for(SensorData d: data)
		{
			Straight view = new Straight(new Vector(x, y), new Vector(Utils.converteToRad(angle + d.getRotation())));	
			Vector closest =  map.closestIntersection(view);
			
			double distanceToClosest = Vector.distance(new Vector(x, y), closest);
			error += Math.sqrt((Math.pow(distanceToClosest - d.getDistance(), 2)));
			//System.out.println("Error: " + error);
//			System.out.println("CLOSEST " + closest);
		}
		

	}

	public void normalize(double maxError, double minError)
	{

		double prev = probability;
		
		if (error < 1 )
			probability = 1;
		else
			probability = 1- ((error - minError )/ ( maxError - minError));
//		probability = prev * ( 1 - ( ( error - minError) / ( maxError - minError ) ));
		//System.out.println("minmaxGEDÖNS " + minError + " "+ maxError);
	}
	public void penalize()
	{
		probability = 0;
	}

	/**
	 * Moves Particle into the direction it is looking
	 * @param distance distance in centimeters, distance that the particle moves
	 */
	public void moveParticle(int distance) {

		setY(y - Math.cos(Utils.converteToRad(getAngle()))*distance);
		setX(x + Math.sin(Utils.converteToRad(getAngle()))*distance);
	}
	
	//TODO
	// RAD statt GRAD!!
	/**
	 * Turns Particle into a given direction
	 * @param theta angle the particle turns
	 */
	public void turnParticle(int theta) {
				
			setAngle((getAngle()+theta) % 360);			
		
	}
}
