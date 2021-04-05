package gui.overlay;

import gui.GameLobbyMenu;
import gui.entity.CharacterCard;
import gui.entity.MenuIcon;
import gui.entity.TextTitle;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.FontWeight;
import logic.AudioLoader;
import update.CharacterSelectUpdate;

public class CharacterSelectOverlay2 extends CharacterSelectOverlay {

	public CharacterSelectOverlay2() {

		MenuIcon changePageIcon = new MenuIcon("img/icon/Arrow.png", 30, 700);
		changePageIcon.setRotate(180);
		changePageIcon.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				triggerOverlay(0, 825, 1);
				GameLobbyMenu.getOverlay1().triggerOverlay(0, 825, 1);
			}
		}); 

		TextTitle title = new TextTitle("Select Character", Color.WHITE, FontWeight.BOLD, 72, 402, 102);
		TextTitle sirThousand = new TextTitle("Sir Thousand Year", Color.WHITE, FontWeight.BOLD, 40, 80, 660);
		TextTitle sirTewada = new TextTitle("Sir Tewada", Color.WHITE, FontWeight.BOLD, 40, 596, 660);
		TextTitle sirTewadee = new TextTitle("Sir Tewadee", Color.WHITE, FontWeight.BOLD, 40, 1034, 660);

		CharacterCard sirThousandCard = new CharacterCard(3, "img/character/SirThousandYear.png",
				AudioLoader.sirThousandSelectBGM, 80, 150);
		CharacterCard sirTewadaCard = new CharacterCard(4, "img/character/SirTewada.png", AudioLoader.sirTewadaSelectBGM, 525,
				150);
		CharacterCard sirTewadeeCard = new CharacterCard(5, "img/character/SirTewadee.png", AudioLoader.sirTewadeeSelectBGM,
				970, 150);
		
		CharacterSelectUpdate.getOverlayTexts().add(sirThousand);
		CharacterSelectUpdate.getOverlayTexts().add(sirTewada);
		CharacterSelectUpdate.getOverlayTexts().add(sirTewadee);

		CharacterSelectUpdate.getCc().add(sirThousandCard);
		CharacterSelectUpdate.getCc().add(sirTewadaCard);
		CharacterSelectUpdate.getCc().add(sirTewadeeCard);
		
		root.getChildren().addAll(sirThousand, sirTewada, sirTewadee, sirThousandCard, sirTewadaCard, sirTewadeeCard,
				changePageIcon);
	}
}
