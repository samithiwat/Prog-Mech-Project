package component.law;

import logic.GameSetUp;

public class MoneyPower extends LawCard {

	public MoneyPower() {
		super("PaLangYernTra", "When a fight happen, richer's attack get increased by 1 point.");
		this.img_path = "img/card/law/MoneyPower.png";
	}

	public void activateEffectCard() {
		GameSetUp.gameLaw.richerAdvantage = 1;
	}
}
