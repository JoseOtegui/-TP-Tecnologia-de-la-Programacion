package Logic.View;

import Logic.Game;

public class DebugPrinter extends BoardPrinter {

	
	public String encodeGame(Game game) {
		return "";
	}

	public String printGame(Game game) {
		String aux = game.getHead();

		aux = aux + "\n" + game.getStringList();

		return aux;
	}

}
