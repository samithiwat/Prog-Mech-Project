package gui.entity;

import gui.GameSettingMenu;
import javafx.event.EventHandler;
import javafx.scene.ImageCursor;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.AudioClip;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.FontWeight;
import logic.AudioLoader;

public class CharacterBox extends AnchorPane {

	private static final int WIDTH = 220;
	private Rectangle bg;

	public CharacterBox(int x,int y) {
		
		setLayoutX(x);
		setLayoutY(y);
		setPrefWidth(WIDTH);
		setPrefHeight(WIDTH);
		bg = new Rectangle(WIDTH, WIDTH);
		bg.setId("character-box");
		bg.setArcWidth(30);
		bg.setArcHeight(30);

		TextTitle empty = new TextTitle("Empty", Color.rgb(57, 62, 70), FontWeight.BOLD, 24, 73, 35);
		
		TextTitle content = new TextTitle("Click here to select character", Color.rgb(57, 62, 70), FontWeight.MEDIUM, 12, 29, 120);

		getChildren().addAll(bg,empty,content);
		
		interact();
	}
	
	public void interact() {
	
		setOnMouseEntered(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				bg.setId("character-box-selected");
				AudioClip effect = AudioLoader.mouseEnterSound;
				effect.play();
				setCursor(new ImageCursor((new Image(ClassLoader.getSystemResource("img/mouseCursorSelected.png").toString()))));
			}
		});
		
		setOnMouseExited(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent arg0) {
				bg.setId("character-box");
				setCursor(new ImageCursor((new Image(ClassLoader.getSystemResource("img/mouseCursor.png").toString()))));
			}
		});
		
		setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				GameSettingMenu.getOverlay1().triggerOverlay(0, 825, 1000);
			}
		});
	}
	
	

}
