package gui.overlay;

import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.SubScene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.util.Duration;

public class Overlay extends SubScene implements Overlayable {

	protected Pane root;

	public Overlay(Pane root, int width, int height, int initX, int initY) {
		super(root, width, height);
		setFill(Color.TRANSPARENT);
		setRoot((Pane) this.getRoot());
		root.setBackground(new Background(new BackgroundFill(Color.TRANSPARENT, null, null)));
		setLayoutX(initX);
		setLayoutY(initY);
		setVisible(false);
		setESCKey();
	}

	public Pane getOverlayRoot() {
		return root;
	}

	public void setRoot(Pane root) {
		this.root = root;
	}

	public boolean triggerOverlay(int dx, int dy, int delay) {
		TranslateTransition tt = new TranslateTransition(Duration.millis(delay));
		boolean key;
		tt.setNode(this);
		if (!isVisible()) {
			tt.setToX(dx);
			tt.setToY(dy);
			setVisible(true);
			key = true;
		} else {
			key = false;
			tt.setToX(-dx);
			tt.setToY(-dy);

			Thread t = new Thread(() -> {

				try {
					Thread.sleep(delay);
				} catch (InterruptedException e) {

				}

				Platform.runLater(new Runnable() {

					@Override
					public void run() {
						setVisible(false);
					}
				});

			});
			t.start();

		}
		tt.play();
		return key;
	}

	public void setESCKey() {
		setOnKeyPressed(new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent event) {
				if (event.getCode().equals(KeyCode.ESCAPE)) {
					triggerOverlay(0, 875, 1500);
				}
			}
		});
	}
}
