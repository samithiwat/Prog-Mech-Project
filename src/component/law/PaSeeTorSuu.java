package component.law;

import logic.GameSetUp;

public class PaSeeTorSuu extends LawCard{
	
public static final String IMG_PATH = "img/card/FightTax.png";
	
	public PaSeeTorSuu() {
		super("PaSeeTorSuu","The challenger requires to pay the government 1,000,000 coconuts to fight");
		this.img_path = "img/card/FightTax.png";
	}
	public void activateEffectCard() {
		GameSetUp.gameLaw.taxFighting = true;
	}
}
