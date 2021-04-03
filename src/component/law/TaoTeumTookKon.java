package component.law;

import logic.GameSetting;

public class TaoTeumTookKon extends LawCard {
	public TaoTeumTookKon() {
		super("TaoTeumTookKon","At the start of the turn, set every player's money to average sum of everyone's money."
				+ "If the average money is below 1,000,000 coconuts, all players' money are set to 1,000,000 coconuts instead.");
	}
	public void activateEffectCard() {
		GameSetting.gameLaw.setMoneyToAverage = true;
	}
}
