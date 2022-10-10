package Logic.Command;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import Logic.Game;
import Logic.Control.Controller;

public class SaveCommand extends Command {

	private String route;
	private final static String helpInfo = "[Save] a game in your route";

	public SaveCommand() {
		super("save", "save", helpInfo);
	}

	private SaveCommand(String n) {
		route = n;
	}

	public Command parse(String[] args) throws CommandParseException {
		if (args.length == 2) {
			if (args[0].equalsIgnoreCase("save"))
				return new SaveCommand(args[1]);
		} 
		return null;
	}

	public boolean execute(Game game, Controller controller) throws CommandExecuteException {

		try {
			FileWriter file = new FileWriter(new File(route));
			BufferedWriter out = new BufferedWriter(file);

			out.write("Plants Vs Zombies v3.0" + "\r\n");
			
			out.write(game.save());
			 
			System.out.println("Game successfully saved in file " + route + ".dat.");
			out.close();
		} catch (IOException e) {
			throw new CommandExecuteException("Invalid filename: the filename contains invalid characters");
		} 
		return true;
	}
}
