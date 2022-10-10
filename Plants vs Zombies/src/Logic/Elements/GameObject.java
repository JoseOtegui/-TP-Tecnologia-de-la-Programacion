package Logic.Elements;

import Logic.View.Board;

public abstract class GameObject {

	private int x;
	private int y;
	private String name;
	private String shortcut;
	private int life;
	protected int cycle = 0;
	protected Board board;

	GameObject(int x, int y, String name, int life, String shortcut) {
		this.x = x;
		this.y = y;
		this.name = name;
		this.life = life;
		this.shortcut = shortcut;
	}

	GameObject(String name, String shortcut) {
		this.name = name;
		this.shortcut = shortcut;
	}

	public abstract void update();

	public abstract boolean getIsZombie();

	public abstract String toStringDebug();

	public int subLife(int damage) {
		life = life - damage;
		return life;
	}

	public boolean isInPosition(int x, int y) {
		return (this.x == x && this.y == y);
	}

	public void setBoard(Board board) {
		this.board = board;
	}

	public int getLife() {
		return life;
	}
	
	public void setLife(int l)
	{
		life = l;
	}

	public int getX() {
		return this.x;
	}

	public int getY() {
		return this.y;
	}

	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getCycle() {
		return cycle;
	}

	public void setCycle(int cycle) {
		this.cycle = cycle;
	}
	
	public String getName() {
		return name;
	}

	public String getShortcut() {
		return shortcut;
	}


	public abstract int getRemainingCycles();
	
	public abstract void  setRemainingCycles(int n);
	
	public abstract int changeCycle(int n);
	
	

}
