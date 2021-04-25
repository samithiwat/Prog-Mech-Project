package component.law;

import logic.GameSetUp;

public class ReakKurnKongKumRang extends LawCard implements Interactable{
	
	public static final String IMG_PATH = "img/card/law/RecallArmy.png";
	
	public ReakKurnKongKumRang() {
		super("ReakKurnKongKumRang","Return all weapon card that have been used in this game into the deck");
		this.img_path = "img/card/law/RecallArmy.png";
	}
	public void activateEffectCard() {
		GameSetUp.removedDeck.returnAllToDeck(GameSetUp.weaponDeck);
	}
}
