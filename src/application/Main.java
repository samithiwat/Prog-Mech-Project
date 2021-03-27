package application;

import gui.LoadingScreen1;
import gui.LoadingScreen2;
import gui.MainMenu;
import gui.StartMenu;
import gui.TransitionScreen;
import javafx.application.Application;
import javafx.stage.Stage;
import logic.SceneController;

public class Main extends Application {

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
//		primaryStage.setScene((new LoadingScreen2()).getScene());
//		primaryStage.show();
		SceneController sControl = new SceneController();
		primaryStage = sControl.getStage();
		primaryStage.show();
		primaryStage.setResizable(false);
		//primaryStage.setMaximized(true);
		SceneController.startCredit();
		
	}

}
