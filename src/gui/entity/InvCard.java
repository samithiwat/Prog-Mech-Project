package gui.entity;

import character.MainCharacter;
import component.weaponCard.WeaponCard;
import gui.MainIsland;
import javafx.event.EventHandler;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import logic.FightController;
import logic.GameSetUp;
import logic.TradeController;
import update.FightOverlayUpdate;
import update.PlayerPanelUpdate;
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
		if (key >= 10 && key < 15 && FightOverlayUpdate.challenger != null) {
			character = FightOverlayUpdate.challenger.getPossessedBy();
		} else if (key >= 15 && FightOverlayUpdate.challenged != null) {
			character = FightOverlayUpdate.challenged.getPossessedBy();
		}
		this.index = -1;
		if (character != null) {
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
				boolean flag = true;
				if (GameSetUp.gameLaw.taxWeapon) {
					for (int i = 0; i < GameSetUp.lawSlot.getTaxedWeapon().size(); i++) {
						if (FightOverlayUpdate.challenger.getPossessedBy().getWeaponHand().get(index).getName()
								.equals(GameSetUp.lawSlot.getTaxedWeapon().get(i).getName())) {
							MainCharacter character = FightOverlayUpdate.challenger.getPossessedBy();
							if (character.getMoney() < 1 * MainCharacter.M) {
								flag = false;
							} else {
								character.setMoney(character.getMoney() - 1 * MainCharacter.M);
							}
						}
					}
				}
				if (!flag) {
					PlayerPanelUpdate.setShowMessage("Too poor!", Color.WHITE, 100, 3000);
					MainIsland.setShowMessage("Too poor", Color.web("0xFEFDE8"), Color.web("0x89949B"), 100, 1, 3000);
				} else {
					FightController.challenger_slot
							.add(FightOverlayUpdate.challenger.getPossessedBy().getWeaponHand().get(index));
					FightOverlayUpdate.challenger.getPossessedBy().getWeaponHand().remove(index);
					setVisible(false);
				}
				FightOverlayUpdate.challengerofferUpdate();
			} else if (key >= 15) {
				boolean flag = true;
				if (GameSetUp.gameLaw.taxWeapon) {
					for (int i = 0; i < GameSetUp.lawSlot.getTaxedWeapon().size(); i++) {
						if (FightOverlayUpdate.challenged.getPossessedBy().getWeaponHand().get(index).getName()
								.equals(GameSetUp.lawSlot.getTaxedWeapon().get(i).getName())) {
							MainCharacter character = FightOverlayUpdate.challenged.getPossessedBy();
							if (character.getMoney() < 1 * MainCharacter.M) {
								flag = false;
							} else {
								character.setMoney(character.getMoney() - 1 * MainCharacter.M);
							}
						}
					}
				}
				if (!flag) {
					PlayerPanelUpdate.setShowMessage("Too poor!", Color.WHITE, 100, 3000);
					MainIsland.setShowMessage("Too poor", Color.web("0xFEFDE8"), Color.web("0x89949B"), 100, 1, 3000);
				} else {
					FightController.challenged_slot
							.add(FightOverlayUpdate.challenged.getPossessedBy().getWeaponHand().get(index));
					FightOverlayUpdate.challenged.getPossessedBy().getWeaponHand().remove(index);
					setVisible(false);
				}
				FightOverlayUpdate.challengedofferUpdate();
			}
		});
		setOnMouseExited(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				setCursor(MOUSE_NORMAL);
				setId("button-release-style");
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

	}

	public int getKey() {
		return key;
	}

	public WeaponCard getCard() {
		return card;
	}

}
