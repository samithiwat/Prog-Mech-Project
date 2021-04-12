package gui.entity;

import javafx.scene.layout.Pane;

public class MapGrid extends Pane {

	private final int HEXAGON_WIDTH = 350;
	private final int HEXAGON_HEIGHT = 350;
	private final int HEXAGON_DISSTANCE_X = 190;

	public MapGrid() {
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				int dx = j * (HEXAGON_DISSTANCE_X + HEXAGON_WIDTH);
				int dy = i * HEXAGON_HEIGHT;
				HexagonPane hexagonPaneEven = new HexagonPane(HEXAGON_WIDTH, HEXAGON_HEIGHT, 132 + dx, -176 + dy);
				HexagonPane hexagonPaneOdd = new HexagonPane(HEXAGON_WIDTH, HEXAGON_HEIGHT, -139 + dx, -351 + dy);
				getChildren().addAll(hexagonPaneOdd, hexagonPaneEven);
			}
		}
	}

}
