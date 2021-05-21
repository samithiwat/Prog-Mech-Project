package gui.entity;

import java.util.ArrayList;

import character.BlackSkull;
import character.Collector;
import character.RedFox;
import character.Teewada;
import character.Teewadee;
import character.ThousandYear;
import component.entity.Minion;
import component.location.Buyable;
import component.location.BuyableLocation;
import component.location.Incomeable;
import component.location.Location;
import component.location.SecretBase;
import component.location.Water;
import gui.MainIsland;
import gui.overlay.TileOverlay;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.input.ContextMenuEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.text.FontWeight;
import logic.GameSetUp;

public class HexagonPane extends Pane implements Clickable {

	public static final int[] tileSurroundCouncil = { 1, 5, 2, 6, 2, 7, 3, 7, 4, 7, 5, 6, 5, 5, 5, 4, 4, 3, 3, 3, 2, 3,
			2, 4 };

	private static final int MAX_MINION = 6;
	private static final int N_COLUMN = 3;
	private final double INIT_X;
	private final double INIT_Y;

	private double x;
	private double y;
	private int row;
	private int column;

	private boolean moveable;
	private boolean isSpawnable = false;

	private HexagonPane hexPane = this;
	private TileOverlay overlay;
	private GridPane minionIconPane;
	private Location locationType;
	private PlayerActionMenu playerActionMenu;

	private TextTitle landInfo;
	private StackPane landInfoRoot;

	public HexagonPane(int width, int height, double x, double y, int row, int column) {

// --------------------------------------------------- Set Up HexagonPane -----------------------------------------------
		setRow(row);
		setColumn(column);
		setX(x);
		setY(y);
		this.INIT_X = x;
		this.INIT_Y = y;
		moveable = false;

		double[] points = { 50, 0, 150, 0, 200, 100, 150, 200, 50, 200, 0, 100 };

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
		minionIconPane.setLayoutX(52);
		minionIconPane.setLayoutY(20);

		landInfo = new TextTitle("", Color.web("0xFEFDE8"), FontWeight.BOLD, 20);

		landInfoRoot = new StackPane(landInfo);
		landInfoRoot.setAlignment(Pos.CENTER);
		landInfoRoot.setPrefWidth(width);
		landInfoRoot.setPrefHeight(height);
		landInfoRoot.setVisible(false);

		getChildren().addAll(minionIconPane, landInfoRoot);
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

				if (GameSetUp.selectedTile != null) {

					boolean canBuyLand = false;

					if (GameSetUp.selectedTile.getLocationType() instanceof Buyable) {

						BuyableLocation location = (BuyableLocation) GameSetUp.selectedTile.getLocationType();

						if (GameSetUp.thisTurn.getMoney() >= location.getCost()) {

							ArrayList<Minion> minions = GameSetUp.selectedTile.getLocationType().getMinionOnLocation();

							for (int i = 0; i < minions.size(); i++) {

								if (minions.get(i).getPossessedBy().equals(GameSetUp.thisTurn)) {

									GameSetUp.selectedTile.getPlayerActionMenu().getBuyLand().setDisable(false);
									canBuyLand = true;
									break;
								}
							}

						}
					}

					if (!canBuyLand) {
						GameSetUp.selectedTile.getPlayerActionMenu().getBuyLand().setDisable(true);
					}

					if (GameSetUp.thisTurn.getMoney() >= Minion.COST) {
						GameSetUp.selectedTile.getPlayerActionMenu().getBuyMinion().setDisable(false);
					} else {
						GameSetUp.selectedTile.getPlayerActionMenu().getBuyMinion().setDisable(true);
					}

					int count = 0;
					boolean canSplit = false;
					boolean canFight1 = false;
					boolean canFight2 = false;

					for (int i = 0; i < GameSetUp.selectedTile.getLocationType().getMinionOnLocation().size(); i++) {
						Minion minion = GameSetUp.selectedTile.getLocationType().getMinionOnLocation().get(i);
						if (minion.getPossessedBy().equals(GameSetUp.thisTurn)) {
							canFight1 = true;
							if (minion.getMyMinion().size() >= 1) {
								for (int j = 0; j < minion.getMyMinion().size(); j++) {
									if (minion.getMyMinion().get(j).getPossessedBy().equals(GameSetUp.thisTurn)) {
										GameSetUp.selectedTile.getPlayerActionMenu().getSplit().setVisible(true);
										canSplit = true;
									}
								}
							}
							count++;
						} else {
							canFight2 = true;
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
					if (canFight1 && canFight2) {
						GameSetUp.selectedTile.getPlayerActionMenu().getFight().setVisible(true);
					} else {
						GameSetUp.selectedTile.getPlayerActionMenu().getFight().setVisible(false);
					}

					for (int i = 0; i < 12; i++) {
						if (tileSurroundCouncil[2 * i] == row && tileSurroundCouncil[2 * i + 1] == column
								&& GameSetUp.selectedTile.getLocationType().getMinionOnLocation().size() > 0) {
							GameSetUp.selectedTile.getPlayerActionMenu().getCouncilFight().setVisible(true);
							break;
						}
					}
					EFFECT_MOUSE_CLICK.play();
					playerActionMenu.show(hexPane, event.getSceneX(), event.getSceneY());
				}

			}

		});

	}

	public void councilInteract() {

	}

	public void dataInteract() {
		setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {

				if (event.getButton() == MouseButton.PRIMARY) {
					GameSetUp.selectedTile = hexPane;

				}
			}
		});
	}

	public void overlayInteract(String mode) {
		setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {

				GameSetUp.selectedTile = hexPane;
				if (event.getButton().equals(MouseButton.PRIMARY)) {
					updateMinionPane(mode);
					updateMinionIcon(minionIconPane);
					overlay.triggerOverlay(TileOverlay.getOverlayDx(), TileOverlay.getOverlayDy(),
							TileOverlay.getOverlayDelay());
				}
			}
		});
	}

	public void showLandInfo() {

		if (locationType instanceof Buyable) {

			BuyableLocation location = (BuyableLocation) locationType;

			if (location.getOwner() != null) {

				landInfo.setText("Name : " + locationType.getName() + "\n" + "Owner : " + location.getOwner().getName()
						+ "\n" + "Income : $" + (((Incomeable) locationType).getIncome()) + " M");

				landInfoRoot
						.setBackground(new Background(new BackgroundFill(location.getOwner().getColor(), null, null)));
				landInfoRoot.setOpacity(0.8);
				landInfoRoot.setVisible(true);
				setId("grid-disable");

			}

			else {
				landInfo.setText("Name : " + locationType.getName() + "\n" + "Owner : NONE\n" + "Income : $"
						+ (((Incomeable) locationType).getIncome()) + " M");
				landInfoRoot.setVisible(true);
				setId("grid-highlight-style");
			}

		}

		if (locationType instanceof SecretBase) {

			landInfo.setText("Name : " + locationType.getName() + "\n" + "Income : $"
					+ (((Incomeable) locationType).getIncome()) + " M");
			landInfoRoot.setBackground(new Background(new BackgroundFill(Color.web("0x3F9466"), null, null)));
			landInfoRoot.setOpacity(0.8);
			landInfoRoot.setVisible(true);

		}
	}

	public void triggerOverlay() {
		overlay.triggerOverlay(0, 825, 1000);
		GameSetUp.selectedIcon.clear();
		

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
			if (MapGrid.isEnable()) {
				MapGrid.getGrids().get(this.row + x).get(this.column + y).setId("grid-release-style");
			} else {
				MapGrid.getGrids().get(this.row + x).get(this.column + y).setId("grid-disable");
			}
			MapGrid.getGrids().get(this.row + x).get(this.column + y).setMoveable(false);
		}
	}

	public void updateMinionIcon(GridPane minionIconPane) {

		minionIconPane.getChildren().clear();

		ArrayList<Minion> minions = locationType.getMinionOnLocation();

		for (int i = 0; i < minions.size(); i++) {

			minionIconPane.add(createMinionIcon(minions.get(i)), i % N_COLUMN, (int) i / N_COLUMN);

		}
	}

	public void updateMinionPane(String mode) {

		overlay.getMinionPane().getChildren().clear();

		ArrayList<Minion> minions = locationType.getMinionOnLocation();
		for (int i = 0; i < minions.size(); i++) {
			addMinionToPane(minions.get(i), overlay.getMinionPane(), i);

		}

		switch (mode) {
		case "OneMinion":
			overlay.getMinionPane().setOneMinionSelectMode();
			break;
		case "TwoMinion":
			overlay.getMinionPane().setTwoMinionSelectMode();
			break;
		}
	}

// ------------------------------------------------- Private Method ----------------------------------------------------------------

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

	public double getX() {
		return x;
	}

	public boolean isMoveable() {
		return moveable;
	}

	public void setMoveable(boolean moveable) {
		this.moveable = moveable;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
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

	public StackPane getLandInfoRoot() {
		return landInfoRoot;
	}

}
