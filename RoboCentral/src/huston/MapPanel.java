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
	}
	
	public MCA getMCA()
	{
		return mca;
	}
	
	public void repaintAll()
	{
		this.repaint();
		this.revalidate();
		System.out.println("Repainted");
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
