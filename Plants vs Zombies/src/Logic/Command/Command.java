package Logic.Command;

import Logic.Game;
import Logic.Control.Controller;

public abstract class Command {

	private String helpInfo;
	protected String commandName;
	private String shortCut;
	protected String addHelp;

	public abstract Command parse(String[] args) throws CommandParseException, CommandExecuteException;

	public abstract boolean execute(Game game, Controller controller) throws CommandExecuteException;

	public Command(String name, String shortcut, String helpText) {
		this.commandName = name;
		this.helpInfo = helpText;
		this.shortCut = shortcut;
	}

	public Command(String name) {
		this.commandName = name;

	}

	public Command() {
	}

	public Command(String name, String plantName)
	{
		this.addHelp = plantName;
		this.commandName = name;
	}
	public String helpInfo() {
		return helpInfo;
	}

}
