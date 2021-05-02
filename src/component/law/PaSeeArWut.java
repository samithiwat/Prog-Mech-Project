package component.law;

import component.weaponCard.WeaponCard;
import logic.GameSetUp;

public class PaSeeArWut extends LawCard {
	
	private WeaponCard listWeapon;
	
	public static final String IMG_PATH = "img/card/law/WeaponTax.png";
	
	public PaSeeArWut() {
		super("PaSeeArWut","If player want to use a weapon that is in tax list, player requires to pay "
				+ "the government 1,000,000 coconuts for the permission");
		this.img_path = "img/card/law/WeaponTax.png";
	}
	
	public PaSeeArWut(WeaponCard weapon) {
		super("PaSeeArWut","If player want to use a weapon that is in tax list, player requires to pay "
				+ "the government 1,000,000 coconuts for the permission");
		this.img_path = "img/card/law/WeaponTax.png";
		this.listWeapon = weapon;
	}
	
	public void activateEffectCard() {
//		don't forget to add weapon in banned weapon
//		parameter name -> GameController.gameLaw.bannedWeapon = bannedWeapon
		GameSetUp.gameLaw.taxWeapon = true;
	}
	public WeaponCard getListWeapon() {
		return listWeapon;
	}

	
// ------------------------------------------ Equal Method -------------------------------------
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		PaSeeArWut other = (PaSeeArWut) obj;
		if (listWeapon == null) {
			if (other.listWeapon != null)
				return false;
		} else if (!listWeapon.equals(other.listWeapon))
			return false;
		return true;
	}
	
}
