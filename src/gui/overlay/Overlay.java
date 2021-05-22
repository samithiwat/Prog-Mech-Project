package gui.overlay;

import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.scene.SubScene;
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
}
