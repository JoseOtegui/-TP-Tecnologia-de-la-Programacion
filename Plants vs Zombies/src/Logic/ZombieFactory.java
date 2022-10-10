package Logic;

import java.util.Random;

import Logic.Command.CommandExecuteException;
import Logic.Elements.BucketHeadZombie;
import Logic.Elements.CommonZombie;
import Logic.Elements.Plant;
import Logic.Elements.SportyZombie;
import Logic.Elements.Zombie;

public abstract class ZombieFactory {

	private static Zombie[] availableZombies = { new CommonZombie(), new BucketHeadZombie(), new SportyZombie() };

	public static Zombie getZombie(Random rnd, int x, int y) {
		 int pos = (rnd.nextInt(availableZombies.length));
	       return availableZombies[pos].getZombie(x,y);
	    }

	public static Zombie loadZombies(String name, int x, int y) throws CommandExecuteException
	{
		for(int i = 0; i < availableZombies.length; i++)
		{
			if(availableZombies[i].getName().equalsIgnoreCase(name) || availableZombies[i].getShortcut().equalsIgnoreCase(name) )
				return availableZombies[i].getZombie(x,y);
		}
		throw new CommandExecuteException("Unknown plant name: " + name);
	}
	
	public static String listOfAvailableZombies() {

		String aux = "";

		for (int i = 0; i < availableZombies.length; i++) {
			aux = aux + availableZombies[i].getInfo() + "\n";
		}
		return aux;
	}

}
