package update;

import gui.GameLobbyMenu;
import gui.StartMenu;
import gui.entity.CharacterCard;
import javafx.application.Platform;
import javafx.scene.ImageCursor;
import javafx.scene.image.Image;

public class CharacterSelectUpdate implements Updateable{
	
	private static Thread t;
	
	public static void mouseEnteredUpdate(CharacterCard cc) {
		cc.setCursor(MOUSE_SELECT);
		cc.setId("character-card-selected");
		GameLobbyMenu.getBGM().stop();
		cc.getSoundEffect().play();
		t = new Thread(()->{
			try {
				Thread.sleep(5000);
			}
			catch(InterruptedException e) {
				cc.getSoundEffect().stop();
				GameLobbyMenu.getBGM().play();
			}
			Platform.runLater(new Runnable() {
				
				@Override
				public void run() {
					cc.getSoundEffect().stop();
					GameLobbyMenu.getBGM().play();
				}
			});
		});
		t.start();
	}
	
	public static void mouseExitedUpdate(CharacterCard cc) {
		cc.setId("character-card");
		cc.setCursor(MOUSE_NORMAL);
		t.interrupt();
	}
	
}
