package component.law;

import logic.GameSetting;

public class PaSeeTorSuu extends LawCard{
	public PaSeeTorSuu() {
		super("PaSeeTorSuu","The challenger requires to pay the government 1,000,000 coconuts to fight");
	}
	public void activateEffectCard() {
		GameSetting.gameLaw.taxFighting = true;
	}
}
