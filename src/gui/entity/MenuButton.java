package gui.entity;

import java.util.ArrayList;

import gui.Sceneable;
import javafx.css.CssMetaData;
import javafx.css.Styleable;
import javafx.event.EventHandler;
import javafx.scene.ImageCursor;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.media.AudioClip;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import logic.AudioLoader;
import logic.FileController;
import logic.SceneController;

public class MenuButton extends Button implements Clickable{
	

	public MenuButton(String content,int contentSize,int width,int height,Color textColor,int x,int y) {
		setLayoutX(x);
		setLayoutY(y);
		setTextFill(textColor);
		setId("button-release-style");
		setText(content);
		setFontRegular(contentSize);
		setPrefHeight(height);
		setPrefWidth(width);
		interact();
	}

	@Override
	public void interact() {
		setOnMouseExited(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				setCursor(MOUSE_NORMAL);
				setId("button-release-style");
				//System.out.println(getStyle());
			}
			
		});
		setOnMouseEntered(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				setCursor(MOUSE_SELECT);
				EFFECT_MOUSE_ENTER.play();
				setId("button-hold-style");
			}
			
		});
	}

	public void setFontRegular(int size) {
		//System.out.println(getClass().getClassLoader().getResource(FONT_PATH_REGULAR));
		setFont(Font.font("Bai Jamjuree",FontWeight.NORMAL,size));
	}
	
	public void setFontBold(int size) {
		setFont(Font.font("Bai Jamjuree",FontWeight.BOLD,size));
	}

	@Override
	public void triggerDisable() {
		setDisable(!isDisable());
	}
}
