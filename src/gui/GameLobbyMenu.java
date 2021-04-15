package gui;

import java.util.ArrayList;

import gui.entity.CharacterSetting;
import gui.entity.GameSetting;
import gui.entity.MenuButton;
import gui.entity.TextTitle;
import gui.overlay.CharacterInfo;
import gui.overlay.CharacterSelectOverlay1;
import gui.overlay.CharacterSelectOverlay2;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.AudioClip;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import logic.AudioLoader;
import logic.GameController;
import logic.GameSetUp;
import logic.SceneController;
import update.AudioUpdate;

public class GameLobbyMenu implements Sceneable {

	private Scene scene;
	private static CharacterSelectOverlay1 characterOverlay1;
	private static CharacterSelectOverlay2 characterOverlay2;
	private static CharacterInfo characterInfo;
	private static AudioClip bgm;
	private static MenuButton start;
	private static ArrayList<CharacterSetting> cBoxes;

	public GameLobbyMenu() {

//		StartMenu.getMenuThemeSong().stop();
		bgm = AudioLoader.lobbyThemeSong;
//		bgm.play();

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

		MenuButton back = new MenuButton("Back to Main Menu", 20, 240, 40, Color.web("0x393E46"), 1102, 758);
		back.setFontBold(20);
		back.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				CLICK_EFFECT.play();
				SceneController.setScene((new MainMenu()).getScene());
				bgm.stop();
				StartMenu.getMenuThemeSong().play();
			}
		});

		start = new MenuButton("Start", 20, 240, 40, Color.web("0x393E46"), 1102, 703);
		start.setFontBold(20);
		start.setId("button-disable-style");
		start.setDisable(true);
		start.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				
//				SceneController.loadingScreen();
				
				
				CLICK_EFFECT.play();
				SceneController.loadingScreen();
				Thread setUp = new Thread(new Runnable() {
					
					@Override
					public void run() {
						GameSetUp gameSetUp = new GameSetUp();
					}
				});
				
				setUp.start();

//				try {
//					setUp.join();
//				
//				} catch (InterruptedException e) {
//					
//				}
				while(!GameSetUp.isFinished()) {
					
					if(GameSetUp.isFinished()) {
						
						SceneController.setScene(SceneController.getMapOverView());
						AudioUpdate.toMapOverview(bgm);
						
						// ----------------------- Create GameController's Thread Run Parallel -------------------------
						
						Thread controller = new Thread(new Runnable() {
							
							@Override
							public void run() {
								GameController gameController = new GameController();
							}
						});
						
						controller.start();
						break;
						}
					}
				}
		});
		
			

// -------------------------------------------- Scene Background --------------------------------------------------------------		

		Rectangle bg = new Rectangle(SceneController.getFullscreenWidth(), SceneController.getFullscreenHeight());
		bg.setFill(Color.rgb(3, 114, 140));

// -------------------------------------------- Character Select Box ----------------------------------------------------------

		CharacterSetting cBox1 = new CharacterSetting(0, 197, 273);
		CharacterSetting cBox2 = new CharacterSetting(1, 497, 273);
		CharacterSetting cBox3 = new CharacterSetting(2, 797, 273);
		CharacterSetting cBox4 = new CharacterSetting(3, 197, 573);
		CharacterSetting cBox5 = new CharacterSetting(4, 497, 573);
		CharacterSetting cBox6 = new CharacterSetting(5, 797, 573);

// -------------------------------------------- Character Overlay ----------------------------------------------------------

		characterOverlay1 = new CharacterSelectOverlay1();
		characterOverlay2 = new CharacterSelectOverlay2();
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

		scene = new Scene(root, SceneController.getFullscreenWidth(), SceneController.getFullscreenWidth());
		scene.setCursor(CURSOR_NORMAL);
		scene.getStylesheets().add(ClassLoader.getSystemResource("css/lobby-style.css").toExternalForm());
		scene.setOnKeyPressed(new EventHandler<KeyEvent>() {

// -------------------------------------------- ESC Key Setting ------------------------------------------------------------------	

			@Override
			public void handle(KeyEvent event) {
				if (event.getCode() == KeyCode.ESCAPE) {
					SceneController.setScene((new MainMenu()).getScene());
					bgm.stop();
					StartMenu.getMenuThemeSong().play();
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
