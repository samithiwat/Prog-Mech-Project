package gui.overlay;

import java.util.ArrayList;

import character.MainCharacter;
import component.weaponCard.Axe;
import component.weaponCard.Bow;
import component.weaponCard.Gun;
import component.weaponCard.Shield;
import component.weaponCard.Sword;
import gui.MainIsland;
import gui.MapOverview;
import gui.entity.Clickable;
import gui.entity.InvCard;
import gui.entity.MenuButton;
import gui.entity.MenuIcon;
import gui.entity.PlayerPanel;
import gui.entity.TextTitle;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.FontWeight;
import logic.FightController;
import logic.GameSetUp;
import logic.TradeController;
import update.FightOverlayUpdate;
import update.PlayerPanelUpdate;

public class FightOverlay extends Overlay {

	protected static final int HEIGHT = 800;
	protected static final int WIDTH = 1400;

	public ArrayList<InvCard> challenger_weaponCard;
	public ArrayList<InvCard> challenged_weaponCard;

	public static boolean challenger_IsAccepted;
	public static boolean challenged_IsAccepted;

	private ImageView challenger_img;
	private ImageView challenged_img;
	
	private MenuButton challenged_accept;
	private MenuButton challenger_accept;
	private MenuIcon challenged_page1_right ;
	private MenuIcon challenged_page2_right ;
	private MenuIcon challenged_page2_left ;
	private MenuIcon challenged_page3_left ;
	private MenuIcon challenger_page1_right ;
	private MenuIcon challenger_page2_right ;
	private MenuIcon challenger_page2_left ;
	private MenuIcon challenger_page3_left ;
	private TextTitle challenger_outcome;
	private TextTitle challenged_outcome;
	
	private GridPane challenged_offer1;
	private GridPane challenger_offer1;
	private GridPane challenged_offer2;
	private GridPane challenger_offer2;
	private GridPane challenged_offer3;
	private GridPane challenger_offer3;

	public FightOverlay() {
		super((new Pane()), WIDTH, HEIGHT, 75, -800);
		setId("overlay");
		prefHeight(HEIGHT);
		prefWidth(WIDTH);

		Rectangle bg = new Rectangle(WIDTH, HEIGHT);
		bg.setFill(Color.web("0x393E46"));
		bg.setId("overlay-bg");

		TextTitle fight = new TextTitle("Fight", Color.WHITE, FontWeight.BOLD, 50, 599 + 45, 72);
		ImageView fightIcon = new ImageView(ClassLoader.getSystemResource("img/icon/FightIcon.png").toString());
		fightIcon.setFitHeight(60);
		fightIcon.setFitWidth(67);
		fightIcon.setX(667);
		fightIcon.setY(416);

//-----------------------------------------challenger side(Left side)----------------------------------------------

		challenger_img = new ImageView(ClassLoader.getSystemResource(GameSetUp.thisTurn.getImg_path()).toString());
		challenger_img.setFitHeight(280);
		challenger_img.setFitWidth(224);
		challenger_img.setX(112);
		challenger_img.setY(37);
		
		VBox challenger_outcome_box = new VBox();
		challenger_outcome_box.setLayoutX(464);
		challenger_outcome_box.setLayoutY(703);
		challenger_outcome_box.setPrefHeight(31);
		challenger_outcome_box.setPrefWidth(472);
		challenger_outcome_box.setStyle("-fx-background-color: #C4C4C4;");
		challenger_outcome = new TextTitle("",Color.WHITE, FontWeight.BOLD, 18, 0, 0);
		challenger_outcome_box.getChildren().add(challenger_outcome);
		
		

		// ------------challenger_challenger_inv-------------------
		challenger_weaponCard = new ArrayList<InvCard>();
		challenger_weaponCard.add(new InvCard(new Sword(), 10));
		challenger_weaponCard.add(new InvCard(new Axe(), 11));
		challenger_weaponCard.add(new InvCard(new Shield(), 12));
		challenger_weaponCard.add(new InvCard(new Bow(), 13));
		challenger_weaponCard.add(new InvCard(new Gun(), 14));

		GridPane challenger_inv = new GridPane();
		challenger_inv.setPrefWidth(363);
		challenger_inv.setPrefHeight(441);
		challenger_inv.setPadding(new Insets(32, 18, 52, 18));
		challenger_inv.setHgap(18);
		challenger_inv.setVgap(37);
		challenger_inv.setLayoutX(45);
		challenger_inv.setLayoutY(349);
		challenger_inv.setStyle("-fx-background-color: #C4C4C4;");

		challenger_inv.add(challenger_weaponCard.get(0), 0, 0);
		challenger_inv.add(challenger_weaponCard.get(1), 1, 0);
		challenger_inv.add(challenger_weaponCard.get(2), 2, 0);
		challenger_inv.add(challenger_weaponCard.get(3), 0, 1);
		challenger_inv.add(challenger_weaponCard.get(4), 1, 1);

//-----------------------------------------challenged side(Right side)----------------------------------------------
		challenged_img = new ImageView(ClassLoader.getSystemResource(GameSetUp.thisTurn.getImg_path()).toString());
		challenged_img.setFitHeight(280);
		challenged_img.setFitWidth(224);
		challenged_img.setX(1060);
		challenged_img.setY(37);

		
		VBox challenged_outcome_box = new VBox();
		challenged_outcome_box.setLayoutX(464);
		challenged_outcome_box.setLayoutY(159);
		challenged_outcome_box.setPrefHeight(31);
		challenged_outcome_box.setPrefWidth(472);
		challenged_outcome_box.setStyle("-fx-background-color: #C4C4C4;");
		challenged_outcome = new TextTitle("",Color.WHITE, FontWeight.BOLD, 18, 0, 0);
		challenged_outcome_box.getChildren().add(challenged_outcome);
		// ------------challenged_inv-------------------

		challenged_weaponCard = new ArrayList<InvCard>();
		challenged_weaponCard.add(new InvCard(new Sword(), 15));
		challenged_weaponCard.add(new InvCard(new Axe(), 16));
		challenged_weaponCard.add(new InvCard(new Shield(), 17));
		challenged_weaponCard.add(new InvCard(new Bow(), 18));
		challenged_weaponCard.add(new InvCard(new Gun(), 19));

		GridPane challenged_inv = new GridPane();
		challenged_inv.setPrefWidth(363);
		challenged_inv.setPrefHeight(441);
		challenged_inv.setPadding(new Insets(32, 18, 52, 18));
		challenged_inv.setHgap(18);
		challenged_inv.setVgap(37);
		challenged_inv.setLayoutX(992);
		challenged_inv.setLayoutY(349);
		challenged_inv.setStyle("-fx-background-color: #C4C4C4;");

		challenged_inv.add(challenged_weaponCard.get(0), 0, 0);
		challenged_inv.add(challenged_weaponCard.get(1), 1, 0);
		challenged_inv.add(challenged_weaponCard.get(2), 2, 0);
		challenged_inv.add(challenged_weaponCard.get(3), 0, 1);
		challenged_inv.add(challenged_weaponCard.get(4), 1, 1);

//----------------------------------------------------Overview--------------------------------------------
		// -------------------------------challenged_offer1-------------------------------
		challenged_offer1 = new GridPane();
		challenged_offer1.setAlignment(Pos.CENTER);
		challenged_offer1.setPrefHeight(171);
		challenged_offer1.setPrefWidth(472);
		challenged_offer1.setPadding(new Insets(13, 13, 13, 13));
		challenged_offer1.setHgap(25);
		challenged_offer1.setLayoutX(464);
		challenged_offer1.setLayoutY(210);
		challenged_offer1.setStyle("-fx-background-color: #C4C4C4;");

		challenged_page1_right = new MenuIcon("img/icon/Arrow2.png", 0, 0);
		challenged_page1_right.setOnMouseClicked((MouseEvent event) -> {
			for(int i = 0 ; i < MapOverview.allFightOverlay.size() ; i++) {
				MapOverview.allFightOverlay.get(i).challenged_offer2.setVisible(true);				
			}
		});
		
		// -------------------------------challenged_offer2-------------------------------
		challenged_offer2 = new GridPane();
		challenged_offer2.setVisible(false);
		challenged_offer2.setAlignment(Pos.CENTER);
		challenged_offer2.setPrefHeight(171);
		challenged_offer2.setPrefWidth(472);
		challenged_offer2.setPadding(new Insets(13, 13, 13, 13));
		challenged_offer2.setHgap(25);
		challenged_offer2.setLayoutX(464);
		challenged_offer2.setLayoutY(210);
		challenged_offer2.setStyle("-fx-background-color: #C4C4C4;");
		

		challenged_page2_left = new MenuIcon("img/icon/Arrow2.png", 0, 0);
		challenged_page2_left.setRotate(180);
		challenged_page2_left.setOnMouseClicked((MouseEvent event) -> {
			for(int i = 0 ; i < MapOverview.allFightOverlay.size() ; i++) {
				MapOverview.allFightOverlay.get(i).challenged_offer2.setVisible(false);				
			}
		});

		challenged_page2_right = new MenuIcon("img/icon/Arrow2.png", 0, 0);
		challenged_page2_right.setOnMouseClicked((MouseEvent event) -> {
			for(int i = 0 ; i < MapOverview.allFightOverlay.size() ; i++) {
				MapOverview.allFightOverlay.get(i).challenged_offer3.setVisible(true);				
			}
		});
		

		// -------------------------------challenged_offer3-------------------------------
		challenged_offer3 = new GridPane();
		challenged_offer3.setVisible(false);
		challenged_offer3.setAlignment(Pos.CENTER);
		challenged_offer3.setPrefHeight(171);
		challenged_offer3.setPrefWidth(472);
		challenged_offer3.setPadding(new Insets(13, 13, 13, 13));
		challenged_offer3.setHgap(25);
		challenged_offer3.setLayoutX(464);
		challenged_offer3.setLayoutY(210);
		challenged_offer3.setStyle("-fx-background-color: #C4C4C4;");
		challenged_page3_left = new MenuIcon("img/icon/Arrow2.png", 0, 0);
		challenged_page3_left.setRotate(180);
		challenged_page3_left.setOnMouseClicked((MouseEvent event) -> {
			for(int i = 0 ; i < MapOverview.allFightOverlay.size() ; i++) {
				MapOverview.allFightOverlay.get(i).challenged_offer3.setVisible(false);				
			}
		});
		
		

		challenged_accept = new MenuButton("Accept", 20, 150, 45, Color.BLACK, 786, 400);
		challenger_accept = new MenuButton("Accept", 20, 150, 45, Color.BLACK, 464, 446);

		// --------------------------challenged_accept-------------------------------------
		challenged_IsAccepted = false;
		challenged_accept.setOnMouseClicked((MouseEvent event) -> {
			if (!challenged_IsAccepted) {
				challenged_IsAccepted = true;
				challenged_accept.setStyle("-fx-background-color : #279F2B;");
			} else {
				challenged_IsAccepted = false;
				challenged_accept.setStyle("-fx-background-color : #C4C4C4;");
			}
			if (challenger_IsAccepted && challenged_IsAccepted) {
				fight();
			}
		});
		challenged_accept.setOnMouseExited(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				if (challenged_IsAccepted == false) {
					setCursor(Clickable.MOUSE_NORMAL);
					challenged_accept.setId("button-release-style");
				}
			}

		});
		challenged_accept.setOnMouseEntered(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				if (challenged_IsAccepted == false) {
					setCursor(Clickable.MOUSE_SELECT);
					Clickable.EFFECT_MOUSE_ENTER.play();
					challenged_accept.setId("button-hold-style");
				}
			}
		});

		// -------------------------------challenger_offer1-------------------------------
		challenger_offer1 = new GridPane();
		challenger_offer1.setAlignment(Pos.CENTER);
		challenger_offer1.setPrefHeight(171);
		challenger_offer1.setPrefWidth(472);
		challenger_offer1.setPadding(new Insets(13, 13, 13, 13));
		challenger_offer1.setHgap(25);
		challenger_offer1.setLayoutX(464);
		challenger_offer1.setLayoutY(510);
		challenger_offer1.setStyle("-fx-background-color: #C4C4C4;");
		
		challenger_page1_right = new MenuIcon("img/icon/Arrow2.png", 0, 0);
		challenger_page1_right.setOnMouseClicked((MouseEvent event) -> {
			for(int i = 0 ; i < MapOverview.allFightOverlay.size() ; i++) {
				MapOverview.allFightOverlay.get(i).challenger_offer2.setVisible(true);				
			}
		});
		

		// -------------------------------challenger_offer2-------------------------------
		challenger_offer2 = new GridPane();
		challenger_offer2.setVisible(false);
		challenger_offer2.setAlignment(Pos.CENTER);
		challenger_offer2.setPrefHeight(171);
		challenger_offer2.setPrefWidth(472);
		challenger_offer2.setPadding(new Insets(13, 13, 13, 13));
		challenger_offer2.setHgap(25);
		challenger_offer2.setLayoutX(464);
		challenger_offer2.setLayoutY(510);
		challenger_offer2.setStyle("-fx-background-color: #C4C4C4;");
		
		challenger_page2_right = new MenuIcon("img/icon/Arrow2.png", 0, 0);
		challenger_page2_right.setOnMouseClicked((MouseEvent event) -> {
			for(int i = 0 ; i < MapOverview.allFightOverlay.size() ; i++) {
				MapOverview.allFightOverlay.get(i).challenger_offer3.setVisible(true);				
			}
		});
		challenger_page2_left = new MenuIcon("img/icon/Arrow2.png", 0, 0);
		challenger_page2_left.setRotate(180);
		challenger_page2_left.setOnMouseClicked((MouseEvent event) -> {
			for(int i = 0 ; i < MapOverview.allFightOverlay.size() ; i++) {
				MapOverview.allFightOverlay.get(i).challenger_offer2.setVisible(false);				
			}
		});

		// -------------------------------challenger_offer3-------------------------------
		challenger_offer3 = new GridPane();
		challenger_offer3.setVisible(false);
		challenger_offer3.setAlignment(Pos.CENTER);
		challenger_offer3.setPrefHeight(171);
		challenger_offer3.setPrefWidth(472);
		challenger_offer3.setPadding(new Insets(13, 13, 13, 13));
		challenger_offer3.setHgap(25);
		challenger_offer3.setLayoutX(464);
		challenger_offer3.setLayoutY(510);
		challenger_offer3.setStyle("-fx-background-color: #C4C4C4;");
		
		challenger_page3_left = new MenuIcon("img/icon/Arrow2.png", 0, 0);
		challenger_page3_left.setRotate(180);
		challenger_page3_left.setOnMouseClicked((MouseEvent event) -> {
			for(int i = 0 ; i < MapOverview.allFightOverlay.size() ; i++) {
				MapOverview.allFightOverlay.get(i).challenger_offer3.setVisible(false);				
			}
		});

		// --------------------------challenger_accept-------------------------------------
		challenger_IsAccepted = false;
		challenger_accept.setOnMouseClicked((MouseEvent event) -> {
			if (!challenger_IsAccepted) {
				challenger_IsAccepted = true;
				setId("button-hold-style");
				challenger_accept.setStyle("-fx-background-color: #279F2B;");
			} else {
				challenger_IsAccepted = false;
				challenger_accept.setStyle("-fx-background-color : #C4C4C4;");
			}
			if (challenger_IsAccepted && challenged_IsAccepted) {
				fight();
			}
		});
		challenger_accept.setOnMouseExited(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				if (challenger_IsAccepted == false) {
					setCursor(Clickable.MOUSE_NORMAL);
					challenger_accept.setId("button-release-style");
				}
			}

		});
		challenger_accept.setOnMouseEntered(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				if (challenger_IsAccepted == false) {
					setCursor(Clickable.MOUSE_SELECT);
					Clickable.EFFECT_MOUSE_ENTER.play();
					challenger_accept.setId("button-hold-style");
				}
			}
		});

		root.getChildren().addAll(bg, fight, fightIcon, challenger_img, challenger_inv, challenged_img, challenged_inv,
				challenger_offer1, challenger_offer2, challenger_offer3, challenged_offer1, challenged_offer2,
				challenged_offer3, challenged_outcome_box, challenger_outcome_box, challenger_accept, challenged_accept);

	}

	public void fight() {
		MainIsland.setShowMessage("Start!", Color.WHITE, 120, 3000);
		PlayerPanelUpdate.setShowMessage("Start!", Color.WHITE, 120, 3000);
		Thread temp = new Thread(() -> {
			FightController.Fight(FightOverlayUpdate.challenger, FightOverlayUpdate.challenged);			
			challenged_IsAccepted = false;
			challenger_IsAccepted = false;
			challenged_accept.setStyle("-fx-background-color : #C4C4C4;");
			challenger_accept.setStyle("-fx-background-color : #C4C4C4;");
			FightController.challenged_slot.clear();
			FightController.challenger_slot.clear();
		});
		temp.start();
		
		GameSetUp.selectedIcon.clear();
	}

	// -------------------------getter/setter----------------------------
	public MenuButton getchallenged_accept() {
		return challenged_accept;
	}

	public MenuButton getchallenger_accept() {
		return challenger_accept;
	}

	public ImageView getchallenger_img() {
		return challenger_img;
	}

	public ImageView getchallenged_img() {
		return challenged_img;
	}

	public GridPane getChallenged_offer1() {
		return challenged_offer1;
	}

	public GridPane getChallenged_offer2() {
		return challenged_offer2;
	}

	public GridPane getChallenged_offer3() {
		return challenged_offer3;
	}

	public MenuIcon getChallenged_page1_right() {
		return challenged_page1_right;
	}

	public MenuIcon getChallenged_page2_right() {
		return challenged_page2_right;
	}

	public MenuIcon getChallenged_page2_left() {
		return challenged_page2_left;
	}

	public MenuIcon getChallenged_page3_left() {
		return challenged_page3_left;
	}

	public MenuIcon getChallenger_page1_right() {
		return challenger_page1_right;
	}

	public MenuIcon getChallenger_page2_right() {
		return challenger_page2_right;
	}

	public MenuIcon getChallenger_page2_left() {
		return challenger_page2_left;
	}

	public MenuIcon getChallenger_page3_left() {
		return challenger_page3_left;
	}

	public GridPane getChallenger_offer1() {
		return challenger_offer1;
	}

	public GridPane getChallenger_offer2() {
		return challenger_offer2;
	}

	public GridPane getChallenger_offer3() {
		return challenger_offer3;
	}

	public TextTitle getChallenger_outcome() {
		return challenger_outcome;
	}

	public TextTitle getChallenged_outcome() {
		return challenged_outcome;
	}
	
	
	
	
	

}
