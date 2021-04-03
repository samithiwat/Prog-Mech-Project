package component.law;

import logic.GameSetUp;

public class SaMakeeProngDong extends LawCard {
	public SaMakeeProngDong() {
		super("SaMakeeProngDong","Give 5,000,000 coconuts to player who doesn't have any weapon card in hand");
	}
	public void activateEffectCard() {
		GameSetUp.gameLaw.giftNoWeapon = true;
	}
}
