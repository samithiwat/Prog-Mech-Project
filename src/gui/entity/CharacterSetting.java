package gui.entity;

import gui.GameLobbyMenu;
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

public class CharacterSetting extends AnchorPane {

	private static final int WIDTH = 220;
	private static final int HEIGHT = 160;
	private Rectangle bg;
	private TextTitle empty;

	public CharacterSetting(int x, int y) {

		setLayoutX(x);
		setLayoutY(y);
		setPrefWidth(WIDTH);
		setPrefHeight(WIDTH);
		bg = new Rectangle(WIDTH, HEIGHT);
		bg.setId("character-box");
		bg.setArcWidth(30);
		bg.setArcHeight(30);

		empty = new TextTitle("Empty", Color.rgb(57, 62, 70), FontWeight.BOLD, 24, 73, 90);

		MenuButton customize = new MenuButton("Customize", 16, 100, 14, Color.web("0x393E46"), 0, 180);
		customize.setFontBold(16);
		
		MenuButton ready = new MenuButton("Ready", 16, 100, 14, Color.web("0x393E46"), 120, 180);
		ready.setFontBold(16);

		getChildren().addAll(bg, empty, customize, ready);

		interact();
	}

	public void interact() {

		bg.setOnMouseEntered(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				empty.setFill(Color.WHITE);
				bg.setId("character-box-selected");
				AudioClip effect = AudioLoader.mouseEnterSound;
				effect.play();
				setCursor(new ImageCursor(
						(new Image(ClassLoader.getSystemResource("img/mouseCursorSelected.png").toString()))));
			}
		});

		bg.setOnMouseExited(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				empty.setFill(Color.rgb(57, 62, 70));
				bg.setId("character-box");
				setCursor(
						new ImageCursor((new Image(ClassLoader.getSystemResource("img/mouseCursor.png").toString()))));
			}
		});

		bg.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				AudioClip effect = AudioLoader.clickEffect;
				effect.play();
				GameLobbyMenu.getOverlay1().triggerOverlay(0, 825, 1000);
			}
		});
	}

}
