package Logic.Elements;

public class CherryBomb extends Plant {
	
	private int frequency = 2;
	private int damage = 10;
	private int remaining_cycles = 0;

	
	public CherryBomb(int x, int y, String name) {
		super(x, y, name, 50, 2,"c");
	}

	public CherryBomb() {
		super("CherryBomb", "c");
	}

	public Plant getPlanta(int x, int y) {
		return new CherryBomb(x, y, this.getName());
	}

	
	public void update() {
		setCycle(getCycle() + 1);
		if(remaining_cycles == 0)
			setRemainingCycles(frequency);
		else
			setRemainingCycles(remaining_cycles -1);
		if (getCycle() % frequency == 0)
		{
			board.cherryAttack(getX(), getY(), damage);	
			board.deleteCherry(getX(),getY());
		}
	}

	public String toString() {
		return " C [" + getLife() + "] ";
	}
	
	public String getInfo()
	{
		return "Peta[c]ereza: Cost: 50 suncoins Harm: 10";
	}
	
	public String toStringDebug() {
		return " C [" + "l:"+ getLife() + ",x:"+getX()+",y:"+getY()+ ",t:"+ remaining_cycles +" ]";
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
