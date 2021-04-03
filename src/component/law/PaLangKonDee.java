package component.law;

import logic.GameSetUp;

public class PaLangKonDee extends LawCard{
	public PaLangKonDee() {
		super("PaLangKonDee","When a player with GoodPoint get attacked, the player's attack will increase by 1 point");
	}
	public void activateEffectCard() {
		GameSetUp.gameLaw.goodPointAdvantage = 1;
	}
}
