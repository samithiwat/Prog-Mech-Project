package gui.overlay;

import character.MainCharacter;
import gui.MapOverview;
import gui.entity.MenuIcon;
import gui.entity.TextTitle;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.media.AudioClip;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.FontWeight;
import logic.AudioLoader;
import logic.GameSetUp;

public class HandOverlay extends Overlay {
	protected static final int HEIGHT = 800;
	protected static final int WIDTH = 1400;
	private TextTitle num_slot1;
	private TextTitle num_slot2;
	private TextTitle num_slot3;
	private TextTitle num_slot4;
	private TextTitle num_slot5;
	private MenuIcon drawCard;

	

	public HandOverlay() {
		super((new Pane()), WIDTH, HEIGHT, 75, -800);

		setId("overlay");
		prefHeight(HEIGHT);
		prefWidth(WIDTH);

		Rectangle bg = new Rectangle(WIDTH, HEIGHT);
		bg.setFill(Color.web("0x393E46"));
		bg.setId("overlay-bg");

		MenuIcon closeIcon = new MenuIcon("img/icon/Cross.png", 1300, 50);
		closeIcon.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				EFFECT_MOUSE_CLICK.play();
				for (int i = 0; i < MapOverview.allHandOverlay.size(); i++) {
					MapOverview.allHandOverlay.get(i).triggerOverlay(0, 825, 1000);
				}
			}
		});

		ImageView slot1 = new ImageView(ClassLoader.getSystemResource("img/weapon/SwordCard.png").toString());
		ImageView slot2 = new ImageView(ClassLoader.getSystemResource("img/weapon/AxeCard.png").toString());
		ImageView slot3 = new ImageView(ClassLoader.getSystemResource("img/weapon/ShieldCard.png").toString());
		ImageView slot4 = new ImageView(ClassLoader.getSystemResource("img/weapon/BowCard.png").toString());
		ImageView slot5 = new ImageView(ClassLoader.getSystemResource("img/weapon/GunCard.png").toString());

		num_slot1 = new TextTitle("x"+GameSetUp.thisTurn.getNum_Sword(), Color.WHITE, FontWeight.BOLD, 50, 0, 0);
		num_slot2 = new TextTitle("x"+GameSetUp.thisTurn.getNum_Axe(), Color.WHITE, FontWeight.BOLD, 50, 0, 0);
		num_slot3 = new TextTitle("x"+GameSetUp.thisTurn.getNum_Shield(), Color.WHITE, FontWeight.BOLD, 50, 0, 0);
		num_slot4 = new TextTitle("x"+GameSetUp.thisTurn.getNum_Bow(), Color.WHITE, FontWeight.BOLD, 50, 0, 0);
		num_slot5 = new TextTitle("x"+GameSetUp.thisTurn.getNum_Gun(), Color.WHITE, FontWeight.BOLD, 50, 0, 0);

		drawCard = new MenuIcon("img/icon/HandsIcon.png", 944, 439);
		
		GridPane gridPane = new GridPane();
		gridPane.setAlignment(Pos.CENTER);
		gridPane.add(slot1, 0, 0);
		gridPane.add(num_slot1, 1, 0);
		gridPane.add(slot2, 2, 0);
		gridPane.add(num_slot2, 3, 0);
		gridPane.add(slot3, 4, 0);
		gridPane.add(num_slot3, 5, 0);
		gridPane.add(slot4, 0, 1);
		gridPane.add(num_slot4, 1, 1);
		gridPane.add(slot5, 2, 1);
		gridPane.add(num_slot5, 3, 1);
		gridPane.add(drawCard, 4, 1, 2, 1);
		gridPane.setHgap(82);
		gridPane.setVgap(45);
		gridPane.setPadding(new Insets(87, 100, 87, 100));
		root.getChildren().addAll(bg, gridPane, closeIcon);
	}


	//------------------getter/setter--------------------------
	
	public TextTitle getNum_slot1() {
		return num_slot1;
	}
	
	
	
	public TextTitle getNum_slot2() {
		return num_slot2;
	}
	
	
	
	public TextTitle getNum_slot3() {
		return num_slot3;
	}
	
	
	
	public TextTitle getNum_slot4() {
		return num_slot4;
	}
	
	
	
	public TextTitle getNum_slot5() {
		return num_slot5;
	}


	public MenuIcon getDrawCard() {
		return drawCard;
	}
	
	
	
}
