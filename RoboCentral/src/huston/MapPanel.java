package huston;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

public class MapPanel extends JPanel{

public Map map;
public MCA mca;
	
	public MapPanel(Map m)
	{
		this.map = m;
		mca = new MCA(map);
		mca.generateParticles(10000);
	}
	
	@Override
    public void paintComponent(Graphics g) 
	{
		g.setColor(Color.BLACK);
		
		if(this.map != null){
			this.map.draw(g);
			this.mca.draw(g);
		}
		
		
    }
}
