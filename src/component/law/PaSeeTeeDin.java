package component.law;

import logic.GameSetUp;

public class PaSeeTeeDin extends LawCard {
	
	public static final String IMG_PATH = "img/card/law/LandTax.png";
	
	public PaSeeTeeDin() {
		super("PaSeeTeeDin","Each turn players have to pay 250,000 coconuts each area the player possesses to the government.");
		this.img_path = "img/card/law/LandTax.png";
	}
	public void activateEffectCard() {
		GameSetUp.gameLaw.taxPerPossessedArea = true;
	}
}
