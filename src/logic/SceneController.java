package logic;

import gui.MainMenu;
import gui.StartMenu;
import javafx.animation.AnimationTimer;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class SceneController {

	private static Stage mainStage;
	private final static int FULLSCREEN_WIDTH = 1540;
	private final static int FULLSCREEN_HEIGHT = 880;
	private static long lastTimeTrigger;
	private static int count;
	private static AnimationTimer animationTimer;

	public SceneController() throws Exception {
		mainStage = new Stage();
		mainStage.setScene((new StartMenu()).getScene());
	}

	public static int getFullscreenWidth() {
		return FULLSCREEN_WIDTH;
	}

	public static int getFullscreenHeight() {
		return FULLSCREEN_HEIGHT;
	}

	public static Stage getStage() {
		return mainStage;
	}

	public static void startCredit() {
		lastTimeTrigger = -1;
		animationTimer = new AnimationTimer() {

			@Override
			public void handle(long now) {
				lastTimeTrigger = (lastTimeTrigger < 0 ? now : lastTimeTrigger);

				if (now - lastTimeTrigger >= 2000000000) {
					System.out.println(count);
					if (count % 2 == 0) {
						StartMenu.setCreditScene(count / 2);
						mainStage.setScene(StartMenu.getScene());
					}
					count++;
					lastTimeTrigger = now;
				}
				if (count == 5) {
					animationTimer.stop();
				}
			}

		};
		animationTimer.start();
	}
}
