package application;

import javafx.application.Application;
import javafx.stage.Stage;
import logic.SceneController;

public class Main extends Application{

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		SceneController sControl = new SceneController();
		primaryStage = sControl.getMainMenu();
		primaryStage.show();
		primaryStage.setResizable(false);
	}

}
