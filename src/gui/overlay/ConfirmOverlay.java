package gui.overlay;

import gui.entity.MenuButton;
import gui.entity.TextTitle;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.FontWeight;

public class ConfirmOverlay extends Overlay {

	private final static int WIDTH = 1000;
	private final static int HEIGHT = 500;

	private VBox content;
	private VBox textBox;
	private TextTitle textLine1;
	private TextTitle textLine2;
	private MenuButton yes;
	private MenuButton no;

	public ConfirmOverlay() {
		super((new Pane()), WIDTH, HEIGHT, 250, -800);
		prefHeight(HEIGHT);
		prefWidth(WIDTH);
		setId("mainmenu-overlay");

		content = new VBox();
		content.setAlignment(Pos.CENTER);
		content.setSpacing(70);
		content.setPadding(new Insets(80, 67, 50, 68));

		Rectangle bg = new Rectangle(WIDTH, HEIGHT);
		bg.setId("overlay-bg");

		textBox = new VBox();
		textBox.setAlignment(Pos.CENTER);

		textLine1 = new TextTitle("", Color.WHITE, FontWeight.BOLD, 50, 0, 0);

		textLine2 = new TextTitle("", Color.WHITE, FontWeight.BOLD, 50, 0, 0);

		textBox.getChildren().addAll(textLine1, textLine2);
		textBox.setLayoutX(250);
		textBox.setLayoutY(100);

		HBox buttonPane = new HBox();
		buttonPane.setSpacing(65);
		buttonPane.setAlignment(Pos.CENTER);

		yes = new MenuButton("Yes", 50, 400, 100, Color.WHITE);

		no = new MenuButton("No", 50, 400, 100, Color.WHITE);

		buttonPane.getChildren().addAll(yes, no);
		
		content.getChildren().addAll(textBox,buttonPane);

		root.getChildren().addAll(bg, content);
	}

	public TextTitle getTextLine1() {
		return textLine1;
	}

	public TextTitle getTextLine2() {
		return textLine2;
	}

	public MenuButton getYes() {
		return yes;
	}

	public MenuButton getNo() {
		return no;
	}

	public VBox getTextBox() {
		return textBox;
	}

	public VBox getContent() {
		return content;
	}
	

}
