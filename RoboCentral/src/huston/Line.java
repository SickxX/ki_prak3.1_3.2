package huston;

import java.awt.Graphics;
import java.util.Arrays;



public class Line {
	private int x1,x2,y1,y2;
	
	public Line (int x1,int y1,int x2, int y2) {
		
		this.x1 = Math.min(x1, x2);
		this.y1 = Math.min(y1, y2);
		this.x2 = Math.max(x1, x2);
		this.y2 = Math.max(y1, y2);
	}
	
	public double length()
	{
		return Math.abs((x1 - x2) + (y1 - y2));
	}
	
	public boolean isHorizontal()
	{
		return y1 == y2;
	}
	
	public Line[] split(int clusterSize)
	{
		Line[] result = new Line[(int)(length() / clusterSize)];
		if(length() < clusterSize) {
			return new Line[] {this};
		}
		if(x1 == x2)
		{
			for(int i = 0; i < result.length; i++)
			{
				result[i] = new Line(x1, y1 + clusterSize * i, x2, y1 + clusterSize * (i+1)); 
			}
		}
		else if(y1 == y2)
		{
			for(int i = 0; i < result.length; i++)
			{
				result[i] = new Line(x1  + clusterSize * i, y1, x1 + clusterSize * (i+1), y2); 
			}
		}
		else {
			throw new IllegalArgumentException("Du bist behindert");
		}
		return result;
	}

	public int getX1() {
		return x1;
	}

	public void setX1(int x1) {
		this.x1 = x1;
	}

	public int getX2() {
		return x2;
	}

	public void setX2(int x2) {
		this.x2 = x2;
	}

	public int getY1() {
		return y1;
	}

	public void setY1(int y1) {
		this.y1 = y1;
	}

	public int getY2() {
		return y2;
	}

	public void setY2(int y2) {
		this.y2 = y2;
	}
	
	public String toString()
	{
		return "(x1, y1) " + x1 + ", " + y1 + " (x2, y2) " + x2 + ", " + y2;
	}
	public void draw(Graphics g, float scale)
	{
//		g.setColor(c);
		g.drawLine(x1, y1, x2, y2);
//		g.drawLine(from.getX() + Vertex.SIZE/2, from.getY() + Vertex.SIZE/2, to.getX() + Vertex.SIZE/2, to.getY() + Vertex.SIZE/2);
		g.setColor(Map.DEFAULT);
		//g.drawLine((int) ((from.getX() + Vertex.SIZE/2) * scale), from.getY() + (int) ((from.getX() + Vertex.SIZE/2 * scale)), (int) ((to.getX() + Vertex.SIZE/2) * scale), (int) ((to.getY() + Vertex.SIZE/2) * scale));
	}

}
