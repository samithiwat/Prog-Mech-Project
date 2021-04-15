package gui.entity;

import java.util.ArrayList;

import character.BlackSkull;
import character.Collector;
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

public class HexagonPane extends Pane implements Clickable {

	private final int MAX_MINION = 6;
	private static final int N_COLUMN = 3;

	private int x;
	private int y;

	private HexagonPane hexPane = this;
	private TileOverlay overlay;
	private GridPane minionIconPane;
	private boolean moveable;
	private Location locationType;
	private PlayerActionMenu playerActionMenu;

	private int row;
	private int column;

	public HexagonPane(int width, int height, int x, int y,

			//////////////// FOR DEBUG ONLY //////////////////////

			int row, int column) {

		//////////////// END OF DEBUG /////////////////////////

// --------------------------------------------------- Set Up HexagonPane -----------------------------------------------
		
		setRow(row);
		setColumn(column);
		setX(x);
		setY(y);
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

//		for (int i = 0; i < MAX_MINION / 3; i++) {
//			for (int j = 0; j < MAX_MINION / 2; j++) {
//				MinionIcon minionIcon = new MinionIcon("img/character/FoxMinionIdle.png", 50, 1, 0);
//				minionIcon.setVisible(false);
//				minionIconPane.add(minionIcon, j, i);
//			}
//		}

		getChildren().addAll(minionIconPane);
	}

	public void interact() {
//		setOnMouseClicked((MouseEvent event) -> {
//			// need to add that this minion has moves left or not
//			// minion.getMoveLeft();
//			if (this.column % 2 == 1) {
//			}
//			if (GameSetUp.selectedTile == null) {
//				GameSetUp.selectedTile = this;
//				this.highlight();
//			} else if (GameSetUp.selectedTile == this) {
//				GameSetUp.selectedTile = null;
//				this.unhighlight();
//			} else if (GameSetUp.selectedTile != null && this.moveable == true) {
//				GameSetUp.selectedTile.unhighlight();
//				// call minion.move
////				minion.move(this.x-GameSetUp.selectedTile.getX(), this.y-GameSetUp.selectedTile.getY());
//				GameSetUp.selectedTile = this;
//				GameSetUp.selectedTile.highlight();
//			}
//		});
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

//		setOnMouseClicked(new EventHandler<MouseEvent>() {
//
//			@Override
//			public void handle(MouseEvent event) {
//				if (event.getButton().equals(MouseButton.PRIMARY)) {
//					overlay.triggerOverlay(TileOverlay.getOverlayDx(), TileOverlay.getOverlayDy(),
//							TileOverlay.getOverlayDelay());
//				} else {
//					//playerActionMenu.show(this, Side.BOTTOM, 0, 0);
//				}
//			}
//
//		});
		
		setOnContextMenuRequested(new EventHandler<ContextMenuEvent>() {

			@Override
			public void handle(ContextMenuEvent event) {
				EFFECT_MOUSE_CLICK.play();
				playerActionMenu.show(hexPane,event.getSceneX(),event.getSceneY());
			}
		
		});
		
	}
	
	public void dataInteract() {
		setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				System.out.println("Clicked!");
				GameSetUp.selectedTile = hexPane;
				System.out.println(GameSetUp.selectedTile);
			}
		});
	}
	
	public void overlayInteract() {
		setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
					overlay.triggerOverlay(TileOverlay.getOverlayDx(), TileOverlay.getOverlayDy(),
							TileOverlay.getOverlayDelay());
			
			}
		});
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
	
	public void updateMinionIcon() {
		
		minionIconPane.getChildren().clear();
		
		ArrayList<Minion> minions = GameSetUp.selectedTile.getLocationType().getMinionOnLocation();

		for (int i = 0; i < minions.size(); i++) {

			minionIconPane.add(createMinionIcon(minions.get(i)), i % N_COLUMN, (int) i / N_COLUMN);
		}
	}
	
	public void updateMinionPane() {
		
		overlay.getMinionPane().getChildren().clear();

		ArrayList<Minion> minions = GameSetUp.selectedTile.getLocationType().getMinionOnLocation();

		for (int i = 0; i < minions.size(); i++) {
			addMinionToPane(minions.get(i), overlay.getMinionPane() , i);
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
		return "Location : "+locationType.getName()+"\n"
				+"Cost : "+locationType.getCost()+"\n"
				+"Row : "+row+", Column : "+column;
	}

////////////////////////////////////////////////////// END OF DEBUG ///////////////////////////////////////////////////////////////////////
}
