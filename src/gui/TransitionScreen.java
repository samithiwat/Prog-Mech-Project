package gui;

import javafx.animation.AnimationTimer;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import logic.SceneController;

public class TransitionScreen implements Showable {

	Scene scene;

	private static AnimationTimer countDown;
	private static long lastTimeTrigger;
	private static int count;

	public TransitionScreen() {

		AnchorPane root = new AnchorPane();

		Rectangle bg = new Rectangle(SceneController.getFullscreenWidth(), SceneController.getFullscreenHeight());
		//bg.setY(210);
		bg.setFill(Color.web("0xF2A388"));

		ImageView coconut = new ImageView(ClassLoader.getSystemResource("img/BiggerCoconut1.png").toString());
		coconut.setViewport(new Rectangle2D(0, 0, 900, 900));
		coconut.setX(330);
		coconut.setY(0);
		AnimationSprites coconutBigger = new AnimationSprites(coconut, 1100, 0, 0, 900, 900, 0, 150, 0, 13);
		coconutBigger.play();

		lastTimeTrigger = -1;

		countDown = new AnimationTimer() {

			@Override
			public void handle(long now) {

				lastTimeTrigger = (lastTimeTrigger < 0 ? now : lastTimeTrigger);

				if (now - lastTimeTrigger >= 500000000) {
					if (count++ == 3) {
						ImageView coconutRollingOut = new ImageView(
								ClassLoader.getSystemResource("img/CoconutRollingOut.png").toString());
						coconutRollingOut.setViewport(new Rectangle2D(0, 0, 1100, 900));
						coconutRollingOut.setX(330);
						coconutRollingOut.setY(0);
						AnimationSprites coconutRollingAnimation = new AnimationSprites(coconutRollingOut, 2000, 0, 0,
								1100, 900, 0, 200, 0, 19);
						coconutRollingAnimation.play();
						root.getChildren().add(coconutRollingOut);
						
					}
					System.out.println(count);
					if(count == 250) {
						System.out.println("Stop!");
						countDown.stop();
						SceneController.setScene((new MainMenu()).getScene());
					}
				}

			}

		};

		countDown.start();

		root.getChildren().addAll(bg, coconut);
		scene = new Scene(root, SceneController.getFullscreenWidth(), SceneController.getFullscreenWidth());
	}

	@Override
	public Scene getScene() {
		return scene;
	}

}
