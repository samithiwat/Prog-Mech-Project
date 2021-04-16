package gui.entity;

import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class CircleButton extends StackPane implements Clickable {

	private Label label;
	private boolean isClicked;

	public CircleButton(String content, int contentSize, Color textColor, int width, int height, double radius, int x,
			int y) {

		setId("circle-button-release-style");
		setAlignment(Pos.CENTER);

		label = new Label(content);
		label.setId("circle-button-text-style");
		label.setFont(Font.font("Bai Jamjuree", FontWeight.BOLD, 36));
		
		isClicked = false;

		setLayoutX(x);
		setLayoutY(y);

		setPrefWidth(width);
		setPrefHeight(height);

		Circle shape = new Circle(radius, radius + x, radius + y);
		setClip(shape);

		getChildren().add(label);

		interact();
	}

	public CircleButton(String img_path, int width, int height, int radius, int x, int y) {

		setId("circle-button-release-style");
		setAlignment(Pos.CENTER);

		label = null;

		ImageView img = new ImageView(ClassLoader.getSystemResource(img_path).toString());
		
		setLayoutX(x);
		setLayoutY(y);

		setPrefWidth(width);
		setPrefHeight(height);

		Circle shape = new Circle(radius, radius + x, radius + y);
		setClip(shape);

		getChildren().add(img);

		interact();
	}

//	public void setButtonImage(Image img) {
//		setBackground(new Background(new BackgroundImage(img, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
//				BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));
//	}

	public void interact() {
		setOnMouseEntered(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				if(isClicked == false) {
					setId("circle-button-hold-style");					
				}
				EFFECT_MOUSE_ENTER.play();
				setCursor(MOUSE_SELECT);
			}
		});

		setOnMouseExited(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				if(isClicked == false) {
					setId("circle-button-release-style");
				}
				setCursor(MOUSE_NORMAL);					
			}
		});

	}

	@Override
	public void triggerDisable() {
		setDisable(!isDisable());
	}

	//-------setter/getter---------

	public boolean isClicked() {
		return isClicked;
	}

	public void setClicked(boolean isClicked) {
		this.isClicked = isClicked;
	}
	
}
