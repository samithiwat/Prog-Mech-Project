package gui;

import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import logic.SceneController;


public class TransitionScreen implements Showable {

	Scene scene;

	public TransitionScreen() {

		AnchorPane root = new AnchorPane();

//		ImageView coconut = new ImageView(ClassLoader.getSystemResource("img/BiggerCoconut1.png").toString());
//		coconut.setViewport(new Rectangle2D(0, 0, 900, 900));
//		coconut.setX(330);
//		coconut.setY(200);
//		AnimationSprites coconutBigger = new AnimationSprites(coconut, 50, 0, 0, 900, 900, 0, 150, 0, 14);
//		coconutBigger.play();
		
		ImageView coconut = new ImageView(ClassLoader.getSystemResource("img/BiggerCoconut2.png").toString());
		coconut.setViewport(new Rectangle2D(0, 0, 900, 900));
		coconut.setX(330);
		coconut.setY(200);
		AnimationSprites coconutBigger = new AnimationSprites(coconut, 50, 0, 0, 900, 900, 0, 150, 0, 14);
		coconutBigger.play();

		root.getChildren().add(coconut);
		scene = new Scene(root, SceneController.getFullscreenWidth(), SceneController.getFullscreenWidth());
	}

	@Override
	public Scene getScene() {
		return scene;
	}

}
