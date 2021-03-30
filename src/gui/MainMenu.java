package gui;

import java.awt.desktop.QuitEvent;
import java.util.ArrayList;

import gui.enity.Clickable;
import gui.enity.MenuButton;
import gui.enity.MenuIcon;
import gui.enity.TextTitle;
import gui.overlay.CreditOverlay;
import gui.overlay.QuitOverlay;
import javafx.event.EventHandler;
import javafx.scene.ImageCursor;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.media.AudioClip;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import logic.AudioLoader;
import logic.SceneController;
import update.CloseGame;

public class MainMenu implements Showable {

	private static Scene scene;
	private static ArrayList<Clickable> components;

	public MainMenu() {

		AnchorPane root = new AnchorPane();

		CreditOverlay creditOverlay = new CreditOverlay();
		QuitOverlay quitOverlay = new QuitOverlay();

		ImageView bg = new ImageView(ClassLoader.getSystemResource("img/MainMenuBG.png").toString());
		MenuIcon creditIcon = new MenuIcon("img/About.png",75,60);
		creditIcon.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				AudioClip effect = AudioLoader.clickEffect;
				effect.play();
				creditOverlay.triggerOverlay(0,825,1000);
			}
		});
		MenuIcon helpIcon = new MenuIcon("img/Help.png",223,60);
		MenuIcon settingIcon = new MenuIcon("img/Setting.png",1300,55);

		TextTitle title = new TextTitle("Coconut Island",Color.WHITE,FontWeight.BOLD,120,371,130);

		GridPane buttonBar = new GridPane();
		buttonBar.setHgap(100);
		buttonBar.setLayoutX(70);
		buttonBar.setLayoutY(680);

		MenuButton start = new MenuButton("Start", 64, 400, 150, Color.WHITE,0,0);

		MenuButton load = new MenuButton("Load", 64, 400, 150, Color.WHITE,0,0);

		MenuButton quit = new MenuButton("Quit", 64, 400, 150, Color.WHITE,0,0);

		quit.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				AudioClip effect = AudioLoader.clickEffect;
				effect.play();
				quitOverlay.triggerOverlay(0,1000,1000);
//				for(Node component : ((AnchorPane) scene.getRoot()).getChildren()) {
//					if(component instanceof Clickable) {
//						component.setDisable(true);
//					}
//				}
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
//		root.getChildren().addAll(bg,title,start);
		scene = new Scene(root, SceneController.getFullscreenWidth(), SceneController.getFullscreenHeight());
		scene.setCursor(new ImageCursor((new Image(ClassLoader.getSystemResource("img/mouseCursor.png").toString()))));
//		System.out.println(ClassLoader.getSystemResource("css/style.css"));
		scene.getStylesheets().add(ClassLoader.getSystemResource("css/style.css").toExternalForm());

	}

	static public ArrayList<Clickable> getComponent() {
		//System.out.println(components);
		return components;
	}
	
	public Scene getScene() {
		return this.scene;
	}

}
