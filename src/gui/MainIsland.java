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
import update.MainIslandUpdate;

public class MainIsland implements Sceneable {
	
	private final static int BG_CENTER_X = 720;
	private final static int BG_CENTER_Y = 670;
	
	private Scene scene;

	private static ImageView bg;
	private static int bgX = BG_CENTER_X;
	private static int bgY = BG_CENTER_Y;
	
	private static Button endTurn;
	private static PointPane governmentPoint;
	private static PointPane goodnessPoint;

	public MainIsland() {
		
		StatusPane statusPane = new StatusPane();

		TurnBar turnBar = new TurnBar();
		turnBar.setLayoutX(820);

		endTurn = new Button("End Turn");
		endTurn.setId("end-turn-button-release-style");
		endTurnInteract();

		MenuIcon handsIcon = new MenuIcon("img/icon/HandsIcon.png", 42, 632);

		governmentPoint = new PointPane(7, 10, Color.web("0xFFFFFF"));
		governmentPoint.setLayoutX(1287);
		governmentPoint.setLayoutY(706);

		goodnessPoint = new PointPane(5, 10, Color.web("0x3D3D3D"));
		goodnessPoint.setLayoutX(65);
		goodnessPoint.setLayoutY(592);
		
		
		Pane root = new Pane();

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
			//System.out.println(key.getCode());
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

			//////////////// FOR DEBUG ONLY //////////////////////
			if (key.getCode() == KeyCode.ESCAPE) {
				System.exit(0);
			}
			//////////////// END OF DEBUG /////////////////////////

		});

		//////////////// FOR DEBUG ONLY //////////////////////

//		HexagonPane test1 = new HexagonPane(350,350,0,0);
//		HexagonPane test2 = new HexagonPane(350,350,190+350,0);
//		root.getChildren().addAll(test1);

		//////////////// END OF DEBUG /////////////////////////
	}
	
	public static void moveBgLeft(int speed) {
		setBgX(getBgX() - speed);
		bg.setViewport(new Rectangle2D(getBgX(), getBgY(), SceneController.getFullscreenWidth(), SceneController.getFullscreenHeight()));
		System.out.println("x: "+getBgX()+", "+"y: "+getBgY());
	}
	
	public static void moveBgRight(int speed) {
		setBgX(getBgX() + speed);
		bg.setViewport(new Rectangle2D(getBgX(), getBgY(), SceneController.getFullscreenWidth(), SceneController.getFullscreenHeight()));
		System.out.println("x: "+getBgX()+", "+"y: "+getBgY());
	}
	
	public static void moveBgDown(int speed) {
		setBgY(getBgY() - speed);
		bg.setViewport(new Rectangle2D(getBgX(), getBgY(), SceneController.getFullscreenWidth(), SceneController.getFullscreenHeight()));
		System.out.println("x: "+getBgX()+", "+"y: "+getBgY());
	}
	
	public static void moveBgUp(int speed) {
		setBgY(getBgY() + speed);
		bg.setViewport(new Rectangle2D(getBgX(), getBgY(), SceneController.getFullscreenWidth(), SceneController.getFullscreenHeight()));
		System.out.println("x: "+getBgX()+", "+"y: "+getBgY());
	}
	
	private void endTurnInteract() {

		Rectangle shape = new Rectangle(200, 100);
		shape.setArcHeight(25);
		shape.setArcWidth(25);

		endTurn.setShape(shape);
		endTurn.setLayoutX(1287);
		endTurn.setLayoutY(742);
		endTurn.setPrefHeight(100);
		endTurn.setPrefWidth(200);
		endTurn.setFont(Font.font("Bai Jamjuree", FontWeight.BOLD, 34));

		endTurn.setOnMouseEntered(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				EFFECT_MOUSE_ENTER.play();
				scene.setCursor(CURSOR_SELECTED);
				endTurn.setId("end-turn-button-hold-style");
			}
		});

		endTurn.setOnMouseExited(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				scene.setCursor(CURSOR_NORMAL);
				endTurn.setId("end-turn-button-release-style");
			}
		});

		endTurn.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				CLICK_EFFECT.play();
				GameSetUp.isEndTurn = true;
			}
		});
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
	
}
