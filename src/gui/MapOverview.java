package gui;

import java.util.ArrayList;

import gui.entity.MenuIcon;
import gui.entity.PlayerPanel;
import gui.entity.TurnChangeScreen;
import gui.overlay.HandOverlay;
import gui.overlay.PlayerList1;
import gui.overlay.PlayerList2;
import gui.overlay.TradeOverlay;
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
import logic.AudioLoader;
import logic.SceneController;
import update.AudioUpdate;

public class MapOverview implements Sceneable {

	private Scene scene;

	private HandOverlay handOverlay;
	private PlayerList1 playerList1;
	private PlayerList2 playerList2;
	private TradeOverlay tradeOverlay;
	private static MenuIcon mainIsland;
	private static MenuIcon prisonIsland;
	private static Pane root;
	private static TurnChangeScreen turnChangeScreen;
	private static StackPane turnChangeScreenRoot;
	public static ArrayList<HandOverlay> allHandOverlay;
	public static ArrayList<PlayerList1> allPlayerList1;
	public static ArrayList<PlayerList2> allPlayerList2;
	public static ArrayList<TradeOverlay> allTradeOverlay;

	private static AudioClip bgm = AudioLoader.beachBGM;

	public MapOverview() {

		turnChangeScreen = new TurnChangeScreen();

		handOverlay = new HandOverlay();
		allHandOverlay = new ArrayList<HandOverlay>();
		allHandOverlay.add(handOverlay);

		playerList1 = new PlayerList1();
		allPlayerList1 = new ArrayList<PlayerList1>();
		allPlayerList1.add(playerList1);

		playerList2 = new PlayerList2();
		allPlayerList2 = new ArrayList<PlayerList2>();
		allPlayerList2.add(playerList2);

		tradeOverlay = new TradeOverlay();
		allTradeOverlay = new ArrayList<TradeOverlay>();
		allTradeOverlay.add(tradeOverlay);

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
				tradeOverlay, turnChangeScreenRoot);
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

}