package gui.overlay;

import java.util.ArrayList;


import javafx.animation.TranslateTransition;
import javafx.css.CssMetaData;
import javafx.css.Styleable;
import javafx.scene.SubScene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.util.Duration;
import logic.FileController;
import logic.SceneController;

public class Overlay extends SubScene implements Overlayable{

	protected AnchorPane root;
	private static final int HEIGHT = 800;
	private static final int WIDTH = 1400;
	
//	protected boolean isVisible = false;

	public Overlay(AnchorPane root) {
		super(root, WIDTH, HEIGHT);
		setId("mainmenu-overlay");
		//setStyle(MAINMENU_OVERLAY_STYLE);
		setFill(Color.rgb(120,120,120,0.9));
		setRoot((AnchorPane) this.getRoot());
		prefHeight(HEIGHT);
		prefWidth(WIDTH);
		setLayoutX(75);
		setLayoutY(-800);
		setVisible(false);
	}

	public AnchorPane getOverlayRoot() {
		return root;
	}

	public void setRoot(AnchorPane root) {
		this.root = root;
	}

	public static int getOverlayHeight() {
		return HEIGHT;
	}

	public static int getOverlayWidth() {
		return WIDTH;
	}

	public void triggerOverlay() {
		
//		//FOR DEBIG ONLY
//		System.out.println("visible : " + isVisible());
//		System.out.println("x :"+getLayoutX()+" , y : "+getLayoutY());
//		//System.out.println("Style : "+getCssMetaData());
//		ArrayList<String> log = new ArrayList<String>();
//		for(CssMetaData<? extends Styleable, ?> data : getCssMetaData()) {
//			for(String splitedData : data.toString().split("CSSProperty"))
//			{
//				log.add(splitedData);
//				log.add("\n");
//			}
//		}
//		try {
//			FileController.write("C:\\Computer Programing\\Java\\ProjectRes\\ProjectLog\\log6.txt", log);
//		}catch(Exception e) {
//			e.printStackTrace();
//		}
//		//END OF DEBUG
		
		TranslateTransition tt = new TranslateTransition(Duration.millis(10));
		tt.setNode(this);
		if(!isVisible()) {
			tt.setToY(825);
			setVisible(true);;
		}else {
			tt.setToY(-825);
			setVisible(false);;
		}
		tt.play();
	}
}
