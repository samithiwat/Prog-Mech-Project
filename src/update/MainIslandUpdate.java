package update;

import java.util.ArrayList;

import gui.MainIsland;
import gui.MapOverview;
import gui.entity.HexagonPane;
import gui.entity.MapGrid;
import gui.entity.PlayerPanel;
import javafx.application.Platform;
import javafx.scene.input.KeyCode;
import logic.SceneController;

public class MainIslandUpdate {

	private final static int INIT_SPEED = 20;
	private final static int MAX_SPEED = 50;
	private final static int ACCELERATE = 8;
	private final static int ACCELERATE_TIME = 200; // milli sec
	private static int current_speed = INIT_SPEED;
	private static long now = System.currentTimeMillis();
	
// ---------------------------------------------------------- Interact Grid With Database ----------------------------------------------
	
	
	
// ---------------------------------------------------------- Move Grid With Background ------------------------------------------------

	public static void moveLeft() {
		calSpeed();
		if (MainIsland.getBgX() - current_speed > 0) {
			for (int i = 0; i < MapGrid.getGrids().size(); i++) {
				ArrayList<HexagonPane> column = MapGrid.getGrids().get(i);
				for (int j = 0; j < column.size(); j++) {
					column.get(j).moveRight(current_speed);

				}
			}
			MainIsland.moveBgLeft(current_speed);
		}

	}

	public static void moveRight() {
		calSpeed();
		if (MainIsland.getBgX() + current_speed < 2900 - SceneController.getFullscreenWidth() - 10) {
			for (int i = 0; i < MapGrid.getGrids().size(); i++) {
				ArrayList<HexagonPane> column = MapGrid.getGrids().get(i);
				for (int j = 0; j < column.size(); j++) {
					column.get(j).moveLeft(current_speed);

				}
			}
			MainIsland.moveBgRight(current_speed);
		}
	}

	public static void moveUp() {
		calSpeed();
		if (MainIsland.getBgY() - current_speed > 0) {
			for (int i = 0; i < MapGrid.getGrids().size(); i++) {
				ArrayList<HexagonPane> column = MapGrid.getGrids().get(i);
				for (int j = 0; j < column.size(); j++) {
					column.get(j).moveUp(current_speed);

				}
			}
			MainIsland.moveBgDown(current_speed);
		}
	}

	public static void moveDown() {
		calSpeed();
		if (MainIsland.getBgY() + current_speed < 2900 - SceneController.getFullscreenHeight()) {
			for (int i = 0; i < MapGrid.getGrids().size(); i++) {
				ArrayList<HexagonPane> column = MapGrid.getGrids().get(i);
				for (int j = 0; j < column.size(); j++) {
					column.get(j).moveDown(current_speed);

				}
			}
			MainIsland.moveBgUp(current_speed);
		}
	}
	
	public static void setCenter() {
			for (int i = 0; i < MapGrid.getGrids().size(); i++) {
				ArrayList<HexagonPane> column = MapGrid.getGrids().get(i);
				for (int j = 0; j < column.size(); j++) {
					column.get(j).setCenter();;

				}
			MainIsland.setBgCenter();
		}
	}
	

// ---------------------------------------------------------- Calculate Current Speed ------------------------------------------------
	
	private static void calSpeed() {
		if(System.currentTimeMillis() - now >= ACCELERATE_TIME) {
			now = System.currentTimeMillis();
			setCurrent_speed(current_speed+ACCELERATE);
		}
		
		//System.out.println(current_speed);
	}

// ---------------------------------------------------------- Getter and Setter ------------------------------------------------
	
	public static int getCurrent_speed() {
		return current_speed;
	}

	public static void setCurrent_speed(int current_speed) {
		if(current_speed <INIT_SPEED) {
			current_speed = INIT_SPEED;
		}
		if(current_speed > MAX_SPEED) {
			current_speed = MAX_SPEED;
		}
		MainIslandUpdate.current_speed = current_speed;
	}
	
}
