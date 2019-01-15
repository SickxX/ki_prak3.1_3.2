package huston;

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

		frame.setSize(1000, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
}
