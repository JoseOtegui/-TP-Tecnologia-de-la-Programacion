package Logic.Command;

import Logic.Game;
import Logic.Control.Controller;

public class HelpCommand extends NoParamsCommand {

	private static final String name = "help";
	private static final String helpInfo = "[H]elp: print this help message.";
	private static final String shortcut = "h";
	
	public HelpCommand() {
		super(name,shortcut, helpInfo);
	}

	public Command parse(String[] args) throws CommandParseException, CommandExecuteException {
		if (args[0].equalsIgnoreCase(shortcut) || args[0].equalsIgnoreCase(name)) {
			if (args.length > 1)
				throw new CommandExecuteException("Help command has no arguments");
			else
				return new HelpCommand();
		}
		return null;
	}

	public boolean execute(Game game, Controller controller) {
		System.out.println(CommandParser.commandHelp());
		return false;
	}
}
