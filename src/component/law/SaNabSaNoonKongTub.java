package component.law;

public class SaNabSaNoonKongTub extends LawCard{
	
	public static final String IMG_PATH = "img/card/SupportArmy.png";
	
	public SaNabSaNoonKongTub() {
		super("SaNabSaNoonKongTub","Increase the money player can get from secret base by 3,000,000 coconuts");
		this.img_path = "img/card/SupportArmy.png";
	}
	public void activateEffectCard() {
		// wait when secretbase location is done.
	}
}
