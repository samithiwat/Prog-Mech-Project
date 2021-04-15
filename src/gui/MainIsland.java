package gui;

import gui.entity.HexagonPane;
import gui.entity.MapGrid;
import gui.entity.MenuIcon;
import gui.entity.PlayerPanel;
import gui.entity.PointPane;
import gui.entity.StatusPane;
import gui.entity.TurnBar;
import javafx.event.EventHandler;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import logic.GameSetUp;
import logic.SceneController;
import update.AudioUpdate;
import update.MainIslandUpdate;

public class MainIsland implements Sceneable {
	
	private final static int BG_CENTER_X = 720;
	private final static int BG_CENTER_Y = 670;
	private static int bgX = BG_CENTER_X;
	private static int bgY = BG_CENTER_Y;
	
	private Scene scene;
	
	private static Pane root;
	
	private static ImageView bg;
	
	private static Button endTurn = PlayerPanel.getEndTurn();
	private static PointPane governmentPoint = PlayerPanel.getGovernmentPoint();
	private static PointPane goodnessPoint = PlayerPanel.getGoodnessPoint();
	private static MenuIcon handsIcon = PlayerPanel.getHandsIcon();
	
	private StatusPane statusPane = PlayerPanel.getStatusPane();
	private TurnBar turnBar = PlayerPanel.getTurnBar();

	public MainIsland() {
		
		root = new Pane();

		bg = new ImageView(ClassLoader.getSystemResource("img/background/MainIsland.png").toString());
		bg.setViewport(new Rectangle2D(BG_CENTER_X, BG_CENTER_Y, SceneController.getFullscreenWidth(),
				SceneController.getFullscreenHeight()));

		MapGrid grid = new MapGrid();
		
		
		root.getChildren().addAll(bg, grid);
		
		root.getChildren().addAll(statusPane, turnBar, handsIcon, endTurn, governmentPoint, goodnessPoint);
		
		scene = new Scene(root, SceneController.getFullscreenWidth(), SceneController.getFullscreenHeight());
		scene.setCursor(CURSOR_NORMAL);
		scene.getStylesheets().add(ClassLoader.getSystemResource("css/map-style.css").toExternalForm());

		scene.setOnKeyPressed(key -> {
			if (key.getCode() == KeyCode.A || key.getCode() == KeyCode.LEFT) {
				MainIslandUpdate.moveLeft();
			}
			if (key.getCode() == KeyCode.D || key.getCode() == KeyCode.RIGHT) {
				MainIslandUpdate.moveRight();
			}
			if (key.getCode() == KeyCode.W || key.getCode() == KeyCode.UP) {
				MainIslandUpdate.moveUp();
			}
			if (key.getCode() == KeyCode.S || key.getCode() == KeyCode.DOWN) {
				MainIslandUpdate.moveDown();
			}

			if (key.getCode() == KeyCode.ESCAPE) {
				AudioUpdate.toMapOverview(null);
				MapOverview.getSceneRoot().getChildren().set(1, new PlayerPanel());
				SceneController.setScene(SceneController.getMapOverView());
			}
		});
		
		scene.setOnKeyReleased(key->{
			MainIslandUpdate.setCurrent_speed(0); 
		});

		//////////////// FOR DEBUG ONLY //////////////////////
		
//		System.out.println("Width : "+bg.getFitWidth()+", Height : "+bg.getFitHeight());
		
//		System.out.println(MapGrid.getGrids());

//		HexagonPane test1 = new HexagonPane(350,350,0,0);
//		HexagonPane test2 = new HexagonPane(350,350,190+350,0);
//		root.getChildren().addAll(test1);

		//////////////// END OF DEBUG /////////////////////////
	}
	
	public static void moveBgLeft(int speed) {
		setBgX(getBgX() - speed);
		bg.setViewport(new Rectangle2D(getBgX(), getBgY(), SceneController.getFullscreenWidth(), SceneController.getFullscreenHeight()));
	}
	
	public static void moveBgRight(int speed) {
		setBgX(getBgX() + speed);
		bg.setViewport(new Rectangle2D(getBgX(), getBgY(), SceneController.getFullscreenWidth(), SceneController.getFullscreenHeight()));
	}
	
	public static void moveBgDown(int speed) {
		setBgY(getBgY() - speed);
		bg.setViewport(new Rectangle2D(getBgX(), getBgY(), SceneController.getFullscreenWidth(), SceneController.getFullscreenHeight()));
	}
	
	public static void moveBgUp(int speed) {
		setBgY(getBgY() + speed);
		bg.setViewport(new Rectangle2D(getBgX(), getBgY(), SceneController.getFullscreenWidth(), SceneController.getFullscreenHeight()));
	}

// ------------------------------------------------ Getter and Setter ------------------------------------------------------------
	
	@Override
	public Scene getScene() {
		return this.scene;
	}

	public static ImageView getBg() {
		return bg;
	}

	public static int getBG_CENTER_X() {
		return BG_CENTER_X;
	}

	public static int getBG_CENTER_Y() {
		return BG_CENTER_Y;
	}

	public static int getBgX() {
		return bgX;
	}

	public static void setBgX(int bgX) {
		MainIsland.bgX = bgX;
	}

	public static int getBgY() {
		return bgY;
	}

	public static void setBgY(int bgY) {
		MainIsland.bgY = bgY;
	}

	public static Pane getSceneRoot() {
		return root;
	}

	public static void setSceneRoot(Pane root) {
		MainIsland.root = root;
	}
	
}
