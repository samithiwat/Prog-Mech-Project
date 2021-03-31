package gui.overlay;

import gui.GameSettingMenu;
import gui.entity.CharacterCard;
import gui.entity.MenuIcon;
import gui.entity.TextTitle;
import javafx.event.EventHandler;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.FontWeight;
import logic.AudioLoader;

public class CharacterSelectOverlay1 extends Overlay {

	private static final int WIDTH = 1400;
	private static final int HEIGHT = 800;

	public CharacterSelectOverlay1() {
		super((new AnchorPane()), WIDTH, HEIGHT, 75, -800);

		setId("character-select-overlay");

		Rectangle bg = new Rectangle(WIDTH, HEIGHT);
		bg.setFill(Color.web("0x393E46"));
		// bg.setStyle("-fx-fill: rgba(57,62,70,0.5)");

		MenuIcon closeIcon = new MenuIcon("img/Cross.png", 1311, 45);

		closeIcon.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent arg0) {
				triggerOverlay(0, 825, 1000);
			}
		});

		MenuIcon changePageIcon = new MenuIcon("img/Arrow.png",1255,700);
		changePageIcon.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent arg0) {
				triggerOverlay(0, 825, 1);
				GameSettingMenu.getOverlay2().triggerOverlay(0, 825, 1);
			}
		}); 
		
		TextTitle title = new TextTitle("Select Character", Color.WHITE, FontWeight.BOLD, 72, 402, 102);
		TextTitle mrFox = new TextTitle("Mr.Red Fox", Color.WHITE, FontWeight.BOLD, 40, 160, 660);
		TextTitle ladyCollector = new TextTitle("Lady Collector", Color.WHITE, FontWeight.BOLD, 40, 565, 660);
		TextTitle blackSkull = new TextTitle("Black Skull", Color.WHITE, FontWeight.BOLD, 40, 1048, 660);

		CharacterCard mrFoxCard = new CharacterCard("img/MrRedFoxCard.png", AudioLoader.mrFoxSelectBGM, 80, 150);
		CharacterCard ladyCollectorCard = new CharacterCard("img/LadyCollectorCard.png", AudioLoader.ladySelectBGM, 525,
				150);
		CharacterCard BlackSkullCard = new CharacterCard("img/BlackSkullCard.png", AudioLoader.blackSkullSelectBGM, 970,
				150);

		root.getChildren().addAll(bg, title, mrFox, ladyCollector, blackSkull, mrFoxCard, ladyCollectorCard,
				BlackSkullCard, closeIcon, changePageIcon);
	}

}
