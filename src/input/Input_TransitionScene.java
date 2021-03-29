  
package input;

import javafx.scene.Scene;
import javafx.scene.input.KeyCode;

public class Input_TransitionScene {
	public static void input(Scene scene) {
		scene.setOnKeyPressed(key -> {
			if(key.getCode() == KeyCode.ESCAPE) {
				System.exit(1);
			}
		});
	}
}