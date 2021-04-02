package component.law;

import logic.GameController;

public class PaSeeTeeDin extends LawCard {
	public PaSeeTeeDin() {
		super("PaSeeTeeDin","Each turn players have to pay 250,000 coconuts each area the player possesses to the government.");
	}
	public void activateEffectCard() {
		GameController.gameLaw.taxPerPossessedArea = true;
	}
}
