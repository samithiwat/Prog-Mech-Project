package gui;

import javafx.scene.ImageCursor;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.media.AudioClip;
import logic.AudioLoader;

public interface Sceneable {
	
	String FONT_NAME = "Bai Jamjuree";
	
	ImageCursor CURSOR_NORMAL = new ImageCursor((new Image(ClassLoader.getSystemResource("img/icon/mouseCursor.png").toString())));
	ImageCursor CURSOR_SELECTED = new ImageCursor((new Image(ClassLoader.getSystemResource("img/icon/mouseCursorSelected.png").toString())));
	
	AudioClip CLICK_EFFECT = AudioLoader.clickEffect;
	AudioClip EFFECT_MOUSE_ENTER = AudioLoader.mouseEnterSound;
	
	public Scene getScene();  
}
