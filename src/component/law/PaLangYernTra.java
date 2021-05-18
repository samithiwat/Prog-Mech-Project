package component.law;

import logic.GameSetUp;

public class PaLangYernTra extends LawCard {

	public PaLangYernTra() {
		super("PaLangYernTra", "When a fight happen, richer's attack get increased by 1 point.");
		this.img_path = "img/card/law/WeaponTax.png";
	}

	public void activateEffectCard() {
		GameSetUp.gameLaw.richerAdvantage = 1;
	}
}
