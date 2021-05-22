package gui;

import java.util.ArrayList;

import gui.entity.Clickable;
import gui.entity.MenuButton;
import gui.entity.MenuIcon;
import gui.entity.TextTitle;
import gui.overlay.ConfirmOverlay;
import gui.overlay.CreditOverlay;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.media.AudioClip;
import javafx.scene.paint.Color;
import javafx.scene.text.FontWeight;
import logic.AudioLoader;
import logic.GameSetUp;
import logic.SceneController;
import update.AudioUpdate;
import update.CloseGame;

public class MainMenu implements Sceneable {

	private static Scene scene;
	private static ArrayList<Clickable> components;
	private ConfirmOverlay quitOverlay;
	private ConfirmOverlay newGameConfirm;

	public MainMenu() {

		AnchorPane root = new AnchorPane();

// -------------------------------------------- Init Overlay --------------------------------------------------------------------

		CreditOverlay creditOverlay = new CreditOverlay();
		quitOverlay = new ConfirmOverlay("button-release-style", "button-hold-style");
		setUpQuitOverlay();

// -------------------------------------------- Scene Background --------------------------------------------------------------	

		ImageView bg = new ImageView(ClassLoader.getSystemResource("img/background/MainMenuBG.png").toString());

// -------------------------------------------- Scene Icon --------------------------------------------------------------------

		MenuIcon creditIcon = new MenuIcon("img/icon/About.png", 75, 60);
		creditIcon.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				AudioClip effect = AudioLoader.clickEffect;
				effect.play();
				creditOverlay.triggerOverlay(0, 825, 1000);
			}
		});
		MenuIcon helpIcon = new MenuIcon("img/icon/Help.png", 223, 60);

		MenuIcon settingIcon = new MenuIcon("img/icon/Setting.png", 1300, 55);

// -------------------------------------------- Scene Text --------------------------------------------------------------------

		TextTitle title = new TextTitle("Coconut Island", Color.WHITE, FontWeight.BOLD, 120, 371, 130);

// -------------------------------------------- Menu Button --------------------------------------------------------------------		

		GridPane buttonBar = new GridPane();
		buttonBar.setHgap(100);
		buttonBar.setLayoutX(70);
		buttonBar.setLayoutY(680);

		MenuButton continueButton = new MenuButton("Continue", 64, 400, 150, Color.WHITE, 0, 0);

		continueButton.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				EFFECT_MOUSE_CLICK.play();
				AudioUpdate.changeCharacter(GameSetUp.thisTurn.getBgm());
				AudioUpdate.changeEnv(MapOverview.getBgm());
				SceneController.goToMapOverview();
			}
		});

		MenuButton newGame = new MenuButton("New Game", 64, 400, 150, Color.WHITE, 0, 0);

		MenuButton quit = new MenuButton("Quit", 64, 400, 150, Color.WHITE, 0, 0);

		quit.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				EFFECT_MOUSE_CLICK.play();
				quitOverlay.triggerOverlay(0, 1000, 1000);
				continueButton.setDisable(true);
				newGame.setDisable(true);
				quit.setDisable(true);
				creditIcon.setDisable(true);
				helpIcon.setDisable(true);
				settingIcon.setDisable(true);
			}

		});

		newGame.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				if (continueButton.isDisable()) {
					EFFECT_MOUSE_CLICK.play();
					SceneController.setScene(SceneController.getGameSettingMenu());
					AudioUpdate.changeEnv(GameLobbyMenu.getBGM());
				} else {
					newGameConfirm.triggerOverlay(0, 1000, 1000);
					continueButton.setDisable(true);
					newGame.setDisable(true);
					quit.setDisable(true);
					creditIcon.setDisable(true);
					helpIcon.setDisable(true);
					settingIcon.setDisable(true);
				}
			}
		});

		buttonBar.add(continueButton, 0, 0);
		buttonBar.add(newGame, 1, 0);
		buttonBar.add(quit, 2, 0);

// -------------------------------------------- Add Icon and Button for Close Game Update --------------------------------------------------------------------	

		components = new ArrayList<Clickable>();
		components.add(creditIcon);
		components.add(helpIcon);
		components.add(settingIcon);
		components.add(continueButton);
		components.add(newGame);
		components.add(quit);

// --------------------------------------------- Set Scene --------------------------------------------------------------------

		root.getChildren().addAll(bg, buttonBar, title, creditIcon, helpIcon, settingIcon, creditOverlay, quitOverlay);

		if (SceneController.getMapOverView() == null) {
			continueButton.setDisable(true);
			continueButton.setId("button-disable-style");
		} else {
			newGameConfirm = new ConfirmOverlay("button-release-style", "button-hold-style");
			setUpNewGameConfirm();
			root.getChildren().add(newGameConfirm);
		}

		scene = new Scene(root, FULLSCREEN_WIDTH, FULLSCREEN_HEIGHT);
		scene.setCursor(MOUSE_NORMAL);
		scene.getStylesheets().add(ClassLoader.getSystemResource("css/mainmenu-style.css").toExternalForm());

	}

// -------------------------------------------- Getter and Setter --------------------------------------------------------------------

	static public ArrayList<Clickable> getComponent() {
		return components;
	}

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
							CloseGame.backed();
						}
					});

				});
				t.start();
			}
		});
	}

	private void setUpNewGameConfirm() {
		newGameConfirm.getTextLine1().setText("Do you sure to start new game?");
		newGameConfirm.getTextLine2().setText("Your process will loss.");

		newGameConfirm.getYes().setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				EFFECT_MOUSE_CLICK.play();
				SceneController.resetGame();
				SceneController.setScene(SceneController.getGameSettingMenu());
				AudioUpdate.changeEnv(GameLobbyMenu.getBGM());
			}
		});

		newGameConfirm.getNo().setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				newGameConfirm.triggerOverlay(0, 1000, 1000);
				Thread t = new Thread(() -> {
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {

					}
					Platform.runLater(new Runnable() {

						@Override
						public void run() {
							newGameConfirm.setVisible(false);
							CloseGame.backed();
						}
					});

				});
				t.start();
			}
		});
	}

}
