package Logic.View;

import Logic.Game;
import Logic.GameObjectList;
import Logic.Elements.GameObject;
import Logic.Elements.Zombie;

public class Board {

	private GameObjectList plantList;
	private GameObjectList zombieList;
	private Game game;
	private GameObject[][] objectBoard;

	public Board(int x, int y, Game game) {
		plantList = new GameObjectList(x * y);
		zombieList = new GameObjectList(x * y);
		objectBoard = new GameObject[x][y];
		this.game = game;
	}

	public void addSuncoins(int suns_gen) {
		game.addSuncoins(suns_gen);
	}

	public void PeaAttack(int x, int y, int damage) {
		int i = x;
		boolean aux = false;
		while (!aux && i < game.getX()) {
			if (objectBoard[i][y] != null && objectBoard[i][y].getIsZombie()) {
				if (zombieList.hitObject(i, y, damage)) {
					objectBoard[i][y] = null;
				}
				aux = true;
			}

			else
				i++;
		}
	}

	public void cherryAttack(int x, int y, int damage) {
		for (int i = x - 1; i < x + 2; i++) {
			for (int j = y - 1; j < y + 2; j++) {
				if (insideBounds(i, j)) {
					if (!(objectBoard[i][j] == null) && objectBoard[i][j].getIsZombie()) {
						if (zombieList.hitObject(i, j, damage))
							objectBoard[i][j] = null;
					}
				}
			}
		}

	}

	public void deleteCherry(int x, int y) {
		int pos = plantList.getPos(plantList.searchObject(x, y));
		if (pos != -1)
			plantList.deleteObject(pos);
	}

	public boolean zombieAttack(int x, int y, int damage) {
		if (objectBoard[x][y] != null && !objectBoard[x][y].getIsZombie()) {
			if (plantList.hitObject(x, y, damage))
				objectBoard[x][y] = null;
			return true;
		}
		return false;
	}

	public GameObjectList getPlantList() {
		return plantList;
	}

	public GameObjectList getZombieList() {
		return zombieList;
	}

	public void addObject(GameObject obj) {
		obj.setBoard(this);
		if (obj.getIsZombie()) {
			zombieList.addObject(obj);
		} else {
			plantList.addObject(obj);
		}

		objectBoard[obj.getX()][obj.getY()] = obj;
	}

	public void update() {
		zombieList.update();
		plantList.update();
	}

	public boolean freePosition(int x, int y) {
		return objectBoard[x][y] == null;
	}

	public boolean zombieAtEnd(int i) {
		return (objectBoard[0][i] != null && objectBoard[0][i].getIsZombie());
	}

	public void deleteZombie(int x, int y) {
		objectBoard[x][y] = null;
	}

	public void addZombie(Zombie zombie) {
		objectBoard[zombie.getX()][zombie.getY()] = zombie;
	}


	public boolean insideBounds(int x, int y) {
		return (x >= 0 && x <= 7 && y >= 0 && y <= 3);
	}
	public void loadLists(GameObjectList plants, GameObjectList zombies)
	{
		plantList = plants;
		zombieList = zombies;
	
	}
	public int getNumZombies()
	{
		return zombieList.getNumObjects();
	}

	public void loadObjectBoard(GameObjectList plantList2, GameObjectList zombieList2) {
		int numPlants = plantList.getNumObjects(), numZombies = zombieList.getNumObjects();
		for(int i = 0; i < numPlants; i++)
			addObject(plantList.get(i));
		for(int i = 0; i < numZombies; i++)
			addObject(zombieList.get(i));
	}

}
