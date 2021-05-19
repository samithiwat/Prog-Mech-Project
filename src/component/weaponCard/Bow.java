package component.weaponCard;

public class Bow extends WeaponCard {
	public Bow() {
		super("Bow", 2, 3, "img/weapon/BowCard.png");
		this.ban_img_path = "img/card/law/BanWeaponBow.png";
		this.tax_img_path = "img/card/law/WeaponTaxBow.png";
	}
}
