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

public class CharacterSelectOverlay2 extends Overlay {

	private static final int WIDTH = 1400;
	private static final int HEIGHT = 800;

	public CharacterSelectOverlay2() {
		super((new AnchorPane()), WIDTH, HEIGHT, 75, -800);

		setId("character-select-overlay");

		Rectangle bg = new Rectangle(WIDTH, HEIGHT);
		bg.setFill(Color.web("0x393E46"));

		MenuIcon closeIcon = new MenuIcon("img/Cross.png", 1311, 45);
		closeIcon.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent arg0) {
				triggerOverlay(0, 825, 1000);
			}
		});

		MenuIcon changePageIcon = new MenuIcon("img/Arrow.png", 30, 700);
		changePageIcon.setRotate(180);
		changePageIcon.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent arg0) {
				triggerOverlay(0, 825, 1);
				GameSettingMenu.getOverlay1().triggerOverlay(0, 825, 1);
			}
		});

		TextTitle title = new TextTitle("Select Character", Color.WHITE, FontWeight.BOLD, 72, 402,102);
		TextTitle sirThousand = new TextTitle("Sir Thousand Year", Color.WHITE, FontWeight.BOLD, 40, 80, 670);
		TextTitle sirTewada = new TextTitle("Sir Tewada", Color.WHITE, FontWeight.BOLD, 40, 596, 670);
		TextTitle sirTewadee = new TextTitle("Sir Tewadee", Color.WHITE, FontWeight.BOLD, 40, 1034, 670);

		CharacterCard sirThousandCard = new CharacterCard("img/SirThousandYearCard.png",
				AudioLoader.sirThousandSelectBGM, 80, 150);
		CharacterCard sirTewadaCard = new CharacterCard("img/SirTewadaCard.png", AudioLoader.sirTewadaSelectBGM, 525,
				150);
		CharacterCard sirTewadeeCard = new CharacterCard("img/SirTewadeeCard.png", AudioLoader.sirTewadeeSelectBGM, 970,
				150);

		root.getChildren().addAll(bg, title, sirThousand, sirTewada, sirTewadee, sirThousandCard, sirTewadaCard,
				sirTewadeeCard, closeIcon, changePageIcon);
	}
}
