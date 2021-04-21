package component.law;

import logic.GameSetUp;

public class SaNabSaNoonKonDee extends LawCard {
	
	public static final String IMG_PATH = "img/card/EncourageGoodGuy.png";
	
	public SaNabSaNoonKonDee() {
		super("SaNabSaNoonKonDee","Player who has GoodPoint, gains 1,000,000 coconuts per GoodPoint");
		this.img_path = "img/card/EncourageGoodGuy.png";
	}
	public void activateEffectCard() {
		GameSetUp.gameLaw.giftPerGoodPoint = true;
	}
}
