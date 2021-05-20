package gui.overlay;

import gui.entity.MenuButton;
import gui.entity.TextTitle;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.media.AudioClip;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.FontWeight;
import logic.AudioLoader;
import update.CloseGame;

public class QuitOverlay extends Overlay {

	private final static int WIDTH = 1000;
	private final static int HEIGHT = 500;

	public QuitOverlay() {
		super((new Pane()), WIDTH, HEIGHT, 250, -800);
		prefHeight(HEIGHT);
		prefWidth(WIDTH);
		setId("mainmenu-overlay");

		Rectangle bg = new Rectangle(WIDTH, HEIGHT);
		bg.setId("overlay-bg");

		VBox textBox = new VBox();
		textBox.setAlignment(Pos.CENTER);

		TextTitle line1 = new TextTitle("Do you want to leave?", Color.WHITE, FontWeight.BOLD, 50, 0, 0);

		TextTitle line2 = new TextTitle("We will miss you", Color.WHITE, FontWeight.BOLD, 50, 0, 0);

		textBox.getChildren().addAll(line1, line2);
		textBox.setLayoutX(250);
		textBox.setLayoutY(100);

		MenuButton yes = new MenuButton("Yes", 50, 400, 100, Color.WHITE, 68, 350);

		MenuButton no = new MenuButton("No", 50, 400, 100, Color.WHITE, 533, 350);
		no.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				AudioClip effect = AudioLoader.clickEffect;
				effect.play();
				triggerOverlay(0, 1000, 1000);
				setVisible(false);
				Thread t = new Thread(() -> {
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {

					}
					Platform.runLater(new Runnable() {

						@Override
						public void run() {
							CloseGame.backed();
						}
					});

				});
				t.start();
			}

		});

		yes.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				AudioClip effect = AudioLoader.quitSound;
				effect.play();
				line1.setText("We hope you will be back :)");
				line1.setX(180);
				line1.setY(150);
				root.getChildren().remove(textBox);
				root.getChildren().add(line1);
				yes.setDisable(true);
				no.setDisable(true);
				Thread t = new Thread(() -> {
					try {
						Thread.sleep(1500);
					} catch (InterruptedException e) {

					}

					Platform.runLater(new Runnable() {

						@Override
						public void run() {
							CloseGame.setIsCloseGame(true);
							CloseGame.update();
						}
					});
				});
				t.start();
			}
		});

		root.getChildren().addAll(bg, yes, no, textBox);
	}

}
