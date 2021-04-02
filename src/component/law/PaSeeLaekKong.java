package component.law;

import logic.GameController;

public class PaSeeLaekKong extends LawCard  {
	public PaSeeLaekKong() {
		super("PaSeeLaekKong","Whoever ask to trade requires to pay the government 1,000,000 coconuts");
	}
	public void activateEffectCard() {
		GameController.gameLaw.taxTrade = true;
	}
}
