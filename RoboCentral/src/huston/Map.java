package huston;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Collections;

public class Map {

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
			int x1 = horizontals.get(i).getX1();
			int y1 = horizontals.get(i).getY1();
			int x2 = horizontals.get(i+1).getX2();
			int y2 = horizontals.get(i+1).getY2();
			int dx = x2 - x1;
			int dy = y2 - y1;
			shape.add(new Rectangle(x1, y1, dx, dy));
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
	}
	
	public boolean isInside(int x, int y)
	{
		for(Rectangle r: shape)
		{
			if(r.contains(new Point(x, y)))
			{
				return true;
			}
		}
		return false;
	}
}
