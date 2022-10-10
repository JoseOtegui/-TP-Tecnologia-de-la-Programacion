package Logic.Elements;

public class Sunflower extends Plant {

	private int frequency = 2;
	private int suns_gen = 10;
	private int remaining_cycles = 0;
	
	public Sunflower(int x, int y, String name) {
		super(x, y, name, 20, 1,"s");

	}

	public Sunflower() {
		super("Sunflower","s");
	}

	public Plant getPlanta(int x, int y) {
		return new Sunflower(x, y, this.getName());
	}

	public void update() {
		setCycle(getCycle() + 1);

		if(remaining_cycles == 0)
			setRemainingCycles(frequency);
		else
			setRemainingCycles(remaining_cycles -1);
		if (getCycle() % frequency == 0)
			board.addSuncoins(suns_gen);
			
	}

	public String toString() {
		return " S [" + getLife() + "] ";
	}
	
	public String getInfo()
	{
		return "[S]unflower: Cost: 20 suncoins Harm: 0";
	}
	public String toStringDebug() {
		return " S [" + "l:"+ getLife() + ",x:"+getX()+",y:"+getY()+ ",t:"+ remaining_cycles +" ]";
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
