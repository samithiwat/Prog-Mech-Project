package update;

import java.util.ArrayList;

import character.*;
import component.entity.Minion;
import gui.entity.HexagonPane;
import gui.entity.MenuIcon;
import gui.entity.MinionIcon;
import gui.entity.MinionPane;
import javafx.scene.layout.GridPane;

public class HexTileUpdate {

	private static int N_COLUMN = 3;

	private HexagonPane hexTile;

// ---------------------------------------------------- Update Minion Icon in Hex Tile -----------------------------------------------------------
// ---------------------------------------------------- eg. moved, spawn, buy minion etc -----------------------------------------------------

	public void updateMinionIcon() {
		GridPane minionIconPane = (GridPane) hexTile.getChildren().get(0);
		minionIconPane.getChildren().clear();

		ArrayList<Minion> minions = hexTile.getLocationType().getMinionOnLocation();

		for (int i = 0; i < minions.size(); i++) {

			minionIconPane.add(createMinionIcon(minions.get(i)), i % N_COLUMN, (int) i / N_COLUMN);
		}

	}

// ---------------------------------------------------- Update Minion Pane in Hex Tile -----------------------------------------------------------

	public void updateMinionPane() {

		MinionPane minionPane = hexTile.getOverlay().getMinionPane();
		minionPane.getChildren().clear();

		ArrayList<Minion> minions = hexTile.getLocationType().getMinionOnLocation();

		for (int i = 0; i < minions.size(); i++) {
			addMinionToPane(minions.get(i), minionPane, i);
		}
	}

	private MinionIcon createMinionIcon(Minion minion) {

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

	private void addMinionToPane(Minion minion, MinionPane minionPane, int index) {

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

	public HexagonPane getHexTile() {
		return hexTile;
	}

	public void setHexTile(HexagonPane hexTile) {
		this.hexTile = hexTile;
	}

}
