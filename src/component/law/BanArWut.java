package component.law;

import logic.GameSetUp;

public class BanArWut extends LawCard {
	public BanArWut() {
		super("BanArWut","Players can't use the weapon that is in the ban list");
	}
	public void activateEffectCard() {
//		don't forget to add weapon in banned weapon
//		parameter name -> GameController.gameLaw.bannedWeapon = bannedWeapon
		GameSetUp.gameLaw.banWeapon = true;
	}
}
