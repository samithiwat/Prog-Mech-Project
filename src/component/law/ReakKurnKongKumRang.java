package component.law;

import logic.GameSetUp;

public class ReakKurnKongKumRang extends LawCard {
	public ReakKurnKongKumRang() {
		super("ReakKurnKongKumRang","Return all weapon card that have been used in this game into the deck");
	}
	public void activateEffectCard() {
		GameSetUp.removedDeck.returnAllToDeck(GameSetUp.weaponDeck);
	}
}
