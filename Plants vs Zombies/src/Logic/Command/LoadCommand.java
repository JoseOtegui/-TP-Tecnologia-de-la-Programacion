package Logic.Command;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

import Logic.Game;
import Logic.GameObjectList;
import Logic.Level;
import Logic.PlantFactory;
import Logic.ZombieFactory;
import Logic.Control.Controller;
import Logic.Elements.GameObject;
import Logic.Elements.Plant;
import Logic.Elements.Zombie;

public class LoadCommand extends Command {

	private String route;
	private final static String helpInfo = "[Load] a game in your route";

	public LoadCommand() {
		super("load", "load", helpInfo);
	}

	private LoadCommand(String n) {
		route = n;
	}

	public Command parse(String[] args) throws CommandParseException {
		if (args.length == 2) {
			if (args[0].equalsIgnoreCase("load"))
				return new LoadCommand(args[1]);
		}
		return null;
	}

	public boolean execute(Game game, Controller controller) throws CommandExecuteException {
		int cycles, remZombies, sunCoins;
		String c;
		String level;
		GameObjectList plantList = new GameObjectList(32);
		GameObjectList zombieList = new GameObjectList(32);
		try {
			FileReader file = new FileReader(new File(route));
			BufferedReader in = new BufferedReader(file);

			String read = in.readLine();
			read = in.readLine();
			String[] tokens = read.split(": ");
			if (!tokens[0].equalsIgnoreCase("cycle")) {
				in.close();
				throw new CommandExecuteException("Load failed: invalid file contents");
			}

			else
				c = tokens[1];
			cycles = Integer.parseInt(c);

			tokens = in.readLine().split(": ");
			if (!tokens[0].equalsIgnoreCase("sunCoins")) {
				in.close();
				throw new CommandExecuteException("Load failed: invalid file contents");
			} else
				sunCoins = Integer.parseInt(tokens[1]);

			tokens = in.readLine().split(": ");
			if (!tokens[0].equalsIgnoreCase("level")) {
				in.close();
				throw new CommandExecuteException("Load failed: invalid file contents");
			} else
				level = tokens[1];

			tokens = in.readLine().split(": ");
			if (!tokens[0].equalsIgnoreCase("remZombies")) {
				in.close();
				throw new CommandExecuteException("Load failed: invalid file contents");
			} else
				remZombies = Integer.parseInt(tokens[1]);
			try {
				tokens = in.readLine().split(": ");
				if (!tokens[0].equalsIgnoreCase("plantList") && !tokens[0].equalsIgnoreCase("zombieList")) {
					in.close();
					throw new CommandExecuteException("Load failed: invalid file contents");
				} else if (tokens[0].equalsIgnoreCase("zombieList")) {
					zombieList = createObjects(tokens[1], true, game);
				} else
					plantList = createObjects(tokens[1], false, game);

				tokens = in.readLine().split(": ");
				if (!tokens[0].equalsIgnoreCase("zombieList")) {
					in.close();
					throw new CommandExecuteException("Load failed: invalid file contents");
				} else
					zombieList = createObjects(tokens[1], true, game);

			} catch (NullPointerException e) {
			} finally {
				if (controller.getHasSeed()) {
					game = new Game(Level.valueOf(level.toUpperCase()), controller.getSeed(), 8, 4);
					game.load(cycles, sunCoins, level, remZombies, plantList, zombieList);
					controller.setGame(game);
				} else {
					game = new Game(Level.valueOf(level.toUpperCase()), 8, 4);
					game.load(cycles, sunCoins, level, remZombies, plantList, zombieList);
					controller.setGame(game);
				}
				in.close();
			}
		} catch (NumberFormatException e) {
			throw new CommandExecuteException("File error");
		} catch (FileNotFoundException e) {
			throw new CommandExecuteException("File not found");

		} catch (IOException ex) {
			throw new CommandExecuteException("File error");
		}

		System.out.println("Game successfully loaded from file => " + route + ".dat.");
		return true;
	}

	private GameObjectList createObjects(String tokens, boolean isZombie, Game game)
			throws NumberFormatException, CommandExecuteException {
		String[] objects = tokens.split(",");
		GameObjectList list = new GameObjectList(32);
		for (int i = 0; i < objects.length; i++) {
			if (!isZombie) {
				list.addObject(game.parsePlant(objects[i]));
			} else {
				list.addObject(game.parseZombie(objects[i]));
			}
		}
		return list;
	}

}
