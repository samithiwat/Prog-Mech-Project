package component.law;

import java.util.ArrayList;

import character.MainCharacter;
import component.weaponCard.WeaponCard;
import location.Mine;

public class LawParameter {
	public boolean taxPerTile;
	public boolean giftPerGoodPoint;
	public boolean taxTrade;
	public boolean taxMine;
	public WeaponCard bannedWeapon;
	public boolean taxWeapon;
	public boolean banWeapon;
	public boolean taxNoGoodPoint;
	public boolean taxFighting;
	public boolean taxPerPossessedArea;
	public boolean giftNoWeapon;
	
	public LawParameter() {
		this.setDefault();
	}
	
	public void setDefault() {
		this.taxPerTile = false;
		this.giftPerGoodPoint = false;
		this.taxTrade = false;
		this.taxMine = false;
		this.taxWeapon = false;
		this.banWeapon = false;
		this.taxNoGoodPoint = false;
		this.taxFighting = false;
		this.taxPerPossessedArea = false;
		this.bannedWeapon = null;
	}
	
	public void activate(MainCharacter character) {
		int tax = 0;
		if(this.taxMine) {
			for(int i = 0 ; i < character.getPossessedArea().size() ; i++) {
				if(character.getPossessedArea().get(i).getName().equals("Mine")) {
					tax += 1;
				}
			}
		}
		if(this.taxNoGoodPoint && character.getGoodPoint() == 0) {
			tax += 1;
		}
		
		if(this.giftPerGoodPoint) {
			tax -= character.getGoodPoint();
		}
		
		if(this.giftNoWeapon && character.getWeaponHand().size() == 0) {
			tax -= 5;
		}
		
		if(this.taxPerPossessedArea) {
			tax += character.getPossessedArea().size();
		}
		
		character.setMoney(character.getMoney()-tax*MainCharacter.M);
	}
	
}
