package huston;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Collections;

public class Map {

	private static final int WIDTH = 600;
	private static final int HEIGHT = 150;
	private static final float SCALE = 1f;
	public static final Color DEFAULT = Color.BLACK;
	public static final int CLUSTERSIZE = 50;
	
	private ArrayList<Line>  lines;
	private ArrayList<Rectangle> shape;

	public Map()
	{
		lines = new ArrayList<>();
		shape = new ArrayList<>();
	}

	public ArrayList<Line> getLines() {
		return lines;
	}


	public void addLine(Line p) 
	{
		lines.add(p);
	}
	
	public void addLines(Line[] p)
	{
		for(Line l: p)
		{
			lines.add(l);			
		}
	}
	
	public void generateShape()
	{
		
		// --- horizontal Lines only!
		ArrayList<Line> horizontals = new ArrayList<>();
		for(Line l : lines)
		{
			if(l.isHorizontal())
				horizontals.add(l);
		}
		
		// --- Sort
		horizontals = radixSort(horizontals);
		
		for(int i = 0; i < horizontals.size() - 1; i+=2)
		{
			int upperX1 = horizontals.get(i).getX1();
			int upperX2 = horizontals.get(i).getX2();
			int upperY = horizontals.get(i).getY2();
			
			// --- TEST
			shape.add(new Rectangle(upperX1, 0, upperX2 - upperX1, upperY));
			
			int lowerX1 = horizontals.get(i+1).getX1();
			int lowerX2 = horizontals.get(i+1).getX2();
			int lowerY = horizontals.get(i+1).getY1();
			shape.add(new Rectangle(lowerX1, lowerY, lowerX2 - lowerX1, HEIGHT - lowerY));

//			int dx = x2 - x1;
//			int dy = y2 - y1;
		}
		
	}
	
	private ArrayList<Line> radixSort(ArrayList<Line> l)
	{
		Collections.sort(l, (p1, p2) -> p1.getY1() - p2.getY1());
		Collections.sort(l, (p1, p2) -> p1.getX1() - p2.getX1());
		return l;
	}
	
	public void draw(Graphics g)
	{
		for(int i = 0; i < lines.size(); i++)
		{
			lines.get(i).draw(g,SCALE);
			System.out.println(i+1 +". Linie " + lines.get(i).getY1()+ " "
			+ lines.get(i).getX1()+ " " +lines.get(i).getY2()+ " " +lines.get(i).getX2() + " Length: " + lines.get(i).length());
		}
		
//		for(Rectangle r: shape)
//		{
//			g.drawRect(r.x, r.y, r.width, r.height);
//		}
	}
	
	public boolean isInside(int x, int y)
	{
		for(Rectangle r: shape)
		{
			if(r.contains(new Point(x, y)))
			{
				return false;
			}
		}
		return true;
	}
}
