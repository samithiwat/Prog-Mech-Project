package gui.entity;

import java.util.ArrayList;

import gui.GameLobbyMenu;
import gui.Showable;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.AudioClip;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.FontWeight;
import logic.AudioLoader;
import update.CharacterSelectUpdate;

public class CharacterSetting extends AnchorPane implements Showable {

	private static final int WIDTH = 220;
	private static final int HEIGHT = 160;
	private final int WIDTH_BG2 = 200;
	private final int HEIGHT_BG2 = 140;
	
	private ArrayList<Rectangle> bg;
	private ArrayList<TextTitle> texts;
	private int id;
	private boolean isSelected = false;

	public CharacterSetting(int id, int x, int y) {

// --------------------------------------------------- Set Up Character Setting -------------------------------------------------------
		
		setCBoxId(id);
		setLayoutX(x);
		setLayoutY(y);
		setPrefWidth(WIDTH);
		setPrefHeight(WIDTH);
		
// --------------------------------------------------- Character Setting Background ----------------------------------------------------		
		Rectangle bg1 = new Rectangle(WIDTH, HEIGHT);
		bg1.setId("character-box");
		
		Rectangle bg2 = new Rectangle(WIDTH_BG2, HEIGHT_BG2);
		bg2.setId("character-box-portraits");
		bg2.setX(10);
		bg2.setY(10);
		bg2.setVisible(false);

// --------------------------------------------------- Character Setting Text -----------------------------------------------------------		

		TextTitle empty = new TextTitle("Empty", Color.rgb(57, 62, 70), FontWeight.BOLD, 24, 73, 90);
		TextTitle name = new TextTitle("", Color.web("0x393E46"), FontWeight.BOLD, 24, 0, 0);

// --------------------------------------------------- Character Setting Button ---------------------------------------------------------
		
		MenuButton customize = new MenuButton("Customize", 14, 100, 40, Color.web("0x393E46"), 0, 180);
		customize.setFontBold(14);

		MenuButton ready = new MenuButton("Ready", 14, 100, 40, Color.web("0x393E46"), 120, 180);
		ready.setFontBold(14);

// ---------------------------------------------------- Add Character Setting's Components for Update Class -----------------------------
		
		bg = new ArrayList<Rectangle>();
		bg.add(bg1);
		bg.add(bg2);
		
		texts = new ArrayList<TextTitle>();
		texts.add(empty);
		texts.add(name);
		
		getChildren().addAll(bg1,bg2, empty, name, customize, ready);

		AnchorPane.setTopAnchor(name, 10.0);
		
		interact();
	}

// -------------------------------------------------- Set Reaction to Client's Interaction ------------------------------------------------------	

	public void interact() {

		setOnMouseEntered(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				AudioClip effect = AudioLoader.mouseEnterSound;
				effect.play();
				setCursor(CURSOR_SELECTED);
				texts.get(0).setFill(Color.WHITE);
				bg.get(0).setId("character-box-hold");
			}
		});

		setOnMouseExited(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				setCursor(CURSOR_NORMAL);
				if(!isSelected()) {
					texts.get(0).setFill(Color.rgb(57, 62, 70));
					bg.get(0).setId("character-box");
				}
				else {
					bg.get(0).setId("character-box-selected");
				}
			}
		});

		setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				AudioClip effect = AudioLoader.clickEffect;
				effect.play();
				CharacterSelectUpdate.setcBoxId(id);
				GameLobbyMenu.getOverlay1().triggerOverlay(0, 825, 1000);
			}
		});
	}

	public int getCBoxId() {
		return this.id;
	}

	public void setCBoxId(int id) {
		this.id = id;
	}

	public ArrayList<Rectangle> getBg() {
		return bg;
	}

	public ArrayList<TextTitle> getTexts() {
		return this.texts;
	}

	public boolean isSelected() {
		return isSelected;
	}

	public void setSelected(boolean isSelected) {
		this.isSelected = isSelected;
	}

}
