package component.law;

import logic.GameSetUp;

public class MineTax extends LawCard {
	
	public MineTax() {
		super("PaSeeMeung","Player who has mines in their possession requires to pay "
				+ "to the government 1,000,000 each mine the player has");	
		this.img_path = "img/card/law/MineTax.png";
	}

	public void activateEffectCard() {
		GameSetUp.gameLaw.taxMine = true;
	}
}
