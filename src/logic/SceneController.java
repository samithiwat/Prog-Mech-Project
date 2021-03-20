package logic;

import gui.MainMenu;
import javafx.stage.Stage;

public class SceneController {
	
	private final static int FULLSCREEN_WIDTH = 1920;
	private final static int FULLSCREEN_HEIGHT = 1080;
	
	public static int getFullscreenWidth() {
		return FULLSCREEN_WIDTH;
	}
	public static int getFullscreenHeight() {
		return FULLSCREEN_HEIGHT;
	}
	
	public Stage getMainMenu() {
		return (new MainMenu()).getStage();
	}
}
