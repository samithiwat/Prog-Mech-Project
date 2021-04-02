package component.law;

import logic.GameController;

public class PaSeeMeung extends LawCard {
	public PaSeeMeung() {
		super("PaSeeMeung","Player who has mines in their possession requires to pay "
				+ "to the government 1,000,000 each mine the player has");		
	}
	public void activateEffectCard() {
		GameController.gameLaw.taxMine = true;
	}
}
