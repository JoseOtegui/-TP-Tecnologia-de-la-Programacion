package Logic.View;

import Logic.Game;

public abstract class BoardPrinter implements GamePrinter{

	protected String board[][];
	protected String hLine;
    protected final String jump = "\n";
	
	public abstract String encodeGame(Game game);
	

}
