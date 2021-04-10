package gui.entity;

import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.FontWeight;

public class StatusBar extends GridPane {
	
	ImageView img;
	TextTitle status;

	public StatusBar(ImageView img, String content, int contentSize, Color contentColor) {
		super();
		
// -------------------------------------------- Set up Bar's Components ------------------------------------------------
		
		status = new TextTitle(content, contentColor, FontWeight.BOLD, contentSize, 0, 0);
		this.img = img;
		
// -------------------------------------------- Add Components to Bar ---------------------------------------------------
		
		add(img, 0, 0);
		add(status, 1, 0);
		setId("status-bar");
	}
}
