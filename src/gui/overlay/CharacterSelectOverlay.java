package gui.overlay;

import gui.GameSettingMenu;
import gui.entity.MenuIcon;
import gui.entity.TextTitle;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.FontWeight;

public abstract class CharacterSelectOverlay extends Overlay {

	private static final int WIDTH = 1400;
	private static final int HEIGHT = 800;

	public CharacterSelectOverlay() {
		super((new AnchorPane()), WIDTH, HEIGHT, 75, -800);

		setId("character-select-overlay");
		setCursor(CURSOR_NORMAL);

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

		TextTitle title = new TextTitle("Select Character", Color.WHITE, FontWeight.BOLD, 72, 402, 102);
		root.getChildren().addAll(bg, title, closeIcon);
	}

}
