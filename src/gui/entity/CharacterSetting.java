package gui.entity;

import java.util.ArrayList;

import gui.GameLobbyMenu;
import gui.Sceneable;
import javafx.event.EventHandler;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.AudioClip;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.FontWeight;
import logic.AudioLoader;
import update.CharacterSelectUpdate;

public class CharacterSetting extends AnchorPane implements Sceneable {

	private static final int WIDTH = 220;
	private static final int HEIGHT = 160;
	private final int WIDTH_BG2 = 200;
	private final int HEIGHT_BG2 = 140;

	private ArrayList<Rectangle> bg;
	private ArrayList<TextTitle> texts;
	private ArrayList<MenuButton> buttons;
	private AnchorPane bgGroup;
	private int id;
	private ImageView portraits;
	private CharacterSetting cBox;
	private boolean isSelected = false;
	private boolean isReady = false;

	public CharacterSetting(int id, int x, int y) {

// --------------------------------------------------- Set Up Character Setting -------------------------------------------------------

		setcBox(this);
		setCBoxId(id);
		setLayoutX(x);
		setLayoutY(y);
		setPrefWidth(WIDTH);
		setPrefHeight(WIDTH);

// --------------------------------------------------- Character Setting Background ----------------------------------------------------		
		bgGroup = new AnchorPane();
		bgGroup.setPrefWidth(220);
		bgGroup.setPrefHeight(160);
		bgGroup.setLayoutX(0);
		bgGroup.setLayoutY(0);

		Rectangle bg1 = new Rectangle(WIDTH, HEIGHT);
		bg1.setId("character-box");

		Rectangle bg2 = new Rectangle(WIDTH_BG2, HEIGHT_BG2);
		bg2.setId("character-box-portraits");
		bg2.setX(10);
		bg2.setY(10);
		bg2.setVisible(false);

// --------------------------------------------------- Character Setting Portraits -----------------------------------------------------------

		portraits = new ImageView(ClassLoader.getSystemResource("img/character/MrRedFox.png").toString());
		portraits.setX(65);
		portraits.setY(45);
		portraits.setFitWidth(90);
		portraits.setFitHeight(105);	
		portraits.setVisible(false);
		
// --------------------------------------------------- Character Setting Text -----------------------------------------------------------		

		TextTitle empty = new TextTitle("Empty", Color.rgb(57, 62, 70), FontWeight.BOLD, 24, 73, 90);
		TextTitle name = new TextTitle("", Color.web("0x393E46"), FontWeight.BOLD, 24, 0, 0);

// --------------------------------------------------- Character Setting Button ---------------------------------------------------------
		
		MenuButton remove = new MenuButton("Remove", 14, 100, 40, Color.web("0x393E46"), 0, 180);
		remove.setFontBold(14);
		remove.setId("button-disable-style");
		remove.setDisable(true);
		
		remove.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				EFFECT_MOUSE_CLICK.play();
				CharacterSelectUpdate.setcBox(getcBox());
				CharacterSelectUpdate.removeUpdate();
			}
		});

		MenuButton ready = new MenuButton("Ready", 14, 100, 40, Color.web("0x393E46"), 120, 180);
		ready.setFontBold(14);
		
		ready.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				EFFECT_MOUSE_CLICK.play();
				CharacterSelectUpdate.setcBox(getcBox());
				CharacterSelectUpdate.readyUpdate();
			}
		});
		
		MenuButton unready = new MenuButton("Unready", 14, 100, 40, Color.web("0x393E46"), 120, 180);
		unready.setFontBold(14);
		unready.setVisible(false);
		
		unready.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				EFFECT_MOUSE_CLICK.play();
				CharacterSelectUpdate.setcBox(getcBox());
				CharacterSelectUpdate.unreadyUpdate();
			}
		});

// ---------------------------------------------------- Add Character Setting's Components for Update Class -----------------------------

		buttons = new ArrayList<MenuButton>();
		buttons.add(ready);
		buttons.add(unready);
		buttons.add(remove);
		
		bg = new ArrayList<Rectangle>();
		bg.add(bg1);
		bg.add(bg2);

		texts = new ArrayList<TextTitle>();
		texts.add(empty);
		texts.add(name);

		bgGroup.getChildren().addAll(bg1, bg2, name, empty, portraits);

		getChildren().addAll(bgGroup, remove, ready, unready);

		AnchorPane.setTopAnchor(name, 10.0);

		interact();
	}

// -------------------------------------------------- Set Reaction to Client's Interaction ------------------------------------------------------	

	public void interact() {

		bgGroup.setOnMouseEntered(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				AudioClip effect = AudioLoader.mouseEnterSound;
				effect.play();
				setCursor(MOUSE_SELECT);
				texts.get(0).setFill(Color.WHITE);
				bg.get(0).setId("character-box-hold");
			}
		});

		bgGroup.setOnMouseExited(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				setCursor(MOUSE_NORMAL);
				if (!isSelected()) {
					texts.get(0).setFill(Color.rgb(57, 62, 70));
					bg.get(0).setId("character-box");
				} else {
					bg.get(0).setId("character-box-selected");
				}
			}
		});

		bgGroup.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				EFFECT_MOUSE_CLICK.play();
				CharacterSelectUpdate.setcBox(getcBox());
				CharacterSelectUpdate.overlayUpdate();
				GameLobbyMenu.getOverlay1().triggerOverlay(0, 875, 1000);
			}
		});
	}
	
// ------------------------------------------------ Getter and Setter ------------------------------------------------------------

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

	public AnchorPane getBgGroup() {
		return bgGroup;
	}

	public void setBgGroup(AnchorPane bgGroup) {
		this.bgGroup = bgGroup;
	}

	public CharacterSetting getcBox() {
		return cBox;
	}

	public void setcBox(CharacterSetting cBox) {
		this.cBox = cBox;
	}

	public ImageView getPortraits() {
		return portraits;
	}

	public void setPortraits(ImageView portraits) {
		this.portraits = portraits;
	}

	public String toString() {
		return "id : " + this.id;
	}

	public boolean isReady() {
		return isReady;
	}

	public void setReady(boolean isReady) {
		this.isReady = isReady;
	}

	public ArrayList<MenuButton> getButtons() {
		return buttons;
	}

	public void setButtons(ArrayList<MenuButton> buttons) {
		this.buttons = buttons;
	}

}
