package gui.entity;

import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.media.AudioClip;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.FontWeight;
import logic.AudioLoader;
import update.CharacterSelectUpdate;

public class CharacterCard extends StackPane {
	
	private AudioClip effect;
	private final int WIDTH_BG1 = 350;
	private final int HEIGHT_BG1 = 450;
	private final int WIDTH_BG2 = 320;
	private final int HEIGHT_BG2 = 400;
	private CharacterCard cc = this;
	private Rectangle bg;
	private TextTitle selectedText;
	private boolean isSelected;
	private int id;

	public CharacterCard(int id, String img_path, AudioClip effect, int x, int y) {
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
		setCardId(id);
		setId("character-card");
		setAlignment(Pos.CENTER);
		setPrefWidth(WIDTH_BG1);
		setPrefHeight(HEIGHT_BG1);
		setLayoutX(x);
		setLayoutY(y);

// ------------------------------------------------- Set Scene Background -------------------------------------------------------------------
		
		bg = new Rectangle(WIDTH_BG2, HEIGHT_BG2);
		bg.setId("character-card-bg");

// ------------------------------------------------- Load Portraits -----------------------------------------------------------
		
		ImageView portraits = new ImageView(ClassLoader.getSystemResource(img_path).toString());
		this.effect = effect;

// ------------------------------------------------- Set SELECTED Text --------------------------------------------------------
		
		selectedText = new TextTitle("SELECTED", Color.web("0x393E46"), FontWeight.BOLD, 52, 41, 262);
		selectedText.setVisible(false);

		getChildren().addAll(bg, portraits, selectedText);
		interact();
	}
	
// ------------------------------------------------ Getter and Setter ------------------------------------------------------------

	public String toString() {
		return "id : " + this.id;
	}
	
	public boolean isSelected() {
		return isSelected;
	}

	public void setSelected(boolean isSelected) {
		this.isSelected = isSelected;
	}
	
	public AudioClip getSoundEffect() {
		return this.effect;
	}

	public Rectangle getBg() {
		return bg;
	}

	public int getCardId() {
		return id;
	}

	public void setCardId(int id) {
		this.id = id;
	}

	public TextTitle getSelectedText() {
		return selectedText;
	}

	public void setSelectedText(TextTitle selectedText) {
		this.selectedText = selectedText;
	}
	
// -------------------------------------------------- Set Reaction to Client's Interaction ------------------------------------------------------

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
				if(event.getButton().equals(MouseButton.SECONDARY)) {
					CharacterSelectUpdate.rightClickUpdate(getCardId());
				}
				else {
					AudioClip effect = AudioLoader.clickEffect;
					effect.play();
					CharacterSelectUpdate.selectCharacterUpdate(getCardId());
				}
				
			}
		});
	}

}
