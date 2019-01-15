package huston;

public class Particle {

	protected int id,x,y;
	protected int[] dir;
	
	public Particle(int id, int x, int y) {
		this.id = id;
		this.x = x;
		this.y = y;
	}
	public Particle(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public Particle(int id, int x, int y, int[] dir) {
		this.id = id;
		this.x = x;
		this.y = y;
		this.dir= dir;
	}
	
}
