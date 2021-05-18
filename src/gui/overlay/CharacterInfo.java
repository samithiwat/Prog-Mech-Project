package gui.overlay;

import gui.entity.MenuIcon;
import gui.entity.TextTitle;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.FontWeight;
import update.CharacterSelectUpdate;

public class CharacterInfo extends Overlay {

	private static final int WIDTH = 1200;
	private static final int HEIGHT = 550;
	private final int WIDTH_BG = 1000;
	private final int HEIGHT_BG = 450;
	private static Rectangle bg2;

	public CharacterInfo() {
		super(new AnchorPane(), WIDTH, HEIGHT, 170, -675);

// ------------------------------------------------- Set Overlay Text --------------------------------------------------------

		TextTitle winCondition = new TextTitle("Win Condition", Color.WHITE, FontWeight.BOLD, 48, 438, 145);
		TextTitle characterCondition = new TextTitle("", Color.WHITE, FontWeight.NORMAL, 48, 438, 245);
		TextTitle passiveText = new TextTitle("Passive", Color.WHITE, FontWeight.BOLD, 48, 511, 345);
		TextTitle characterPassive = new TextTitle("", Color.WHITE, FontWeight.NORMAL, 48, 511, 445);

// ------------------------------------------------- Set Overlay Background --------------------------------------------------------		
		Rectangle bg1 = new Rectangle(WIDTH, HEIGHT);
		bg1.setFill(BG_COLOR);

		bg2 = new Rectangle(WIDTH_BG, HEIGHT_BG);
		bg2.setX(100);
		bg2.setY(50);

// ------------------------------------------------- Set Overlay Close Icon --------------------------------------------------------

		MenuIcon closeIcon = new MenuIcon("img/icon/Cross.png", 1138, 18);
		closeIcon.setFitHeight(40);
		closeIcon.setFitWidth(40);

		closeIcon.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				EFFECT_MOUSE_CLICK.play();
				triggerOverlay(0, 825, 1000);
				CharacterSelectUpdate.closeUpdate();
				Thread t = new Thread(() -> {
					try {

						Thread.sleep(1000);

					} catch (InterruptedException e) {

					}
					Platform.runLater(new Runnable() {

						@Override
						public void run() {
							CharacterSelectUpdate.overlayUpdate();
						}
					});
				});
				t.start();

			}
		});

		CharacterSelectUpdate.getOverlayInfoTexts().add(characterCondition);
		CharacterSelectUpdate.getOverlayInfoTexts().add(characterPassive);

		root.getChildren().addAll(bg1, bg2, closeIcon, winCondition, characterCondition, passiveText, characterPassive);

	}

	public static Rectangle getBg2() {
		return bg2;
	}

	public static void setBg2(Rectangle bg2) {
		CharacterInfo.bg2 = bg2;
	}

}
