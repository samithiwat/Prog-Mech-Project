package component.law;

public class KaYayPeunTeeSongKram extends LawCard implements Interactable{
	
	public static final String IMG_PATH = "img/card/law/ExtendWarZone.png";
	
	public KaYayPeunTeeSongKram() {
		super("KaYayPeunTeeSongKram","A minion can move 3 tiles each turn");
		this.img_path = "img/card/law/ExtendWarZone.png";
	}
	public void activateEffectCard() {
		//Hasn't done it yet ;/
	}
}
