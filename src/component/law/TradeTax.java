package component.law;

import logic.GameSetUp;

public class TradeTax extends LawCard  {
	
	public TradeTax() {
		super("PaSeeLaekKong","Whoever ask to trade requires to pay the government 1,000,000 coconuts");
		this.img_path = "img/card/law/TradeTax.png";
	}
	public void activateEffectCard() {
		GameSetUp.gameLaw.taxTrade = true;
	}
}
