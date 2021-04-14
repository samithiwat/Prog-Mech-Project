package gui.entity;

import component.location.Location;
import gui.overlay.Overlay;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Polygon;

public class HexagonPane extends Pane implements Clickable {

	private int x;
	private int y;
	
	private Overlay overlay;
	private Location locationType;
	
	////////////////FOR DEBUG ONLY //////////////////////
	private int row;
	private int column;
	////////////////END OF DEBUG /////////////////////////
	
	

	public HexagonPane(int width, int height, int x, int y,
			
	////////////////FOR DEBUG ONLY //////////////////////
	
	int row,int column) {

	////////////////END OF DEBUG /////////////////////////
		

		//////////////// FOR DEBUG ONLY //////////////////////

		setRow(row);
		setColumn(column);
		
		// System.out.println("x: "+x+", "+"y: "+y);

		//////////////// END OF DEBUG /////////////////////////

		setX(x);
		setY(y);

		double[] points = { 
				53, 0.5, 
				197, 0.5,
				250, 125.5,
				197, 250.5,
				53, 250.5,
				0, 125.5 };

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
				if(MapGrid.isEnable()) {
					setId("grid-release-style");
				}
				else {
					setId("grid-disable");
				}
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

// ------------------------------------------------ Getter and Setter ------------------------------------------------------------

	public int getX() {
		return x;
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
	
		public Overlay getOverlay() {
		return overlay;
	}

	public void setOverlay(Overlay overlay) {
		this.overlay = overlay;
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
		return "<row :" + this.row + ", column : "+ this.column+">";
	}
	
////////////////////////////////////////////////////// END OF DEBUG ///////////////////////////////////////////////////////////////////////
}
