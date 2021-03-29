package logic;

import gui.MainMenu;
import gui.StartMenu;
import javafx.animation.AnimationTimer;
import javafx.scene.Scene;
import javafx.stage.Stage;
import update.CloseGame;
import input.Input_StartMenu;

public class SceneController {

	private static Stage mainStage;
//	private final static int FULLSCREEN_WIDTH = 1540;
	private final static int FULLSCREEN_WIDTH = 1400;
//	private final static int FULLSCREEN_HEIGHT = 880;
	private final static int FULLSCREEN_HEIGHT = 800;
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
	
	public static void setScene(Scene scene) {
		mainStage.setScene(scene);
	}

	public static void startCredit() {
		lastTimeTrigger = -1;
		animationTimer = new AnimationTimer() {

			@Override
			public void handle(long now) {
				lastTimeTrigger = (lastTimeTrigger < 0 ? now : lastTimeTrigger);
				if (now - lastTimeTrigger >= 2000000000 || Input_StartMenu.isSkip ) {
					System.out.println(count);
					if(Input_StartMenu.isSkip) {
						System.out.println("Skipped" + count);
					}
					if (count % 2 == 0) {
						StartMenu.setCreditScene(count / 2);
						mainStage.setScene(StartMenu.getScene());
					}
					count++;
					Input_StartMenu.isSkip = false;
					lastTimeTrigger = now;
				}
				if (count == 5) {
					animationTimer.stop();
				}
				CloseGame.update();
			}

		};
		animationTimer.start();
	}
}
