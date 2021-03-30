package gui.enity;
import java.util.ArrayList;

import javafx.css.CssMetaData;
import javafx.css.Styleable;
import javafx.event.EventHandler;
import javafx.scene.ImageCursor;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.AudioClip;
import logic.AudioLoader;
import logic.FileController;

public class MenuIcon extends ImageView implements Clickable{
	
	public MenuIcon(String img_path) {
		super(ClassLoader.getSystemResource(img_path).toString());
		interact();
	}

	@Override
	public void interact() {
		
		//FOR DEBIG ONLY
//		ArrayList<String> log = new ArrayList<String>();
//		for(CssMetaData<? extends Styleable, ?> data : getCssMetaData()) {
//			for(String splitedData : data.toString().split("CSSProperty"))
//			{
//				log.add(splitedData);
//				log.add("\n");
//			}
//		}
//		//log.add("Hello World");
//		try {
//			FileController.write("C:\\Computer Programing\\Java\\ProjectRes\\ProjectLog\\log4.txt", log);
//		}catch(Exception e) {
//			e.printStackTrace();
//		}
		
		//END OF DEBUG
		
		setOnMouseEntered(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				setCursor(new ImageCursor((new Image(ClassLoader.getSystemResource("img/MouseCursorSelected.png").toString()))));
				AudioClip effect = AudioLoader.mouseEnterSound;
				effect.play();
				setEffect(new DropShadow());
			}
			
		});
		
		setOnMouseExited(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				setCursor(new ImageCursor((new Image(ClassLoader.getSystemResource("img/MouseCursor.png").toString()))));
				setEffect(null);
			}
		});
	}
}
