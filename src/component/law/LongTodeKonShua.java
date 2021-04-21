package component.law;

import logic.GameSetUp;

public class LongTodeKonShua extends LawCard {
	
	public static final String IMG_PATH = "img/card/PunishBadGuy.png";
	
	public LongTodeKonShua() {
		super("LongTodeKonShua","Player who doesn't have GoodPoint has to pay the government 1,000,000 coconuts");
		this.img_path = "img/card/PunishBadGuy.png";
	}
	
	public void activateEffectCard() {
		GameSetUp.gameLaw.taxNoGoodPoint = true;
	}

}
