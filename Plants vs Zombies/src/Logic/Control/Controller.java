package Logic.Control;

import java.util.Scanner;

import Logic.Game;
import Logic.Level;
import Logic.MainRunException;
import Logic.PlantFactory;
import Logic.Command.Command;
import Logic.Command.CommandExecuteException;
import Logic.Command.CommandParseException;
import Logic.Command.CommandParser;
import Logic.View.BoardPrinter;
import Logic.View.DebugPrinter;
import Logic.View.ReleasePrinter;

public class Controller {

	private Game game;
	private Scanner scanner;
	private final String prompt = "Command > ";
	private final String unknownCommandMsg = "Comando Irreconocible.";
	String printMode = "Release";
	private BoardPrinter boardPrinter = new ReleasePrinter();
	private boolean has_seed;
	private long seed_aux;

	public Controller(String level, String rnd) throws MainRunException {
		scanner = new Scanner(System.in);
		Level lvl;
		long seed;
		try {
		lvl = Level.valueOf(level.toUpperCase());
		}catch (Exception e)
		{
			throw new MainRunException("Usage: plantsVsZombies <EASY|HARD|INSANE> [seed]: level must be one of: EASY, HARD, INSANE\r\n");
		}
		try {
			
		 seed = Long.parseLong(rnd);
		}catch (Exception e)
		{
			throw new MainRunException("Usage: plantsVsZombies <EASY|HARD|INSANE> [seed]: the seed must be a number\r\n");
		}
		seed_aux = seed;
		game = new Game(lvl, seed, 8, 4);
		has_seed = true;
	}

	public Controller(String level) {
		scanner = new Scanner(System.in);
		Level lvl = Level.valueOf(level.toUpperCase());
		game = new Game(lvl, 8, 4);
		has_seed = false;
	}

	public void run() {

		while (!game.isFinished()) {
			System.out.print(prompt);

			String[] words = scanner.nextLine().toLowerCase().trim().split("\\s+");
			try {

				Command command = CommandParser.parseCommand(words);

				if (command != null) {
					if (command.execute(game, this))
					{
						System.out.println(boardPrinter.printGame(game));
						game.update();
					}
				} else {
					System.err.println(unknownCommandMsg + "\n");
				}
			} catch (CommandParseException ex) {
				System.out.format(ex.getMessage() + " %n %n");
			} catch (CommandExecuteException e) {
				System.out.format(e.getMessage() + " %n %n");
			}
			
		}
		if (game.zombiesWins())
			System.out.println("Zombies wins");
		else
			System.out.println("Plants wins");
	}

	public void getAvailablePlants() {

		System.out.println(PlantFactory.listOfAvailablePLants());
	}

	public void changePrintMode() {
		if (printMode.equals("Debug")) {
			printMode = "Release";
			boardPrinter = new ReleasePrinter();
		} else {
			printMode = "Debug";
			boardPrinter = new DebugPrinter();
		}
	}
	
	public boolean getHasSeed()
	{
		return has_seed;
	}

	public long getSeed() {
		return seed_aux;
	}

	public void setGame(Game game2) {
		this.game = game2;
	}
}
