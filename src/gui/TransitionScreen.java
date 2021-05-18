package gui;

import javafx.application.Platform;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.AudioClip;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import logic.AudioLoader;
import logic.SceneController;
import sprites.AnimationSprites;

public class TransitionScreen implements Sceneable {

	Scene scene;

	public TransitionScreen() {

		AnchorPane root = new AnchorPane();
		root.setMouseTransparent(true);

		Rectangle bg = new Rectangle(SceneController.getFullscreenWidth(), SceneController.getFullscreenHeight());
		bg.setFill(Color.web("0xF2A388"));
		AudioClip effect = AudioLoader.popSound;
		effect.play();
		ImageView coconut = new ImageView(ClassLoader.getSystemResource("img/sprites/BiggerCoconut.png").toString());
		coconut.setViewport(new Rectangle2D(0, 0, 900, 900));
		coconut.setX(330);
		coconut.setY(0);
		AnimationSprites coconutBigger = new AnimationSprites(coconut, 1100, 0, 0, 900, 900, 0, 150, 0, 13);
		coconutBigger.play();

		Thread t = new Thread(() -> {

			try {
				Thread.sleep(975);
			} catch (InterruptedException e) {

			}

			Platform.runLater(new Runnable() {

				@Override
				public void run() {
					ImageView coconutRollingOut = new ImageView(
							ClassLoader.getSystemResource("img/sprites/CoconutRollingOut.png").toString());
					coconutRollingOut.setViewport(new Rectangle2D(0, 0, 1150, 900));
					coconutRollingOut.setX(330);
					coconutRollingOut.setY(0);
					AnimationSprites coconutRollingAnimation = new AnimationSprites(coconutRollingOut, 2000, 0, 0, 1210,
							900, 0, 90, 0, 23);
					coconutRollingAnimation.play();
					root.getChildren().add(coconutRollingOut);

					Thread t = new Thread(() -> {
						try {
							Thread.sleep(2000);
						} catch (InterruptedException e) {

						}

						Platform.runLater(new Runnable() {

							@Override
							public void run() {
								SceneController.setScene((new MainMenu()).getScene());
							}
						});

					});
					t.start();
				}
			});
		});
		t.start();
		root.getChildren().addAll(bg, coconut);
		scene = new Scene(root, SceneController.getFullscreenWidth(), SceneController.getFullscreenWidth());
	}

	@Override
	public Scene getScene() {
		return scene;
	}

}
