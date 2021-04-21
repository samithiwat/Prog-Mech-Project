package component.law;

import logic.GameSetUp;

public class PaSeeMeung extends LawCard {
	
	public static final String IMG_PATH = "img/card/MineTax.png";
	
	public PaSeeMeung() {
		super("PaSeeMeung","Player who has mines in their possession requires to pay "
				+ "to the government 1,000,000 each mine the player has");	
		this.img_path = "img/card/MineTax.png";
	}
	public void activateEffectCard() {
		GameSetUp.gameLaw.taxMine = true;
	}
}
