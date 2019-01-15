package huston;

import java.util.ArrayList;
import java.util.Random;


public class MCA {
	
	
	private ArrayList<Particle> partMenge = new ArrayList<>();
	
	protected final static int M = 1000; 
	private int r1,r2;
	private Random rand1 = new Random();

	public ArrayList<Particle> getParticle(){
		return partMenge;
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
		
		while( i < M ) {
			//r1 zufallswert für die xAchse
			//r2 für yAchse
			r1 = rand1.nextInt(6000);
			r2 = rand1.nextInt(1500);
			partMenge.add(new Particle(i, r1, r2));
			i++;	
		}
	}
}
