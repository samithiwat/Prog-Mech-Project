package component.law;

import logic.GameController;

public class PaSeeArWut extends LawCard {
	public PaSeeArWut() {
		super("PaSeeArWut","If player want to use a weapon that is in tax list, player requires to pay "
				+ "the government 1,000,000 coconuts for the permission");
	}
	public void activateEffectCard() {
//		don't forget to add weapon in banned weapon
//		parameter name -> GameController.gameLaw.bannedWeapon = bannedWeapon
		GameController.gameLaw.taxWeapon = true;
	}
}
