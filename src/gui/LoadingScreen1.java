package gui;

import javafx.animation.Animation;
import javafx.animation.Transition;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.util.Duration;
import logic.SceneController;
import sprites.AnimationSprites;


public class LoadingScreen1 implements Showable {
	private Scene scene;

	public LoadingScreen1() {
		AnchorPane root = new AnchorPane();

		ImageView coconutRolling = new ImageView(ClassLoader.getSystemResource("img/CoconutRolling.png").toString());
		coconutRolling.setX(1000);
		coconutRolling.setY(720);
		AnimationSprites coconutAnimation = new AnimationSprites(coconutRolling, 1500, 0, 0, 100, 100, 0, 50, 0, 14);
		coconutAnimation.setCycleCount(Animation.INDEFINITE);
		coconutAnimation.play();

		String loading = "Now Loading...";
		Text text = new Text("");
		text.setX(1120);
		text.setY(790);
		text.setFill(Color.WHITE);
		text.setFont(Font.font("Bai Jamjuree", FontWeight.BOLD, 48));

		Animation animation = new Transition() {

			{
				setCycleDuration(Duration.millis(2200));
			}

			@Override
			protected void interpolate(double frac) {
				final int length = loading.length();
				final int n = Math.round(length * (float) frac);
//				System.out.println("n :"+n);
				text.setText(loading.substring(0, n));
			}

		};
		animation.setAutoReverse(true);
		animation.setCycleCount(Animation.INDEFINITE);
		animation.play();

		Rectangle bg = new Rectangle(SceneController.getFullscreenWidth(), SceneController.getFullscreenHeight());
		bg.setFill(Color.web("0xF2A388"));

		root.getChildren().addAll(bg, coconutRolling, text);
		scene = new Scene(root, SceneController.getFullscreenWidth(), SceneController.getFullscreenHeight());
	}

	public Scene getScene() {
		return scene;
	}
}
