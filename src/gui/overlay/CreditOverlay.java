package gui.overlay;

import gui.enity.MenuIcon;
import gui.enity.TextTitle;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
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

		TextTitle title = new TextTitle("Programing Methodology Project",Color.WHITE,FontWeight.BOLD,50,0,0);

		TextTitle by = new TextTitle("By",Color.WHITE,FontWeight.BOLD,50,0,0);

		TextTitle name1 = new TextTitle("Viritpol Limpawittayakul",Color.WHITE,FontWeight.BOLD,50,0,0);

		TextTitle name2 = new TextTitle("Samithiwat Boonchai",Color.WHITE,FontWeight.BOLD,50,0,0);

		
		VBox textBox = new VBox(title,by,name1,name2);
		textBox.setAlignment(Pos.CENTER);
		textBox.setSpacing(50);
		textBox.setLayoutX(300);
		textBox.setLayoutY(200);

		root.getChildren().addAll(mainBG, bg1, textBox, closeIcon);
	}
}
