package gui;

import java.util.ArrayList;

import gui.entity.CharacterBox;
import gui.entity.GameSetting;
import gui.entity.TextTitle;
import gui.overlay.CharacterSelectOverlay1;
import gui.overlay.CharacterSelectOverlay2;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.AudioClip;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.FontWeight;
import logic.AudioLoader;
import logic.SceneController;

public class GameSettingMenu implements Showable {

	private Scene scene;
	private static CharacterSelectOverlay1 characterOverlay1;
	private static CharacterSelectOverlay2 characterOverlay2;
	private static AudioClip bgm;
	private static ArrayList<CharacterBox> cBoxes;

	public GameSettingMenu() {

		StartMenu.getMenuThemeSong().stop();
		bgm = AudioLoader.lobbyThemeSong;
		bgm.play();

		AnchorPane root = new AnchorPane();

		GameSetting gameSetting = new GameSetting();
		gameSetting.setLayoutX(1097);
		gameSetting.setLayoutY(253);

		TextTitle title = new TextTitle("Game Lobby", Color.WHITE, FontWeight.BOLD, 72, 554, 113);
		TextTitle label = new TextTitle("Players", Color.WHITE, FontWeight.BOLD, 48, 682, 203);
		Rectangle titleBox = new Rectangle(1200, 80);
		titleBox.setX(170);
		titleBox.setY(153);
		titleBox.setFill(Color.web("0x61656B"));
		titleBox.setArcWidth(30);
		titleBox.setArcHeight(30);

		TextTitle back = new TextTitle("Back to Main Menu", Color.web("0x393E46"), FontWeight.BOLD, 20, 1125, 786);

		back.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				SceneController.setScene((new MainMenu()).getScene());
			}
		});

		Rectangle backBox = new Rectangle(240, 40);
		backBox.setFill(Color.web("0xFEFDE8"));
		backBox.setX(1097);
		backBox.setY(758);
		backBox.setArcWidth(30);
		backBox.setArcHeight(30);
		
		TextTitle start = new TextTitle("Start", Color.WHITE, FontWeight.BOLD, 48, 682, 203);

		Rectangle bg = new Rectangle(SceneController.getFullscreenWidth(), SceneController.getFullscreenHeight());
		bg.setFill(Color.rgb(3, 114, 140));

		CharacterBox cBox1 = new CharacterBox(197, 273);
		CharacterBox cBox2 = new CharacterBox(497, 273);
		CharacterBox cBox3 = new CharacterBox(797, 273);
		CharacterBox cBox4 = new CharacterBox(197, 573);
		CharacterBox cBox5 = new CharacterBox(497, 573);
		CharacterBox cBox6 = new CharacterBox(797, 573);

		characterOverlay1 = new CharacterSelectOverlay1();
		characterOverlay2 = new CharacterSelectOverlay2();

		cBoxes = new ArrayList<CharacterBox>();
		cBoxes.add(cBox4);
		cBoxes.add(cBox5);
		cBoxes.add(cBox6);

		root.getChildren().addAll(bg, cBox1, cBox2, cBox3, cBox4, cBox5, cBox6);
		root.getChildren().addAll(backBox, back,  title, titleBox, label, gameSetting, characterOverlay1,
				characterOverlay2);

		scene = new Scene(root, SceneController.getFullscreenWidth(), SceneController.getFullscreenWidth());
		scene.setCursor(CURSOR_NORMAL);
		scene.getStylesheets().add(ClassLoader.getSystemResource("css/style.css").toExternalForm());
		scene.setOnKeyPressed(new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent event) {
				if (event.getCode() == KeyCode.ESCAPE) {
					SceneController.setScene((new MainMenu()).getScene());
				}
			}
		});
	}

	@Override
	public Scene getScene() {
		return scene;
	}

	public static CharacterSelectOverlay1 getOverlay1() {
		return characterOverlay1;
	}

	public static CharacterSelectOverlay2 getOverlay2() {
		return characterOverlay2;
	}

	public static AudioClip getBGM() {
		return bgm;
	}

	public static void setCBoxes(ArrayList<CharacterBox> cBoxes) {
		GameSettingMenu.cBoxes = cBoxes;
	}

	public static ArrayList<CharacterBox> getCBoxes() {
		return cBoxes;
	}

}
