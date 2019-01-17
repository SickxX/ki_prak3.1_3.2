package huston;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Random;

import huston.Sensor.SensorData;
import math.Straight;
import math.Vector;


public class MCA {


	private ArrayList<Particle> partMenge = new ArrayList<>();
	private static final float SCALE = 1f;
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
	
	public void start()
	{
		generateParticles(Particle.TOTAL_PARTICLES);
		recalculateParticles();
	}

	public void draw(Graphics g)
	{
		for(int i = 0; i < partMenge.size(); i++)
		{
			partMenge.get(i).draw(g,SCALE);
//			System.out.println(partMenge.get(i).getAngle());
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
		
		addParticle(new Particle(0, 10, 90, 180));
	//	addParticle(new Particle(0, 10, 90, 30));
		
		
		// --- Fill with random Particles
		while( i < p ) {
			//r1 zufallswert f�r die xAchse
			//r2 f�r yAchse
			r1 = rand1.nextDouble()*600;
			r2 = rand1.nextDouble()*150;
			angle = rand1.nextFloat()*360;

			if(map.isInside((int)r1, (int)r2)) {
				partMenge.add(new Particle(i, r1, r2,angle));
				i++;					
			}
		}
	}
	
	public void recalculateParticles()
	{
		
		// --- Mockdata
		ArrayList<SensorData> data = new ArrayList<>();
		data.add(new SensorData(0, 10));
		data.add(new SensorData(45, 15));
	
		// --- Recalculate the Probability
		for(Particle part : partMenge)
		{
			part.recalculate(data, map);
		}
		
		// --- Resampling
		int randomIndex = rand1.nextInt(partMenge.size());
		
	}
	public void moveParticles(int distance) {
		for(Particle p : partMenge) {
			p.moveParticle(distance);
		}
	}
}
