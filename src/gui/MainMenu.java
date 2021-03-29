package gui;

import gui.enity.MenuButton;
import implement.Showable;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.media.AudioClip;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import logic.AudioLoader;
import logic.SceneController;

public class MainMenu implements Showable {

	private Scene scene;

	public MainMenu() {

		AnchorPane root = new AnchorPane();

		ImageView bg = new ImageView(ClassLoader.getSystemResource("img/MainMenuBG.png").toString());
		ImageView creditIcon = new ImageView(ClassLoader.getSystemResource("img/About.png").toString());
		creditIcon.setX(75);
		creditIcon.setY(60);
		ImageView helpIcon = new ImageView(ClassLoader.getSystemResource("img/Help.png").toString());
		helpIcon.setX(223);
		helpIcon.setY(60);
		ImageView settingIcon = new ImageView(ClassLoader.getSystemResource("img/Setting.png").toString());
		settingIcon.setX(1300);
		settingIcon.setY(55);

		Text title = new Text("Coconut Island");
		title.setFont(Font.font("Bai Jamjuree", FontWeight.BOLD, 120));
		title.setFill(Color.WHITE);
		title.setX(371);
		title.setY(130);

		GridPane buttonBar = new GridPane();
		buttonBar.setHgap(100);
		buttonBar.setLayoutX(70);
		buttonBar.setLayoutY(680);
		
		MenuButton start = new MenuButton("Start", 64, 400, 150,Color.WHITE);

		MenuButton load = new MenuButton("Load", 64, 400, 150,Color.WHITE);

		MenuButton quit = new MenuButton("Quit", 64, 400, 150,Color.WHITE);

		buttonBar.add(start, 0, 0);
		buttonBar.add(load, 1, 0);
		buttonBar.add(quit, 2, 0);
		
		root.getChildren().addAll(bg,buttonBar, title,creditIcon,helpIcon,settingIcon);
//		root.getChildren().addAll(bg,title,start);
		scene = new Scene(root, SceneController.getFullscreenWidth(), SceneController.getFullscreenHeight());
//		System.out.println(ClassLoader.getSystemResource("css/style.css"));
		scene.getStylesheets().add(ClassLoader.getSystemResource("css/style.css").toExternalForm());
	}

	public Scene getScene() {
		return this.scene;
	}

}
