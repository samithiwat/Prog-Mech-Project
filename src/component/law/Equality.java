package component.law;

import logic.GameSetUp;

public class Equality extends LawCard {

	public Equality() {
		super("TaoTeumTookKon",
				"At the start of the turn, set every player's money to average sum of everyone's money. "
						+ "If the average money is below 1,000,000 coconuts, all players' money are set to 1,000,000 coconuts instead.");
		this.img_path = "img/card/law/Equality.png";
	}

	public void activateEffectCard() {
		GameSetUp.gameLaw.setMoneyToAverage = true;
	}
}
