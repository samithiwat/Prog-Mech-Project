package gui.entity;

import java.util.ArrayList;

import gui.GameLobbyMenu;
import gui.overlay.CharacterSelectOverlay;
import javafx.application.Platform;
import javafx.css.CssMetaData;
import javafx.css.Styleable;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.media.AudioClip;
import javafx.scene.shape.Rectangle;
import logic.AudioLoader;
import logic.FileController;
import logic.GameController;
import update.CharacterSelectUpdate;
import update.GameSettingUpdate;

public class CharacterCard extends StackPane {

	private AudioClip effect;
	private final int WIDTH_BG1 = 350;
	private final int HEIGHT_BG1 = 450;
	private final int WIDTH_BG2 = 320;
	private final int HEIGHT_BG2 = 400;
	private CharacterCard cc = this;
	private Rectangle bg;
	private int id;

	public CharacterCard(int id, String img_path, AudioClip effect, int x, int y) {
		super();
//		//FOR DEBIG ONLY
//		System.out.println(styleProperty());
//		System.out.println(rootProperty());
//		System.out.println(fillProperty());
//		System.out.println(root.getChildren());
//		System.out.println(userAgentStylesheetProperty());
//		System.out.println("visible : " + isVisible());
//		System.out.println("x : "+getLayoutX()+" , y : "+getLayoutY());
//		//System.out.println("Style : "+getCssMetaData());
//		ArrayList<String> log = new ArrayList<String>();
//		for(CssMetaData<? extends Styleable, ?> data : getCssMetaData()) {
//			for(String splitedData : data.toString().split("CSSProperty"))
//			{
//				log.add(splitedData);
//				log.add("\n");
//			}
//		}
//		try {
//			FileController.write("C:\\Computer Programing\\Java\\ProjectRes\\ProjectLog\\log7.txt", log);
//		}catch(Exception e) {
//			e.printStackTrace();
//		}
//		//END OF DEBUG
		setCardId(id);
		setId("character-card");
		setAlignment(Pos.CENTER);
		setPrefWidth(WIDTH_BG1);
		setPrefHeight(HEIGHT_BG1);
		setLayoutX(x);
		setLayoutY(y);

		bg = new Rectangle(WIDTH_BG2, HEIGHT_BG2);
		bg.setId("character-card-bg");

		ImageView portraits = new ImageView(ClassLoader.getSystemResource(img_path).toString());
		this.effect = effect;
		getChildren().addAll(bg, portraits);
		interact();
	}

	public AudioClip getSoundEffect() {
		return this.effect;
	}

	public Rectangle getBg() {
		return bg;
	}

	public int getCardId() {
		return id;
	}

	public void setCardId(int id) {
		this.id = id;
	}

	public void interact() {
		setOnMouseEntered(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				CharacterSelectUpdate.mouseEnteredUpdate(cc);
			}
		});

		setOnMouseExited(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				CharacterSelectUpdate.mouseExitedUpdate(cc);
			}

		});

		setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				AudioClip effect = AudioLoader.clickEffect;
				effect.play();
				CharacterSelectUpdate.selectCharacterUpdate(getCardId());
			}
		});
	}

}
