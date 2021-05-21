package gui.entity;

import java.util.ArrayList;

import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.FontWeight;
import update.GameSettingUpdate;

public class GameSetting extends StackPane {

	private final int BOX_WIDTH = 200;
	private final int BOX_HEIGHT = 30;
	private final int MAP_BG_WIDTH = 250;
	private final int MAP_BG_HEIGHT = 250;

	private static ArrayList<TextTitle> texts;

	public GameSetting() {

		setId("setting-menu");
		setPrefHeight(435);
		setPrefWidth(250);
		setAlignment(Pos.CENTER);
		
		VBox component = new VBox();
		component.setSpacing(50);
		component.setAlignment(Pos.CENTER);
		component.setPadding(new Insets(25));
		
		ImageView mapImg = new ImageView(ClassLoader.getSystemResource("img/background/MainIslandOverview.png").toString());
		mapImg.setFitHeight(146);
		mapImg.setFitWidth(200);
		
		StackPane mapBg = new StackPane(mapImg);
		mapBg.setPrefWidth(MAP_BG_WIDTH);
		mapBg.setPrefHeight(MAP_BG_HEIGHT);
		mapBg.setAlignment(Pos.CENTER);
		mapBg.setId("map-bg");
		
		VBox settingPane = new VBox();
		settingPane.setSpacing(15);
		settingPane.setAlignment(Pos.CENTER);

		TextTitle map = new TextTitle("Map", Color.WHITE, FontWeight.BOLD, 14, 100, 14);
		StackPane mapBox1 = new StackPane(map);
		mapBox1.setAlignment(Pos.CENTER);
		mapBox1.setPrefWidth(BOX_WIDTH);
		mapBox1.setPrefHeight(BOX_HEIGHT);
		mapBox1.setId("box");
		MenuIcon mapButton1 = new MenuIcon("img/icon/triangleButton.png",0,1);
		MenuIcon mapButton2 = new MenuIcon("img/icon/triangleButton.png",184,1);
		mapButton2.setRotate(180);
		TextTitle mapName = new TextTitle("Coconut Island", Color.WHITE, FontWeight.BOLD, 14, 0, 14);
		HBox mapBox2 = new HBox();
		mapBox2.getChildren().addAll(mapButton1,mapName,mapButton2);
		mapBox2.setPrefWidth(BOX_WIDTH);
		mapBox2.setPrefHeight(BOX_HEIGHT);
		mapBox2.setAlignment(Pos.CENTER);
		mapBox2.setSpacing(50);
		mapBox2.setId("box");

		TextTitle maxPlayer = new TextTitle("Max Player", Color.WHITE, FontWeight.BOLD, 14, 0, 14);
		StackPane maxPlayerBox1 = new StackPane(maxPlayer);
		maxPlayerBox1.setPrefHeight(BOX_HEIGHT);
		maxPlayerBox1.setPrefWidth(BOX_WIDTH);
		maxPlayerBox1.setAlignment(Pos.CENTER);
		maxPlayerBox1.setId("box");

		HBox maxPlayerBox2 = new HBox();
		maxPlayerBox2.setPrefHeight(BOX_HEIGHT);
		maxPlayerBox2.setPrefWidth(BOX_WIDTH);
		maxPlayerBox2.setAlignment(Pos.CENTER);
		maxPlayerBox2.setSpacing(95);
		maxPlayerBox2.setId("box");
		MenuIcon maxPlayerButton1 = new MenuIcon("img/icon/triangleButton.png",0,1);

		maxPlayerButton1.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				GameSettingUpdate.maxPlayerUpdate(false);
			}

		});

		MenuIcon maxPlayerButton2 = new MenuIcon("img/icon/triangleButton.png", 183, 1);
		maxPlayerButton2.setRotate(180);
		TextTitle maxPlayerNum = new TextTitle("" + GameSettingUpdate.getNPlayer(), Color.WHITE, FontWeight.BOLD, 14,
				119, 14);

		maxPlayerButton2.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				GameSettingUpdate.maxPlayerUpdate(true);
			}
		});
		
		maxPlayerBox2.getChildren().addAll(maxPlayerButton1,maxPlayerNum,maxPlayerButton2);

		settingPane.getChildren().addAll(mapBox1,mapBox2,maxPlayerBox1,maxPlayerBox2);
		
		texts = new ArrayList<TextTitle>();
		texts.add(maxPlayerNum);
		texts.add(mapName);
		component.getChildren().addAll(mapBg,settingPane);
		getChildren().addAll(component);

	}

	public static ArrayList<TextTitle> getTexts() {
		return texts;
	}

}
