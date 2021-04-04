package gui;

import java.util.ArrayList;

import gui.entity.CharacterCard;
import gui.entity.Clickable;
import gui.entity.MenuButton;
import gui.entity.MenuIcon;
import gui.entity.TextTitle;
import gui.overlay.CreditOverlay;
import gui.overlay.QuitOverlay;
import javafx.event.EventHandler;
import javafx.scene.ImageCursor;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.media.AudioClip;
import javafx.scene.paint.Color;
import javafx.scene.text.FontWeight;
import logic.AudioLoader;
import logic.SceneController;

public class MainMenu implements Showable, Sceneable {

	private static Scene scene;
	private static ArrayList<Clickable> components;

	public MainMenu() {

		AnchorPane root = new AnchorPane();

		CreditOverlay creditOverlay = new CreditOverlay();
		QuitOverlay quitOverlay = new QuitOverlay();

		ImageView bg = new ImageView(ClassLoader.getSystemResource("img/background/MainMenuBG.png").toString());
		MenuIcon creditIcon = new MenuIcon("img/icon/About.png",75,60);
		creditIcon.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				AudioClip effect = AudioLoader.clickEffect;
				effect.play();
				creditOverlay.triggerOverlay(0,825,1000);
			}
		});
		MenuIcon helpIcon = new MenuIcon("img/icon/Help.png",223,60);
		MenuIcon settingIcon = new MenuIcon("img/icon/Setting.png",1300,55);

		TextTitle title = new TextTitle("Coconut Island",Color.WHITE,FontWeight.BOLD,120,371,130);

		GridPane buttonBar = new GridPane();
		buttonBar.setHgap(100);
		buttonBar.setLayoutX(70);
		buttonBar.setLayoutY(680);

		MenuButton start = new MenuButton("Start", 64, 400, 150, Color.WHITE,0,0);
		
		start.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				AudioClip effect = AudioLoader.clickEffect;
				effect.play();
				SceneController.setScene(SceneController.getGameSettingMenu());
				StartMenu.getMenuThemeSong().stop();
				GameLobbyMenu.getBGM().play();
				System.out.println("Change to Start Menu...");
			}
		});

		MenuButton load = new MenuButton("Load", 64, 400, 150, Color.WHITE,0,0);

		MenuButton quit = new MenuButton("Quit", 64, 400, 150, Color.WHITE,0,0);

		quit.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				AudioClip effect = AudioLoader.clickEffect;
				effect.play();
				quitOverlay.triggerOverlay(0,1000,1000);
				start.setDisable(true);
				load.setDisable(true);
				quit.setDisable(true);
				creditIcon.setDisable(true);
				helpIcon.setDisable(true);
				settingIcon.setDisable(true);
			}

		});

		buttonBar.add(start, 0, 0);
		buttonBar.add(load, 1, 0);
		buttonBar.add(quit, 2, 0);
		
		components = new ArrayList<Clickable>();
		components.add(creditIcon);
		components.add(helpIcon);
		components.add(settingIcon);
		components.add(start);
		components.add(load);
		components.add(quit);
		
		
		root.getChildren().addAll(bg, buttonBar, title, creditIcon, helpIcon, settingIcon, creditOverlay, quitOverlay);
		
		scene = new Scene(root, SceneController.getFullscreenWidth(), SceneController.getFullscreenHeight());
		scene.setCursor(MOUSE_NORMAL);
//		System.out.println(ClassLoader.getSystemResource("css/style.css"));
		scene.getStylesheets().add(ClassLoader.getSystemResource("css/mainmenu-style.css").toExternalForm());

	}

	static public ArrayList<Clickable> getComponent() {
		//System.out.println(components);
		return components;
	}
	
	public Scene getScene() {
		return this.scene;
	}

}
