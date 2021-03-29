package gui;

import gui.enity.MenuButton;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import javafx.scene.media.AudioClip;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import logic.AudioLoader;
import logic.SceneController;

public class MainMenu implements Showable{
	
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
		title.setFont(Font.font("Bai Jamjuree",FontWeight.BOLD,120));
		title.setFill(Color.WHITE);
		title.setX(371);
		title.setY(130);
		
		MenuButton start = new MenuButton("Start",64,400,150);
		start.setLayoutX(65);
		start.setLayoutY(680);
		
		root.getChildren().addAll(bg,title,creditIcon,settingIcon,helpIcon,start);
		scene = new Scene(root,SceneController.getFullscreenWidth(),SceneController.getFullscreenHeight());
//		System.out.println(ClassLoader.getSystemResource("css/style.css"));
		scene.getStylesheets().add(ClassLoader.getSystemResource("css/style.css").toExternalForm());
	}
	
	public Scene getScene() {
		return this.scene;
	}
	
}
