package Logic.View;	

import Logic.Game;

public class ReleasePrinter extends BoardPrinter {

	
	public String encodeGame(Game game) {
		String[][] board1 = game.board();
		hLine = "";

		 for (int i = 0; i < board1[0].length * 8 + 1; i++) {
	            hLine = hLine + "-";
	        }
	        String aux = hLine + jump;

	        for (int i = 0; i < board1.length; i++) {

	            for (int j = 0; j < board1[0].length; j++) {
	                if (board1[i][j] != null) {
	                    aux = aux + "|" + board1[i][j];
	                } else {
	                    aux = aux + "|       ";
	                }
	            }
	            aux = aux + "|" + jump + hLine + jump;
	        }
		return aux;
	}
	
    public String printGame(Game game) {
    	String aux = game.getHead();
    	
		aux = aux + "\n" + encodeGame(game);

		return aux; 
	}


}
