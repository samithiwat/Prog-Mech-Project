package gui.overlay;

import gui.GameLobbyMenu;
import gui.entity.CharacterCard;
import gui.entity.MenuIcon;
import gui.entity.TextTitle;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.FontWeight;
import logic.AudioLoader;

public class CharacterSelectOverlay1 extends CharacterSelectOverlay {

	public CharacterSelectOverlay1() {

		MenuIcon changePageIcon = new MenuIcon("img/icon/Arrow.png", 1255, 700);
		changePageIcon.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				triggerOverlay(0, 825, 1);
				GameLobbyMenu.getOverlay2().triggerOverlay(0, 825, 1);
			}
		});

		TextTitle mrFox = new TextTitle("Mr.Red Fox", Color.WHITE, FontWeight.BOLD, 40, 160, 660);
		TextTitle ladyCollector = new TextTitle("Lady Collector", Color.WHITE, FontWeight.BOLD, 40, 565, 660);
		TextTitle blackSkull = new TextTitle("Black Skull", Color.WHITE, FontWeight.BOLD, 40, 1048, 660);

		CharacterCard mrFoxCard = new CharacterCard(1, "img/character/MrRedFoxCard.png", AudioLoader.mrFoxSelectBGM, 80, 150);

		mrFoxCard.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {

			}
		});

		CharacterCard ladyCollectorCard = new CharacterCard(2, "img/character/LadyCollectorCard.png", AudioLoader.ladySelectBGM,
				525, 150);
		CharacterCard BlackSkullCard = new CharacterCard(3, "img/character/BlackSkullCard.png", AudioLoader.blackSkullSelectBGM,
				970, 150);

		root.getChildren().addAll(mrFox, ladyCollector, blackSkull, mrFoxCard, ladyCollectorCard, BlackSkullCard,
				changePageIcon);
	}

}
