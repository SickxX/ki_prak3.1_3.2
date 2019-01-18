package huston;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

import huston.Sensor.SensorData;
import math.Straight;
import math.Utils;
import math.Vector;

public class Particle {

	public static final int TOTAL_PARTICLES = 10000;
	
	protected double id,x,y;
	protected double angle;
	private double probability;

	public Particle(double id, double x, double y) {
		this.id = id;
		this.x = x;
		this.y = y;
		probability = 1;
	}
	public Particle(double x, double y) {
		this(-1, x, y);
	}

	//	public Particle(double id, double x, double y, double[] dir) {
	//		this.id = id;
	//		this.x = x;
	//		this.y = y;
	//		this.dir = dir;
	//	}
	public Particle(double id, double x, double y, float angle) {
		this(id, x, y);
		this.angle = angle;
	}
	public double getAngle() {
		return angle;
	}
	public void setAngle(double angle) {
		this.angle = angle;
	}
	public double getId() {
		return id;
	}
	public void setId(double id) {
		this.id = id;
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
	
	public String toString()
	{
		return "Partikel: " + id + ", x:" + x + ", y" + y + ", probability:" + probability; 
	}
	
	public double getProbabitlity() {
		return probability;
	}
	
	public void draw(Graphics g, float scale)
	{
		
		g.setColor(new Color(1, 0, 0, (float)(probability)));
//		g.setColor(new Color(1f, 0f, 0f));
		g.fillOval((int)getX(),(int) getY(), 3, 3);
}

	/**
	 * Recalculates based on Scanned data
	 * @param data The Scan result
	 * @param map The Map
	 */
	public void recalculate(ArrayList<SensorData> data, Map map)
	{
		double error = 0;
		
		for(SensorData d: data)
		{
			Straight view = new Straight(new Vector(x, y), new Vector(Utils.converteToRad(angle + d.getRotation())));	
			Vector closest =  map.closestIntersection(view);
			
			double distanceToClosest = Vector.distance(new Vector(x, y), closest);
			error += (Math.pow(distanceToClosest - d.getDistance(), 2));
		}
		
		if(error != 0)
			probability = 1 / error;
		else 
			probability = 1;
		
		if(probability > 1)
			probability = 1;

	}


	/**
	 * 
	 * @param distance distance in centimeters, distance that the particle moves
	 */
	public void moveParticle(int distance) {

		setY(y + Math.sin(getAngle())*distance);
		setX(x + Math.cos(getAngle())*distance);
	}	
	public void turnParticle(int theta) {
		if((getAngle()+theta) < 0 ) {
			setAngle(360 +(getAngle()+theta));
		}else if((getAngle()+theta) > 360){			
			setAngle((getAngle()+theta) % 360);			
		}
	}
}
