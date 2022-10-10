package Logic.Elements;

public abstract class Plant extends GameObject {

	private int cost;
	private final boolean isZombie = false;

	public Plant(int x, int y, String name, int cost, int life,String shortcut) {
		super(x, y, name, life,shortcut);
		this.cost = cost;
	}

	public Plant(String name, String shortcut) {
		super(name,shortcut);
	}

	public abstract Plant getPlanta(int x, int y);

	public abstract String toString();

	public abstract String getInfo();
	


	public boolean getIsZombie() {
		return isZombie;
	}

	public int getCost() {
		return cost;
	}

	
}
