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
		status = new TextTitle(content, contentColor, FontWeight.BOLD, contentSize, 0, 0);
		this.img = img;
		add(img, 0, 0);
		add(status, 1, 0);
		setId("status-bar");
	}
}
