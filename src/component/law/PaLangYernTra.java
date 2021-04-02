package component.law;

import logic.GameController;

public class PaLangYernTra extends LawCard {
	public PaLangYernTra() {
		super("PaLangYernTra","When a fight happen, richer's attack get increased by 1 point.");
	}
	public void activateEffectCard() {
		GameController.gameLaw.richerAdvantage = 1;
	}
}
