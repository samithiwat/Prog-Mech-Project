package update;

import gui.GameSettingMenu;
import gui.StartMenu;
import gui.entity.CharacterCard;
import javafx.application.Platform;
import javafx.scene.ImageCursor;
import javafx.scene.image.Image;

public class CharacterSelectUpdate {
	
	private static Thread t;
	
	public static void mouseEnteredUpdate(CharacterCard cc) {
		cc.setCursor(new ImageCursor((new Image(ClassLoader.getSystemResource("img/MouseCursorSelected.png").toString()))));
		cc.setId("character-card-selected");
		GameSettingMenu.getBGM().stop();
		cc.getSoundEffect().play();
		t = new Thread(()->{
			try {
				Thread.sleep(5000);
			}
			catch(InterruptedException e) {
				cc.getSoundEffect().stop();
				GameSettingMenu.getBGM().play();
			}
			Platform.runLater(new Runnable() {
				
				@Override
				public void run() {
					cc.getSoundEffect().stop();
					GameSettingMenu.getBGM().play();
				}
			});
		});
		t.start();
	}
	
	public static void mouseExitedUpdate(CharacterCard cc) {
		cc.setId("character-card");
		cc.setCursor(new ImageCursor((new Image(ClassLoader.getSystemResource("img/mouseCursor.png").toString()))));
		t.interrupt();
	}
	
	public static void selectCharacterUpdate(CharacterCard cc) {
		switch(cc.getName()) {
		case "MrRedFox" :
			//GameController.
		}
	}
}
