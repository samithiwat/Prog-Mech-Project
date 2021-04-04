package gui.entity;

import java.util.ArrayList;

import gui.GameLobbyMenu;
import javafx.application.Platform;
import javafx.css.CssMetaData;
import javafx.css.Styleable;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.media.AudioClip;
import logic.AudioLoader;
import logic.FileController;
import update.CharacterSelectUpdate;
import update.GameSettingUpdate;

public class CharacterCard extends StackPane{
	
	private AudioClip effect;
	private final int WIDTH = 350;
	private final int HEIGHT = 450;
	private CharacterCard cc = this;
	private int id;

	public CharacterCard(int id,String img_path,AudioClip effect,int x,int y) {
		super();
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
//			FileController.write("C:\\Computer Programing\\Java\\ProjectRes\\ProjectLog\\log7.txt", log);
//		}catch(Exception e) {
//			e.printStackTrace();
//		}
//		//END OF DEBUG
		
		setId("character-card");
		setAlignment(Pos.CENTER);
		setPrefWidth(WIDTH);
		setPrefHeight(HEIGHT);
		setLayoutX(x);
		setLayoutY(y);
		ImageView portraits = new ImageView(ClassLoader.getSystemResource(img_path).toString());
		this.effect = effect;
		getChildren().add(portraits);
		interact();
	}
	
	public AudioClip getSoundEffect() {
		return this.effect;
	}
	
	public int getCardId() {
		return id;
	}

	public void setCardId(int id) {
		this.id = id;
	}

	public void interact() {
		setOnMouseEntered(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				CharacterSelectUpdate.mouseEnteredUpdate(cc);
			}
		});
		
		setOnMouseExited(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				CharacterSelectUpdate.mouseExitedUpdate(cc);
			}
		
		});
		
		setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				switch(id) {
				case 1 :
					GameSettingUpdate.selectCharacterUpdate(1);
					break;
				case 2 :
					GameSettingUpdate.selectCharacterUpdate(2);
					break;
				case 3 :
					GameSettingUpdate.selectCharacterUpdate(3);
					break;
				case 4 :
					GameSettingUpdate.selectCharacterUpdate(4);
					break;
				case 5 :
					GameSettingUpdate.selectCharacterUpdate(5);
					break;
				case 6 :
					GameSettingUpdate.selectCharacterUpdate(6);
					break;
				}
			}
		});
	}
}
