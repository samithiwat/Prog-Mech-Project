package application;

import gui.MainIsland;
import gui.MapOverview;
import gui.StartMenu;
import javafx.application.Application;
import javafx.stage.Stage;
import logic.SceneController;

public class Main extends Application {

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
//		System.out.println(ClassLoader.getSystemResource("audio/MainMenuThemeSong1.wav").toString());
//		AudioClip effect = AudioLoader.menuThemeSong;
//		effect.play();
		primaryStage.setScene((new MainIsland()).getScene());
		primaryStage.show();
//		SceneController.setScene((new StartMenu()).getScene());
//		SceneController.getStage().show();
//		SceneController.getStage().setResizable(false);
//		//primaryStage.setMaximized(true);
//		SceneController.startCredit();
	}

}
