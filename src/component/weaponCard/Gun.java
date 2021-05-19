package component.weaponCard;

public class Gun extends WeaponCard {
	public Gun() {
		super("Gun", 3, 3, "img/weapon/GunCard.png");
		this.ban_img_path = "img/card/law/BanWeaponGun.png";
		this.tax_img_path = "img/card/law/WeaponTaxGun.png";
	}
}
