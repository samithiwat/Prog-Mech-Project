package update;

import component.weaponCard.WeaponCard;
import gui.MapOverview;
import gui.entity.InvCard;
import gui.overlay.TradeOverlay;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import logic.GameSetUp;
import logic.TradeController;

public class TradeOverlayUpdate {
	public static void pfpUpdate() {
		for(int i = 0 ; i< MapOverview.allTradeOverlay.size() ; i++) {
			TradeOverlay overlay = MapOverview.allTradeOverlay.get(i);
			overlay.getTraded_img().setImage(new Image(ClassLoader.getSystemResource(GameSetUp.selectedCharacter.getImg_path()).toString()));
			overlay.getTrader_img().setImage(new Image(ClassLoader.getSystemResource(GameSetUp.thisTurn.getImg_path()).toString()));
		}
	}
	public static void invUpdate() {
		for(int i = 0 ; i < MapOverview.allTradeOverlay.size() ; i++) {
			TradeOverlay overlay = MapOverview.allTradeOverlay.get(i);
			for(int j = 0 ; j < overlay.trader_weaponCard.size() ; j++) {
				InvCard card = overlay.trader_weaponCard.get(j);
				if(card.findCard(card.getKey()) == -1 || TradeController.trader_WeaponSlot.size() >= 2  ) {
					card.setVisible(false);
				}
				else {
					card.setVisible(true);
				}
			}
			for(int j = 0 ; j < overlay.traded_weaponCard.size() ; j++) {
				InvCard card = overlay.traded_weaponCard.get(j);
				if(card.findCard(card.getKey()) == -1 || TradeController.traded_WeaponSlot.size() >= 2  ) {
					card.setVisible(false);
				}
				else {
					card.setVisible(true);
				}
			}
		}
	}

	public static void tradedofferUpdate() {
		for(int i = 0 ; i < MapOverview.allTradeOverlay.size() ; i++) {
			TradeOverlay overlay = MapOverview.allTradeOverlay.get(i);
			overlay.getTraded_offer().getChildren().clear();
			for(int j = 0 ; j < TradeController.traded_WeaponSlot.size() ; j++) {
				WeaponCard card = TradeController.traded_WeaponSlot.get(j);
				ImageView card_img = new ImageView(ClassLoader.getSystemResource(card.getImg_path()).toString());
				card_img.setFitHeight(145);
				card_img.setFitWidth(97);
				card_img.setOnMouseClicked((MouseEvent event) -> {
					//key > 4
					GameSetUp.selectedCharacter.getWeaponOnHand().add(card);
					TradeController.traded_WeaponSlot.remove(card);
					tradedofferUpdate();
				});
				overlay.getTraded_offer().add(card_img, j, 0);
			}
			overlay.getTraded_offer().add(overlay.getTraded_offer_money(), 2, 0);
		}
		invUpdate();
	}
	public static void traderofferUpdate() {
		for(int i = 0 ; i < MapOverview.allTradeOverlay.size() ; i++) {
			TradeOverlay overlay = MapOverview.allTradeOverlay.get(i);
			overlay.getTrader_offer().getChildren().clear();
			for(int j = 0 ; j < TradeController.trader_WeaponSlot.size() ; j++) {
				WeaponCard card = TradeController.trader_WeaponSlot.get(j);
				ImageView card_img = new ImageView(ClassLoader.getSystemResource(card.getImg_path()).toString());
				card_img.setFitHeight(145);
				card_img.setFitWidth(97);
				card_img.setOnMouseClicked((MouseEvent event) -> {
					//key > 4
					GameSetUp.thisTurn.getWeaponOnHand().add(card);
					TradeController.trader_WeaponSlot.remove(card);
					traderofferUpdate();
				});
				overlay.getTrader_offer().add(card_img, j, 0);
			}
			overlay.getTrader_offer().add(overlay.getTrader_offer_money(), 2, 0);
		}
		invUpdate();
	}
	
	public static void acceptUpdate() {
		for(int i = 0 ; i < MapOverview.allTradeOverlay.size() ; i++) {
			TradeOverlay overlay = MapOverview.allTradeOverlay.get(i);
			if (TradeOverlay.traded_IsAccepted) {
				overlay.getTraded_accept().setStyle("-fx-background-color : #279F2B;");
			} else {
				overlay.getTraded_accept().setStyle("-fx-background-color : #C4C4C4;");
			}
			
			if (TradeOverlay.trader_IsAccepted) {
				overlay.getTrader_accept().setStyle("-fx-background-color : #279F2B;");
			} else {
				overlay.getTrader_accept().setStyle("-fx-background-color : #C4C4C4;");
			}
		}
	}
}
