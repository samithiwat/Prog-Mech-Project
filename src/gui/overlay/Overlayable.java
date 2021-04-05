package gui.overlay;

import javafx.scene.ImageCursor;
import javafx.scene.image.Image;
import javafx.scene.media.AudioClip;
import logic.AudioLoader;

public interface Overlayable {
	
	String FONT_NAME = "Bai Jamjuree";
	
	ImageCursor CURSOR_NORMAL = new ImageCursor((new Image(ClassLoader.getSystemResource("img/icon/mouseCursor.png").toString())));
	ImageCursor CURSOR_SELECTED = new ImageCursor((new Image(ClassLoader.getSystemResource("img/icon/mouseCursorSelected.png").toString())));
	
	AudioClip CLICK_EFFECT = AudioLoader.clickEffect;
	
	public void triggerOverlay(int dx,int dy,int delay);
	
}
 