	package Logic.Command;

import Logic.Game;
import Logic.Control.Controller;

public class ListCommand extends NoParamsCommand{	
	
	private static final String name = "list";
	private static final String helpInfo = "[L]ist: print the list of available plants.";
	private static final String shortcut = "l";
	
	public ListCommand()
	{
		super(name,shortcut,helpInfo);
	}
	public Command parse(String[] args) throws CommandParseException, CommandExecuteException {
		if(args[0].equalsIgnoreCase(shortcut)|| args[0].equalsIgnoreCase(name))
		{
			if(args.length > 1)
				throw new CommandExecuteException("List command has no arguments");
			else
				return new ListCommand();
		}
		
		return null;
	}
	
	
	public boolean execute(Game game, Controller controller) {
		controller.getAvailablePlants();
		return false;
	}

}
