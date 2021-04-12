package gui.entity;

import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Polygon;

public class HexagonPane extends Pane implements Clickable{

	
	public HexagonPane(int width,int height,int x,int y) {
		
		//////////////// FOR DEBUG ONLY //////////////////////		
		
		//System.out.println("x: "+x+", "+"y: "+y);
		
		//////////////// END OF DEBUG /////////////////////////
		
		double[] points = {
				81, 0.5,
				272, 0.5,
				350.75, 175.5,
				272, 350.5,
				81, 350.5,
				0.75, 175.5
		};
		
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

}
