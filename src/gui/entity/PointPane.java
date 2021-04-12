package gui.entity;

import java.util.ArrayList;

import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class PointPane extends HBox {

	private int nPoints;
	private ArrayList<Circle> points;
	private Color normalColor;

	public PointPane(int nPoints, double space, Color normalColor) {

// -------------------------------------- Initialize Variable -----------------------------------------------

		this.normalColor = normalColor;
		this.nPoints = nPoints;

// -------------------------------------- Set Up Pane --------------------------------------------------------

		setSpacing(space);

// ------------------------------------- Create Point Bar ----------------------------------------------------

		for (int i = 0; i < nPoints; i++) {

			Circle point = new Circle(10);
			point.setFill(normalColor);

			points = new ArrayList<Circle>();
			points.add(point);

			getChildren().add(point);
		}
	}

	public void updatePoint(int nCounted, Color countedColor) {
		for (int i = 0; i < nPoints; i++) {
			if (i < nCounted) {
				points.get(i).setFill(countedColor);
			} else {
				points.get(i).setFill(normalColor);
			}
		}
	}
}
