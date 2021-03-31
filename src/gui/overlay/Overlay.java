package gui.overlay;


import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.scene.SubScene;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

public class Overlay extends SubScene implements Overlayable {

	protected AnchorPane root;

//	protected boolean isVisible = false;

	public Overlay(AnchorPane root,int width,int height,int initX,int initY) {
		super(root, width, height);
		//setFill(Color.TRANSPARENT);
		setRoot((AnchorPane) this.getRoot());
		setLayoutX(initX);
		setLayoutY(initY);
		setVisible(false);
	}

	public AnchorPane getOverlayRoot() {
		return root;
	}

	public void setRoot(AnchorPane root) {
		this.root = root;
	}

	public void triggerOverlay(int dx,int dy,int delay) {

//		//FOR DEBIG ONLY
//		System.out.println(styleProperty());
//		System.out.println(rootProperty());
//		System.out.println(fillProperty());
//		System.out.println(root.getChildren());
//		System.out.println(userAgentStylesheetProperty());
//		System.out.println("visible : " + isVisible());
//		System.out.println("x : "+getLayoutX()+" , y : "+getLayoutY());
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

		TranslateTransition tt = new TranslateTransition(Duration.millis(delay));
		tt.setNode(this);
		if (!isVisible()) {
			tt.setToX(dx);
			tt.setToY(dy);
			setVisible(true);
		} else {
			tt.setToX(-dx);
			tt.setToY(-dy);
			
			Thread t = new Thread(()->{
				try {
					Thread.sleep(1000);
				}catch(InterruptedException e) {
					
				}
				Platform.runLater(new Runnable() {
					
					@Override
					public void run() {
						setVisible(false);
					}
				});
				
			});
			t.start();
			
		}
		tt.play();
	}
}
