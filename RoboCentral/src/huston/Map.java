package huston;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

public class Map {

	private static final float SCALE = 1f;
	public static final Color DEFAULT = Color.BLACK;

	private ArrayList<Line>  lines;


	public ArrayList<Line> getLines() {
		return lines;
	}

	public Map(int n)
	{
		lines= new ArrayList<>();
	}

	public void addLine(Line p) 
	{
		lines.add(p);
	}
	public void draw(Graphics g)
	{
		for(int i = 0; i < lines.size(); i++)
		{
			lines.get(i).draw(g,SCALE);
			System.out.println(i+1 +". Linie " + lines.get(i).getY1()+ " " + lines.get(i).getX1()+ " " +lines.get(i).getY2()+ " " +lines.get(i).getX2());
		}	
	}
}
