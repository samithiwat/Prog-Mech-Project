package component.law;

import logic.GameSetting;

public class PaSeeLaekKong extends LawCard  {
	public PaSeeLaekKong() {
		super("PaSeeLaekKong","Whoever ask to trade requires to pay the government 1,000,000 coconuts");
	}
	public void activateEffectCard() {
		GameSetting.gameLaw.taxTrade = true;
	}
}
