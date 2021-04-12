package gui;

import gui.entity.HexagonPane;
import gui.entity.MapGrid;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import logic.SceneController;

public class MainIsland implements Sceneable {

	private Scene scene;

	public MainIsland() {
		Pane root = new Pane();
		
		MapGrid grid = new MapGrid();

		root.getChildren().addAll(grid);

		scene = new Scene(root, SceneController.getFullscreenWidth(), SceneController.getFullscreenHeight());
		scene.setCursor(CURSOR_NORMAL);
		scene.getStylesheets().add(ClassLoader.getSystemResource("css/map-style.css").toExternalForm());
		
		//////////////// FOR DEBUG ONLY //////////////////////
		
		this.scene.setOnKeyPressed(key -> {
			if(key.getCode() == KeyCode.ESCAPE ) {
				System.exit(0);
			}
			
		});
		
		
		
//		HexagonPane test1 = new HexagonPane(350,350,0,0);
//		HexagonPane test2 = new HexagonPane(350,350,190+350,0);
//		root.getChildren().addAll(test1);
		
		//////////////// END OF DEBUG /////////////////////////
	}

	@Override
	public Scene getScene() {
		return this.scene;
	}

}
