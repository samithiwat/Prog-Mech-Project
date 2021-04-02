package gui;

import gui.entity.CharacterBox;
import gui.entity.TextTitle;
import gui.overlay.CharacterSelectOverlay1;
import gui.overlay.CharacterSelectOverlay2;
import javafx.scene.Scene;
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


	public GameSettingMenu() {
		
		StartMenu.getMenuThemeSong().stop();
		bgm = AudioLoader.lobbyThemeSong;
		bgm.play();
		
		
		
		AnchorPane root = new AnchorPane();

		TextTitle title = new TextTitle("Game Lobby", Color.WHITE, FontWeight.BOLD, 72, 554, 113);
		TextTitle label = new TextTitle("Players", Color.WHITE, FontWeight.BOLD, 48, 682, 203);
		Rectangle titleBox = new Rectangle(1200, 80);
		titleBox.setX(170);
		titleBox.setY(153);
		titleBox.setFill(Color.web("0x61656B"));
		titleBox.setArcWidth(30);
		titleBox.setArcHeight(30);

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

		root.getChildren().addAll(bg, cBox1, cBox2, cBox3, cBox4, cBox5, cBox6);
		root.getChildren().addAll(title, titleBox, label, characterOverlay1, characterOverlay2);

		scene = new Scene(root, SceneController.getFullscreenWidth(), SceneController.getFullscreenWidth());
		scene.setCursor(CURSOR_NORMAL);
		scene.getStylesheets().add(ClassLoader.getSystemResource("css/style.css").toExternalForm());
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

}
