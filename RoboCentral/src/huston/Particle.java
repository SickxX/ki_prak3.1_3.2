package huston;

import java.awt.Color;
import java.awt.Graphics;

public class Particle {

	protected int id,x,y;
	protected int[] dir;
	
	public Particle(int id, int x, int y) {
		this.id = id;
		this.x = x;
		this.y = y;
	}
	public Particle(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public Particle(int id, int x, int y, int[] dir) {
		this.id = id;
		this.x = x;
		this.y = y;
		this.dir = dir;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	public int[] getDir() {
		return dir;
	}
	public void setDir(int[] dir) {
		this.dir = dir;
	}
	
	public void draw(Graphics g, float scale)
	{
//		g.setColor(c);
		g.fillOval(getX(), getY(), 3, 3);
//		g.drawLine(from.getX() + Vertex.SIZE/2, from.getY() + Vertex.SIZE/2, to.getX() + Vertex.SIZE/2, to.getY() + Vertex.SIZE/2);
		g.setColor(Color.RED);
		//g.drawLine((int) ((from.getX() + Vertex.SIZE/2) * scale), from.getY() + (int) ((from.getX() + Vertex.SIZE/2 * scale)), (int) ((to.getX() + Vertex.SIZE/2) * scale), (int) ((to.getY() + Vertex.SIZE/2) * scale));
	}
	
	
}
