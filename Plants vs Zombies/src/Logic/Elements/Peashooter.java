package Logic.Elements;

public class Peashooter extends Plant {

	private int frequency = 1;
	private int damage = 1;
	private int remaining_cycles = 0;
	
	public Peashooter(int x, int y, String name) {
		super(x, y, name, 50, 3,"p");
	}

	public Peashooter() {
		super("Peashooter","p");
	}

	public Plant getPlanta(int x, int y) {
		return new Peashooter(x, y, this.getName());
	}

	public void update() {
		setCycle(getCycle() + 1);
		if(remaining_cycles == 0)
			setRemainingCycles(frequency);
		else
			setRemainingCycles(remaining_cycles -1);
		if (getCycle() % frequency == 0)
			board.PeaAttack(getX() + 1, getY(), damage);
	}

	public String toString() {
		return " P [" + getLife() + "] ";
	}
	
	public String getInfo()
	{
		return "[P]eashooter: Cost: 50 suncoins Harm: 1";
	}
	public String toStringDebug() {
		return " P [" + "l:"+ getLife() + ",x:"+getX()+",y:"+getY()+ ",t:"+ remaining_cycles +" ]";
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
