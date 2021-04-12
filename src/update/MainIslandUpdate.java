package update;

import java.util.ArrayList;

import gui.MainIsland;
import gui.entity.HexagonPane;
import gui.entity.MapGrid;
import logic.SceneController;

public class MainIslandUpdate {

	private final static int SPEED = 20;
	
// ---------------------------------------------------------- Interact Grid With Database ----------------------------------------------
	
	
	
// ---------------------------------------------------------- Move Grid With Background ------------------------------------------------

	public static void moveLeft() {
		if (MainIsland.getBgX() - SPEED > 0) {
			for (int i = 0; i < MapGrid.getGrids().size(); i++) {
				ArrayList<HexagonPane> column = MapGrid.getGrids().get(i);
				for (int j = 0; j < column.size(); j++) {
					column.get(j).moveRight(SPEED);

				}
			}
			MainIsland.moveBgLeft(SPEED);
		}

	}

	public static void moveRight() {
		if (MainIsland.getBgX() + SPEED < 2900 - SceneController.getFullscreenWidth() - 10) {
			for (int i = 0; i < MapGrid.getGrids().size(); i++) {
				ArrayList<HexagonPane> column = MapGrid.getGrids().get(i);
				for (int j = 0; j < column.size(); j++) {
					column.get(j).moveLeft(SPEED);

				}
			}
			MainIsland.moveBgRight(SPEED);
		}
	}

	public static void moveUp() {
		if (MainIsland.getBgY() - SPEED > 0) {
			for (int i = 0; i < MapGrid.getGrids().size(); i++) {
				ArrayList<HexagonPane> column = MapGrid.getGrids().get(i);
				for (int j = 0; j < column.size(); j++) {
					column.get(j).moveUp(SPEED);

				}
			}
			MainIsland.moveBgDown(SPEED);
		}
	}

	public static void moveDown() {
		if (MainIsland.getBgY() + SPEED < 2900 - SceneController.getFullscreenHeight()) {
			for (int i = 0; i < MapGrid.getGrids().size(); i++) {
				ArrayList<HexagonPane> column = MapGrid.getGrids().get(i);
				for (int j = 0; j < column.size(); j++) {
					column.get(j).moveDown(SPEED);

				}
			}
			MainIsland.moveBgUp(SPEED);
		}
	}

}
