package gui;

import java.util.ArrayList;

import gui.entity.CharacterSetting;
import gui.entity.GameSetting;
import gui.entity.MenuButton;
import gui.entity.TextTitle;
import gui.overlay.CharacterInfo;
import gui.overlay.CharacterSelectOverlayPage1;
import gui.overlay.CharacterSelectOverlayPage2;
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
import logic.GameSetUp;
import logic.SceneController;
import update.AudioUpdate;

public class GameLobbyMenu implements Sceneable {

	private Scene scene;
	private static CharacterSelectOverlayPage1 characterOverlay1;
	private static CharacterSelectOverlayPage2 characterOverlay2;
	private static CharacterInfo characterInfo;
	private static AudioClip bgm;
	private static MenuButton start;
	private static ArrayList<CharacterSetting> cBoxes;

	private static Thread setUp;

	public GameLobbyMenu() {

		bgm = AudioLoader.lobbyThemeSong;

		AnchorPane root = new AnchorPane();

// ----------------------------------------------- Select Character Array Set Up ---------------------------------------------		

		for (int i = 0; i < 6; i++) {
			GameSetUp.gameCharacter.add(null);
		}

// ----------------------------------------------- Create Game Setting Tab ---------------------------------------------------
		GameSetting gameSetting = new GameSetting();
		gameSetting.setLayoutX(1097);
		gameSetting.setLayoutY(253);

// ----------------------------------------------- Create Misc Box -----------------------------------------------------------		

		TextTitle title = new TextTitle("Game Lobby", Color.WHITE, FontWeight.BOLD, 72, 554, 113);
		TextTitle label = new TextTitle("Players", Color.WHITE, FontWeight.BOLD, 48, 682, 203);
		Rectangle titleBox = new Rectangle(1200, 80);
		titleBox.setX(170);
		titleBox.setY(153);
		titleBox.setFill(Color.web("0x61656B"));
		titleBox.setArcWidth(30);
		titleBox.setArcHeight(30);

// ---------------------------------------------- Create Back to Main Menu Button --------------------------------------------	

		MenuButton back = new MenuButton("Back to Main Menu", 20, 270, 50, Color.web("0x393E46"), 1102, 768);
		back.setFontBold(20);
		back.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				EFFECT_MOUSE_CLICK.play();
				SceneController.setScene((new MainMenu()).getScene());
				AudioUpdate.changeEnv(StartMenu.getMenuThemeSong());
			}
		});

		start = new MenuButton("Start", 20, 270, 50, Color.web("0x393E46"), 1102, 703);
		start.setFontBold(20);
		start.setId("button-disable-style");
		start.setDisable(true);
		start.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {

				EFFECT_MOUSE_CLICK.play();
				setUp = new Thread(new Runnable() {

					@Override
					public void run() {
						GameSetUp gameSetUp = new GameSetUp();
					}
				});

				setUp.start();
				SceneController.loadingScreen();
			}

		});

// -------------------------------------------- Scene Background --------------------------------------------------------------		

		Rectangle bg = new Rectangle(FULLSCREEN_WIDTH, FULLSCREEN_HEIGHT);
		bg.setFill(Color.rgb(3, 114, 140));

// -------------------------------------------- Character Select Box ----------------------------------------------------------

		CharacterSetting cBox1 = new CharacterSetting(0, 197, 273);
		CharacterSetting cBox2 = new CharacterSetting(1, 497, 273);
		CharacterSetting cBox3 = new CharacterSetting(2, 797, 273);
		CharacterSetting cBox4 = new CharacterSetting(3, 197, 573);
		CharacterSetting cBox5 = new CharacterSetting(4, 497, 573);
		CharacterSetting cBox6 = new CharacterSetting(5, 797, 573);

// -------------------------------------------- Character Overlay ----------------------------------------------------------

		characterOverlay1 = new CharacterSelectOverlayPage1();
		characterOverlay2 = new CharacterSelectOverlayPage2();
		characterInfo = new CharacterInfo();

// -------------------------------------------- Add cBox to Array for Character Update ----------------------------------------------------------

		cBoxes = new ArrayList<CharacterSetting>();
		cBoxes.add(cBox1);
		cBoxes.add(cBox2);
		cBoxes.add(cBox3);
		cBoxes.add(cBox4);
		cBoxes.add(cBox5);
		cBoxes.add(cBox6);

// -------------------------------------------- Set Scene -----------------------------------------------------------------------

		root.getChildren().addAll(bg, cBox1, cBox2, cBox3, cBox4, cBox5, cBox6);
		root.getChildren().addAll(start, back, title, titleBox, label, gameSetting, characterOverlay1,
				characterOverlay2, characterInfo);

		scene = new Scene(root, FULLSCREEN_WIDTH, FULLSCREEN_HEIGHT);
		scene.setCursor(MOUSE_NORMAL);
		scene.getStylesheets().add(ClassLoader.getSystemResource("css/lobby-style.css").toExternalForm());
		scene.setOnKeyPressed(new EventHandler<KeyEvent>() {

// -------------------------------------------- ESC Key Setting ------------------------------------------------------------------	

			@Override
			public void handle(KeyEvent event) {
				if (event.getCode() == KeyCode.ESCAPE) {
					SceneController.setScene((new MainMenu()).getScene());
					AudioUpdate.changeEnv(StartMenu.getMenuThemeSong());
				}
			}
		});
	}

// -------------------------------------------- Getter and Setter -------------------------------------------------------------------

	@Override
	public Scene getScene() {
		return scene;
	}

	public static CharacterSelectOverlayPage1 getOverlay1() {
		return characterOverlay1;
	}

	public static CharacterSelectOverlayPage2 getOverlay2() {
		return characterOverlay2;
	}

	public static AudioClip getBGM() {
		return bgm;
	}

	public static void setCBoxes(ArrayList<CharacterSetting> cBoxes) {
		GameLobbyMenu.cBoxes = cBoxes;
	}

	public static ArrayList<CharacterSetting> getCBoxes() {
		return cBoxes;
	}

	public static CharacterInfo getCharacterInfo() {
		return characterInfo;
	}

	public static void setCharacterInfo(CharacterInfo characterInfo) {
		GameLobbyMenu.characterInfo = characterInfo;
	}

	public static MenuButton getStart() {
		return start;
	}

}
