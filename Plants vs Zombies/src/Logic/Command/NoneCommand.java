package Logic.Command;

import Logic.Game;
import Logic.Control.Controller;

public class NoneCommand extends NoParamsCommand {

	private static final String name = "none";
	private static final String helpInfo = "none: skips cycle.";
	private static final String shortcut = "";
	
	public NoneCommand()
	{
		super(name,shortcut,helpInfo);
	}
	
	public Command parse(String[] args) throws CommandParseException, CommandExecuteException {
		if(args[0].equalsIgnoreCase(shortcut)|| args[0].equalsIgnoreCase(name))
		{
			if(args.length > 1)
				throw new CommandExecuteException("None command has no arugmentes");
			else
				return new NoneCommand();
		}
		return null;
	}

	
	public boolean execute(Game game, Controller controller) {
		return true;
	}

}
