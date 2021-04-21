package component.law;

import logic.GameSetUp;

public class PaSeeArWut extends LawCard {
	
	public static final String IMG_PATH = "img/card/WeaponTax.png";
	
	public PaSeeArWut() {
		super("PaSeeArWut","If player want to use a weapon that is in tax list, player requires to pay "
				+ "the government 1,000,000 coconuts for the permission");
		this.img_path = "img/card/WeaponTax.png";
	}
	public void activateEffectCard() {
//		don't forget to add weapon in banned weapon
//		parameter name -> GameController.gameLaw.bannedWeapon = bannedWeapon
		GameSetUp.gameLaw.taxWeapon = true;
	}
}
