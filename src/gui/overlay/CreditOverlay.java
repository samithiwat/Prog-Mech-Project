package gui.overlay;

import gui.enity.MenuIcon;
import javafx.event.EventHandler;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.AudioClip;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import logic.AudioLoader;
import javafx.scene.input.MouseEvent;

public class CreditOverlay extends Overlay {
	
	protected static final int HEIGHT = 800;
	protected static final int WIDTH = 1400;

	public CreditOverlay() {
		super((new AnchorPane()),WIDTH,HEIGHT,75,-800);

		setId("mainmenu-overlay");
		prefHeight(HEIGHT);
		prefWidth(WIDTH);
		
		Rectangle mainBG = new Rectangle(WIDTH, HEIGHT);
		mainBG.setFill(Color.rgb(57, 62, 70));
		Rectangle bg1 = new Rectangle(1200, 600);
		bg1.setFill(Color.rgb(20, 20, 20));
		bg1.setX(100);
		bg1.setY(100);

		MenuIcon closeIcon = new MenuIcon("img/Cross.png",1300,50);
		closeIcon.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				AudioClip effect = AudioLoader.clickEffect;
				effect.play();
				triggerOverlay(0,825,1000);
			}
		});

		Text title = new Text("Programing Methodology Project");
		title.setFont(Font.font(FONT_NAME, FontWeight.BOLD, 50));
		title.setFill(Color.WHITE);
		title.setX(300);
		title.setY(250);
		Text by = new Text("By");
		by.setFont(Font.font(FONT_NAME, FontWeight.BOLD, 50));
		by.setFill(Color.WHITE);
		by.setX(680);
		by.setY(350);
		Text name1 = new Text("Viritpol Limpawittayakul");
		name1.setFont(Font.font(FONT_NAME, FontWeight.BOLD, 50));
		name1.setFill(Color.WHITE);
		name1.setX(400);
		name1.setY(450);
		Text name2 = new Text("Samithiwat Boonchai");
		name2.setFont(Font.font(FONT_NAME, FontWeight.BOLD, 50));
		name2.setFill(Color.WHITE);
		name2.setX(400);
		name2.setY(550);

		root.getChildren().addAll(mainBG, bg1, title, by, name1, name2, closeIcon);
	}
}
