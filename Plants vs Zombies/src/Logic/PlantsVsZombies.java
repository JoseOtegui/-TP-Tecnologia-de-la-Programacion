package Logic;

import Logic.Control.Controller;

public class PlantsVsZombies {

	public static void main(String[] args) {

		if(args.length==1) {
			new Controller(args[0]).run();
		}
		else if(args.length==2) {
			try{
			Controller controller= new Controller(args[0], args[1]);
			controller.run();
			}catch (MainRunException f)
			{
				System.out.format(f.getMessage());

			}
		} 
		else
			try {
				throw new MainRunException("Usage: plantsVsZombies <EASY|HARD|INSANE> [seed]\r\n" );
			} catch (MainRunException e) {
				System.out.format(e.getMessage() + " %n %n");

			}
			
	}        
}
