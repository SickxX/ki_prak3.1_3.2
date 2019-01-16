package huston;

import java.awt.Color;

import javax.swing.JFrame;

public class MapContainer {
	private MapBuild mb;
	private Map map;

	private MapPanel mp;
	public MapContainer()
	{
		mb = new MapBuild();
		map = mb.newMap();
		mp = new MapPanel(map);
		start();
		mp.repaint();

		
		
		

	}

	public void start()
	{
		JFrame frame = new JFrame("Graph");

		frame.add(mp);

		frame.setSize(650, 200);
		frame.setBackground(Color.WHITE);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
}
