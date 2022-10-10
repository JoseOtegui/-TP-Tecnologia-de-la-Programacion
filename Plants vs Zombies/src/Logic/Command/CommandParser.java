	package Logic.Command;

public class CommandParser {

	static Command[] availableCommands = { new AddCommand(), new ResetCommand(), new HelpCommand(), new ListCommand(),
			new NoneCommand(), new ExitCommand(), new ZombieListCommand(), new LoadCommand(), new SaveCommand(), new PrintModeCommand()};

	public static Command parseCommand(String[] args) throws CommandParseException, CommandExecuteException {
		try {
for (int i = 0; i < availableCommands.length; i++) {
			Command returnCommand = availableCommands[i].parse(args);
			if (returnCommand != null)
				return returnCommand;
		}
		}
		catch (CommandParseException ex)
		{
			throw new CommandParseException("Unknown command. Use ’help’ to see the available commands");
		}
		return null;
	}

	public static String commandHelp() {
		String aux = "";

		for (int i = 0; i < availableCommands.length; i++) {
			aux = aux + availableCommands[i].helpInfo() + "\n";
		}
		return "The available commands are:\r\n" + aux;
	}
}
