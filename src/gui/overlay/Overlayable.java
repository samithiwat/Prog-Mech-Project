package gui.overlay;

import javafx.scene.ImageCursor;
import javafx.scene.image.Image;
import javafx.scene.media.AudioClip;
import javafx.scene.paint.Color;
import logic.AudioLoader;

public interface Overlayable {
	
	String FONT_NAME = "Bai Jamjuree";
	
	ImageCursor CURSOR_NORMAL = new ImageCursor((new Image(ClassLoader.getSystemResource("img/icon/mouseCursor.png").toString())));
	ImageCursor CURSOR_SELECTED = new ImageCursor((new Image(ClassLoader.getSystemResource("img/icon/mouseCursorSelected.png").toString())));
	
	AudioClip EFFECT_MOUSE_CLICK = AudioLoader.clickEffect;
	AudioClip EFFECT_MOUSE_ENTER = AudioLoader.mouseEnterSound;
	AudioClip EFFECT_ERROR = AudioLoader.errorSound;
	
	Color BG_COLOR = Color.web("0x393E46",0.85);

	public void triggerOverlay(int dx,int dy,int delay);
	
}
 