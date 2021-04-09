package gui.entity;

import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class CircleButton extends MenuButton {

	public CircleButton(String content, int contentSize, int width, int height, double radius, Color textColor,int x, int y) {
		super(content, contentSize, width, height, textColor, x, y);
		Circle shape = new Circle(radius);
		setId("circle-button-release-style");
		setShape(shape);
		interact();
	}

	public void setButtonImage(Image img) {
		setBackground(new Background(new BackgroundImage(img, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
				BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));
	}

	public void interact() {
		setOnMouseEntered(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				setId("circle-button-hold-style");
				setCursor(MOUSE_SELECT);
				EFFECT_MOUSE_ENTER.play();
			}
		});
		
		setOnMouseExited(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				setId("circle-button-release-style");
				setCursor(MOUSE_NORMAL);
			}
		});
	
	}
	
}
