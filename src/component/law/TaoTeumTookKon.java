package component.law;

import logic.GameController;

public class TaoTeumTookKon extends LawCard {
	public TaoTeumTookKon() {
		super("TaoTeumTookKon","At the start of the turn, set every player's money to average sum of everyone's money.");
	}
	public void activateEffectCard() {
		GameController.gameLaw.setMoneyToAverage = true;
	}
}
