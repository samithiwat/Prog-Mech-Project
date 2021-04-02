package gui.entity;

import java.util.ArrayList;

import javafx.css.CssMetaData;
import javafx.css.Styleable;
import javafx.event.EventHandler;
import javafx.scene.ImageCursor;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.media.AudioClip;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import logic.AudioLoader;
import logic.FileController;
import logic.SceneController;

public class MenuButton extends Button implements Clickable {
	
	private final String FONT_PATH_REGULAR = "font/Bai_Jamjuree/BaiJamjuree-Regular.ttf";
	private final String FONT_PATH_BOLD = "font/Bai_Jamjuree/BaiJamjuree-Bold.ttf";
	private final String FONT_PATH = "font/Bai_Jamjuree/BaiJamjuree-Bold.ttf";

	public MenuButton(String content,int contentSize,int width,int height,Color textColor,int x,int y) {
		setLayoutX(x);
		setLayoutY(y);
		setTextFill(textColor);
		setId("mainmenu-button-release-style");
		setText(content);
		setFontRegular(contentSize);
		setPrefHeight(height);
		setPrefWidth(width);
		interact();
	}

	@Override
	public void interact() {
		setOnMouseExited(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				setCursor(new ImageCursor((new Image(ClassLoader.getSystemResource("img/MouseCursor.png").toString()))));
				setId("mainmenu-button-release-style");
				//System.out.println(getStyle());
			}
			
		});
		setOnMouseEntered(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				
				//FOR DEBIG ONLY
//				ArrayList<String> log = new ArrayList<String>();
//				for(CssMetaData<? extends Styleable, ?> data : getCssMetaData()) {
//					for(String splitedData : data.toString().split("CSSProperty"))
//					{
//						log.add(splitedData);
//						log.add("\n");
//					}
//				}
//				//log.add("Hello World");
//				try {
//					FileController.write("C:\\Computer Programing\\Java\\ProjectRes\\ProjectLog\\log3.txt", log);
//				}catch(Exception e) {
//					e.printStackTrace();
//				}
				
				//END OF DEBUG
				setCursor(new ImageCursor((new Image(ClassLoader.getSystemResource("img/MouseCursorSelected.png").toString()))));
				AudioClip effect = AudioLoader.mouseEnterSound;
				effect.play();
				setId("mainmenu-button-hold-style");
				//System.out.println(getStyle());
			}
			
		});
	}

	public void setFontRegular(int size) {
		//System.out.println(getClass().getClassLoader().getResource(FONT_PATH_REGULAR));
		setFont(Font.font("Bai Jamjuree",FontWeight.NORMAL,size));
	}
	
	public void setFontBold(int size) {
		setFont(Font.loadFont(getClass().getClassLoader().getResource
		        (FONT_PATH_BOLD).toExternalForm(), size));
	}

	@Override
	public void triggerDisable() {
		setDisable(!isDisable());
	}
}
