package application;

import gui.MainMenu;
import gui.StartMenu;
import javafx.application.Application;
import javafx.scene.media.AudioClip;
import javafx.stage.Stage;
import logic.AudioLoader;
import logic.SceneController;

public class Main extends Application {

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		primaryStage.setScene((new MainMenu()).getScene());
		primaryStage.show();
//		SceneController.setScene((new StartMenu()).getScene());
//		SceneController.getStage().show();
//		SceneController.getStage().setResizable(false);
//		//primaryStage.setMaximized(true);
//		SceneController.startCredit();
		
	}

}
