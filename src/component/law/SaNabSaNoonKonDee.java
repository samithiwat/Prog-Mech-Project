package component.law;

import logic.GameSetting;

public class SaNabSaNoonKonDee extends LawCard {
	public SaNabSaNoonKonDee() {
		super("SaNabSaNoonKonDee","Player who has GoodPoint, gains 1,000,000 coconuts per GoodPoint");
	}
	public void activateEffectCard() {
		GameSetting.gameLaw.giftPerGoodPoint = true;
	}
}
