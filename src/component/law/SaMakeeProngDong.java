package component.law;

import logic.GameSetting;

public class SaMakeeProngDong extends LawCard {
	public SaMakeeProngDong() {
		super("SaMakeeProngDong","Give 5,000,000 coconuts to player who doesn't have any weapon card in hand");
	}
	public void activateEffectCard() {
		GameSetting.gameLaw.giftNoWeapon = true;
	}
}
