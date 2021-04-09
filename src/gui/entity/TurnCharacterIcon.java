package gui.entity;

import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Circle;

public class TurnCharacterIcon extends AnchorPane {

	private ImageView img;
	private ImageView crown;

	public TurnCharacterIcon(ImageView img, double imgInitX, double imgInitY) {
		super();
		this.img = img;
		img.setViewport(new Rectangle2D(imgInitX, imgInitY, 200, 200));
		img.setFitHeight(60);
		img.setFitHeight(60);
		
		StackPane portraits = new StackPane();
		portraits.setAlignment(Pos.BOTTOM_CENTER);
		portraits.setShape(new Circle(35));
		portraits.getChildren().add(img);
		portraits.setId("turn-character-icon");
		
		crown = new ImageView(ClassLoader.getSystemResource("img/icon/Crown.png").toString());
		crown.setX(19);
		crown.setY(0);
		crown.setVisible(false);
		
		getChildren().addAll(portraits,crown);
	}
	
	public void setPlayerTurn(boolean isPlayerTurn) {
		if(isPlayerTurn) {
			crown.setVisible(true);
		}
		else {
			crown.setVisible(false);
		}
	}
	
	public void setPortraits(String img_path,double imgInitX, double imgInitY) {
		img.setImage(new Image(ClassLoader.getSystemResource(img_path).toString()));
		img.setViewport(new Rectangle2D(imgInitX, imgInitY, 200, 200));
		img.setFitHeight(60);
		img.setFitHeight(60);
	}
	
}
