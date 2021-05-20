package gui;

import gui.entity.MenuIcon;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

public class SelectMapScenes {
	private Scene scene;

	public SelectMapScenes() {
//--------------------------------------------BG-------------------------------------------------------
		ImageView bg = new ImageView(ClassLoader.getSystemResource("img/background/MapBackGround.png").toString());
		MenuIcon smallIsland = new MenuIcon("img/icon/PrisonIslandEmpty.png", 221, 214);
		MenuIcon bigIsland = new MenuIcon("img/icon/BigIslandEmpty.png", 1100, 817);
		AnchorPane mapPane = new AnchorPane();
		HBox hbox = new HBox();
		hbox.setPadding(new Insets(22));
		hbox.getChildren().add(smallIsland);
		GridPane gridPane = new GridPane();
		gridPane.setAlignment(Pos.CENTER);
		gridPane.add(bigIsland, 0, 0);
		hbox.getChildren().add(gridPane);

		mapPane.getChildren().addAll(bg, hbox);
		this.scene = new Scene(mapPane);
		this.scene.setOnKeyPressed(key -> {
			System.exit(0);
		});

	}

	public Scene getScene() {
		return this.scene;
	}
}
