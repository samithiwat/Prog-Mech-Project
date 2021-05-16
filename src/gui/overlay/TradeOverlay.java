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
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.FontWeight;
import logic.GameSetUp;
import logic.TradeController;
import update.PlayerPanelUpdate;
import update.TradeOverlayUpdate;

public class TradeOverlay extends Overlay {

	protected static final int HEIGHT = 800;
	protected static final int WIDTH = 1400;

	public ArrayList<InvCard> trader_weaponCard;
	public ArrayList<InvCard> traded_weaponCard;

	public static boolean trader_IsAccepted;
	public static boolean traded_IsAccepted;
	
	private MenuButton traded_accept;
	private MenuButton trader_accept;
	private TextField traded_money;
	private TextField trader_money;
	private ImageView trader_img;
	private ImageView traded_img;
	private GridPane traded_offer;
	private GridPane trader_offer;
	private TextTitle traded_offer_money;
	private TextTitle trader_offer_money;

	public TradeOverlay() {
		super((new Pane()), WIDTH, HEIGHT, 75, -800);
		setId("overlay");
		prefHeight(HEIGHT);
		prefWidth(WIDTH);

		Rectangle bg = new Rectangle(WIDTH, HEIGHT);
		bg.setFill(Color.web("0x393E46"));
		bg.setId("overlay-bg");

		TextTitle trade = new TextTitle("Trade", Color.WHITE, FontWeight.BOLD, 50, 599 + 45, 72);
		ImageView tradeIcon = new ImageView(ClassLoader.getSystemResource("img/icon/TradeIcon.png").toString());
		tradeIcon.setX(650);
		tradeIcon.setY(396);

//-----------------------------------------Trader side(Left side)----------------------------------------------

		trader_img = new ImageView(
				ClassLoader.getSystemResource(GameSetUp.thisTurn.getImg_path()).toString());
		trader_img.setFitHeight(280);
		trader_img.setFitWidth(224);
		trader_img.setX(112);
		trader_img.setY(37);

		// ------------trader_trader_inv-------------------
		trader_weaponCard = new ArrayList<InvCard>();
		trader_weaponCard.add(new InvCard(new Sword(), 0));
		trader_weaponCard.add(new InvCard(new Axe(), 1));
		trader_weaponCard.add(new InvCard(new Shield(), 2));
		trader_weaponCard.add(new InvCard(new Bow(), 3));
		trader_weaponCard.add(new InvCard(new Gun(), 4));
		

		trader_money = new TextField("0");
		trader_money.setPrefHeight(62);
		trader_money.setPrefWidth(97);

		trader_money.setStyle("-fx-text-fill: white; -fx-font-size: 30px;-fx-control-inner-background: #393E46;");

		GridPane trader_inv = new GridPane();
		trader_inv.setPrefWidth(363);
		trader_inv.setPrefHeight(441);
		trader_inv.setPadding(new Insets(32, 18, 52, 18));
		trader_inv.setHgap(18);
		trader_inv.setVgap(37);
		trader_inv.setLayoutX(45);
		trader_inv.setLayoutY(349);
		trader_inv.setStyle("-fx-background-color: #C4C4C4;");

		trader_inv.add(trader_weaponCard.get(0), 0, 0);
		trader_inv.add(trader_weaponCard.get(1), 1, 0);
		trader_inv.add(trader_weaponCard.get(2), 2, 0);
		trader_inv.add(trader_weaponCard.get(3), 0, 1);
		trader_inv.add(trader_weaponCard.get(4), 1, 1);
		trader_inv.add(trader_money, 2, 1);

//-----------------------------------------Traded side(Right side)----------------------------------------------
		traded_img = new ImageView(
				ClassLoader.getSystemResource(GameSetUp.thisTurn.getImg_path()).toString());
		traded_img.setFitHeight(280);
		traded_img.setFitWidth(224);
		traded_img.setX(1060);
		traded_img.setY(37);

		// ------------traded_inv-------------------

		traded_weaponCard = new ArrayList<InvCard>();
		traded_weaponCard.add(new InvCard(new Sword(), 5));
		traded_weaponCard.add(new InvCard(new Axe(), 6));
		traded_weaponCard.add(new InvCard(new Shield(), 7));
		traded_weaponCard.add(new InvCard(new Bow(), 8));
		traded_weaponCard.add(new InvCard(new Gun(), 9));

		traded_money = new TextField("0");
		traded_money.setPrefHeight(62);
		traded_money.setPrefWidth(97);

		traded_money.setStyle("-fx-text-fill: white; -fx-font-size: 30px;-fx-control-inner-background: #393E46;");

		GridPane traded_inv = new GridPane();
		traded_inv.setPrefWidth(363);
		traded_inv.setPrefHeight(441);
		traded_inv.setPadding(new Insets(32, 18, 52, 18));
		traded_inv.setHgap(18);
		traded_inv.setVgap(37);
		traded_inv.setLayoutX(992);
		traded_inv.setLayoutY(349);
		traded_inv.setStyle("-fx-background-color: #C4C4C4;");

		traded_inv.add(traded_weaponCard.get(0), 0, 0);
		traded_inv.add(traded_weaponCard.get(1), 1, 0);
		traded_inv.add(traded_weaponCard.get(2), 2, 0);
		traded_inv.add(traded_weaponCard.get(3), 0, 1);
		traded_inv.add(traded_weaponCard.get(4), 1, 1);
		traded_inv.add(traded_money, 2, 1);

//----------------------------------------------------Overview--------------------------------------------
		// -------------------------------traded_offer-------------------------------
		traded_offer = new GridPane();
		traded_offer.setAlignment(Pos.CENTER);
		traded_offer.setPrefHeight(171);
		traded_offer.setPrefWidth(472);
		traded_offer.setPadding(new Insets(13, 42, 13, 42));
		traded_offer.setHgap(51);
		traded_offer.setLayoutX(464);
		traded_offer.setLayoutY(210);
		traded_offer.setStyle("-fx-background-color: #C4C4C4;");
		
		traded_offer_money = new TextTitle("", Color.WHITE, FontWeight.BOLD, 30);
		traded_offer_money.textProperty().bind(traded_money.textProperty());

		traded_offer.add(traded_offer_money, 2, 0);
		
		traded_accept = new MenuButton("Accept", 20, 150, 45, Color.BLACK, 786, 400);
		trader_accept = new MenuButton("Accept", 20, 150, 45, Color.BLACK, 464, 446);

		// --------------------------traded_accept-------------------------------------
		traded_IsAccepted = false;
		traded_accept.setOnMouseClicked((MouseEvent event) -> {
			if (!traded_IsAccepted) {
				traded_IsAccepted = true;
				traded_accept.setStyle("-fx-background-color : #279F2B;");
			} else {
				traded_IsAccepted = false;
				traded_accept.setStyle("-fx-background-color : #C4C4C4;");
			}
			if(trader_IsAccepted && traded_IsAccepted) {
				trade();
			}
		});
		traded_accept.setOnMouseExited(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				if (traded_IsAccepted == false) {
					setCursor(Clickable.MOUSE_NORMAL);
					traded_accept.setId("button-release-style");
				}
			}

		});
		traded_accept.setOnMouseEntered(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				if (traded_IsAccepted == false) {
					setCursor(Clickable.MOUSE_SELECT);
					Clickable.EFFECT_MOUSE_ENTER.play();
					traded_accept.setId("button-hold-style");
				}
			}
		});

		// -------------------------------trader_offer-------------------------------
		trader_offer = new GridPane();
		trader_offer.setAlignment(Pos.CENTER);
		trader_offer.setPrefHeight(171);
		trader_offer.setPrefWidth(472);
		trader_offer.setPadding(new Insets(13, 42, 13, 42));
		trader_offer.setHgap(51);
		trader_offer.setLayoutX(464);
		trader_offer.setLayoutY(510);
		trader_offer.setStyle("-fx-background-color: #C4C4C4;");
		for (int i = 0; i < TradeController.trader_WeaponSlot.size(); i++) {
			// add img of card to trader_offer
		}
		trader_offer_money = new TextTitle("", Color.WHITE, FontWeight.BOLD, 30);
		trader_offer_money.textProperty().bind(trader_money.textProperty());

		trader_offer.add(trader_offer_money, 2, 0);

		// --------------------------trader_accept-------------------------------------
		trader_IsAccepted = false;
		trader_accept.setOnMouseClicked((MouseEvent event) -> {
			if (!trader_IsAccepted) {
				trader_IsAccepted = true;
				setId("button-hold-style");
				trader_accept.setStyle("-fx-background-color: #279F2B;");
			} else {
				trader_IsAccepted = false;
				trader_accept.setStyle("-fx-background-color : #C4C4C4;");
			}
			if(trader_IsAccepted && traded_IsAccepted) {
				trade();
			}
		});
		trader_accept.setOnMouseExited(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				if (trader_IsAccepted == false) {
					setCursor(Clickable.MOUSE_NORMAL);
					trader_accept.setId("button-release-style");
				}
			}

		});
		trader_accept.setOnMouseEntered(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				if (trader_IsAccepted == false) {
					setCursor(Clickable.MOUSE_SELECT);
					Clickable.EFFECT_MOUSE_ENTER.play();
					trader_accept.setId("button-hold-style");
				}
			}
		});

		root.getChildren().addAll(bg, trade, tradeIcon, trader_img, trader_inv, traded_img, traded_inv, trader_offer,
				traded_offer, trader_accept, traded_accept);

	}
	
	public void trade() {
		TradeController.traded_money = (int)(MainCharacter.M*Float.parseFloat(traded_money.getText()));
		TradeController.trader_money = (int)(MainCharacter.M*Float.parseFloat(trader_money.getText()));
		TradeController.trade(TradeOverlayUpdate.trader, TradeOverlayUpdate.traded);
		for(int i = 0 ; i < MapOverview.allTradeOverlay.size() ; i++) {
			MapOverview.allTradeOverlay.get(i).triggerOverlay(0, 825, 1000);
		}
		traded_IsAccepted = false;
		trader_IsAccepted = false;
		traded_accept.setStyle("-fx-background-color : #C4C4C4;");
		trader_accept.setStyle("-fx-background-color : #C4C4C4;");
		trader_money.setText("0");
		traded_money.setText("0");
		PlayerPanelUpdate.updateStatusPane();
		PlayerPanelUpdate.updatePlayerList();;
	}

	//-------------------------getter/setter----------------------------
	public MenuButton getTraded_accept() {
		return traded_accept;
	}

	public MenuButton getTrader_accept() {
		return trader_accept;
	}

	public GridPane getTraded_offer() {
		return traded_offer;
	}

	public GridPane getTrader_offer() {
		return trader_offer;
	}

	public TextTitle getTraded_offer_money() {
		return traded_offer_money;
	}

	public TextTitle getTrader_offer_money() {
		return trader_offer_money;
	}

	public ImageView getTrader_img() {
		return trader_img;
	}

	public ImageView getTraded_img() {
		return traded_img;
	}


	
	
	
	
	
	
	
}
