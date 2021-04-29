package component.law;

public class RatTaBarnPatTinKrubBarn extends InteractLawCard{
	
	public static final String IMG_PATH = "img/card/law/Pardon.png";
	
	public RatTaBarnPatTinKrubBarn() {
		super("Pardon","Each turn the government can return a banished rat back to the owner.");
		this.img_path = "img/card/law/Pardon.png";
		this.icon_img_path = "img/icon/PardonIcon.png";
	}
	public void activateEffectCard() {
		//haven't written banish zone
	}
}
