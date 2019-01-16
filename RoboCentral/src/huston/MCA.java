package huston;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Random;


public class MCA {


	private ArrayList<Particle> partMenge = new ArrayList<>();
	private static final float SCALE = 1f;
	protected final static int M = 1000; 
	private double r1,r2;
	private float angle;
	private Random rand1 = new Random();
	private Map map;
	
	public MCA(Map map)
	{
		this.map = map;
	}


	public ArrayList<Particle> getParticle(){
		return partMenge;
	}

	public void draw(Graphics g)
	{
		for(int i = 0; i < partMenge.size(); i++)
		{
			partMenge.get(i).draw(g,SCALE);
			System.out.println(partMenge.get(i).getAngle());
		}	
		System.out.println("particles " + partMenge.size());
	}

	public void addNewParticle(int x, int y) {
		partMenge.add(new Particle(x, y));
	}

	public void addParticle(Particle p) {
		partMenge.add(p);
	}

	public void generateParticles(int p){
		partMenge.clear();

		int i = 0;

		while( i < p ) {
			//r1 zufallswert für die xAchse
			//r2 für yAchse
			r1 = rand1.nextDouble()*600;
			r2 = rand1.nextDouble()*150;
			angle = rand1.nextFloat()*360;
			if(map.isInside((int)r1, (int)r2)) {
				partMenge.add(new Particle(i, r1, r2));
				i++;					
			}

		}
	}
	public void moveParticles(int distance) {
		for(Particle p : partMenge) {
			p.moveParticle(distance);
		}
	}
}
