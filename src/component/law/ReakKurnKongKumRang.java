package component.law;

import logic.GameController;

public class ReakKurnKongKumRang extends LawCard {
	public ReakKurnKongKumRang() {
		super("ReakKurnKongKumRang","Return all weapon card that have been used in this game into the deck");
	}
	public void activateEffectCard() {
		GameController.removedDeck.returnAllToDeck(GameController.weaponDeck);
	}
}
