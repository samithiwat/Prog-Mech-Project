package gui.entity;

import component.location.Location;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Polygon;

public class HexagonPane extends Pane implements Clickable {

	private int x;
	private int y;
	private int row;
	private int column;
	
	private Location locationType;

	public HexagonPane(int width, int height, int x, int y,int row,int column) {

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
				setId("grid-release-style");
			}
		});
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
