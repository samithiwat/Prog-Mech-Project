package gui.entity;

import character.MainCharacter;
import component.weaponCard.WeaponCard;
import javafx.event.EventHandler;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import logic.FightController;
import logic.GameSetUp;
import logic.TradeController;
import update.FightOverlayUpdate;
import update.TradeOverlayUpdate;

public class InvCard extends ImageView implements Clickable {
	private int index;
	private WeaponCard card;
	private int key;

	public InvCard(WeaponCard card, int key) {
		super(ClassLoader.getSystemResource(card.getImg_path()).toString());
		this.card = card;
		this.key = key;
		this.index = findCard(key);
		this.setFitHeight(145);
		this.setFitWidth(97);
		interact();
	}

	public int findCard(int key) {
		MainCharacter character = GameSetUp.thisTurn;
		if (key < 5 || GameSetUp.selectedCharacter == null) {
			character = TradeOverlayUpdate.trader;
		} else if (key >= 5 && key < 10) {
			character = TradeOverlayUpdate.traded;
		} 
		if (key >= 10 && key < 15 && GameSetUp.selectedIcon.size() > 0) {
			character = FightOverlayUpdate.challenger.getPossessedBy();
		} else if(key >= 15 && GameSetUp.selectedIcon.size() > 0){
//			System.out.println("findcard");
			character = FightOverlayUpdate.challenged.getPossessedBy();
		}
//		System.out.println(character.getName());
		this.index = -1;
		if(character != null) {
			for (int i = 0; i < character.getWeaponHand().size(); i++) {
				if (character.getWeaponHand().get(i).getName().equals(card.getName())) {
					this.index = i;
				}
			}			
		}
		return this.index;
	}

	public void interact() {
		setOnMouseClicked((MouseEvent event) -> {
			// 0-9
			if (key < 5) {
				TradeController.trader_WeaponSlot.add(TradeOverlayUpdate.trader.getWeaponHand().get(index));
				TradeOverlayUpdate.trader.getWeaponHand().remove(index);
				setVisible(false);
				TradeOverlayUpdate.traderofferUpdate();
			} else if (key >= 5 && key < 10) {
				TradeController.traded_WeaponSlot.add(TradeOverlayUpdate.traded.getWeaponHand().get(index));
				TradeOverlayUpdate.traded.getWeaponHand().remove(index);
				setVisible(false);
				TradeOverlayUpdate.tradedofferUpdate();
			}
			// 10-19
			else if (key >= 10 && key < 15) {
				FightController.challenger_slot
						.add(FightOverlayUpdate.challenger.getPossessedBy().getWeaponHand().get(index));
				FightOverlayUpdate.challenger.getPossessedBy().getWeaponHand().remove(index);
				setVisible(false);
				FightOverlayUpdate.challengerofferUpdate();
			} else if (key >= 15) {
				FightController.challenged_slot
						.add(FightOverlayUpdate.challenged.getPossessedBy().getWeaponHand().get(index));
				FightOverlayUpdate.challenged.getPossessedBy().getWeaponHand().remove(index);
				setVisible(false);
				FightOverlayUpdate.challengedofferUpdate();
			}
		});
		setOnMouseExited(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				setCursor(MOUSE_NORMAL);
				setId("button-release-style");
				// System.out.println(getStyle());
			}

		});
		setOnMouseEntered(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				setCursor(MOUSE_SELECT);
				EFFECT_MOUSE_ENTER.play();
				setId("button-hold-style");
			}

		});
	}

	@Override
	public void triggerDisable() {
		// TODO Auto-generated method stub

	}

	public int getKey() {
		return key;
	}

}
