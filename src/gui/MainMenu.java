package gui;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import javafx.scene.media.AudioClip;
import logic.AudioLoader;
import logic.SceneController;

public class MainMenu implements Showable{
	
	private Scene scene;
	
	public MainMenu() {
		
		AnchorPane root = new AnchorPane();
		
		ImageView bg = new ImageView(ClassLoader.getSystemResource("img/MainMenuBG.png").toString());
		
		
		root.getChildren().add(bg);
		scene = new Scene(root,SceneController.getFullscreenWidth(),SceneController.getFullscreenHeight());
	}
	
	public Scene getScene() {
		return this.scene;
	}
	
}
