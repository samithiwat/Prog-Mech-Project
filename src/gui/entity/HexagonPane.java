package gui.entity;

import java.util.ArrayList;

import character.BlackSkull;
import character.Collector;
import character.MainCharacter;
import character.RedFox;
import character.Teewada;
import character.Teewadee;
import character.ThousandYear;
import component.entity.Minion;
import component.location.Location;
import component.location.Water;
import gui.overlay.Overlay;
import gui.overlay.TileOverlay;
import javafx.event.EventHandler;
import javafx.geometry.Side;
import javafx.scene.input.ContextMenuEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Polygon;
import logic.GameSetUp;
import update.HexTileUpdate;
import update.PlayerPanelUpdate;

public class HexagonPane extends Pane implements Clickable {

	private static final int MAX_MINION = 6;
	private static final int N_COLUMN = 3;
	private final int INIT_X;
	private final int INIT_Y;

	private int x;
	private int y;
	private int row;
	private int column;

	private boolean moveable;
	private boolean isSpawnable = false;

	private HexagonPane hexPane = this;
	private TileOverlay overlay;
	private GridPane minionIconPane;
	private Location locationType;
	private PlayerActionMenu playerActionMenu;

	public HexagonPane(int width, int height, int x, int y,

			//////////////// FOR DEBUG ONLY //////////////////////

			int row, int column) {

		//////////////// END OF DEBUG /////////////////////////

// --------------------------------------------------- Set Up HexagonPane -----------------------------------------------
		setRow(row);
		setColumn(column);
		setX(x);
		setY(y);
		this.INIT_X = x;
		this.INIT_Y = y;
		moveable = false;

		double[] points = { 53, 0.5, 197, 0.5, 250, 125.5, 197, 250.5, 53, 250.5, 0, 125.5 };

		Polygon poly = new Polygon(points);

		Polygon shape = new Polygon(points);

		setShape(shape);
		setClip(poly);
		setLayoutX(x);
		setLayoutY(y);
		setPrefWidth(width);
		setPrefHeight(height);
		setId("grid-release-style");
		interact();

		playerActionMenu = new PlayerActionMenu();

		minionIconPane = new GridPane();
		minionIconPane.setHgap(10);
		minionIconPane.setVgap(10);
		minionIconPane.setLayoutX(40);
		minionIconPane.setLayoutY(25);

		getChildren().addAll(minionIconPane);
	}

	public void interact() {
		setOnMouseEntered(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				setCursor(MOUSE_SELECT);
				EFFECT_MOUSE_ENTER.play();
				setId("grid-hold-style");
			}
		});

		setOnMouseExited(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				setCursor(MOUSE_NORMAL);
				if (moveable) {

				} else if (MapGrid.isEnable()) {
					setId("grid-release-style");
				} else {
					setId("grid-disable");
				}
			}
		});

		setOnContextMenuRequested(new EventHandler<ContextMenuEvent>() {

			@Override
			public void handle(ContextMenuEvent event) {

				boolean canBuyLand = false;

				if (GameSetUp.thisTurn.getMoney() >= GameSetUp.selectedTile.getLocationType().getCost()) {

					ArrayList<Minion> minions = GameSetUp.selectedTile.getLocationType().getMinionOnLocation();

					for (int i = 0; i < minions.size(); i++) {
						if (minions.get(i).getPossessedBy().equals(GameSetUp.thisTurn)) {
							GameSetUp.selectedTile.getPlayerActionMenu().getBuyLand().setDisable(false);
							canBuyLand = true;
							break;
						}
					}

				}

				if (!canBuyLand) {
					GameSetUp.selectedTile.getPlayerActionMenu().getBuyLand().setDisable(true);
				}

				if (GameSetUp.thisTurn.getMoney() >= Minion.getCost()) {
					GameSetUp.selectedTile.getPlayerActionMenu().getBuyMinion().setDisable(false);
				} else {
					GameSetUp.selectedTile.getPlayerActionMenu().getBuyMinion().setDisable(true);
				}

				int count = 0;
				boolean canSplit = false;

				for (int i = 0; i < GameSetUp.selectedTile.getLocationType().getMinionOnLocation().size(); i++) {
					Minion minion = GameSetUp.selectedTile.getLocationType().getMinionOnLocation().get(i);
					if (minion.getPossessedBy().equals(GameSetUp.thisTurn)) {
						if (minion.getMyMinion().size() >= 1) {
							for (int j = 0; j < minion.getMyMinion().size(); j++) {
								if (minion.getMyMinion().get(j).getPossessedBy().equals(GameSetUp.thisTurn)) {
									GameSetUp.selectedTile.getPlayerActionMenu().getSplit().setVisible(true);
									canSplit = true;
								}
							}
						}
						count++;
					}

				}
				if (count >= 2) {
					GameSetUp.selectedTile.getPlayerActionMenu().getCombine().setVisible(true);
				} else if (count < 2) {
					GameSetUp.selectedTile.getPlayerActionMenu().getCombine().setVisible(false);
				}
				if (!canSplit) {
					GameSetUp.selectedTile.getPlayerActionMenu().getSplit().setVisible(false);
				}

				EFFECT_MOUSE_CLICK.play();
				playerActionMenu.show(hexPane, event.getSceneX(), event.getSceneY());
			}

		});

	}

	public void dataInteract() {
		setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {

				if (event.getButton() == MouseButton.PRIMARY) {
					GameSetUp.selectedTile = hexPane;

//////////////////////////////////////////////////////////DEBUG //////////////////////////////////////////////////////////////

					System.out.println(GameSetUp.selectedTile);

///////////////////////////////////////////////////////// END OF DEBUG /////////////////////////////////////////////////////////

				}
			}
		});
	}

	public void overlayInteract() {
		setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {

				GameSetUp.selectedTile = hexPane;
				if (event.getButton().equals(MouseButton.PRIMARY)) {
					updateMinionPane();
					updateMinionIcon(minionIconPane);
					overlay.triggerOverlay(TileOverlay.getOverlayDx(), TileOverlay.getOverlayDy(),
							TileOverlay.getOverlayDelay());
				}
//////////////////////////////////////////////////////////DEBUG //////////////////////////////////////////////////////////////

				System.out.println(GameSetUp.selectedTile);

///////////////////////////////////////////////////////// END OF DEBUG /////////////////////////////////////////////////////////
			}
		});
	}

	public void moveInteract() {
//		setOnMouseClicked((MouseEvent event) -> {
//		// need to add that this minion has moves left or not
//		 minion.getMoveLeft();
//		if (this.column % 2 == 1) {
//		}
//		if (GameSetUp.selectedTile == null) {
//			GameSetUp.selectedTile = this;
//			this.highlight();
//		} else if (GameSetUp.selectedTile == this) {
//			GameSetUp.selectedTile = null;
//			this.unhighlight();
//		} else if (GameSetUp.selectedTile != null && this.moveable == true) {
//			GameSetUp.selectedTile.unhighlight();
//			// call minion.move
//			minion.move(this.x-GameSetUp.selectedTile.getX(), this.y-GameSetUp.selectedTile.getY());
//			GameSetUp.selectedTile = this;
//			GameSetUp.selectedTile.highlight();
//			if(minion.moveleft() == 0) {
//	            GameSetUp.selectedTile.unhighlight();
//	            GameSetUp.selectedTile = null;
//	        }
//		}
//	});
	}

	public void triggerOverlay() {
		overlay.triggerOverlay(0, 825, 1000);
	}

	@Override
	public void triggerDisable() {
		setDisable(!isDisable());
	}

	public void moveLeft(int speed) {
		setX(getX() - speed);
		setLayoutX(getX());
	}

	public void moveRight(int speed) {
		setX(getX() + speed);
		setLayoutX(getX());
	}

	public void moveDown(int speed) {
		setY(getY() - speed);
		setLayoutY(getY());
	}

	public void moveUp(int speed) {
		setY(getY() + speed);
		setLayoutY(getY());
	}

	public void setCenter() {
		setX(INIT_X);
		setY(INIT_Y);
		setLayoutX(getX());
		setLayoutY(getY());
	}

	public void highlight() {
		int key = 0;
		if (column % 2 == 1) {
			key = 1;
		}
		for (int i = 0; i < 7; i++) {
			int x = component.entity.moveable.DIRECTION[key][2 * i];
			int y = component.entity.moveable.DIRECTION[key][2 * i + 1];
			if (this.row + x < 0 || this.row + x >= 9 || this.column + y < 0 || this.column + y >= 11) {
				continue;
			}
			if (GameSetUp.map[this.row + x][this.column + y] instanceof Water) {
				continue;
			}
			MapGrid.getGrids().get(this.row + x).get(this.column + y).setId("grid-hold-style");
			MapGrid.getGrids().get(this.row + x).get(this.column + y).setMoveable(true);
		}
	}

	public void unhighlight() {
		int key = 0;
		if (column % 2 == 1) {
			key = 1;
		}
		for (int i = 0; i < 7; i++) {
			int x = component.entity.moveable.DIRECTION[key][2 * i];
			int y = component.entity.moveable.DIRECTION[key][2 * i + 1];
			if (this.row + x < 0 || this.row + x >= 9 || this.column + y < 0 || this.column + y >= 11) {
				continue;
			}
			if (GameSetUp.map[this.row + x][this.column + y] instanceof Water) {
				continue;
			}
			MapGrid.getGrids().get(this.row + x).get(this.column + y).setId("grid-release-style");
			MapGrid.getGrids().get(this.row + x).get(this.column + y).setMoveable(false);
		}
	}

	public void updateMinionIcon(GridPane minionIconPane) {

		minionIconPane.getChildren().clear();

		ArrayList<Minion> minions = locationType.getMinionOnLocation();

		for (int i = 0; i < minions.size(); i++) {

			minionIconPane.add(createMinionIcon(minions.get(i)), i % N_COLUMN, (int) i / N_COLUMN);

////////////////////////////////////////////////////////// DEBUG //////////////////////////////////////////////////////////////

			// System.out.println("Update Icon Pane");

///////////////////////////////////////////////////////// END OF DEBUG /////////////////////////////////////////////////////////
		}
	}

	public void updateMinionPane() {

		overlay.getMinionPane().getChildren().clear();

		ArrayList<Minion> minions = locationType.getMinionOnLocation();
		System.out.println(locationType);
		for (int i = 0; i < minions.size(); i++) {
			addMinionToPane(minions.get(i), overlay.getMinionPane(), i);

		}
	}

	private MinionPortraits createMinionIcon(Minion minion) {

		MinionPortraits minionIcon = null;

		if (minion.getPossessedBy() instanceof RedFox) {
			minionIcon = new MinionPortraits("img/character/FoxMinionIdle.png", 50, 1, 0);
		}
		if (minion.getPossessedBy() instanceof Collector) {
			minionIcon = new MinionPortraits("img/character/LadyCollectorMinionIdle.png", 50, 37, 0);
		}
		if (minion.getPossessedBy() instanceof BlackSkull) {
			minionIcon = new MinionPortraits("img/character/BlackSkullMinionWalking.png", 50, 6, 7);
		}
		if (minion.getPossessedBy() instanceof ThousandYear) {
			minionIcon = new MinionPortraits("img/character/SirThousandMinionIdle.png", 50, 0, 0);
		}
		if (minion.getPossessedBy() instanceof Teewada) {
			minionIcon = new MinionPortraits("img/character/SirTewadaMinionIdle.png", 0, 0, 0);
		}
		if (minion.getPossessedBy() instanceof Teewadee) {
			minionIcon = new MinionPortraits("img/character/SirTewadeeMinionIdle.png", 0, 0, 0);
		}

		return minionIcon;
	}

	private void addMinionToPane(Minion minion, MinionPane minionPane, int index) {

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

// ------------------------------------------------ Getter and Setter ------------------------------------------------------------

	public int getX() {
		return x;
	}

	public boolean isMoveable() {
		return moveable;
	}

	public void setMoveable(boolean moveable) {
		this.moveable = moveable;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public Location getLocationType() {
		return locationType;
	}

	public void setLocationType(Location locationType) {
		this.locationType = locationType;
	}

	public TileOverlay getOverlay() {
		return overlay;
	}

	public void setOverlay(TileOverlay overlay) {
		this.overlay = overlay;
	}

	public GridPane getMinionIconPane() {
		return minionIconPane;
	}

	public HexagonPane getHexPane() {
		return hexPane;
	}

	public PlayerActionMenu getPlayerActionMenu() {
		return playerActionMenu;
	}

	public boolean isSpawnable() {
		return isSpawnable;
	}

	public void setSpawnable(boolean isSpawnable) {
		this.isSpawnable = isSpawnable;
	}

	public static int getMAX_MINION() {
		return MAX_MINION;
	}

///////////////////////////////////////////////////// FOR DEBUG ONLY //////////////////////////////////////////////////////////////////////

	public int getRow() {
		return row;
	}

	public void setRow(int row) {
		this.row = row;
	}

	public int getColumn() {
		return column;
	}

	public void setColumn(int column) {
		this.column = column;
	}

	public String toString() {
		return locationType + "\n"
				+ "Row : " + row + ", Column : " + column + "\n";
	}

////////////////////////////////////////////////////// END OF DEBUG ///////////////////////////////////////////////////////////////////////
}
