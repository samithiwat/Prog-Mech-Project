package gui;

import javafx.scene.ImageCursor;
import javafx.scene.Scene;
import javafx.scene.image.Image;

public interface Showable {
	
	String FONT_NAME = "Bai Jamjuree";
	
	ImageCursor CURSOR_NORMAL = new ImageCursor((new Image(ClassLoader.getSystemResource("img/mouseCursor.png").toString())));
	ImageCursor CURSOR_SELECTED = new ImageCursor((new Image(ClassLoader.getSystemResource("img/mouseCursorSelected.png").toString())));
	
	public Scene getScene();
}
