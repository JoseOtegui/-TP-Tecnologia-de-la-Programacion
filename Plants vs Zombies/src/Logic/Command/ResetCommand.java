package Logic.Command;

import Logic.Game;
import Logic.Control.Controller;

public class ResetCommand extends NoParamsCommand {

	private static final String name = "reset";
	private static final String helpInfo = "[R]eset: resets game.";
	private static final String shortcut = "r";
	
	public ResetCommand()
	{
		super(name,shortcut,helpInfo);
	}
	
	public Command parse(String[] args) throws CommandParseException, CommandExecuteException {
		if(args[0].equalsIgnoreCase(shortcut)|| args[0].equalsIgnoreCase(name))
		{
			if(args.length > 1)
				throw new CommandExecuteException("Reset command has no arguments");
			else
				return new ResetCommand();
		}
		return null;
		
	}

	
	public boolean execute(Game game, Controller controller) {
		game.reset();
		return true;
	}

}
