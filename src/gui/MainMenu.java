package gui;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.media.AudioClip;
import logic.AudioLoader;
import logic.SceneController;

public class MainMenu implements Showable{
	
	private Scene scene;
	
	public MainMenu() {
		AudioClip menuThemeSong = AudioLoader.menuThemeSong;
		menuThemeSong.setCycleCount(AudioClip.INDEFINITE);
		menuThemeSong.play();
		
		StackPane root = new StackPane();
		root.setAlignment(Pos.CENTER);
		
		scene = new Scene(root,SceneController.getFullscreenWidth(),SceneController.getFullscreenHeight());
	}
	
	public Scene getScene() {
		return this.scene;
	}
	
}
