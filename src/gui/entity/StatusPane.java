package gui.entity;

import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;

public class StatusPane extends GridPane {

	public StatusPane() {
		super();
		
// ------------------------------------------------------ Set Up Pane --------------------------------------------------------------
		
		setAlignment(Pos.CENTER);
		
// ------------------------------------------------------ Set Up Button Pane -------------------------------------------------------
		
		HBox ButtonPane = new HBox();
		ButtonPane.setSpacing(70);

		CircleButton finance = new CircleButton("", 0, 50, 50, 25, Color.web("0xFECEB8"), 86, 65);
		finance.setButtonImage(new Image(ClassLoader.getSystemResource("img/icon/GoldIngot.png").toString()));

		finance.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {

			}
		});

		CircleButton currentLaw = new CircleButton("", 0, 50, 50, 25, Color.web("0xFECEB8"), 206, 65);

		CircleButton landInfo = new CircleButton("", 0, 50, 50, 25, Color.web("0xFECEB8"), 326, 65);

		CircleButton characterInfo = new CircleButton("!", 40, 50, 50, 25, Color.web("0xFECEB8"), 446, 65);

		CircleButton toggleGrid = new CircleButton("", 0, 50, 50, 25, Color.web("0xFECEB8"), 566, 65);

		ButtonPane.getChildren().addAll(finance, currentLaw, landInfo, characterInfo, toggleGrid);

// --------------------------------------------------- Set Up Status Bar --------------------------------------------------------------
		
		StatusBar money = new StatusBar(new ImageView(ClassLoader.getSystemResource("img/icon/Coin.png").toString()),
				"$1 M", 36, Color.web("FEFDE8"));

		StatusBar minion = new StatusBar(
				new ImageView(ClassLoader.getSystemResource("img/icon/FoxMinion.png").toString()), "2", 36,
				Color.web("FEFDE8"));

		StatusBar land = new StatusBar(new ImageView(ClassLoader.getSystemResource("img/icon/House1.png").toString()),
				"3", 36, Color.web("FEFDE8"));

// --------------------------------------------------- Add Components to Pane ----------------------------------------------------------
		
		add(money, 0, 0);
		add(minion, 1, 0);
		add(land, 2, 0);
		add(ButtonPane, 1, 0, 3, 1);
	}

}
