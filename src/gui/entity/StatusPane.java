package gui.entity;

import java.util.ArrayList;

import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;

public class StatusPane extends GridPane {

	private static StatusBar money;
	private static StatusBar minion;
	private static StatusBar land;

	public StatusPane() {
		super();

// ------------------------------------------------------ Set Up Pane --------------------------------------------------------------

		for (int i = 0; i < 3; i++) {
			ColumnConstraints column = new ColumnConstraints(240);
			getColumnConstraints().add(column);
		}

		setAlignment(Pos.CENTER);

// ------------------------------------------------------ Set Up Button Pane -------------------------------------------------------

		HBox buttonPane = new HBox();
		buttonPane.setSpacing(70);
		buttonPane.setPadding(new Insets(10, 80, 5, 80));
		buttonPane.setAlignment(Pos.CENTER);
		buttonPane.setId("button-pane");

		CircleButton finance = new CircleButton("img/icon/GoldIngot.png", 50, 50, 25, 0, 0);

		finance.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {

			}
		});

		// CircleButton currentLaw = new CircleButton("", 0, 50, 50, 25,
		// Color.web("0xFECEB8"), 206, 65);

		// CircleButton landInfo = new CircleButton("", 0, 50, 50, 25,
		// Color.web("0xFECEB8"), 326, 65);

		CircleButton characterInfo = new CircleButton("!", 36, Color.web("0xFECEB8"), 50, 50, 25, 0, 0);

		CircleButton toggleGrid = new CircleButton("img/icon/GridIcon.png", 50, 50, 25, 0, 0);

		buttonPane.getChildren().addAll(finance, characterInfo, toggleGrid);

		// buttonPane.getChildren().addAll(finance, currentLaw, landInfo, characterInfo,
		// toggleGrid);

// --------------------------------------------------- Set Up Status Bar --------------------------------------------------------------

		money = new StatusBar("img/icon/Coin.png", 36, 10, Color.web("FEFDE8"), new Insets(5, 10, 5, 10));

		minion = new StatusBar("img/icon/FoxMinion.png", 36, 60, Color.web("FEFDE8"), new Insets(5, 60, 5, 60));

		land = new StatusBar("img/icon/House1.png", 36, 50, Color.web("FEFDE8"), new Insets(5, 60, 5, 40));

// --------------------------------------------------- Add Components to Pane ----------------------------------------------------------

		add(money, 0, 0);
		add(minion, 1, 0);
		add(land, 2, 0);
		add(buttonPane, 0, 1, 3, 1);

	}

	public static StatusBar getMoney() {
		return money;
	}

	public static StatusBar getMinion() {
		return minion;
	}

	public static StatusBar getLand() {
		return land;
	}

}
