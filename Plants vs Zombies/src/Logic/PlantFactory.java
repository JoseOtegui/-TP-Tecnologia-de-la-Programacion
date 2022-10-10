package Logic;

import Logic.Command.CommandExecuteException;
import Logic.Elements.CherryBomb;
import Logic.Elements.Peashooter;
import Logic.Elements.Plant;
import Logic.Elements.Sunflower;
import Logic.Elements.WallNut;

public abstract class PlantFactory {

	private static Plant [] availablePlants= { new Sunflower(), new Peashooter(), new CherryBomb(), new WallNut()};
	
	public static Plant getPlant(String name, int x, int y) throws CommandExecuteException
	{
		for(int i = 0; i < availablePlants.length; i++)
		{
			if(availablePlants[i].getName().equalsIgnoreCase(name) || availablePlants[i].getShortcut().equalsIgnoreCase(name) )
				return availablePlants[i].getPlanta(x,y);
		}
		throw new CommandExecuteException("Unknown plant name: " + name);
	}
	public static String listOfAvailablePLants()
	{
		
		String aux = "";
		
		for(int i = 0; i < availablePlants.length; i++)
		{
			aux = aux + availablePlants[i].getInfo() + "\n";
		}
		return aux;
	}
	
	
}
