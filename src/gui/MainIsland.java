package gui;

import java.util.ArrayList;

import gui.entity.ActivedLawPane;
import gui.entity.HexagonPane;
import gui.entity.MapGrid;
import gui.entity.MenuIcon;
import gui.entity.PlayerPanel;
import gui.entity.PointPane;
import gui.entity.StatusPane;
import gui.entity.TextTitle;
import gui.entity.TurnBar;
import gui.overlay.CurrentLaw;
import gui.overlay.FightOverlay;
import gui.overlay.Government;
import gui.overlay.HandOverlay;
import gui.overlay.ObjectiveOverlay;
import gui.overlay.PlayerList1;
import gui.overlay.PlayerList2;
import gui.overlay.SelectWeaponOverlay;
import gui.overlay.TradeOverlay;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import logic.GameSetUp;
import logic.SceneController;
import update.AudioUpdate;
import update.HexTileUpdate;
import update.MainIslandUpdate;
import update.PlayerPanelUpdate;

public class MainIsland implements Sceneable {

	private final static int BG_CENTER_X = 415;
	private final static int BG_CENTER_Y = 420;
	private static int bgX = BG_CENTER_X;
	private static int bgY = BG_CENTER_Y;

	private static Scene scene;

	private static Pane root;

	private static ImageView bg;

	private static Button endTurn = PlayerPanel.getEndTurn();
	private static PointPane governmentPoint = PlayerPanel.getGovernmentPoint();
	private static PointPane goodnessPoint = PlayerPanel.getGoodnessPoint();
	private static MenuIcon handsIcon = PlayerPanel.getHandsIcon();

	private static StatusPane statusPane = PlayerPanel.getStatusPane();
	private static TurnBar turnBar = PlayerPanel.getTurnBar();

	private static StackPane infoRoot;
	private static TextTitle info;

	private static StackPane messageRoot;
	private static TextTitle message;

	public MainIsland() {
		root = new Pane();

		bg = new ImageView(ClassLoader.getSystemResource("img/background/MainIsland.png").toString());
		bg.setViewport(new Rectangle2D(BG_CENTER_X, BG_CENTER_Y, SceneController.getFullscreenWidth(),
				SceneController.getFullscreenHeight()));

		MapGrid grid = new MapGrid();

		info = new TextTitle("Select spawn location of your minion", Color.web("0x393E46"), FontWeight.BOLD, 48, 376,
				779);

		infoRoot = new StackPane(info);
		infoRoot.setPrefWidth(SceneController.getFullscreenWidth());
		infoRoot.setPrefHeight(SceneController.getFullscreenHeight());
		infoRoot.setPadding(new Insets(50));
		infoRoot.setAlignment(Pos.BOTTOM_CENTER);

		message = new TextTitle("", Color.web("0x393E46"), FontWeight.BOLD, 48, 376, 779);

		messageRoot = new StackPane(message);
		messageRoot.setPrefWidth(SceneController.getFullscreenWidth());
		messageRoot.setPrefHeight(SceneController.getFullscreenHeight());
		messageRoot.setAlignment(Pos.CENTER);
		messageRoot.setVisible(false);

		root.getChildren().addAll(bg, infoRoot, grid);
		// --------------------------overlay/others section-----------------------------

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
		
// ----------------------------------------------------- Add Scene's Component --------------------------------------------------------
		
		ActivedLawPane activedLawPane = new ActivedLawPane();
		PlayerPanelUpdate.allActivedLawPanes.add(activedLawPane);
		
		root.getChildren().addAll(statusPane, turnBar, handsIcon, endTurn, governmentPoint, goodnessPoint,activedLawPane, handOverlay,
			playerList1, playerList2, currentLaw, government,objectiveOverlay,tradeOverlay,selectWeaponOverlay, fightOverlay, messageRoot);

		scene = new Scene(root, SceneController.getFullscreenWidth(), SceneController.getFullscreenHeight());
		scene.setCursor(MOUSE_NORMAL);
		scene.getStylesheets().add(ClassLoader.getSystemResource("css/map-style.css").toExternalForm());

		scene.setOnKeyReleased(key -> {
			MainIslandUpdate.setCurrent_speed(0);

		});

	}

	public static void moveBgLeft(int speed) {
		setBgX(getBgX() - speed);
		bg.setViewport(new Rectangle2D(getBgX(), getBgY(), SceneController.getFullscreenWidth(),
				SceneController.getFullscreenHeight()));
	}

	public static void moveBgRight(int speed) {
		setBgX(getBgX() + speed);
		bg.setViewport(new Rectangle2D(getBgX(), getBgY(), SceneController.getFullscreenWidth(),
				SceneController.getFullscreenHeight()));
	}

	public static void moveBgDown(int speed) {
		setBgY(getBgY() - speed);
		bg.setViewport(new Rectangle2D(getBgX(), getBgY(), SceneController.getFullscreenWidth(),
				SceneController.getFullscreenHeight()));
	}

	public static void moveBgUp(int speed) {
		setBgY(getBgY() + speed);
		bg.setViewport(new Rectangle2D(getBgX(), getBgY(), SceneController.getFullscreenWidth(),
				SceneController.getFullscreenHeight()));
	}

	public static void setBgCenter() {
		setBgX(BG_CENTER_X);
		setBgY(BG_CENTER_Y);
		bg.setViewport(new Rectangle2D(getBgX(), getBgY(), SceneController.getFullscreenWidth(),
				SceneController.getFullscreenHeight()));
	}

	public static void dataInteractMode() {
		turnBar.setVisible(false);
		statusPane.setVisible(false);
		endTurn.setVisible(false);
		governmentPoint.setVisible(false);
		goodnessPoint.setVisible(false);
		handsIcon.setVisible(false);
		infoRoot.setVisible(true);
		HexTileUpdate.setDataInteract();
		disableESC();
	}

	public static void overlayInteractMode(String mode) {
		turnBar.setVisible(true);
		statusPane.setVisible(true);
		endTurn.setVisible(true);
		governmentPoint.setVisible(true);
		goodnessPoint.setVisible(true);
		handsIcon.setVisible(true);
		infoRoot.setVisible(false);
		HexTileUpdate.setOverlayInteract(mode);
		enableESC();
	}

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

	private static void disableESC() {

		scene.setOnKeyPressed(key -> {

			if (key.getCode() == KeyCode.A || key.getCode() == KeyCode.LEFT) {
				MainIslandUpdate.moveLeft();
			}
			if (key.getCode() == KeyCode.D || key.getCode() == KeyCode.RIGHT) {
				MainIslandUpdate.moveRight();
			}
			if (key.getCode() == KeyCode.W || key.getCode() == KeyCode.UP) {
				MainIslandUpdate.moveUp();
			}
			if (key.getCode() == KeyCode.S || key.getCode() == KeyCode.DOWN) {
				MainIslandUpdate.moveDown();
			}

		});

	}

	private static void enableESC() {

		scene.setOnKeyPressed(key -> {
			if (key.getCode() == KeyCode.A || key.getCode() == KeyCode.LEFT) {
				MainIslandUpdate.moveLeft();
			}
			if (key.getCode() == KeyCode.D || key.getCode() == KeyCode.RIGHT) {
				MainIslandUpdate.moveRight();
			}
			if (key.getCode() == KeyCode.W || key.getCode() == KeyCode.UP) {
				MainIslandUpdate.moveUp();
			}
			if (key.getCode() == KeyCode.S || key.getCode() == KeyCode.DOWN) {
				MainIslandUpdate.moveDown();
			}

			if (key.getCode() == KeyCode.ESCAPE) {
//				MapOverview.getSceneRoot().getChildren().set(1, new PlayerPanel());
//				SceneController.setScene(SceneController.getMapOverView());
				AudioUpdate.change(null, MapOverview.getBgm());
				SceneController.goToMapOverview();
			}
		});
	}

// ------------------------------------------------ Getter and Setter ------------------------------------------------------------

	@Override
	public Scene getScene() {
		return this.scene;
	}

	public static ImageView getBg() {
		return bg;
	}

	public static int getBG_CENTER_X() {
		return BG_CENTER_X;
	}

	public static int getBG_CENTER_Y() {
		return BG_CENTER_Y;
	}

	public static int getBgX() {
		return bgX;
	}

	public static void setBgX(int bgX) {
		MainIsland.bgX = bgX;
	}

	public static int getBgY() {
		return bgY;
	}

	public static void setBgY(int bgY) {
		MainIsland.bgY = bgY;
	}

	public static Pane getSceneRoot() {
		return root;
	}

	public static void setSceneRoot(Pane root) {
		MainIsland.root = root;
	}

	public static StackPane getInfoRoot() {
		return infoRoot;
	}

	public static TextTitle getInfo() {
		return info;
	}

	public static StackPane getMessageRoot() {
		return messageRoot;
	}

	public static void setMessageRoot(StackPane messageRoot) {
		MainIsland.messageRoot = messageRoot;
	}

	public static TextTitle getMessage() {
		return message;
	}

	public static void setMessage(TextTitle message) {
		MainIsland.message = message;
	}

}
