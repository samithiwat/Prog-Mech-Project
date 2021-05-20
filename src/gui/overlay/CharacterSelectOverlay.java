package gui.overlay;

import gui.entity.MenuIcon;
import gui.entity.TextTitle;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.FontWeight;
import update.CharacterSelectUpdate;

public abstract class CharacterSelectOverlay extends Overlay {

	private static final int WIDTH = 1400;
	private static final int HEIGHT = 800;

	public CharacterSelectOverlay() {
		super((new Pane()), WIDTH, HEIGHT, 75, -850);
		setCursor(CURSOR_NORMAL);

// -------------------------------------------- Scene Background --------------------------------------------------------------	

		Rectangle bg = new Rectangle(WIDTH, HEIGHT);
		bg.setFill(BG_COLOR);
		bg.setId("overlay-bg");

// -------------------------------------------- Close Icon ---------------------------------------------------------------------

		MenuIcon closeIcon = new MenuIcon("img/icon/Cross.png", 1311, 45);

		closeIcon.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				EFFECT_MOUSE_CLICK.play();
				triggerOverlay(0, 875, 1500);
				CharacterSelectUpdate.closeUpdate();
			}
		});

// -------------------------------------------- Overlay Text ---------------------------------------------------------------------

		TextTitle title = new TextTitle("Select Character", Color.WHITE, FontWeight.BOLD, 72, 402, 102);
		TextTitle info = new TextTitle("Right Click for more information", Color.WHITE, FontWeight.MEDIUM, 48, 356,
				750);

		root.getChildren().addAll(bg, info, title, closeIcon);
	}

}
