package component.law;

import logic.GameSetUp;

public class PaSeeLaekKong extends LawCard  {
	
	public static final String IMG_PATH = "img/card/TradeTax.png";
	
	public PaSeeLaekKong() {
		super("PaSeeLaekKong","Whoever ask to trade requires to pay the government 1,000,000 coconuts");
		this.img_path = "img/card/TradeTax.png";
	}
	public void activateEffectCard() {
		GameSetUp.gameLaw.taxTrade = true;
	}
}
