package gui.entity;

import character.MainCharacter;
import component.weaponCard.WeaponCard;
import javafx.event.EventHandler;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import logic.GameSetUp;
import logic.TradeController;
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
		MainCharacter character;
		if(key < 5 || GameSetUp.selectedCharacter == null) {
			character = GameSetUp.thisTurn;
		}
		else {
			character = GameSetUp.selectedCharacter;
		}
		this.index = -1;
		for(int i = 0 ; i < character.getWeaponHand().size(); i++) {
			if(character.getWeaponHand().get(i).getName().equals(card.getName())) {
				this.index = i;
			}
		}
		return this.index;
	}
	
	public void interact() {
		setOnMouseClicked((MouseEvent event) -> {
			if(key < 5) {
				TradeController.trader_WeaponSlot.add(GameSetUp.thisTurn.getWeaponHand().get(index));
				GameSetUp.thisTurn.getWeaponHand().remove(index);
				setVisible(false);
				TradeOverlayUpdate.traderofferUpdate();
			}
			else {
				TradeController.traded_WeaponSlot.add(GameSetUp.selectedCharacter.getWeaponHand().get(index));
				GameSetUp.selectedCharacter.getWeaponHand().remove(index);
				setVisible(false);
				TradeOverlayUpdate.tradedofferUpdate();
			}
		});
		setOnMouseExited(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				setCursor(MOUSE_NORMAL);
				setId("button-release-style");
				//System.out.println(getStyle());
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
