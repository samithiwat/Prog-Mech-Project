package gui;

import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.media.AudioClip;
import javafx.scene.shape.Polygon;
import logic.AudioLoader;

public class GameMapScene {
	private Scene scene;
	public GameMapScene() {
//		ImageView bg = new ImageView(ClassLoader.getSystemResource("img/background/GameBackGround.png").toString());
		ImageView bgMap = new ImageView(ClassLoader.getSystemResource("img/background/Map.png").toString());
//		bg.setFitHeight(800);
//		bg.setFitWidth(1200);
		bgMap.setFitHeight(880);
		bgMap.setPreserveRatio(true);
		
		Pane root = new Pane();
		root.getChildren().addAll(bgMap, createHexAt(179,166));
		Scene scene = new Scene(root);
		this.scene = scene;
		this.scene.setOnKeyPressed(key -> {
			System.exit(0);
		});
		
	}
	
	public Scene getScene() {
		return this.scene;
	}

public Button createHexAt(double xPos, double yPos) {
    Button aButton = new Button();
    aButton.setLayoutX(xPos);
    aButton.setLayoutY(yPos);
    aButton.setPrefWidth(76);
    aButton.setPrefHeight(76.3);
//    double[] path = new double[12];
    double[] path = {79,0,0,175,70,350,271,350,350,175,271,0};
//    for (int q = 0; q < 6; q++) {
//        double x = Math.cos(Math.PI / 3.0 * q + Math.PI / 2.0);
//        double y = Math.sin(Math.PI / 3.0 * q + Math.PI / 2.0);
//        path[q * 2] = x;
//        path[q * 2 + 1] = y;
//}
    Polygon aPoly = new Polygon(path);
    aButton.setShape(aPoly);
    aButton.setStyle("-fx-background-color: transparent;-fx-border-color: #000000;\r\n"
    		+ "    -fx-border-width: 2;");
    
    aButton.setPickOnBounds(false);
    return aButton;
}
}