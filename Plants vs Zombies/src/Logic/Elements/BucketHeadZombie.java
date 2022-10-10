package Logic.Elements;

public class BucketHeadZombie extends Zombie {

	private int frequency = 4;
	private int remaining_cycles = 0;
	private int damage = 1;

	public BucketHeadZombie(int x, int y, String name) {
		super(x, y, name, 8, "w");
	}

	public BucketHeadZombie() {
		super("BucketHeadZombie", "w");
	}

	public Zombie getZombie(int x, int y) {
		return new BucketHeadZombie(x, y, "BucketHeadZombie");
	}

	public String toString() {
		return " W [" + getLife() + "] ";
	}

	public String getInfo() {
		return "[Z]ombie caracubo: speed: 4 Harm: 1 Life: 8";
	}

	public String toStringDebug() {
		return " W [" + "l:" + getLife() + ",x:" + getX() + ",y:" + getY() + ",t:" + remaining_cycles + " ]";
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
