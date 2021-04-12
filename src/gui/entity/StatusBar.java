package gui.entity;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.FontWeight;

public class StatusBar extends HBox {
	
	ImageView img;
	TextTitle status;

	public StatusBar(ImageView img, String content, int contentSize,int spacing, Color contentColor,Insets inset) {
		super();
		
// -------------------------------------------- Set up Bar's Components ------------------------------------------------
		
		status = new TextTitle(content, contentColor, FontWeight.BOLD, contentSize, 0, 0);
		this.img = img;
		
		setAlignment(Pos.CENTER_LEFT);
		setPadding(inset);
		setSpacing(spacing);
		setId("status-bar");
		
// -------------------------------------------- Add Components to Bar ---------------------------------------------------
	
		getChildren().addAll(img,status);
	}
}
