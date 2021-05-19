package component.law;

import logic.GameSetUp;

public class FightTax extends LawCard {

	public FightTax() {
		super("PaSeeTorSuu", "The challenger requires to pay the government 1,000,000 coconuts to fight");
		this.img_path = "img/card/law/FightTax.png";
	}

	public void activateEffectCard() {
		GameSetUp.gameLaw.taxFighting = true;
	}
}
