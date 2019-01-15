package huston;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

public class MapPanel extends JPanel{

public Map map;
	
	public MapPanel(Map m)
	{
		this.map = m;
	}
	
	@Override
    public void paintComponent(Graphics g) 
	{
		g.setColor(Color.BLACK);
		
		if(this.map != null){
			this.map.draw(g);
		}
    }
}
