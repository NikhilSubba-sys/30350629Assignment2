package operation;

/**
 * @author Nikhil
 * @version 1.0
 * @created 02-Apr-2020 8:30:00am
 */
public class CouncilSystem {

	public static void main(String[] args) {
		CouncilSystem cs = new CouncilSystem();
		cs.startApplication();
	}

	private void startApplication() {
		MainMenu mm = new MainMenu();
		mm.operateMenu();	
	}

}
