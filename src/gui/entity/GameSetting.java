package gui.entity;

import java.util.ArrayList;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.FontWeight;
import update.GameSettingUpdate;

public class GameSetting extends AnchorPane {

	private final int BOX_WIDTH = 200;
	private final int BOX_HEIGHT = 25;
	
	private static ArrayList<TextTitle> texts;

	public GameSetting() {

		setId("setting-menu");
		setPrefHeight(435);
		setPrefWidth(250);

		TextTitle map = new TextTitle("Map", Color.WHITE, FontWeight.BOLD, 14, 110, 267);
		Rectangle mapBox1 = new Rectangle(BOX_WIDTH, BOX_HEIGHT);
		mapBox1.setId("box");
		mapBox1.setX(25);
		mapBox1.setY(250);
		Rectangle mapBox2 = new Rectangle(BOX_WIDTH, BOX_HEIGHT);
		mapBox2.setId("box");
		mapBox2.setX(25);
		mapBox2.setY(295);
		MenuIcon mapButton1 = new MenuIcon("img/icon/triangleButton.png", 28, 297);
		MenuIcon mapButton2 = new MenuIcon("img/icon/triangleButton.png", 202, 297);
		mapButton2.setRotate(180);
		TextTitle mapName = new TextTitle("Coconut Island", Color.WHITE, FontWeight.BOLD, 14, 74, 312);

		TextTitle maxPlayer = new TextTitle("Max Player", Color.WHITE, FontWeight.BOLD, 14, 86, 358);
		Rectangle maxPlayerBox1 = new Rectangle(BOX_WIDTH, BOX_HEIGHT);
		maxPlayerBox1.setId("box");
		maxPlayerBox1.setX(25);
		maxPlayerBox1.setY(340);
		Rectangle maxPlayerBox2 = new Rectangle(BOX_WIDTH, BOX_HEIGHT);
		maxPlayerBox2.setId("box");
		MenuIcon maxPlayerButton1 = new MenuIcon("img/icon/triangleButton.png", 28, 387);
		
		maxPlayerButton1.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				GameSettingUpdate.maxPlayerUpdate(false);
			}
		
		});
		
		MenuIcon maxPlayerButton2 = new MenuIcon("img/icon/triangleButton.png", 202, 387);
		maxPlayerButton2.setRotate(180);
		maxPlayerBox2.setX(25);
		maxPlayerBox2.setY(385);
		TextTitle maxPlayerNum = new TextTitle(""+GameSettingUpdate.getNPlayer(), Color.WHITE, FontWeight.BOLD, 14, 119, 402);

		maxPlayerButton2.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				GameSettingUpdate.maxPlayerUpdate(true);
			}
		});
		
		texts = new ArrayList<TextTitle>();
		texts.add(maxPlayerNum);
		texts.add(mapName);
		
		getChildren().addAll(mapBox1, mapBox2, map, mapButton1, maxPlayerBox1, maxPlayerBox2, mapButton2, maxPlayer,
				maxPlayerButton1, maxPlayerButton2, mapName, maxPlayerNum);

	}

	public static ArrayList<TextTitle> getTexts() {
		return texts;
	}
	
}
