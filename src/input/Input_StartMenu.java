package input;

import update.CloseGame;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;

public class Input_StartMenu{
	public static boolean isSkip = true;
	
	public static void input(Scene scene) {
		scene.setOnKeyPressed(key -> {
			if(key.getCode() == KeyCode.SPACE) {
				isSkip = true;
			}
			else if(key.getCode() == KeyCode.ESCAPE) {
				CloseGame.setIsCloseGame(true);
			}
		});
	}
	
}