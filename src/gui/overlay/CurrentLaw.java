package gui.overlay;

import component.law.LawCard;
import gui.entity.MenuIcon;
import gui.entity.StatusPane;
import gui.entity.TextTitle;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.FontWeight;
import logic.GameSetUp;
import logic.Util;

public class CurrentLaw extends Overlay {

	private static final int WIDTH_BG1 = 1400;
	private static final int HEIGHT_BG1 = 840;
	private static final int WIDTH_BG2 = 1260;
	private static final int HEIGHT_BG2 = 650;
	private static final int WIDTH_INFO_ROOT = 900;
	private static final int HEIGHT_INFO_ROOT = 200;
	private static final int IMG_WIDTH = 170;
	private static final int IMG_HEIGHT = 250;
	private static final int LINE_LENGTH = 38;

	private GridPane currentLawSlot;

	public CurrentLaw() {
		super((new Pane()), WIDTH_BG1, HEIGHT_BG1, 75, -860);
		setCursor(CURSOR_NORMAL);

// --------------------------------------- Scene Background -------------------------------------------------

		Rectangle bg1 = new Rectangle(WIDTH_BG1, HEIGHT_BG1);
		bg1.setFill(BG_COLOR);
		bg1.setOpacity(0.8);

		StackPane bg2 = new StackPane();
		bg2.setAlignment(Pos.CENTER);
		bg2.setId("law-overlay-bg");
		bg2.setPrefWidth(WIDTH_BG2);
		bg2.setPrefHeight(HEIGHT_BG2);
		bg2.setLayoutX(70);
		bg2.setLayoutY(110);

// -------------------------------------------- Title ---------------------------------------------------------	

		TextTitle title = new TextTitle("Current Law", Color.WHITE, FontWeight.BOLD, 90, 447, 90);

// ------------------------------------------ Close Icon -------------------------------------------------------

		MenuIcon closeIcon = new MenuIcon("img/icon/Cross.png", 1311, 45);

		closeIcon.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				EFFECT_MOUSE_CLICK.play();
				triggerOverlay(0, 875, 1500);
				StatusPane.triggerCurrentLaw();
			}
		});

// ------------------------------------------ Law Information --------------------------------------------------

		currentLawSlot = new GridPane();
		currentLawSlot.setAlignment(Pos.CENTER);
		currentLawSlot.setVgap(70);
		currentLawSlot.setHgap(50);
		currentLawSlot.setPadding(new Insets(50, 60, 50, 60));

// ------------------------------------- Add Scene's Component to Root -----------------------------------------

		bg2.getChildren().add(currentLawSlot);

		root.getChildren().addAll(bg1, bg2, title, closeIcon);

	}

// ---------------------------------------------------------------------------------------------------------------

	public void update() {

		currentLawSlot.getChildren().clear();

		for (int i = 0; i < GameSetUp.lawSlot.nSlot(); i++) {

			if (GameSetUp.lawSlot.getSlot(i).getLaw() != null) {

				LawCard law = GameSetUp.lawSlot.getSlot(i).getLaw();

				ImageView img = new ImageView(ClassLoader.getSystemResource(law.getImg_path()).toString());
				img.setFitWidth(IMG_WIDTH);
				img.setFitHeight(IMG_HEIGHT);
				currentLawSlot.add(img, 0, i);

//				TextTitle info = new TextTitle(law.getEffectCard(), Color.web("0x393E46"), FontWeight.BOLD, 36);
				TextTitle info = new TextTitle(Util.formatDescription(law.getEffectCard(), LINE_LENGTH), Color.web("0x393E46"), FontWeight.BOLD, 36);
				StackPane infoRoot = new StackPane(info);
				infoRoot.setAlignment(Pos.CENTER);
				infoRoot.setPrefHeight(HEIGHT_INFO_ROOT);
				infoRoot.setPrefWidth(WIDTH_INFO_ROOT);
				infoRoot.setId("current-law-overlay-info-root");
				currentLawSlot.add(infoRoot, 1, i);
			}

			else {
				ImageView img = new ImageView(ClassLoader.getSystemResource("img/card/Cardback.png").toString());
				img.setFitWidth(IMG_WIDTH);
				img.setFitHeight(IMG_HEIGHT);
				currentLawSlot.add(img, 0, i);

				TextTitle info = new TextTitle("Empty", Color.web("0x393E46"), FontWeight.BOLD, 36);
				StackPane infoRoot = new StackPane(info);
				infoRoot.setAlignment(Pos.CENTER);
				infoRoot.setPrefHeight(HEIGHT_INFO_ROOT);
				infoRoot.setPrefWidth(WIDTH_INFO_ROOT);
				infoRoot.setId("current-law-overlay-info-root");
				currentLawSlot.add(infoRoot, 1, i);
			}

		}
	}
	
}
