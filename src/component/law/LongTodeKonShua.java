package component.law;

import logic.GameSetting;

public class LongTodeKonShua extends LawCard {
	public LongTodeKonShua() {
		super("LongTodeKonShua","Player who doesn't have GoodPoint has to pay the government 1,000,000 coconuts");
	}
	
	public void activateEffectCard() {
		GameSetting.gameLaw.taxNoGoodPoint = true;
	}

}
