package gui.overlay;

import javafx.scene.ImageCursor;
import javafx.scene.image.Image;

public interface Overlayable {
	
	String FONT_NAME = "Bai Jamjuree";
	
	ImageCursor CURSOR_NORMAL = new ImageCursor((new Image(ClassLoader.getSystemResource("img/mouseCursor.png").toString())));
	ImageCursor CURSOR_SELECTED = new ImageCursor((new Image(ClassLoader.getSystemResource("img/mouseCursorSelected.png").toString())));
	
	public void triggerOverlay(int dx,int dy,int delay);
	
}
 