package huston;

import huston.XMLimport;

public class MapBuild {

	private XMLimport imp;

	public MapBuild(){
		this.imp = new XMLimport("3.2_Houses-1819.svg");
	}

	public Map newMap()
	{
		int nSize = imp.getLength("line");
		System.out.println("New Graph with " + nSize + " Lines");
		long elapsedTime = System.currentTimeMillis();
		Map g = new Map();
		
		System.out.println("Adding Lines!");
		for(int i=0;i<nSize;i++){
			Line l = new Line(imp.reader(i,"x1","line"),imp.reader(i, "y1","line"),imp.reader(i, "x2","line"),imp.reader(i, "y2","line"));
			if(l.length() > Map.CLUSTERSIZE)
			{
				g.addLines(l.split(Map.CLUSTERSIZE));
			}
			else 
			{
				g.addLine(l);				
			}
		}
		System.out.println("Finishing...");
		System.out.println("Elapsed Time: " + (System.currentTimeMillis() - elapsedTime) + "ms");
		g.generateShape();
		return g;
	}
}
