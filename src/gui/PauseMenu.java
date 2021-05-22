package gui;

import gui.entity.MenuButton;
import gui.overlay.ConfirmOverlay;
import javafx.animation.Animation;
import javafx.animation.Transition;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.AudioClip;
import javafx.scene.paint.Color;
import javafx.util.Duration;
import logic.AudioLoader;
import logic.SceneController;
import update.AudioUpdate;
import update.CloseGame;

public class PauseMenu implements Sceneable {

	private static Scene scene;
	private ConfirmOverlay quitOverlay;

	public PauseMenu() {

		StackPane root = new StackPane();
		root.setAlignment(Pos.CENTER);

		quitOverlay = new ConfirmOverlay();
		setUpQuitOverlay();

		VBox menuPane = new VBox();
		menuPane.setSpacing(90);
		menuPane.setAlignment(Pos.CENTER);

		MenuButton continueButton = new MenuButton("Continue", 72, 500, 90, Color.web("0x393E46"));

		continueButton.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				AudioUpdate.changeEnv(MapOverview.getBgm());
				SceneController.goToMapOverview();
			}
		});

		MenuButton exitToMenu = new MenuButton("Exit to Menu", 72, 500, 90, Color.web("0x393E46"));

		exitToMenu.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				AudioUpdate.changeCharacter(null);
				AudioUpdate.changeEnv(StartMenu.getMenuThemeSong());
				SceneController.setScene((new MainMenu()).getScene());
			}
		});

		MenuButton quit = new MenuButton("Quit", 72, 500, 90, Color.web("0x393E46"));

		quit.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				EFFECT_MOUSE_CLICK.play();
				quitOverlay.triggerOverlay(0, 1000, 1000);
			}

		});

		menuPane.getChildren().addAll(continueButton, exitToMenu, quit);

		ImageView coconut = new ImageView(ClassLoader.getSystemResource("img/icon/BigCoconut.png").toString());

		Animation animation = new Transition() {

			{
				setCycleDuration(Duration.millis(20000));
			}

			@Override
			protected void interpolate(double frac) {
				final int degree = Math.round(360 * (float) frac);
				final int posX = Math.round(SceneController.getFullscreenWidth() * (float) frac);
				coconut.setRotate(degree);
				coconut.setLayoutX(posX);
			}
		};
		animation.setCycleCount(Animation.INDEFINITE);
		animation.play();

		root.getChildren().addAll(coconut, menuPane, quitOverlay);

		scene = new Scene(root, SceneController.getFullscreenWidth(), SceneController.getFullscreenHeight());
		scene.setCursor(MOUSE_NORMAL);
	}

	@Override
	public Scene getScene() {
		return scene;
	}
	
	private void setUpQuitOverlay() {
		quitOverlay.getTextLine1().setText("Do you want to leave?");
		quitOverlay.getTextLine2().setText("We will miss you.");
		quitOverlay.getYes().setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				AudioClip effect = AudioLoader.quitSound;
				effect.play();
				quitOverlay.getTextLine1().setText("We hope you will be back :)");
				quitOverlay.getTextLine2().setText("");
				quitOverlay.getYes().setDisable(true);
				quitOverlay.getNo().setDisable(true);
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

		quitOverlay.getNo().setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				AudioClip effect = AudioLoader.clickEffect;
				effect.play();
				quitOverlay.triggerOverlay(0, 1000, 1000);
				Thread t = new Thread(() -> {
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {

					}
					Platform.runLater(new Runnable() {

						@Override
						public void run() {
							quitOverlay.setVisible(false);
						}
					});

				});
				t.start();
			}
		});
	}
}
