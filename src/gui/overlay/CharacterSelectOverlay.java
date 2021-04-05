package gui.overlay;

import java.util.ArrayList;

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
import update.CharacterSelectUpdate;

public abstract class CharacterSelectOverlay extends Overlay {

	private static final int WIDTH = 1400;
	private static final int HEIGHT = 800;

	public CharacterSelectOverlay() {
		super((new AnchorPane()), WIDTH, HEIGHT, 75, -800);
		//setId("overlay");
		setCursor(CURSOR_NORMAL);

// -------------------------------------------- Scene Background --------------------------------------------------------------	
		
		Rectangle bg = new Rectangle(WIDTH, HEIGHT);
		bg.setFill(Color.web("0x393E46"));
		bg.setId("overlay-bg");
		

// -------------------------------------------- Close Icon ---------------------------------------------------------------------
		
		MenuIcon closeIcon = new MenuIcon("img/icon/Cross.png", 1311, 45);

		closeIcon.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				CLICK_EFFECT.play();
				triggerOverlay(0, 825, 1000);
				CharacterSelectUpdate.closeUpdate();
			}
		}); 

		TextTitle title = new TextTitle("Select Character", Color.WHITE, FontWeight.BOLD, 72, 402, 102);
		
		root.getChildren().addAll(bg, title, closeIcon);
	}

}
