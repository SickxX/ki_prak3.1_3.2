package huston;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import huston.Robot.SensorData;


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
	}


	public void draw(Graphics g)
	{
		for(int i = 0; i < partMenge.size(); i++)
		{ 
			partMenge.get(i).draw(g,SCALE);

		}	

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


		// --- Fill with random Particles
		while( i < p ) {
			//r1 zufallswert für die xAchse
			//r2 für yAchse
			r1 = rand1.nextDouble()*600;
			r2 = rand1.nextDouble()*150;
			angle = rand1.nextFloat()*360;
			if(map.isInside((int)r1, (int)r2)) {
				partMenge.add(new Particle(r1, r2,angle));
				i++;					
			}
		}
	}

	public void recalculateParticles(ArrayList<SensorData> data)
	{

		double maxError = 0, minError = Double.MAX_VALUE;
		// --- Recalculate the Probability
		for(Particle part : partMenge)
		{
			part.recalculate(data, map);
			if(part.getError() > maxError) maxError = part.getError();
			if(part.getError() < minError) minError = part.getError();
		}

		for(Particle part : partMenge)
		{
			
			part.normalize(maxError, minError);
			
			if(!map.isInside((int)part.x, (int)part.y) || part.x < 0 || part.y < 0 || part.x > Map.WIDTH || part.y > Map.HEIGHT)
			{
				part.penalize();
			}
		}

	}

	public ArrayList<Particle> resample()
	{
		ArrayList<Particle> newList = new ArrayList<Particle>();
		// --- Resampling
		System.out.println("partMenge.Size: " + partMenge.size());
		while(newList.size() < partMenge.size()) 
		{
			int randomIndex = rand1.nextInt(partMenge.size());

			if ( 1 - partMenge.get(randomIndex).getProbabitlity() <= rand1.nextDouble()) 
			{
				Particle p = partMenge.get(randomIndex);
//				newList.addAll(p.mutate(2));
				newList.add(p);
			}
		}
		return newList;
	}

	public void doResampling()
	{

		partMenge = resample();
		System.out.println("Done resampling");
	}

	/**
	 * calls the moveParticle method for every particle
	 * @param distance  distance in centimeters, distance that the particles move
	 */
	public void moveParticles(int distance) {
		for(Particle p : partMenge) {
			p.moveParticle(distance);
		}
	}
	/**
	 * calls the turnParticle method for every particle
	 * @param theta angle the particles turn
	 */
	public void turnParticles(int theta) {
		for(Particle p : partMenge) {
			p.turnParticle(theta);
		}
	}
}
