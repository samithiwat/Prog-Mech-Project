package update;

import java.util.ArrayList;

import character.*;
import component.entity.Minion;
import gui.entity.HexagonPane;
import gui.entity.MapGrid;
import gui.entity.MenuIcon;
import gui.entity.MinionIcon;
import gui.entity.MinionPane;
import javafx.scene.layout.GridPane;
import logic.GameSetUp;

public class HexTileUpdate {

	private static final int N_COLUMN = 3;

	private static HexagonPane hexTile;

// ---------------------------------------------------- Update Tile's Interact Type ----------------------------------------------------------
	
	public static void setDataInteract() {
		for (int i = 0; i < MapGrid.getGrids().size(); i++) {
			ArrayList<HexagonPane> column = MapGrid.getGrids().get(i);
			for (int j = 0; j < column.size(); j++) {
				column.get(j).dataInteract();
			}
		}
	}
	
	public static void setOverlayInteract() {
		for (int i = 0; i < MapGrid.getGrids().size(); i++) {
			ArrayList<HexagonPane> column = MapGrid.getGrids().get(i);
			for (int j = 0; j < column.size(); j++) {
				column.get(j).overlayInteract();
			}
		}
	}
	
	
// ---------------------------------------------------- Update Minion Icon in Hex Tile -----------------------------------------------------------
// ---------------------------------------------------- eg. moved, spawn, buy minion etc -----------------------------------------------------

	public static void updateMinionIcon() {
		GridPane minionIconPane = (GridPane) GameSetUp.selectedTile.getChildren().get(0);
		minionIconPane.getChildren().clear();

		ArrayList<Minion> minions = GameSetUp.selectedTile.getLocationType().getMinionOnLocation();

		for (int i = 0; i < minions.size(); i++) {

			minionIconPane.add(createMinionIcon(minions.get(i)), i % N_COLUMN, (int) i / N_COLUMN);
		}

	}

// ---------------------------------------------------- Update Minion Pane in Hex Tile -----------------------------------------------------------

	public static void updateMinionPane() {

		MinionPane minionPane = GameSetUp.selectedTile.getOverlay().getMinionPane();
		minionPane.getChildren().clear();

		ArrayList<Minion> minions = GameSetUp.selectedTile.getLocationType().getMinionOnLocation();

		for (int i = 0; i < minions.size(); i++) {
			addMinionToPane(minions.get(i), minionPane, i);
		}
	}

	private static MinionIcon createMinionIcon(Minion minion) {

		MinionIcon minionIcon = null;

		if (minion.getPossessedBy() instanceof RedFox) {
			minionIcon = new MinionIcon("img/character/FoxMinionIdle.png", 50, 1, 0);
		}
		if (minion.getPossessedBy() instanceof Collector) {
			minionIcon = new MinionIcon("img/character/LadyCollectorMinionIdle.png", 50, 37, 0);
		}
		if (minion.getPossessedBy() instanceof BlackSkull) {
			minionIcon = new MinionIcon("img/character/BlackSkullMinionWalking.png", 50, 6, 7);
		}
		if (minion.getPossessedBy() instanceof ThousandYear) {
			minionIcon = new MinionIcon("img/character/SirThousandMinionIdle.png", 50, 0, 0);
		}
		if (minion.getPossessedBy() instanceof Teewada) {
			minionIcon = new MinionIcon("img/character/SirTewadaMinionIdle.png", 0, 0, 0);
		}
		if (minion.getPossessedBy() instanceof Teewadee) {
			minionIcon = new MinionIcon("img/character/SirTewadeeMinionIdle.png", 0, 0, 0);
		}

		return minionIcon;
	}

	private static void addMinionToPane(Minion minion, MinionPane minionPane, int index) {

		MenuIcon minionIcon = null;

		if (minion.getPossessedBy() instanceof RedFox) {
			minionIcon = new MenuIcon("img/character/FoxMinionIdle.png", 0, 0);
		}
		if (minion.getPossessedBy() instanceof Collector) {
			minionIcon = new MenuIcon("img/character/LadyCollectorMinionIdle.png", 0, 0);
		}
		if (minion.getPossessedBy() instanceof BlackSkull) {
			minionIcon = new MenuIcon("img/character/BlackSkullMinionWalking.png", 0, 0);
		}
		if (minion.getPossessedBy() instanceof ThousandYear) {
			minionIcon = new MenuIcon("img/character/SirThousandMinionIdle.png", 0, 0);
		}
		if (minion.getPossessedBy() instanceof Teewada) {
			minionIcon = new MenuIcon("img/character/SirTewadaMinionIdle.png", 0, 0);
		}
		if (minion.getPossessedBy() instanceof Teewadee) {
			minionIcon = new MenuIcon("img/character/SirTewadeeMinionIdle.png", 0, 0);
		}

		minionPane.setMinionAtPos(minionIcon, index);

	}

// ---------------------------------------------------- Getter and Setter ------------------------------------------------------------

	public static HexagonPane getHexTile() {
		return hexTile;
	}

	public static void setHexTile(HexagonPane hexTile) {
		HexTileUpdate.hexTile = hexTile;
	}

}
