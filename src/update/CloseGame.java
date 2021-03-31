package update;

import gui.MainMenu;
import gui.entity.Clickable;

public class CloseGame {
	private static boolean isCloseGame = false;
	
	public static void setIsCloseGame(boolean isClose) {
		isCloseGame = isClose;
	}
	public static void update() {
		if(isCloseGame) {
			System.exit(1);
		}
		
	}
	
	public static void backed() {
		for(Clickable component : MainMenu.getComponent()) {
			//System.out.println(component);
			component.triggerDisable();
		}
	}
}