package component.law;

import component.weaponCard.WeaponCard;
import logic.GameSetUp;

public class WeaponTax extends LawCard {

	private WeaponCard listWeapon;

	public WeaponTax() {
		super("PaSeeArWut", "If player want to use a weapon that is in tax list, player requires to pay "
				+ "the government 1,000,000 coconuts for the permission");
		this.img_path = "img/card/law/WeaponTax.png";
	}

	public WeaponTax(WeaponCard weapon) {
		super("PaSeeArWut", "If player want to use a weapon that is in tax list, player requires to pay "
				+ "the government 1,000,000 coconuts for the permission");
		this.img_path = "img/card/law/WeaponTax.png";
		this.listWeapon = weapon;
	}

	public void activateEffectCard() {
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
		WeaponTax other = (WeaponTax) obj;
		if (listWeapon == null) {
			if (other.listWeapon != null)
				return false;
		} else if (!listWeapon.equals(other.listWeapon))
			return false;
		return true;
	}

}
