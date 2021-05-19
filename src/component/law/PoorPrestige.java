package component.law;

import logic.GameSetUp;

public class PoorPrestige extends LawCard {

	public PoorPrestige() {
		super("SakSeeKonJon", "When a figth happen, poorer player's attack get increased by 1 point.");
		this.img_path = "img/card/law/PoorPrestige.png";
	}

	public void activateEffectCard() {
		GameSetUp.gameLaw.poorerAdvantage = 1;
	}
}
