package Logic.Elements;

public class WallNut extends Plant {

	public WallNut(int x, int y, String name) {
		super(x, y, name, 50, 10, "w");
	}

	public WallNut() {
		super("WallNut", "w");
	}

	public Plant getPlanta(int x, int y) {
		return new WallNut(x, y, this.getName());
	}

	public void update() {
	}

	public String toString() {
		int l = getLife();
		if (l >= 10)
			return " N[" + l + "] ";
		else
			return " N [" + l + "] ";
	}

	public String getInfo() {
		return "[N]uez: Cost: 50 suncoins Harm: 0";
	}

	public String toStringDebug() {
		return " N [" + "l:" + getLife() + ",x:" + getX() + ",y:" + getY() + ",t:" + "0 ]";
	}

	public int saveCycle() {
		return cycle;
	}

	public int getRemainingCycles() {
		return 0;
	}

	public void setRemainingCycles(int n) {
	}

	public int changeCycle(int n) {
		return 0;
	}

}
