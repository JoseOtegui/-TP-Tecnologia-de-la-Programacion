package Logic.Elements;

public class SportyZombie extends Zombie {
	
	private int frequency = 1;
	private int damage = 1;
	private int remaining_cycles = 0;
	
	public SportyZombie(int x, int y, String name) {
		super(x, y, name,2,"x");
	}

	public SportyZombie() {
		super("SportyZombie","x");
	}

	public Zombie getZombie(int x, int y) {
		return new SportyZombie(x,y,"SportyZombie");
	}

	public String toString() {
		return " X [" + getLife() + "] ";
	}

	
	
	public String getInfo() {
		return "[Z]ombie deportista: speed: 1 Harm: 1 Life: 2";
	}
	
	public String toStringDebug() {
		return " X [" + "l:"+ getLife() + ",x:"+getX()+",y:"+getY()+ ",t:"+ remaining_cycles +" ]";
	}

	protected int getFrequency() {
		return frequency;
	}

	protected int getDamage() {
		return damage;
	}

	
	public int getRemainingCycles() {
		return remaining_cycles;
	}

	public void setRemainingCycles(int n) {
		this.remaining_cycles = n;
	}
	
	public int changeCycle(int n) {
		return frequency-n;
	}
		
}
