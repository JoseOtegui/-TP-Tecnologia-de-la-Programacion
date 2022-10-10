package Logic;

import Logic.Command.CommandExecuteException;
import Logic.Elements.GameObject;

public class GameObjectList {

	private GameObject objectList[];
	int num_objects;
	private Game game;

	public GameObjectList(int n) {
		objectList = new GameObject[n];
		num_objects = 0;

	}

	public void update() {
		for (int i = 0; i < num_objects; i++) {
			objectList[i].update();
		}
	}

	public void addObject(GameObject object) {
		if (num_objects < objectList.length) {
			objectList[num_objects++] = object;
		} else {
			GameObject[] aux = new GameObject[objectList.length * 2];
			for (int i = 0; i < num_objects; i++)
				aux[i] = objectList[i];

			objectList = aux;
			addObject(object);
		}
	}

	public boolean hitObject(int x, int y, int damage) {
		boolean found = false;
		boolean deleted = false;
		for (int i = 0; i < num_objects && !found; i++) {
			if (objectList[i].isInPosition(x, y)) {
				found = true;
				if (objectList[i].subLife(damage) <= 0) {
					deleteObject(i);
					deleted = true;
				}
			}
		}
		return deleted;
	}

	public void deleteObject(int pos) {
		num_objects--;
		for (int i = pos; i < num_objects; i++) {
			objectList[i] = objectList[i + 1];
		}
		objectList[num_objects + 1] = null;
	}

	public boolean freePosition(int x, int y) {
		boolean free = true;
		for (int i = 0; i < num_objects; i++) {
			if (objectList[i].getX() == x && objectList[i].getY() == y) {
				free = false;
			}
		}
		return free;
	}

	public GameObject searchObject(int x, int y) {
		int i = 0;
		while (i < num_objects) {
			if (objectList[i].getX() == x && objectList[i].getY() == y) {
				return objectList[i];
			} else
				i++;
		}
		return null;
	}

	public int getPos(GameObject object) {
		int i = 0;
		boolean found = false;
		while (i < num_objects && !found) {
			if (objectList[i] == object)
				found = true;
			else
				i++;
		}
		if (found)
			return i;
		else
			return -1;
	}

	public int getNumObjects() {
		return num_objects;
	}

	public GameObject get(int i) {
		return objectList[i];
	}

	public String stringList() {
		   String aux="";
	        
	        for(int i =0; i< num_objects; i++)
	        aux= aux + objectList[i].toStringDebug()+"|";
	        
	        return aux;
	}

	public void set(GameObject object, int i) {
		objectList[i] = object;
	}

	public String save() {
		String aux = ": ";
		for (int i = 0; i < num_objects; i++) {
			aux = aux + objectList[i].getShortcut() + ":" + objectList[i].getLife() + ":" + objectList[i].getX() + ":"
					+ objectList[i].getY() + ":" + objectList[i].getRemainingCycles();
			if (num_objects > 1 && i+1 != num_objects) {
				aux = aux + ",";
			}
		}

		return aux;

	}
}
