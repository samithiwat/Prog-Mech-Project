package gui.entity;

import javafx.scene.layout.Pane;
import javafx.scene.shape.Polygon;

public class HexagonalButton extends Pane{
	private int ordX;
	private int ordY;
	public HexagonalButton(double posX, double posY,int ordX, int ordY) {
		this.ordX = ordX;
		this.ordY = ordY;
		this.setLayoutX(posX);
		this.setLayoutY(posY);
		this.setPrefWidth(70);
		this.setPrefHeight(70);
		double[] path = {54.035,70,70.07,34.49,54.035,0,16.035,0,0,34.49,16.035,70};
		Polygon aPoly = new Polygon(path);
		this.setShape(aPoly);
	    this.setStyle("-fx-background-color: transparent;-fx-border-color: #000000;\r\n"
	    		+ "    -fx-border-width: 0.5;");
	    if(ordX == 4 && ordY == 5) {
	    	this.setStyle("-fx-background-color: transparent;-fx-border-color: #000000;\r\n"
	    			+ "    -fx-border-width: 5;");
	    }
	    
	    this.setPickOnBounds(false);
	}
}
