package component.law;

import logic.GameSetting;

public class PaLangYernTra extends LawCard {
	public PaLangYernTra() {
		super("PaLangYernTra","When a fight happen, richer's attack get increased by 1 point.");
	}
	public void activateEffectCard() {
		GameSetting.gameLaw.richerAdvantage = 1;
	}
}
