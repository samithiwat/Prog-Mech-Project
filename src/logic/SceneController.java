package logic;

import java.util.Random;

import gui.LoadingScreen1;
import gui.LoadingScreen2;
import gui.MainIsland;
import gui.MainMenu;
import gui.MapOverview;
import gui.PrisonIsland;
import gui.StartMenu;
import gui.entity.PlayerPanel;
import javafx.animation.AnimationTimer;
import javafx.scene.Scene;
import javafx.scene.media.AudioClip;
import javafx.stage.Stage;
import update.CloseGame;
import input.Input_StartMenu;

public class SceneController {

	private static Stage mainStage = new Stage();
	private final static int FULLSCREEN_WIDTH = 1540;
//	private final static int FULLSCREEN_WIDTH = 1400;
	private final static int FULLSCREEN_HEIGHT = 880;
//	private final static int FULLSCREEN_HEIGHT = 800;
	private static long lastTimeTrigger;
	private static int count;
	private static AnimationTimer animationTimer;
	
	private static Stage loadingStage;
	
	private static Scene gameSettingMenu;
	private static Scene mapOverView;
	private static Scene mainIsland;
	private static Scene prisonIsland;
	private static Scene Ocean;
	
	public SceneController() throws Exception {
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

	public static Scene getScene() {
		return mainStage.getScene();
	}
	
	public static void startCredit() {
		lastTimeTrigger = -1;
		animationTimer = new AnimationTimer() {

			@Override
			public void handle(long now) {
				lastTimeTrigger = (lastTimeTrigger < 0 ? now : lastTimeTrigger);
				if (now - lastTimeTrigger >= 2000000000 || Input_StartMenu.isSkip ) {
					//System.out.println(count);
					if(Input_StartMenu.isSkip) {
						System.out.println("Skipped" + count);
					}
					if (count % 2 == 0) {
						StartMenu.setCreditScene(count / 2);
						mainStage.setScene(StartMenu.getScene());
					}
					count++;
					//Input_StartMenu.isSkip = false;
					lastTimeTrigger = now;
				}
				if (count == 7) {
					animationTimer.stop();
				}
				CloseGame.update();
			}

		};
		animationTimer.start();
	}
	
	public static void createGameScene() {
		
		SceneController.mapOverView= (new MapOverview()).getScene();
		PlayerPanel dummy = new PlayerPanel();
		
//		SceneController.Ocean = (new Ocean()).getScene();
		SceneController.prisonIsland = (new PrisonIsland()).getScene();
		SceneController.mainIsland= (new MainIsland()).getScene();
	}
	
	public static void loadingScreen() {
		Random rand = new Random();
		int sceneNum = rand.nextInt(2);

//		int sceneNum = 1;
		
		switch(sceneNum) {
		case 0 :
			setScene((new LoadingScreen1()).getScene());
			break;
		case 1 :
			setScene((new LoadingScreen2()).getScene());
		}
	}
	
// -------------------------------------------- Getter and Setter --------------------------------------------------------------------
	
	public static Scene getGameSettingMenu() {
		return gameSettingMenu;
	}

	public static void setGameSettingMenu(Scene gameSettingMenu) {
		SceneController.gameSettingMenu = gameSettingMenu;
	}

	public static Scene getMapOverView() {
		return mapOverView;
	}

	public static Scene getMainIsland() {
		return mainIsland;
	}

	public static Scene getPrisonIsland() {
		return prisonIsland;
	}

	public static Scene getOcean() {
		return Ocean;
	}

	public static Stage getLoadingStage() {
		return loadingStage;
	}

	public static void setLoadingStage(Stage loadingStage) {
		SceneController.loadingStage = loadingStage;
	}
	
}