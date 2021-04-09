package gui.entity;

import javafx.scene.ImageCursor;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.media.AudioClip;
import logic.AudioLoader;

public interface Clickable {
	
	String FONT_PATH_REGULAR = "font/Bai_Jamjuree/BaiJamjuree-Regular.ttf";
	String FONT_PATH_BOLD = "font/Bai_Jamjuree/BaiJamjuree-Bold.ttf";
	String FONT_PATH = "font/Bai_Jamjuree/BaiJamjuree-Bold.ttf";
	
	ImageCursor MOUSE_NORMAL = new ImageCursor((new Image(ClassLoader.getSystemResource("img/icon/MouseCursor.png").toString())));
	ImageCursor MOUSE_SELECT = new ImageCursor((new Image(ClassLoader.getSystemResource("img/icon/MouseCursorSelected.png").toString())));

	AudioClip EFFECT_MOUSE_ENTER = AudioLoader.mouseEnterSound;
	AudioClip EFFECT_MOUSE_CLICK = AudioLoader.clickEffect;
	
	ImageView RED_FOX_IMG = new ImageView(ClassLoader.getSystemResource("img/character/MrRedFox.png").toString());
	ImageView LADY_COLLECTOR_IMG = new ImageView(ClassLoader.getSystemResource("img/character/LadyCollector.png").toString());
	ImageView BLACK_SKULL_IMG = new ImageView(ClassLoader.getSystemResource("img/character/BlackSkull.png").toString());
	ImageView SIR_THOUSAND_IMG = new ImageView(ClassLoader.getSystemResource("img/character/SirThousandYear.png").toString());
	ImageView SIR_TEWADA = new ImageView(ClassLoader.getSystemResource("img/character/SirTewada.png").toString());
	ImageView SIR_TEWADEE = new ImageView(ClassLoader.getSystemResource("img/character/SirTewadee.png").toString());
	
	public void interact();

	public void triggerDisable(); 
}
