package component.law;

public class RatTaBarnPatTinKrubBarn extends LawCard {
	
	public static final String IMG_PATH = "img/card/Pardon.png";
	
	public RatTaBarnPatTinKrubBarn() {
		super("RatTaBarnPatTinKrubBarn","Each turn the government can return a banished rat back to the owner.");
		this.img_path = "img/card/Pardon.png";
	}
	public void activateEffectCard() {
		//haven't written banish zone
	}
}
