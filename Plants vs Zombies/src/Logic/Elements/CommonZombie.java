package Logic.Elements;

public class CommonZombie extends Zombie {

	private int frequency = 2;
	private int damage = 1;
	private int remaining_cycles = 0;

	public CommonZombie(int x, int y, String name) {
		super(x, y, name, 5, "z");
	}

	public CommonZombie() {
		super("CommonZombie", "z");
	}

	public Zombie getZombie(int x, int y) {
		return new CommonZombie(x, y, "CommonZombie");
	}

	public String toString() {
		return " Z [" + getLife() + "] ";
	}
	
	public String toStringDebug() {
		return " Z [" + "l:"+ getLife() + ",x:"+getX()+",y:"+getY()+ ",t:"+ remaining_cycles +" ]";
	}

	public String getInfo() {
		return "[Z]ombie comun: speed: 2 Harm: 1 Life: 5";
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
