package gui;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.media.AudioClip;
import javafx.stage.Stage;
import logic.AudioLoader;
import logic.SceneController;

public class MainMenu {
	
	private Stage mainStage;
	
	public MainMenu() {
		AudioClip menuThemeSong = AudioLoader.menuThemeSong;
		menuThemeSong.setCycleCount(AudioClip.INDEFINITE);
		menuThemeSong.play();
		
		StackPane root = new StackPane();
		root.setAlignment(Pos.CENTER);
		
		mainStage = new Stage();
		mainStage.setScene(new Scene(root,SceneController.getFullscreenWidth(),SceneController.getFullscreenHeight()));
		mainStage.setTitle("Main Menu");
	}
	
	public Stage getStage() {
		return this.mainStage;
	}
	
}
