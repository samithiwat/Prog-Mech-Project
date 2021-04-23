package component.law;

public class JubKaoKuk extends LawCard {
	
	public static final String IMG_PATH = "img/card/law/Capture.png";
	
	public JubKaoKuk() {
		super("JubKaoKuk","The government can arrest one random minion from the map.(If there is no sub-minion, the government "
				+ "can arrest the header");
		this.img_path = "img/card/law/Capture.png";
	}
	public void activateEffectCard() {
		//arrest a minion to the prison 
		// code not finished
	}
}
