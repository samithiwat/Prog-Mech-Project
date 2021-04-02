package gui.overlay;

import gui.GameSettingMenu;
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

public class CharacterSelectOverlay2 extends CharacterSelectOverlay{
	
	public CharacterSelectOverlay2() {

		MenuIcon changePageIcon = new MenuIcon("img/Arrow.png", 30, 700);
		changePageIcon.setRotate(180);
		changePageIcon.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				triggerOverlay(0, 825, 1);
				GameSettingMenu.getOverlay1().triggerOverlay(0, 825, 1);
			}
		});

		TextTitle sirThousand = new TextTitle("Sir Thousand Year", Color.WHITE, FontWeight.BOLD, 40, 80, 660);
		TextTitle sirTewada = new TextTitle("Sir Tewada", Color.WHITE, FontWeight.BOLD, 40, 596, 660);
		TextTitle sirTewadee = new TextTitle("Sir Tewadee", Color.WHITE, FontWeight.BOLD, 40, 1034, 660);

		CharacterCard sirThousandCard = new CharacterCard("SirThousand","img/SirThousandYearCard.png",
				AudioLoader.sirThousandSelectBGM, 80, 150);
		CharacterCard sirTewadaCard = new CharacterCard("SirTewada","img/SirTewadaCard.png", AudioLoader.sirTewadaSelectBGM, 525,
				150);
		CharacterCard sirTewadeeCard = new CharacterCard("SirTewadee","img/SirTewadeeCard.png", AudioLoader.sirTewadeeSelectBGM, 970,
				150);

		root.getChildren().addAll( sirThousand, sirTewada, sirTewadee, sirThousandCard, sirTewadaCard,
				sirTewadeeCard, changePageIcon);
	}
}
