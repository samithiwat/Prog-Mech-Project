package component.law;

import logic.GameSetUp;

public class BanArWut extends LawCard {
	public static LawCard instance;
	public static final String IMG_PATH = "img/card/BanWeapon.png";
	
	public BanArWut() {
		super("BanArWut","Players can't use the weapon that is in the ban list");
		this.img_path = "img/card/BanWeapon.png";
	}
	public void activateEffectCard() {
//		don't forget to add weapon in banned weapon
//		parameter name -> GameController.gameLaw.bannedWeapon = bannedWeapon
		GameSetUp.gameLaw.banWeapon = true;
	}
}
