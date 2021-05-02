package gui;

import javafx.animation.Animation;
import javafx.animation.Transition;
import javafx.application.Preloader;
import javafx.application.Preloader.StateChangeNotification.Type;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import logic.SceneController;
import sprites.AnimationSprites;
import sprites.AnimationTimerSprites;


public class LoadingScreen2 implements Sceneable {

	private Stage stage;
	
	private Scene scene;

	public LoadingScreen2() {
		AnchorPane root = new AnchorPane();

		String loading = "Now Loading...";
		Text text = new Text("");
		text.setX(1120);
		text.setY(790);
		text.setFill(Color.web("0x393E46"));
		text.setFont(Font.font("Bai Jamjuree", FontWeight.BOLD, 48));

		Animation animation = new Transition() {

			{
				setCycleDuration(Duration.millis(2200));
			}

			@Override
			protected void interpolate(double frac) {

				final int length = loading.length();
				final int n = Math.round(length * (float) frac);
				text.setText(loading.substring(0, n));
				
				//System.out.println(frac);
			}

		};
		animation.setAutoReverse(true);
		animation.setCycleCount(Animation.INDEFINITE);
		animation.play();
		
		System.out.println("loading...");
		
		ImageView treeFilling = new ImageView(ClassLoader.getSystemResource("img/sprites/TreeFilling.png").toString());
		treeFilling.setViewport(new Rectangle2D(0, 0, 140, 140));
		treeFilling.setX(950);
		treeFilling.setY(670);
		AnimationSprites treeFillingAnimation = new AnimationSprites(treeFilling, 1500, 0, 0, 140, 140, 0, 50, 0, 7);
		treeFillingAnimation.setAutoReverse(true);
		treeFillingAnimation.setCycleCount(Animation.INDEFINITE);
		treeFillingAnimation.play();

		Rectangle bg = new Rectangle(SceneController.getFullscreenWidth(), SceneController.getFullscreenWidth());
		bg.setFill(Color.web("0xFAE5CB"));

		root.getChildren().addAll(bg, text, treeFilling);
		scene = new Scene(root, SceneController.getFullscreenWidth(), SceneController.getFullscreenHeight());

	}

	public Scene getScene() {
		return scene;
	}

}
