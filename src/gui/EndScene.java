package gui;

import character.MainCharacter;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

public class EndScene implements Sceneable{

	private Scene scene;
	
	public EndScene(MainCharacter winner) {
	
		HBox root = new HBox();
		
		ImageView winnerPortraits = winner.getWinnerImg();
		
		
		
		
	}
	
	@Override
	public Scene getScene() {
		return this.scene;
	}

}
