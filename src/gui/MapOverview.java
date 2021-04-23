package gui;

import java.util.ArrayList;

import gui.entity.MenuIcon;
import gui.entity.PlayerPanel;
import gui.entity.TextTitle;
import gui.entity.TurnChangeScreen;
import gui.overlay.CurrentLaw;
import gui.overlay.FightOverlay;
import gui.overlay.Government;
import gui.overlay.HandOverlay;
import gui.overlay.PlayerList1;
import gui.overlay.PlayerList2;
import gui.overlay.SelectWeaponOverlay;
import gui.overlay.TradeOverlay;
import javafx.application.Platform;
//import gui.entity.HexagonalButton;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.effect.BoxBlur;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.media.AudioClip;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.FontWeight;
import logic.AudioLoader;
import logic.SceneController;
import update.AudioUpdate;

public class MapOverview implements Sceneable {

	private Scene scene;

	private HandOverlay handOverlay;
	private PlayerList1 playerList1;
	private PlayerList2 playerList2;
	private TradeOverlay tradeOverlay;
	private FightOverlay fightOverlay;
	private static MenuIcon mainIsland;
	private static MenuIcon prisonIsland;
	private static Pane root;

	private static TurnChangeScreen turnChangeScreen;
	private static StackPane turnChangeScreenRoot;

	private static StackPane messageRoot;
	private static TextTitle message;

	public static ArrayList<HandOverlay> allHandOverlay;
	public static ArrayList<PlayerList1> allPlayerList1;
	public static ArrayList<PlayerList2> allPlayerList2;
	public static ArrayList<CurrentLaw> allCurrentLaw;
	public static ArrayList<Government> allGovernment;
	public static ArrayList<TradeOverlay> allTradeOverlay;
	public static ArrayList<SelectWeaponOverlay> allSelectWeapon;
	public static ArrayList<FightOverlay> allFightOverlay;

	private static AudioClip bgm = AudioLoader.beachBGM;

	public MapOverview() {

		turnChangeScreen = new TurnChangeScreen();

		fightOverlay = new FightOverlay();
		allFightOverlay = new ArrayList<FightOverlay>();
		allFightOverlay.add(fightOverlay);
		
		handOverlay = new HandOverlay();
		allHandOverlay = new ArrayList<HandOverlay>();
		allHandOverlay.add(handOverlay);

		playerList1 = new PlayerList1();
		allPlayerList1 = new ArrayList<PlayerList1>();
		allPlayerList1.add(playerList1);

		playerList2 = new PlayerList2();
		allPlayerList2 = new ArrayList<PlayerList2>();
		allPlayerList2.add(playerList2);

		CurrentLaw currentLaw = new CurrentLaw();
		allCurrentLaw = new ArrayList<CurrentLaw>();
		allCurrentLaw.add(currentLaw);

		Government government = new Government();
		allGovernment = new ArrayList<Government>();
		allGovernment.add(government);

		SelectWeaponOverlay selectWeaponOverlay = new SelectWeaponOverlay();
		allSelectWeapon = new ArrayList<SelectWeaponOverlay>();
		allSelectWeapon.add(selectWeaponOverlay);

		tradeOverlay = new TradeOverlay();
		allTradeOverlay = new ArrayList<TradeOverlay>();
		allTradeOverlay.add(tradeOverlay);

		message = new TextTitle("", Color.web("0x393E46"), FontWeight.BOLD, 48, 376, 779);

		messageRoot = new StackPane(message);
		messageRoot.setPrefWidth(SceneController.getFullscreenWidth());
		messageRoot.setPrefHeight(SceneController.getFullscreenHeight());
		messageRoot.setAlignment(Pos.CENTER);
		messageRoot.setVisible(false);

		PlayerPanel playerPanel = new PlayerPanel();

		Rectangle bg = new Rectangle(SceneController.getFullscreenWidth(), SceneController.getFullscreenHeight());
		bg.setFill(Color.web("0x4DC3D3"));

		prisonIsland = new MenuIcon("img/background/PrisonIslandOverview.png", 186, 171);
		prisonIsland.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {

			}
		});

		mainIsland = new MenuIcon("img/background/MainIslandOverview.png", 343, 174);
		mainIsland.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {

				AudioUpdate.change(bgm, null);

				MainIsland.getSceneRoot().getChildren().set(3, PlayerPanel.getStatusPane());
				MainIsland.getSceneRoot().getChildren().set(4, PlayerPanel.getTurnBar());
				MainIsland.getSceneRoot().getChildren().set(5, PlayerPanel.getHandsIcon());
				MainIsland.getSceneRoot().getChildren().set(6, PlayerPanel.getEndTurn());
				MainIsland.getSceneRoot().getChildren().set(7, PlayerPanel.getGovernmentPoint());
				MainIsland.getSceneRoot().getChildren().set(8, PlayerPanel.getGoodnessPoint());

				SceneController.setScene(SceneController.getMainIsland());

			}
		});

//		ImageView bg = new ImageView(ClassLoader.getSystemResource("img/background/MapOverview.png").toString());
//		ImageView bgMap = new ImageView(ClassLoader.getSystemResource("img/background/Map.png").toString());
//		bg.setFitHeight(800);
//		bg.setFitWidth(1200);
//		bgMap.setFitHeight(880);
//		bgMap.setPreserveRatio(true);

		turnChangeScreenRoot = new StackPane(turnChangeScreen);
		turnChangeScreenRoot.setPrefWidth(SceneController.getFullscreenWidth());
		turnChangeScreenRoot.setPrefHeight(SceneController.getFullscreenHeight());
		turnChangeScreenRoot.setAlignment(Pos.CENTER);
		turnChangeScreenRoot.setVisible(false);

		root = new Pane();
		root.getChildren().addAll(bg, playerPanel, prisonIsland, mainIsland, handOverlay, playerList1, playerList2,
				currentLaw, government, tradeOverlay,selectWeaponOverlay, messageRoot, turnChangeScreenRoot);
//		root.getChildren().addAll(bg,createHexAt(529,91.69));
//		root.getChildren().add(createHexAt(529, 91.69+68.98));
//		root.getChildren().add(createHexAt(583.25,57.2));
//		for(int i = 0 ; i < 10 ; i++) {
//			for(int j = 0 ; j < 11 ; j++) {
//				if(j%2 == 0 && i != 9)
//				{
////					System.out.println(j/2);
////					root.getChildren().add(createHexAt(532+(j/2)*(38.44+68.98),87+i*70));
//					root.getChildren().add(new HexagonalButton(532+(j/2)*(38.44+68.98),87+i*70,i,j));
//				}
//				else if(j%2 == 1) {
////					root.getChildren().add(createHexAt(586+((j-1)/2)*(38.44+68.98),52.2+i*70));
//					root.getChildren().add(new HexagonalButton(585.8+((j-1)/2)*(38.44+68.98),51.9+i*70,i,j));
//				}
//			}
//		}

		scene = new Scene(root, SceneController.getFullscreenWidth(), SceneController.getFullscreenHeight());
		scene.setCursor(CURSOR_NORMAL);
		scene.getStylesheets().add(ClassLoader.getSystemResource("css/map-style.css").toExternalForm());

		this.scene.setOnKeyPressed(key -> {
			//////////////// FOR DEBUG ONLY //////////////////////
			if (key.getCode() == KeyCode.ESCAPE) {
				System.exit(0);
			}
			//////////////// END OF DEBUG /////////////////////////
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

// -------------------------------------------- Getter and Setter --------------------------------------------------

	public Scene getScene() {
		return this.scene;
	}

	public static AudioClip getBgm() {
		return bgm;
	}

	public static Pane getSceneRoot() {
		return root;
	}

	public void setSceneRoot(Pane root) {
		this.root = root;
	}

	public static TurnChangeScreen getTurnChangeScreen() {
		return turnChangeScreen;
	}

	public static MenuIcon getMainIsland() {
		return mainIsland;
	}

	public static MenuIcon getPrisonIsland() {
		return prisonIsland;
	}

	public static StackPane getTurnChangeScreenRoot() {
		return turnChangeScreenRoot;
	}

	public static StackPane getMessageRoot() {
		return messageRoot;
	}

	public static void setMessageRoot(StackPane messageRoot) {
		MapOverview.messageRoot = messageRoot;
	}

	public static TextTitle getMessage() {
		return message;
	}

	public static void setMessage(TextTitle message) {
		MapOverview.message = message;
	}
	
	

}