package Logic;

import java.util.Random;

import Logic.Command.CommandExecuteException;
import Logic.Elements.GameObject;
import Logic.Elements.Plant;
import Logic.Elements.Zombie;
import Logic.View.Board;

public class Game {

	private Board board;
	private int x;
	private int y;
	private SunCoinManager coins;
	private long seed = 0;
	private ZombieManager zombies;
	private int cycle;
	private Random rnd;
	private Level level;

	public Game(Level level, long seed, int x, int y) {
		coins = new SunCoinManager(50);
		this.x = x;
		this.y = y;
		this.level = level;
		board = new Board(x, y, this);
		zombies = new ZombieManager(level);
		cycle = 0;
		rnd = new Random(seed);
		this.seed = seed;
		
	}

	public Game(Level level, int x, int y) {
		coins = new SunCoinManager(50);
		this.x = x;
		this.y = y;
		this.level = level;
		board = new Board(x, y, this);
		zombies = new ZombieManager(level);
		rnd = new Random();
		cycle = 0;

	}

	public Board getBoard() {
		return board;
	}

	public boolean pay(int cost) {
		return coins.pay(cost);
	}

	public void add(GameObject obj) {
		board.addObject(obj);
	}

	public void update() {
		cycle++;

		board.update();
		int y = 0;
		if (zombies.isZombieAdded(rnd)) {

			do {
				y = rnd.nextInt(this.y - 1);

			} while (!board.freePosition(this.x - 1, y));

			board.addObject(ZombieFactory.getZombie(rnd, this.x - 1, y));
		}

	}

	public void reset() {
		if(seed == 0)
		{
			coins = new SunCoinManager(50);
			board = new Board(x, y, this);
			zombies = new ZombieManager(level);
			rnd = new Random();
			cycle = 0;
		}
		else
		{
			coins = new SunCoinManager(50);
			board = new Board(x, y, this);
			zombies = new ZombieManager(level);
			rnd = new Random();
			cycle = 0;
			rnd = new Random(seed);
		}
			
	}

	public String getHead() {
		return "Number of cycles: " + cycle + "\nSun coins: " + coins.getSuncoins() + "\nRemaining zombies: "
				+ zombies.zombiesLeft() + "\nLevel: " + this.level + "\nSeed: " + this.seed + "\n\n";
	}

	public void addSuncoins(int suns_gen) {
		coins.deposit(suns_gen);
	}

	public int getX() {
		return this.x;
	}

	public String[][] board() {
		GameObjectList plantList = board.getPlantList();
		GameObjectList zombieList = board.getZombieList();
		int numPlants = plantList.getNumObjects();
		int numZombies = zombieList.getNumObjects();
		String[][] board = new String[y][x];
		for (int i = 0; i < x; i++) {
			for (int j = 0; j < y; j++) {
				board[j][i] = "       ";
			}

		}
		for (int i = 0; i < numPlants; i++) {
			GameObject p = plantList.get(i);
			board[p.getY()][p.getX()] = p.toString();
		}
		for (int i = 0; i < numZombies; i++) {
			GameObject z = zombieList.get(i);
			board[z.getY()][z.getX()] = z.toString();
		}

		return board;
	}

	public boolean zombiesWins() {
		int i = 0;
		boolean found = false;
		while (i < this.y && !found) {
			if (board.zombieAtEnd(i))
				return true;
			else
				i++;
		}
		return false;
	}

	public boolean isFinished() {
		if (zombiesWins() || (zombies.zombiesLeft() == 0 && board.getNumZombies() == 0))
			return true;
		return false;
	}

	public String getStringList() {
		String aux = "|";
		aux = aux + board.getPlantList().stringList() + board.getZombieList().stringList();

		return aux;
	}

	public void load(int cycles, int sunCoins, String level, int remZombies, GameObjectList plantList,
			GameObjectList zombieList) {
		this.cycle = cycles;
		coins.setSunCoins(sunCoins);
		this.level = Level.valueOf(level.toUpperCase());
		board.loadLists(plantList, zombieList);
		board.loadObjectBoard(plantList, zombieList);
	}

	public String save() {
		StringBuilder sb = new StringBuilder();
		sb.append("Cycle: ");
		sb.append(cycle);
		sb.append("\r\n");
		sb.append("sunCoins: ");
		sb.append(coins.getSuncoins());
		sb.append("\r\n");
		sb.append("level: ");
		sb.append(level);
		sb.append("\r\n");
		sb.append("remZombies: ");
		sb.append(zombies.zombiesLeft());
		sb.append("\r\n");
		if (board.getPlantList().getNumObjects() > 0) {
			sb.append("plantList");
			sb.append(board.getPlantList().save());
			sb.append("\r\n");
		}
		if (board.getZombieList().getNumObjects() > 0) {
			sb.append("zombieList");
			sb.append(board.getZombieList().save());
			sb.append("\r\n");
		}

		return sb.toString();
	}
	
	public GameObject parsePlant(String aux) throws NumberFormatException, CommandExecuteException {
		String[] tokens = aux.split(":");
		Plant plant = PlantFactory.getPlant(tokens[0], Integer.parseInt(tokens[2]), Integer.parseInt(tokens[3]));
		plant.setLife(Integer.parseInt(tokens[1]));
		plant.changeCycle(Integer.parseInt(tokens[4]));
		return plant;
	}

	public GameObject parseZombie(String aux) throws NumberFormatException, CommandExecuteException {
		String[] tokens = aux.split(":");
		Zombie zombie = ZombieFactory.loadZombies(tokens[0], Integer.parseInt(tokens[2]), Integer.parseInt(tokens[3]));
		zombie.setLife(Integer.parseInt(tokens[1]));
		zombie.changeCycle(Integer.parseInt(tokens[4]));
		return zombie;
	}

}
