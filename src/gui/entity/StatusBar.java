package gui.entity;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.FontWeight;

public class StatusBar extends HBox {
	
	private ImageView img;
	private TextTitle status;

	public StatusBar(String img_path, int contentSize,int spacing, Color contentColor,Insets inset) {
		super();
		
// -------------------------------------------- Set up Bar's Components ------------------------------------------------
		
		status = new TextTitle("", contentColor, FontWeight.BOLD, contentSize, 0, 0);
		this.img = new ImageView(ClassLoader.getSystemResource(img_path).toString());
		
		setAlignment(Pos.CENTER_LEFT);
		setPadding(inset);
		setSpacing(spacing);
		setId("status-bar");
		
// -------------------------------------------- Add Components to Bar ---------------------------------------------------
	
		getChildren().addAll(img,status);
	}

	public ImageView getImg() {
		return img;
	}

	public TextTitle getStatus() {
		return status;
	}
	
}
