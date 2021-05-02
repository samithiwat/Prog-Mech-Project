package gui.entity;

import java.util.ArrayList;

import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

public class MapGrid extends Pane {

	private final int HEXAGON_WIDTH = 200;
	private final int HEXAGON_HEIGHT = 200;
	private final double HEXAGON_DISSTANCE_X = 100;
	private final double HEXAGON_INIT_X_ODD = -100;
	private final double HEXAGON_INIT_Y_ODD = -300;
	private final double HEXAGON_INIT_X_EVEN = 50;
	private final double HEXAGON_INIT_Y_EVEN = -200;
	private static final int N_ROW = 9;
	private static final int N_COLUMN = 6;

	private static boolean isEnable = true;
	private static ArrayList<ArrayList<HexagonPane>> grids = new ArrayList<ArrayList<HexagonPane>>();
	private static ArrayList<ArrayList<GridPane>> minionIconPanes = new ArrayList<ArrayList<GridPane>>();
	
	public static HexagonPane councilTile;

	public MapGrid() {
		for (int i = 0; i < N_ROW; i++) {
			
			ArrayList<HexagonPane> column = new ArrayList<HexagonPane>();
			ArrayList<GridPane> iconColumn = new ArrayList<GridPane>();
			
			for (int j = 0; j < N_COLUMN; j++) {
				double dx = j * (HEXAGON_DISSTANCE_X + HEXAGON_WIDTH);
				double dy = i * HEXAGON_HEIGHT;
				if (j == 5) {
					HexagonPane hexagonPaneOdd = new HexagonPane(HEXAGON_WIDTH, HEXAGON_HEIGHT, HEXAGON_INIT_X_ODD + dx,
							HEXAGON_INIT_Y_ODD + dy, i, j*2);
					column.add(hexagonPaneOdd);
					iconColumn.add(hexagonPaneOdd.getMinionIconPane());
					getChildren().add(hexagonPaneOdd);
				} else {
					HexagonPane hexagonPaneOdd = new HexagonPane(HEXAGON_WIDTH, HEXAGON_HEIGHT, HEXAGON_INIT_X_ODD + dx,
							HEXAGON_INIT_Y_ODD + dy, i, j*2);
					HexagonPane hexagonPaneEven = new HexagonPane(HEXAGON_WIDTH, HEXAGON_HEIGHT,
							HEXAGON_INIT_X_EVEN + dx, HEXAGON_INIT_Y_EVEN + dy, i, j*2+1);
					column.add(hexagonPaneOdd);
					iconColumn.add(hexagonPaneOdd.getMinionIconPane());
					column.add(hexagonPaneEven);
					iconColumn.add(hexagonPaneEven.getMinionIconPane());
					getChildren().addAll(hexagonPaneOdd, hexagonPaneEven);
				}
			}
			grids.add(column);
			minionIconPanes.add(iconColumn);
		}
		councilTile = grids.get(3).get(5);
	}

// ------------------------------------------------ Getter and Setter ------------------------------------------------------------

	public static ArrayList<ArrayList<HexagonPane>> getGrids() {
		return grids;
	}

	public static ArrayList<ArrayList<GridPane>> getMinionIconPanes() {
		return minionIconPanes;
	}

	public static int getN_ROW() {
		return N_ROW;
	}

	public static int getN_COLUMN() {
		return N_COLUMN;
	}

	public static boolean isEnable() {
		return isEnable;
	}

	public static void setEnable(boolean isEnable) {
		MapGrid.isEnable = isEnable;
	}

}
