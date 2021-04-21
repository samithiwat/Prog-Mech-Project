package component.law;

import logic.GameSetUp;

public class ReakKurnKongKumRang extends LawCard {
	
	public static final String IMG_PATH = "img/card/RecallArmy.png";
	
	public ReakKurnKongKumRang() {
		super("ReakKurnKongKumRang","Return all weapon card that have been used in this game into the deck");
		this.img_path = "img/card/RecallArmy.png";
	}
	public void activateEffectCard() {
		GameSetUp.removedDeck.returnAllToDeck(GameSetUp.weaponDeck);
	}
}
