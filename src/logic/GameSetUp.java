package logic;

import java.util.ArrayList;

import character.MainCharacter;
import component.entity.Minion;
import component.law.LawDeck;
import component.law.LawSlot;
import component.location.Buyable;
import component.location.Council;
import component.location.Field;
import component.location.Forest;
import component.location.Location;
import component.location.Mine;
import component.location.Plain;
import component.location.SecretBase;
import component.location.Village;
import component.location.Water;
import component.weaponCard.Axe;
import component.weaponCard.Bow;
import component.weaponCard.Gun;
import component.weaponCard.RemovedDeck;
import component.weaponCard.Shield;
import component.weaponCard.Sword;
import component.weaponCard.WeaponDeck;
import gui.GameLobbyMenu;
import gui.MainIsland;
import gui.MapOverview;
import gui.entity.HexagonPane;
import gui.entity.MapGrid;
import gui.entity.MinionIcon;
import gui.entity.PlayerPanel;
import gui.overlay.TileOverlay;
import javafx.animation.AnimationTimer;
import javafx.application.Platform;
import javafx.scene.effect.BoxBlur;
import javafx.scene.paint.Color;
import update.AudioUpdate;
import update.FightOverlayUpdate;
import update.HexTileUpdate;
import update.PlayerPanelUpdate;

public class GameSetUp {
	private static int MAP_N_ROW = 9;
	private static int MAP_N_COLUMN = 11;

	private static long lastTimeTrigger = -1;
	private static final long DURATION = 100000000;
	private static AnimationTimer animationTimer;
	private static int animationCount = 0;

	public static ArrayList<MainCharacter> gameCharacter = new ArrayList<MainCharacter>();
	public static GameLaw gameLaw = new GameLaw();
	public static WeaponDeck weaponDeck = new WeaponDeck();
	public static RemovedDeck removedDeck = new RemovedDeck();
	public static LawSlot lawSlot = new LawSlot();
	public static LawDeck lawDeck = new LawDeck();
	public static ArrayList<SecretBase> allsecretBases = new ArrayList<SecretBase>();
	private static final int DUPLICATE = 5;
	public static int turn = 1;
	public static int cycle = 0;
	public static int governmentPoint = 0;
	public static int countDownDuration = 0;
	public static boolean canBuyMinion = true;
	public static boolean isGameEnd = false;
	public static boolean isEndTurn;
	public static MainCharacter theGovernment = null;
	public static Location[][] map = new Location[9][11];
	public static MainCharacter thisTurn;
	public static boolean isHighlightSpawnable = false;
	public static boolean isHighlightPlain = false;
	public static boolean isReset = false;
	public static boolean isTurnChange = false;
	public static boolean isSelectMinionSpawn = false;
	public static boolean isCountDown = false;
	public static boolean isCancel = false;
	public static boolean isShowLandInfo = false;
	public static HexagonPane initialTile = null;
	public static HexagonPane selectedTile = null;
	public static ArrayList<MinionIcon> selectedIcon = new ArrayList<MinionIcon>();
	public static MainCharacter selectedCharacter = null;
	public static boolean isDraw = true;
	public static boolean isFightOverlayOffersUpdate = false;
	public static boolean isFightTradeMode = false;
	public GameSetUp() {
		thisTurn = gameCharacter.get(0);
//----------------------------------Weapon Set Up---------------------------------------------------
		for (int i = 0; i < DUPLICATE; i++) {
			weaponDeck.addCard(new Axe());
			weaponDeck.addCard(new Bow());
			weaponDeck.addCard(new Gun());
			weaponDeck.addCard(new Shield());
			weaponDeck.addCard(new Sword());
		}
//----------------------------------Add Law Card----------------------------------------------------
		lawDeck.setUpLawDeck();
//----------------------------------Map Initialize------------------------------------------------------
		setUpMap();
		SceneController.createGameScene();
		setUpMapWithHexPane();

//////////////////////////////////////////////////////////////FOR DEBUG ONLY ///////////////////////////////////////////////////////////////////

//printMap();

////////////////////////////////////////////////////////////// END OF DEBUG /////////////////////////////////////////////////////////////////////	

// -------------------------------------------------- Set Up Select Minion Spawn Mode -------------------------------------------------

		GameSetUp.isSelectMinionSpawn = true;
		Thread controller = new Thread(new Runnable() {

			@Override
			public void run() {

				GameController gameController = new GameController();
			}
		});

		controller.start();
		updateAnimation();

	}

// --------------------------------------------------------- Map Set Up Method ------------------------------------------------------------------

	private void setUpMap() {
		String[] mapCode = { "51400302105", "1310X001301", "04030X00230", "0001X6X1000", "0020XXX2X02", "200011000X0",
				"00401000430", "10302X3000X", "50004000305" };

		for (int i = 0; i < MAP_N_ROW; i++) {
			for (int j = 0; j < MAP_N_COLUMN; j++) {
				char code = mapCode[i].charAt(j);
				if (code == '0') {
					map[i][j] = new Plain();
				} else if (code == '1') {
					map[i][j] = new Forest();
				} else if (code == '2') {
					map[i][j] = new Field();
				} else if (code == '3') {
					map[i][j] = new Village();
				} else if (code == '4') {
					map[i][j] = new Mine();
				} else if (code == '5') {
					map[i][j] = new SecretBase();
					allsecretBases.add((SecretBase) map[i][j]);
				} else if (code == '6') {
					map[i][j] = new Council();
				} else {
					map[i][j] = new Water();
				}
			}
		}
	}

	private void setUpMapWithHexPane() {
		
		int count =0;
		
		for (int i = 0; i < MapGrid.getGrids().size(); i++) {
			ArrayList<HexagonPane> column = MapGrid.getGrids().get(i);
			for (int j = 0; j < column.size(); j++) {
				column.get(j).setLocationType(map[i][j]);

				TileOverlay overlay = createTileOverlay(i, j);
				column.get(j).setOverlay(overlay);

//////////////////////////////////////////////////////////////FOR DEBUG ONLY ///////////////////////////////////////////////////////////////////
//				System.out.println(overlay);;
				System.out.println(Math.round(count++)+"%");

//////////////////////////////////////////////////////////////FOR DEBUG ONLY ///////////////////////////////////////////////////////////////////
			}
		}

		for (int i = 0; i < MapGrid.getGrids().size(); i++) {
			ArrayList<HexagonPane> column = MapGrid.getGrids().get(i);
			for (int j = 0; j < column.size(); j++) {
				setTileSpawnable(column.get(j), i, j);
				setUpPlayerActionMenu(column.get(j), i, j);
			}
		}

	}

	private TileOverlay createTileOverlay(int row, int column) {
		String[][] mapHexCode = { { "54", "11", "43", "02", "03", "33", "03", "22", "11", "01", "51" },
				{ "11", "37", "11", "02", "X1", "03", "02", "11", "31", "01", "11" },
				{ "03", "42", "02", "36", "03", "X4", "02", "09", "21", "36", "03" },
				{ "03", "03", "03", "11", "X4", "61", "X4", "11", "03", "03", "03" },
				{ "05", "07", "21", "05", "X4", "X4", "X4", "22", "X1", "02", "21" },
				{ "21", "07", "07", "07", "12", "12", "03", "07", "07", "X2", "08" },
				{ "05", "05", "45", "07", "12", "07", "07", "07", "44", "34", "08" },
				{ "13", "05", "31", "05", "22", "X3", "32", "05", "08", "08", "X2" },
				{ "53", "06", "0A", "06", "41", "06", "0B", "06", "31", "06", "52" } };

		TileOverlay overlay = null;

		if (mapHexCode[row][column].equals("01")) {
			int[] posXList = { 45, 215, 439, 945, 1170, 1284 };
			int[] posYList = { 582, 349, 582, 586, 344, 568 };
			overlay = new TileOverlay("img/background/PlainBackground1.png", posXList, posYList);
		}

		if (mapHexCode[row][column].equals("02")) {
			int[] posXList = { 45, 215, 439, 945, 1170, 1284 };
			int[] posYList = { 582, 349, 582, 586, 344, 568 };
			overlay = new TileOverlay("img/background/PlainBackground2.png", posXList, posYList);
		}

		if (mapHexCode[row][column].equals("03")) {
			int[] posXList = { 45, 215, 439, 945, 1170, 1284 };
			int[] posYList = { 582, 349, 582, 586, 344, 568 };
			overlay = new TileOverlay("img/background/PlainBackground3.png", posXList, posYList);
		}

		if (mapHexCode[row][column].equals("04")) {
			int[] posXList = { 45, 215, 439, 945, 1170, 1284 };
			int[] posYList = { 582, 349, 582, 586, 344, 568 };
			overlay = new TileOverlay("img/background/PlainBackground4.png", posXList, posYList);
		}

		if (mapHexCode[row][column].equals("05")) {
			int[] posXList = { 45, 215, 439, 945, 1170, 1284 };
			int[] posYList = { 582, 349, 582, 586, 344, 568 };
			overlay = new TileOverlay("img/background/PlainBackground5.png", posXList, posYList);
		}

		if (mapHexCode[row][column].equals("06")) {
			int[] posXList = { 276, 460, 749, 1042, 521, 697 };
			int[] posYList = { 604, 630, 605, 615, 404, 387 };
			overlay = new TileOverlay("img/background/PlainBackground6.png", posXList, posYList);
		}

		if (mapHexCode[row][column].equals("07")) {
			int[] posXList = { 88, 378, 643, 896, 1178, 1211 };
			int[] posYList = { 619, 308, 467, 579, 590, 325 };
			overlay = new TileOverlay("img/background/PlainBackground7.png", posXList, posYList);
		}

		if (mapHexCode[row][column].equals("08")) {
			int[] posXList = { 180, 194, 656, 835, 1125, 1170 };
			int[] posYList = { 334, 563, 443, 593, 604, 344 };
			overlay = new TileOverlay("img/background/PlainBackground8.png", posXList, posYList);
		}

		if (mapHexCode[row][column].equals("09")) {
			int[] posXList = { 113, 462, 534, 756, 1141, 1196 };
			int[] posYList = { 234, 336, 562, 392, 227, 423 };
			overlay = new TileOverlay("img/background/PlainBackground9.png", posXList, posYList);
		}

		if (mapHexCode[row][column].equals("0A")) {
			int[] posXList = { 77, 415, 701, 879, 887, 1201 };
			int[] posYList = { 401, 593, 605, 396, 615, 482 };
			overlay = new TileOverlay("img/background/PlainBackground10.png", posXList, posYList);
		}

		if (mapHexCode[row][column].equals("0B")) {
			int[] posXList = { 127, 442, 697, 898, 1170, 1178 };
			int[] posYList = { 461, 579, 583, 485, 344, 593 };
			overlay = new TileOverlay("img/background/PlainBackground11.png", posXList, posYList);
		}

		if (mapHexCode[row][column].equals("11")) {
			int[] posXList = { 38, 317, 505, 734, 886, 1242 };
			int[] posYList = { 321, 258, 368, 273, 359, 446 };
			overlay = new TileOverlay("img/background/ForestBackground1.png", posXList, posYList);
		}

		if (mapHexCode[row][column].equals("12")) {
			int[] posXList = { 91, 381, 559, 789, 932, 1253 };
			int[] posYList = { 372, 472, 565, 493, 562, 416 };
			overlay = new TileOverlay("img/background/ForestBackground2.png", posXList, posYList);
		}

		if (mapHexCode[row][column].equals("13")) {
			int[] posXList = { 64, 359, 485, 759, 1021, 1212 };
			int[] posYList = { 576, 445, 416, 269, 430, 414 };
			overlay = new TileOverlay("img/background/ForestBackground3.png", posXList, posYList);
		}

		if (mapHexCode[row][column].equals("21")) {
			int[] posXList = { 135, 247, 530, 742, 991, 1179 };
			int[] posYList = { 438, 605, 428, 611, 510, 608 };
			overlay = new TileOverlay("img/background/FarmBackground1.png", posXList, posYList);
		}

		if (mapHexCode[row][column].equals("22")) {
			int[] posXList = { 124, 470, 728, 950, 1077, 1263 };
			int[] posYList = { 568, 543, 632, 478, 625, 466 };
			overlay = new TileOverlay("img/background/FarmBackground2.png", posXList, posYList);
		}

		if (mapHexCode[row][column].equals("31")) {
			int[] posXList = { 153, 300, 586, 758, 1098, 1155 };
			int[] posYList = { 635, 362, 517, 537, 335, 639 };
			overlay = new TileOverlay("img/background/VillageBackground1.png", posXList, posYList);
		}

		if (mapHexCode[row][column].equals("32")) {
			int[] posXList = { 153, 300, 586, 758, 1098, 1155 };
			int[] posYList = { 635, 362, 517, 537, 335, 639 };
			overlay = new TileOverlay("img/background/VillageBackground2.png", posXList, posYList);
		}

		if (mapHexCode[row][column].equals("33")) {
			int[] posXList = { 153, 300, 586, 758, 1098, 1155 };
			int[] posYList = { 635, 362, 517, 537, 335, 639 };
			overlay = new TileOverlay("img/background/VillageBackground3.png", posXList, posYList);
		}

		if (mapHexCode[row][column].equals("34")) {
			int[] posXList = { 153, 300, 586, 758, 1098, 1155 };
			int[] posYList = { 635, 362, 517, 537, 335, 639 };
			overlay = new TileOverlay("img/background/VillageBackground4.png", posXList, posYList);
		}

		if (mapHexCode[row][column].equals("36")) {
			int[] posXList = { 45, 372, 603, 822, 992, 1219 };
			int[] posYList = { 593, 563, 593, 619, 581, 462 };
			overlay = new TileOverlay("img/background/VillageBackground6.png", posXList, posYList);
		}

		if (mapHexCode[row][column].equals("37")) {
			int[] posXList = { 541, 681, 730, 906, 965, 1199 };
			int[] posYList = { 663, 487, 629, 502, 645, 536 };
			overlay = new TileOverlay("img/background/VillageBackground7.png", posXList, posYList);
		}

		if (mapHexCode[row][column].equals("41")) {
			int[] posXList = { 39, 295, 443, 836, 975, 1217 };
			int[] posYList = { 607, 519, 476, 511, 602, 596 };
			overlay = new TileOverlay("img/background/MineBackground1.png", posXList, posYList);
		}

		if (mapHexCode[row][column].equals("42")) {
			int[] posXList = { 41, 295, 530, 798, 982, 1265 };
			int[] posYList = { 595, 483, 507, 520, 604, 419 };
			overlay = new TileOverlay("img/background/MineBackground2.png", posXList, posYList);
		}

		if (mapHexCode[row][column].equals("43")) {
			int[] posXList = { 92, 329, 561, 850, 1012, 1266 };
			int[] posYList = { 595, 583, 473, 465, 597, 351 };
			overlay = new TileOverlay("img/background/MineBackground3.png", posXList, posYList);
		}

		if (mapHexCode[row][column].equals("44")) {
			int[] posXList = { 61, 329, 535, 883, 1055, 1285 };
			int[] posYList = { 562, 458, 471, 486, 595, 407 };
			overlay = new TileOverlay("img/background/MineBackground4.png", posXList, posYList);
		}

		if (mapHexCode[row][column].equals("45")) {
			int[] posXList = { 101, 419, 696, 786, 1080, 1303 };
			int[] posYList = { 556, 454, 451, 643, 557, 552 };
			overlay = new TileOverlay("img/background/MineBackground5.png", posXList, posYList);
		}

		if (mapHexCode[row][column].equals("51")) {
			int[] posXList = { 62, 284, 436, 900, 1003, 1246 };
			int[] posYList = { 632, 382, 547, 555, 628, 611 };
			overlay = new TileOverlay("img/background/MilitaryBaseBackground1.png", posXList, posYList);
		}

		if (mapHexCode[row][column].equals("52")) {
			int[] posXList = { 62, 267, 634, 831, 963, 1251 };
			int[] posYList = { 632, 400, 650, 483, 600, 598 };
			overlay = new TileOverlay("img/background/MilitaryBaseBackground2.png", posXList, posYList);
		}

		if (mapHexCode[row][column].equals("53")) {
			int[] posXList = { 313, 293, 664, 812, 953, 957 };
			int[] posYList = { 369, 634, 656, 481, 575, 342 };
			overlay = new TileOverlay("img/background/MilitaryBaseBackground3.png", posXList, posYList);
		}

		if (mapHexCode[row][column].equals("54")) {
			int[] posXList = { 0, 218, 322, 531, 999, 1246 };
			int[] posYList = { 630, 403, 599, 543, 518, 611 };
			overlay = new TileOverlay("img/background/MilitaryBaseBackground1.png", posXList, posYList);
		}

		if (mapHexCode[row][column].equals("61")) {
			int[] posXList = { 1043 };
			int[] posYList = { 380 };
			overlay = new TileOverlay("img/background/CouncilBackground.png", posXList, posYList);
		}

		if (mapHexCode[row][column].equals("X1")) {
			int[] posXList = {};
			int[] posYList = {};
			overlay = new TileOverlay("img/background/LakeBackground1.png", posXList, posYList);
		}

		if (mapHexCode[row][column].equals("X2")) {
			int[] posXList = {};
			int[] posYList = {};
			overlay = new TileOverlay("img/background/LakeBackground2.png", posXList, posYList);
		}

		if (mapHexCode[row][column].equals("X3")) {
			int[] posXList = {};
			int[] posYList = {};
			overlay = new TileOverlay("img/background/LakeBackground3.png", posXList, posYList);
		}

		if (mapHexCode[row][column].equals("X4")) {
			int[] posXList = {};
			int[] posYList = {};
			overlay = new TileOverlay("img/background/LakeBackground4.png", posXList, posYList);
		}

		MainIsland.getSceneRoot().getChildren().add(overlay);

		return overlay;

	}

	private void setTileSpawnable(HexagonPane tile, int row, int column) {


		if (tile.getLocationType() instanceof Village) {

			if (column % 2 != 0) {

				try {
					if (MapGrid.getGrids().get(row - 1).get(column).getLocationType() instanceof Plain) {
						MapGrid.getGrids().get(row - 1).get(column).setSpawnable(true);
					}
				} catch (Exception e) {

				}
				try {
					if (MapGrid.getGrids().get(row).get(column - 1).getLocationType() instanceof Plain) {
						MapGrid.getGrids().get(row).get(column - 1).setSpawnable(true);
					}
				} catch (Exception e) {

				}
				try {
					if (MapGrid.getGrids().get(row).get(column + 1).getLocationType() instanceof Plain) {
						MapGrid.getGrids().get(row).get(column + 1).setSpawnable(true);
					}

				} catch (Exception e) {

				}
				try {
					if (MapGrid.getGrids().get(row + 1).get(column - 1).getLocationType() instanceof Plain) {
						MapGrid.getGrids().get(row + 1).get(column - 1).setSpawnable(true);
					}

				} catch (Exception e) {

				}
				try {
					if (MapGrid.getGrids().get(row + 1).get(column).getLocationType() instanceof Plain) {
						MapGrid.getGrids().get(row + 1).get(column).setSpawnable(true);
					}
				} catch (Exception e) {

				}
				try {
					if (MapGrid.getGrids().get(row + 1).get(column + 1).getLocationType() instanceof Plain) {
						MapGrid.getGrids().get(row + 1).get(column + 1).setSpawnable(true);
					}
				} catch (Exception e) {

				}

			} else if (column % 2 == 0) {

				try {
					System.out.println(MapGrid.getGrids().get(row - 1).get(column - 1).getLocationType());
				} catch (Exception e) {

				}

				try {
					System.out.println(MapGrid.getGrids().get(row - 1).get(column).getLocationType());
				} catch (Exception e) {

				}

				try {
					System.out.println(MapGrid.getGrids().get(row - 1).get(column + 1).getLocationType());
				} catch (Exception e) {

				}

				try {
					System.out.println(MapGrid.getGrids().get(row).get(column - 1).getLocationType());
				} catch (Exception e) {

				}

				try {
					System.out.println(MapGrid.getGrids().get(row).get(column + 1).getLocationType());
				} catch (Exception e) {

				}

				try {
					System.out.println(MapGrid.getGrids().get(row + 1).get(column).getLocationType());
				} catch (Exception e) {

				}

				try {
					if (MapGrid.getGrids().get(row - 1).get(column - 1).getLocationType() instanceof Plain) {
						MapGrid.getGrids().get(row - 1).get(column - 1).setSpawnable(true);
					}
				} catch (Exception e) {

				}
				try {
					if (MapGrid.getGrids().get(row - 1).get(column).getLocationType() instanceof Plain) {
						MapGrid.getGrids().get(row - 1).get(column).setSpawnable(true);
					}
				} catch (Exception e) {

				}
				try {
					if (MapGrid.getGrids().get(row - 1).get(column + 1).getLocationType() instanceof Plain) {
						MapGrid.getGrids().get(row - 1).get(column + 1).setSpawnable(true);
					}

				} catch (Exception e) {

				}
				try {
					if (MapGrid.getGrids().get(row).get(column - 1).getLocationType() instanceof Plain) {
						MapGrid.getGrids().get(row).get(column - 1).setSpawnable(true);
					}

				} catch (Exception e) {

				}
				try {
					if (MapGrid.getGrids().get(row).get(column + 1).getLocationType() instanceof Plain) {
						MapGrid.getGrids().get(row).get(column + 1).setSpawnable(true);
					}

				} catch (Exception e) {

				}
				try {
					if (MapGrid.getGrids().get(row + 1).get(column).getLocationType() instanceof Plain) {
						MapGrid.getGrids().get(row + 1).get(column).setSpawnable(true);
					}
				} catch (Exception e) {

				}

			}
		}

		if (tile.getLocationType() instanceof Field) {
			MapGrid.getGrids().get(row).get(column).setSpawnable(true);
		}

	}

	private void setUpPlayerActionMenu(HexagonPane tile, int row, int column) {
		if (tile.getLocationType() instanceof Buyable) {
			tile.getPlayerActionMenu().getBuyLand().setVisible(true);
		}
	}

// -------------------------------------------- Update Animation --------------------------------------------------------------------

	private void updateAnimation() {
		animationTimer = new AnimationTimer() {

			@Override
			public void handle(long now) {
				lastTimeTrigger = (lastTimeTrigger < 0 ? now : lastTimeTrigger);
				if (now - lastTimeTrigger > DURATION) {

					HexTileUpdate.updateMinionIcon();

					if (GameSetUp.isCountDown) {

						BoxBlur blur = new BoxBlur();
						blur.setIterations(20);

						MainIsland.getBg().setEffect(blur);
						MainIsland.getInfoRoot().setVisible(false);

						if (animationCount % 10 == 0) {
							MainIsland.setShowMessage("Game Start in " + (countDownDuration - animationCount / 10),
									Color.web("0xFEFDE8"), Color.web("0x89949B"), 140, 1, 1000);
							AudioLoader.countDownEffect1.play();
						}

						if (animationCount + 1 == countDownDuration * 10) {
							MainIsland.getBg().setEffect(null);
							animationCount = 0;
							GameSetUp.isCountDown = false;
						}

						animationCount++;
					}

					if(GameSetUp.isShowLandInfo) {
						HexTileUpdate.showLandInfo();
					}
					
					if (GameSetUp.isHighlightPlain) {
						PlayerPanelUpdate.highlightPlainTile();
					}

					if (GameSetUp.isHighlightSpawnable) {
						PlayerPanelUpdate.highlightSpawnableTile();
					}

					if (GameSetUp.isReset) {
						MainIsland.getBg().setEffect(null);
						PlayerPanelUpdate.resetTile();
						GameSetUp.isReset = false;
					}

					if (GameSetUp.isSelectMinionSpawn) {
						MainIsland.getSceneRoot().getChildren().set(3, PlayerPanel.getStatusPane());
						MainIsland.getSceneRoot().getChildren().set(4, PlayerPanel.getTurnBar());
						MainIsland.getSceneRoot().getChildren().set(5, PlayerPanel.getHandsIcon());
						MainIsland.getSceneRoot().getChildren().set(6, PlayerPanel.getEndTurn());
						MainIsland.getSceneRoot().getChildren().set(7, PlayerPanel.getGovernmentPoint());
						MainIsland.getSceneRoot().getChildren().set(8, PlayerPanel.getGoodnessPoint());

						MainIsland.setShowMessage(GameSetUp.thisTurn.getName() + "'s Turn", Color.web("0xFEFDE8"),
								Color.web("0x89949B"), 140, 1, 2000);

						SceneController.setScene(SceneController.getMainIsland());
						GameLobbyMenu.getBGM().stop();
						GameSetUp.isSelectMinionSpawn = false;
					}

					if (GameSetUp.isTurnChange) {

						BoxBlur blur = new BoxBlur();
						blur.setIterations(20);

						MapOverview.getTurnChangeScreen().update();
						MapOverview.getTurnChangeScreenRoot().setVisible(true);
						AudioUpdate.change(null, MapOverview.getBgm());
						MapOverview.getSceneRoot().getChildren().set(1, new PlayerPanel());
						SceneController.setScene(SceneController.getMapOverView());

						MapOverview.getMainIsland().setEffect(blur);
						MapOverview.getPrisonIsland().setEffect(blur);
						SceneController.getMapOverView().getRoot().setDisable(true);

						PlayerPanelUpdate.closePanel();
						Thread t = new Thread(() -> {
							try {
								Thread.sleep(3000);
							} catch (InterruptedException e) {

							}

							Platform.runLater(new Runnable() {

								@Override
								public void run() {
									MapOverview.getMainIsland().setEffect(null);
									MapOverview.getPrisonIsland().setEffect(null);
									MapOverview.getTurnChangeScreenRoot().setVisible(false);
									PlayerPanelUpdate.openPanel();
									SceneController.getMapOverView().getRoot().setDisable(false);
								}

							});
						});
						t.start();
						GameSetUp.isTurnChange = false;
					}
					
					if(isFightOverlayOffersUpdate) {
						FightOverlayUpdate.challengedofferUpdate();
						FightOverlayUpdate.challengerofferUpdate();
						isFightOverlayOffersUpdate = false;
					}

					if (GameSetUp.isGameEnd) {
						animationTimer.stop();
						lastTimeTrigger = -1;
					}

					lastTimeTrigger = now;
				}
			}

		};

		animationTimer.start();
	}

}
