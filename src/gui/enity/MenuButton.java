package gui.enity;

import implement.Clickable;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.media.AudioClip;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import logic.AudioLoader;

public class MenuButton extends Button implements Clickable {
	
	private final String FONT_PATH_REGULAR = "font/Bai_Jamjuree/BaiJamjuree-Regular.ttf";
	private final String FONT_PATH_BOLD = "font/Bai_Jamjuree/BaiJamjuree-Bold.ttf";
	private final String FONT_PATH = "font/Bai_Jamjuree/BaiJamjuree-Bold.ttf";

	public MenuButton(String content,int contentSize,int width,int height,Color textColor) {
		setTextFill(textColor);
		getStyleClass().add("mainmenu-button-release-style");
		setText(content);
		setFontRegular(contentSize);
		setPrefHeight(height);
		setPrefWidth(width);
		interact();
	}

	@Override
	public void interact() {
		setOnMouseReleased(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				getStyleClass().add("mainmenu-button-release-style");
			}
			
		});
		setOnMouseEntered(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				AudioClip effect = AudioLoader.mouseEnterSound;
				effect.play();
				getStyleClass().add("mainmenu-button-hold-style");
			}
			
		});
	}

	public void setFontRegular(int size) {
		//System.out.println(getClass().getClassLoader().getResource(FONT_PATH_REGULAR));
		setFont(Font.font("Bai Jamjuree",FontWeight.NORMAL,size));
	}
	
	public void setFontBold(int size) {
		setFont(Font.loadFont(getClass().getClassLoader().getResource
		        (FONT_PATH_BOLD).toExternalForm(), size));
	}
}
