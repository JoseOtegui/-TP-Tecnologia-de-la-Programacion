package Logic.Command;

import Logic.Game;
import Logic.ZombieFactory;
import Logic.Control.Controller;

public class ZombieListCommand extends NoParamsCommand {
	
	private static final String name = "zombielist";
	private static final String helpInfo = "zombieList: print the list of zombies.";
	private static final String shortcut = "zl";
	
	public ZombieListCommand()
	{
		super(name,shortcut,helpInfo);
	}
	
	public ZombieListCommand parse(String[] args) throws CommandParseException, CommandExecuteException {
		if(args[0].equalsIgnoreCase(name) || args[0].equals(shortcut))
		{
			if(args.length > 1)
				throw new CommandExecuteException("Zombielist command has no arguments");
			else
				return new ZombieListCommand();
		}
		return null;
	}
	
	
	public boolean execute(Game game, Controller controller) {
	       System.out.println(ZombieFactory.listOfAvailableZombies());
		return false;
	}

}
