package component.law;

import logic.GameSetting;

public class ReakKurnKongKumRang extends LawCard {
	public ReakKurnKongKumRang() {
		super("ReakKurnKongKumRang","Return all weapon card that have been used in this game into the deck");
	}
	public void activateEffectCard() {
		GameSetting.removedDeck.returnAllToDeck(GameSetting.weaponDeck);
	}
}
