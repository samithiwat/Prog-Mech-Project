package gui;

import gui.entity.PlayerPanel;
//import gui.entity.HexagonalButton;
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
import logic.SceneController;

public class MapOverview implements Sceneable{
	private Scene scene;
	public MapOverview() {
		
		PlayerPanel playerPanel = new PlayerPanel();
		
		ImageView bg = new ImageView(ClassLoader.getSystemResource("img/background/MapOverview.png").toString());
//		ImageView bgMap = new ImageView(ClassLoader.getSystemResource("img/background/Map.png").toString());
//		bg.setFitHeight(800);
//		bg.setFitWidth(1200);
//		bgMap.setFitHeight(880);
//		bgMap.setPreserveRatio(true);
		
		Pane root = new Pane();
		root.getChildren().addAll(bg,playerPanel);
//		root.getChildren().addAll(bg,createHexAt(529,91.69));
//		root.getChildren().add(createHexAt(529, 91.69+68.98));
//		root.getChildren().add(createHexAt(583.25,57.2));
//		for(int i = 0 ; i < 10 ; i++) {
//			for(int j = 0 ; j < 11 ; j++) {
//				if(j%2 == 0 && i != 9)
//				{
////					System.out.println(j/2);
////					root.getChildren().add(createHexAt(532+(j/2)*(38.44+68.98),87+i*70));
//					root.getChildren().add(new HexagonalButton(532+(j/2)*(38.44+68.98),87+i*70,i,j));
//				}
//				else if(j%2 == 1) {
////					root.getChildren().add(createHexAt(586+((j-1)/2)*(38.44+68.98),52.2+i*70));
//					root.getChildren().add(new HexagonalButton(585.8+((j-1)/2)*(38.44+68.98),51.9+i*70,i,j));
//				}
//			}
//		}
		
		scene = new Scene(root, SceneController.getFullscreenWidth(), SceneController.getFullscreenHeight());
		scene.setCursor(CURSOR_NORMAL);
		scene.getStylesheets().add(ClassLoader.getSystemResource("css/map-style.css").toExternalForm());
		//Scene scene = new Scene(root);
		//this.scene = scene;
		this.scene.setOnKeyPressed(key -> {
			System.exit(0);
		});
		
	}
	
	public Scene getScene() {
		return this.scene;
	}

}