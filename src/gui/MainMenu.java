package gui;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.media.AudioClip;
import javafx.stage.Stage;
import logic.AudioLoader;
import logic.SceneController;

public class MainMenu implements Showable{
	
	private Scene scene;
	
	public MainMenu() {		
		StackPane root = new StackPane();
		root.setAlignment(Pos.CENTER);
		scene= new Scene(root,SceneController.getFullscreenWidth(),SceneController.getFullscreenHeight());
	}
	

	@Override
	public Scene getScene() {
		return scene;
	}
	
}
