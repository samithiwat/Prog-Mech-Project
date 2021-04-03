package component.law;

import logic.GameSetUp;

public class PaSeeTorSuu extends LawCard{
	public PaSeeTorSuu() {
		super("PaSeeTorSuu","The challenger requires to pay the government 1,000,000 coconuts to fight");
	}
	public void activateEffectCard() {
		GameSetUp.gameLaw.taxFighting = true;
	}
}
