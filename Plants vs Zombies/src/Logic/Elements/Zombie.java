package Logic.Elements;

public abstract class Zombie extends GameObject {

	private final boolean isZombie = true;

	public Zombie(int x, int y, String name, int life, String shortcut) {
		super(x, y, name, life, shortcut);
	}

	public Zombie(String name,String shortcut) {
		super(name, shortcut);
	}

	public abstract Zombie getZombie(int x, int y);

	public abstract String toString();

	public abstract String getInfo();

	public boolean getIsZombie() {
		return isZombie;
	}

	protected abstract int getFrequency();
	
	protected abstract int getDamage();
	public void update() {
		setCycle(getCycle() + 1);
		if(getRemainingCycles() == 0)
			setRemainingCycles(getFrequency());
		else
			setRemainingCycles(getRemainingCycles()-1);
		if (getCycle() % getFrequency() == 0)
			if (!board.zombieAttack(getX() - 1, getY(), getDamage())) {
				board.deleteZombie(getX(),getY());
				int x_aux = getX() - 1;
				setX(x_aux);
				board.addZombie(this);
			}
	}
	
}
