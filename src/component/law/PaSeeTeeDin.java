package component.law;

import logic.GameSetting;

public class PaSeeTeeDin extends LawCard {
	public PaSeeTeeDin() {
		super("PaSeeTeeDin","Each turn players have to pay 250,000 coconuts each area the player possesses to the government.");
	}
	public void activateEffectCard() {
		GameSetting.gameLaw.taxPerPossessedArea = true;
	}
}
