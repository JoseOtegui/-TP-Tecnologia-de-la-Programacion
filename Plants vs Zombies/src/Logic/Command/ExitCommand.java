package Logic.Command;

import Logic.Game;
import Logic.Control.Controller;

public class ExitCommand extends NoParamsCommand {

	private static final String name = "exit";
	private static final String helpInfo = "[E]xit: terminate the program.";
	private static final String shortcut = "e";
	
	public ExitCommand () {
		super(name,shortcut,helpInfo);
	}
	
	public Command parse(String[] args) throws CommandParseException, CommandExecuteException {
		if(args[0].equalsIgnoreCase(shortcut)|| args[0].equalsIgnoreCase(name))
		{
			if(args.length > 1)
				throw new CommandExecuteException("Exit command has no arguments");
			else
				return new ExitCommand();
		}
		return null;
	}

	

	public boolean execute(Game game, Controller controller) {
		System.out.println("****** Game over!: User exit ******");
        System.exit(0);
        return false;
	}
	

}
