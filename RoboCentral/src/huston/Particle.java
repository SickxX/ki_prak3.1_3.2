package huston;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import math.Straight;
import math.Vector;

public class Particle {

	public static final int TOTAL_PARTICLES = 100;
	
	protected double id,x,y;
	protected double[] dir;
	protected float angle;
	private double probability;
	
	public Particle(double id, double x, double y) {
		this.id = id;
		this.x = x;
		this.y = y;
		probability = 1 / TOTAL_PARTICLES;
	}
	public Particle(double x, double y) {
		this.x = x;
		this.y = y;
	}
	
	public Particle(double id, double x, double y, double[] dir) {
		this.id = id;
		this.x = x;
		this.y = y;
		this.dir = dir;
	}
	public Particle(double id, double x, double y, float angle) {
		this.id = id;
		this.x = x;
		this.y = y;
		this.angle = angle;
	}
	public float getAngle() {
		return angle;
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
	public double[] getDir() {
		return dir;
	}
	public void setDir(double[] dir) {
		this.dir = dir;
	}
	
	public void draw(Graphics g, float scale)
	{
//		g.setColor(c);
		g.fillOval((int)getX(),(int) getY(), 3, 3);
//		g.drawLine(from.getX() + Vertex.SIZE/2, from.getY() + Vertex.SIZE/2, to.getX() + Vertex.SIZE/2, to.getY() + Vertex.SIZE/2);
		g.setColor(Color.RED);
		//g.drawLine((int) ((from.getX() + Vertex.SIZE/2) * scale), from.getY() + (int) ((from.getX() + Vertex.SIZE/2 * scale)), (int) ((to.getX() + Vertex.SIZE/2) * scale), (int) ((to.getY() + Vertex.SIZE/2) * scale));
	}
	
	/**
	 * Recalculate this particles probability
	 * @param forward 0 degree
	 * @param left -90 degree
	 * @param right 90 degree
	 */
	public void recalculate(float forward, float left, float right, Map m)
	{
		float dForward = 0;
		
		Straight view = new Straight(new Vector(x, y), new Vector(1, 0, angle));
		System.out.println(" ------------ Closest: " + m.closestIntersection(view));
		
		

	}
	
	
	/**
	 * 
	 * @param distance distance in centimeters, distance that the particle moves
	 */
	public void moveParticle(int distance) {
		setY(y + Math.sin(getAngle())*distance);
		setX(x + Math.cos(getAngle())*distance);
	}	
}
