package component.law;

import logic.GameController;

public class SakSeeKonJon extends LawCard {
	public SakSeeKonJon() {
		super("SakSeeKonJon","When a figth happen, poorer player's attack get increased by 1 point.");
	}
	public void activateEffectCard() {
		GameController.gameLaw.poorerAdvantage = 1;
	}
}
