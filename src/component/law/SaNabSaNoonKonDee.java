package component.law;

import logic.GameController;

public class SaNabSaNoonKonDee extends LawCard {
	public SaNabSaNoonKonDee() {
		super("SaNabSaNoonKonDee","Player who has GoodPoint, gains 1,000,000 coconuts per GoodPoint");
	}
	public void activateEffectCard() {
		GameController.gameLaw.giftPerGoodPoint = true;
	}
}
