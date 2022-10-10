package Logic.Command;

import Logic.Game;
import Logic.Control.Controller;

public class PrintModeCommand extends Command {

	private static final String name = "printmode";
	private static final String helpInfo = "[P]rintMode: change print mode [Release|Debug].";
	private static final String shortcut = "p";
	
	public PrintModeCommand()
	{
		super(name,shortcut,helpInfo);
	}
	
	public Command parse(String[] args) throws CommandParseException, CommandExecuteException {
		if (args[0].equalsIgnoreCase(shortcut) || args[0].equalsIgnoreCase(name)) {
			if (args.length > 1)
				throw new CommandExecuteException("Printmode command has no arguments");
			else
				return new PrintModeCommand();
		}
		return null;
	}
	
	public boolean execute(Game game, Controller controller) {
		controller.changePrintMode();
		return true;
	}
}