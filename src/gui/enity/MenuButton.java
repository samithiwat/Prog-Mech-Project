package gui.enity;

import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.media.AudioClip;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import logic.AudioLoader;

public class MenuButton extends Button implements Clickable {
	
	private final String FONT_PATH_REGULAR = "font/Bai_Jamjuree/BaiJamjuree-Regular.ttf";
	private final String FONT_PATH_BOLD = "font/Bai_Jamjuree/BaiJamjuree-Bold.ttf";
	private final String FONT_PATH = "font/Bai_Jamjuree/BaiJamjuree-Bold.ttf";

	public MenuButton(String content,int contentSize,int width,int height) {
		getStyleClass().add("menu-button");
		setText(content);
		setFontRegular(contentSize);
		setPrefHeight(height);
		setPrefWidth(width);
	}

	@Override
	public void interact() {
		setOnMouseEntered(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent arg0) {
				AudioClip effect = AudioLoader.mouseEnterSound;
				effect.play();
				
			}
			
		});
	}

	public void setFontRegular(int size) {
		System.out.println(getClass().getClassLoader().getResource(FONT_PATH_REGULAR).toExternalForm());
		setFont(Font.loadFont(getClass().getClassLoader().getResource
		        (FONT_PATH_REGULAR).toExternalForm(), size));
	}
	
	public void setFontBold(int size) {
		setFont(Font.loadFont(getClass().getClassLoader().getResource
		        (FONT_PATH_BOLD).toExternalForm(), size));
	}
}
