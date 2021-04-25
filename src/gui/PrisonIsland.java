package gui;

import gui.entity.MenuIcon;
import gui.entity.PlayerPanel;
import gui.entity.TextTitle;
import gui.overlay.CurrentLaw;
import gui.overlay.FightOverlay;
import gui.overlay.Government;
import gui.overlay.HandOverlay;
import gui.overlay.ObjectiveOverlay;
import gui.overlay.PlayerList1;
import gui.overlay.PlayerList2;
import gui.overlay.SelectWeaponOverlay;
import gui.overlay.TileOverlay;
import gui.overlay.TradeOverlay;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.media.AudioClip;
import javafx.scene.paint.Color;
import javafx.scene.text.FontWeight;
import logic.AudioLoader;
import logic.SceneController;
import update.AudioUpdate;
import update.MainIslandUpdate;

public class PrisonIsland implements Sceneable {

	private static AudioClip bgm = AudioLoader.beachBGM;
	
	private Scene scene;
	private PlayerPanel playerPanel;
	
	private static Pane root;
	
	private static StackPane messageRoot;
	private static TextTitle message;

	public PrisonIsland() {

// ---------------------------------------------------- Scene Background -----------------------------------------------------------------

		root = new Pane();

		ImageView bg = new ImageView(
				ClassLoader.getSystemResource("img/background/PrisonIslandBackground.png").toString());

// ----------------------------------------------------- Prison Overlay ------------------------------------------------------------------
		
		int[] posXList = { 147, 384, 641, 869, 1040, 1164 };
		int[] posYList = { 518, 418, 401, 449, 550, 398 };
		
		TileOverlay prisonOverlay = new TileOverlay("img/background/jailBackground.png", posXList, posYList);

// --------------------------------------------------------- Prison ----------------------------------------------------------------------

		MenuIcon prison = new MenuIcon("img/background/prison.png", 660, 230);
		prison.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				prisonOverlay.triggerOverlay(0, 825, 1000);
			}
			
		});

// ------------------------------------------------------ Player Panel -------------------------------------------------------------------

		playerPanel = new PlayerPanel();
		
		message = new TextTitle("", Color.web("0x393E46"), FontWeight.BOLD, 48, 376, 779);

		messageRoot = new StackPane(message);
		messageRoot.setPrefWidth(SceneController.getFullscreenWidth());
		messageRoot.setPrefHeight(SceneController.getFullscreenHeight());
		messageRoot.setAlignment(Pos.CENTER);
		messageRoot.setVisible(false);
		
		HandOverlay handOverlay = new HandOverlay();
		MapOverview.allHandOverlay.add(handOverlay);

		PlayerList1 playerList1 = new PlayerList1();
		MapOverview.allPlayerList1.add(playerList1);

		PlayerList2 playerList2 = new PlayerList2();
		MapOverview.allPlayerList2.add(playerList2);

		CurrentLaw currentLaw = new CurrentLaw();
		MapOverview.allCurrentLaw.add(currentLaw);

		Government government = new Government();
		MapOverview.allGovernment.add(government);

		SelectWeaponOverlay selectWeaponOverlay = new SelectWeaponOverlay();
		MapOverview.allSelectWeapon.add(selectWeaponOverlay);
		
		ObjectiveOverlay objectiveOverlay = new ObjectiveOverlay();
		MapOverview.allObjectiveOverlay.add(objectiveOverlay);
		
		TradeOverlay tradeOverlay = new TradeOverlay();
		MapOverview.allTradeOverlay.add(tradeOverlay);

		FightOverlay fightOverlay = new FightOverlay();
		MapOverview.allFightOverlay.add(fightOverlay);
		
// ------------------------------------------------------ Add Component ------------------------------------------------------------------

		root.getChildren().addAll(bg, playerPanel ,prison, prisonOverlay);
		root.getChildren().addAll( handOverlay,
				playerList1, playerList2, currentLaw, government,objectiveOverlay,tradeOverlay,selectWeaponOverlay, fightOverlay, messageRoot);
		scene = new Scene(root,SceneController.getFullscreenWidth(),SceneController.getFullscreenHeight());
		scene.setCursor(MOUSE_NORMAL);
		scene.getStylesheets().add(ClassLoader.getSystemResource("css/map-style.css").toExternalForm());
		
		scene.setOnKeyPressed(new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent event) {
				if(event.getCode().equals(KeyCode.ESCAPE)) {
					AudioUpdate.change(bgm, MapOverview.getBgm());
					MapOverview.getSceneRoot().getChildren().set(1, new PlayerPanel());
					SceneController.setScene(SceneController.getMapOverView());
				}
			}
		});
	}
	
	// ----------------------------------------------- Show Message ------------------------------------------------------------
	
	public static void setShowMessage(String message, Color color, Color strokeColor, int size, int strokeWidth,
				int duration) {
			messageRoot.setVisible(true);
			getMessage().setFontBold(size);
			getMessage().setFill(color);
			getMessage().setStroke(strokeColor);
			getMessage().setStrokeWidth(strokeWidth);
			getMessage().setText(message);

			Thread t = new Thread(() -> {
				try {
					Thread.sleep(duration);
				} catch (InterruptedException e) {

				}

				Platform.runLater(new Runnable() {

					@Override
					public void run() {
						messageRoot.setVisible(false);
					}
				});
			});
			t.start();
		}

		public static void setShowMessage(String message, Color color, int size, int duration) {
			messageRoot.setVisible(true);
			getMessage().setFontBold(size);
			getMessage().setFill(color);
			getMessage().setStroke(Color.TRANSPARENT);
			getMessage().setText(message);

			Thread t = new Thread(() -> {
				try {
					Thread.sleep(duration);
				} catch (InterruptedException e) {

				}

				Platform.runLater(new Runnable() {

					@Override
					public void run() {
						messageRoot.setVisible(false);
					}
				});
			});
			t.start();
		}

// --------------------------------------------------- Getter and Setter ---------------------------------------------------------------
		
	@Override
	public Scene getScene() {
		return scene;
	}

	public static AudioClip getBgm() {
		return bgm;
	}
	
	public static Pane getSceneRoot() {
		return root;
	}

	public static StackPane getMessageRoot() {
		return messageRoot;
	}

	public static TextTitle getMessage() {
		return message;
	}
	
	
}
