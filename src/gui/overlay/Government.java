package gui.overlay;

import component.law.LawCard;
import gui.entity.LawCardIcon;
import gui.entity.LawCardSlot;
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
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.FontWeight;
import logic.GameSetUp;

public class Government extends Overlay {

	private static final int WIDTH = 1400;
	private static final int HEIGHT = 800;
	private static final int WIDTH_ACTIVED_LAW = 350;
	private static final int HEIGHT_ACTIVE_LAW = 650;
	private static final int ACTIVED_LAW_IMG_WIDTH = 170;
	private static final int ACTIVED_LAW_IMG_HEIGHT = 250;

	private VBox activedLaw;

	public Government() {
		super((new Pane()), WIDTH, HEIGHT, 75, -850);
		setCursor(CURSOR_NORMAL);

// ------------------------------------ Scene Background -------------------------------------
		Rectangle bg = new Rectangle(WIDTH, HEIGHT);
		bg.setFill(BG_COLOR);

		activedLaw = new VBox();
		activedLaw.setLayoutX(70);
		activedLaw.setLayoutY(110);
		activedLaw.setPrefWidth(WIDTH_ACTIVED_LAW);
		activedLaw.setPrefHeight(HEIGHT_ACTIVE_LAW);
		activedLaw.setId("law-overlay-bg");
		activedLaw.setSpacing(50);
		activedLaw.setPadding(new Insets(50, 90, 50, 59));

		LawCardSlot lawCardSlot = new LawCardSlot();
		lawCardSlot.setLayoutX(460);
		lawCardSlot.setLayoutY(110);

		LawCardIcon.setImgWidth(ACTIVED_LAW_IMG_WIDTH);
		LawCardIcon.setImgHeight(ACTIVED_LAW_IMG_HEIGHT);

// --------------------------------------------- Title -------------------------------------------------------

		TextTitle title = new TextTitle("Government", Color.WHITE, FontWeight.BOLD, 90, 522, 90);

// ------------------------------------------ Close Icon -------------------------------------------------------

		MenuIcon closeIcon = new MenuIcon("img/icon/Cross.png", 1311, 45);

		closeIcon.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				EFFECT_MOUSE_CLICK.play();
				triggerOverlay(0, 875, 1500);
				StatusPane.triggerGovernment();
			}
		});

// ------------------------------------------ Add Overlay's Component -------------------------------------

		root.getChildren().addAll(bg, activedLaw, lawCardSlot, title, closeIcon);

	}

	public void updateActivedLaw() {
		activedLaw.getChildren().clear();

		for (int i = 0; i < GameSetUp.lawSlot.nSlot(); i++) {

			if (GameSetUp.lawSlot.getSlot(i) != null) {

				LawCard law = GameSetUp.lawSlot.getSlot(i);

				LawCardIcon img = new LawCardIcon(law);
				activedLaw.getChildren().add(img);
			}

			else {
				LawCardIcon img = new LawCardIcon(null);
				activedLaw.getChildren().add(img);
			}

		}

	}

}
