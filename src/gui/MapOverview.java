package gui;

import java.util.ArrayList;

import character.BlackSkull;
import character.Collector;
import character.RedFox;
import character.Teewada;
import character.Teewadee;
import character.ThousandYear;
import component.entity.Minion;
import component.location.Ocean;
import component.location.Prison;
import gui.entity.ActivedLawPane;
import gui.entity.MenuIcon;
import gui.entity.MinionIcon;
import gui.entity.MinionPane;
import gui.entity.PlayerPanel;
import gui.entity.TextTitle;
import gui.entity.TurnChangeScreen;
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
import update.PlayerPanelUpdate;

public class MapOverview implements Sceneable {

	private Scene scene;

	private HandOverlay handOverlay;
	private PlayerList1 playerList1;
	private PlayerList2 playerList2;
	private TradeOverlay tradeOverlay;
	private FightOverlay fightOverlay;
	private static MenuIcon mainIsland;
	private static MenuIcon prisonIsland;
	private static MenuIcon oceanTile;
	private static TileOverlay oceanOverlay;
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
	public static ArrayList<ObjectiveOverlay> allObjectiveOverlay;
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

		ObjectiveOverlay objectiveOverlay = new ObjectiveOverlay();
		allObjectiveOverlay = new ArrayList<ObjectiveOverlay>();
		allObjectiveOverlay.add(objectiveOverlay);

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
		
		ActivedLawPane activedLawPane = new ActivedLawPane();
		PlayerPanelUpdate.allActivedLawPanes.add(activedLawPane);

		Rectangle bg = new Rectangle(SceneController.getFullscreenWidth(), SceneController.getFullscreenHeight());
		bg.setFill(Color.web("0x4DC3D3"));

		int[] posXList = { 147, 384, 641, 869, 1040, 1164 };
		int[] posYList = { 518, 418, 401, 449, 550, 398 };

		oceanOverlay = new TileOverlay("img/background/Ocean.png", posXList, posYList);

		oceanTile = new MenuIcon("img/background/OceanTile.png", 1230, 190);

		oceanTile.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				EFFECT_MOUSE_CLICK.play();
				MapOverview.updateOverlay();
				oceanOverlay.triggerOverlay(0, 825, 1000);
			}
		});

		prisonIsland = new MenuIcon("img/background/PrisonIslandOverview.png", 80, 140);
		prisonIsland.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				AudioUpdate.change(bgm, PrisonIsland.getBgm());
				SceneController.goToPrisonIsland();
			}
		});

		mainIsland = new MenuIcon("img/background/MainIslandOverview.png", 343, 174);
		mainIsland.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				
				AudioUpdate.change(bgm, null);

				SceneController.goToMainIsland();

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
		root.getChildren().addAll(bg, playerPanel, prisonIsland, mainIsland, oceanTile,activedLawPane, oceanOverlay, handOverlay,
				playerList1, playerList2, currentLaw, government, objectiveOverlay, tradeOverlay, selectWeaponOverlay,
				messageRoot, turnChangeScreenRoot);
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
		scene.setCursor(MOUSE_NORMAL);
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
	
	// ------------------------------------------------- Update Prison Overlay  ------------------------------------------------------------
	
			public static void updateOverlay() {
				oceanOverlay.getMinionPane().getChildren().clear();
				
				ArrayList<Minion> minions = Ocean.banishedMinion;
				for (int i = 0; i < minions.size(); i++) {
					addMinionToPane(minions.get(i), oceanOverlay.getMinionPane(), i);

				}
				
				oceanOverlay.getMinionPane().setPardonMode();
			}
			
	// ---------------------------------------------------- Private Method -----------------------------------------------------------------
			
			private static void addMinionToPane(Minion minion, MinionPane minionPane, int index) {

				MenuIcon minionIcon = null;

				if (minion.getPossessedBy() instanceof RedFox) {
					minionIcon = new MinionIcon("img/character/FoxMinionIdle.png", 0, 0, minion);
				}
				if (minion.getPossessedBy() instanceof Collector) {
					minionIcon = new MinionIcon("img/character/LadyCollectorMinionIdle.png", 0, 0, minion);
				}
				if (minion.getPossessedBy() instanceof BlackSkull) {
					minionIcon = new MinionIcon("img/character/BlackSkullMinionWalking.png", 0, 0, minion);
				}
				if (minion.getPossessedBy() instanceof ThousandYear) {
					minionIcon = new MinionIcon("img/character/SirThousandMinionIdle.png", 0, 0, minion);
				}
				if (minion.getPossessedBy() instanceof Teewada) {
					minionIcon = new MinionIcon("img/character/SirTewadaMinionIdle.png", 0, 0, minion);
				}
				if (minion.getPossessedBy() instanceof Teewadee) {
					minionIcon = new MinionIcon("img/character/SirTewadeeMinionIdle.png", 0, 0, minion);
				}

				minionPane.setMinionAtPos(minionIcon, index);

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
	
	public static MenuIcon getOceanTile() {
		return oceanTile;
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

	public static TileOverlay getOceanOverlay() {
		return oceanOverlay;
	}

}