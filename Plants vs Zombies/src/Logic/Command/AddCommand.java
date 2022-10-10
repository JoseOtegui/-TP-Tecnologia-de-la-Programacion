package Logic.Command;

import Logic.Game;
import Logic.PlantFactory;
import Logic.Control.Controller;
import Logic.Elements.Plant;
import Logic.View.Board;

public class AddCommand extends Command {

	private Board board;
	protected int x;
	protected int y;
	private static final String name_ = "Add";
	private static final String shortcut = "a";
	private static final String help = "[A]dd <plant> <x> <y>: Adds a plant in position x, y.";
	public String plantName;

	public AddCommand(int x, int y) {
		super(name_);
		this.x = x;
		this.y = y;

	}

	public AddCommand() {
		super(name_, shortcut, help);
	}

	public AddCommand(int x, int y, String plantName) {
		super(name_, plantName);
		this.x = x;
		this.y = y;
	}

	public Command parse(String[] args) throws CommandParseException, CommandExecuteException {
		if (args[0].equals(shortcut) || args[0].equals(name_)) {
			if (args.length > 4 || args.length < 4)
				throw new CommandExecuteException(
						"Incorrect number of arguments for add command: [A]dd <plant> <x> <y>\r");
			else {
				try {
					x = Integer.parseInt(args[2]);
					y = Integer.parseInt(args[3]);
				} catch (NumberFormatException e) {
					throw new CommandExecuteException(
							"Invalid argument for add command, number expected: [A]dd <plant> <x> <y>\r");
				}
				if (x < 0 || x > 7 || y < 0 || y > 3)
					throw new CommandExecuteException(
							"Failed to add Sunflower: (" + x + ", " + y + ") is an invalid position\r");
				if (args[0].equalsIgnoreCase(commandName) || args[0].equalsIgnoreCase(shortcut)) {
					plantName = args[1];
					return new AddCommand(x, y, plantName);
				}
			}
		}
		return null;
	}

	public boolean execute(Game game, Controller controller) throws CommandExecuteException {
		Plant plant = PlantFactory.getPlant(addHelp, x, y);
		plant.setBoard(game.getBoard());
		if (game.getBoard().freePosition(x, y)) {
			if (game.pay(plant.getCost())) {
				game.add(plant);
			} else
				throw new CommandExecuteException(
						"Failed to add " + plant.getName() + " : not enough suncoins available");
		} else
			throw new CommandExecuteException(
					"Failed to add Sunflower: (" + x + ", " + y + ") is an invalid position\r");
		return true;
	}
}