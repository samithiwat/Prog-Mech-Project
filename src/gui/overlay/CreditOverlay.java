package gui.overlay;

import java.util.ArrayList;

import gui.enity.MenuIcon;
import javafx.css.CssMetaData;
import javafx.css.Styleable;
import javafx.event.EventHandler;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import logic.FileController;
import javafx.scene.input.MouseEvent;

public class CreditOverlay extends Overlay{
	
	public CreditOverlay() {
		super((new AnchorPane()));
		//root = (AnchorPane) this.getRoot();
		//setId("mainmenu-overlay");
		
		Rectangle bg = new Rectangle(1200,600);
		bg.setX(100);
		//bg.setY(-500);
		
		MenuIcon closeIcon = new MenuIcon("img/Cross.png");
		closeIcon.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent arg0) {
				triggerOverlay();
			}
		});
		closeIcon.setX(1300);
		
		Text title = new Text("Programing Methodology Project");
		title.setFill(Color.WHITE);
		title.setX(300);
		bg.setY(400);
		Text by = new Text("By");
		by.setFill(Color.WHITE);
		by.setX(600);
		bg.setY(450);
		Text name1 = new Text("Viritpol Limpawittayakul");
		name1.setFill(Color.WHITE);
		name1.setX(300);
		bg.setY(500);
		Text name2 = new Text("Samithiwat Boonchai");
		name2.setFill(Color.WHITE);
		name2.setX(300);
		bg.setY(550);
		
		root.getChildren().addAll(bg,title,by,name1,name2,closeIcon);
		AnchorPane.setTopAnchor(closeIcon, 50.0);
		AnchorPane.setTopAnchor(bg, 100.0);
		AnchorPane.setTopAnchor(title, 400.0);
		AnchorPane.setTopAnchor(by, 450.0);
		AnchorPane.setTopAnchor(name1, 500.0);
		AnchorPane.setTopAnchor(name2, 550.0);
		
		//FOR DEBIG ONLY
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
		//END OF DEBUG
	}
}
