package component.law;

import java.util.ArrayList;

import character.MainCharacter;
import component.location.Mine;
import component.weaponCard.WeaponCard;
import logic.GameController;

public class GameLaw {
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
	public int goodPointAdvantage;
	public int noGoodPointDisadvantage;
	public int richerAdvantage;
	public int poorerAdvantage;
	public boolean setMoneyToAverage;
	
	public GameLaw() {
		this.setDefault();
	}
	
	public void setDefault() {
		this.poorerAdvantage = 0;
		this.richerAdvantage = 0;
		this.noGoodPointDisadvantage = 0;
		this.goodPointAdvantage = 0;
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
		this.setMoneyToAverage = false;
	}
	
	public void activateEachTurn(MainCharacter character) {
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
		GameController.theGovernment.setMoney(GameController.theGovernment.getMoney() + tax*MainCharacter.M);
	}
	
	public void activateEachCycle() {
		if(this.setMoneyToAverage) {
			int sum = 0;
			for(int i = 0 ; i < GameController.gameCharacter.size() ; i++) {
				sum += GameController.gameCharacter.get(i).getMoney();
			}
			int averageSum = 1*MainCharacter.M;
			while(averageSum*GameController.gameCharacter.size() < sum) {
				averageSum += 500000;
			}
			for(int i = 0 ; i < GameController.gameCharacter.size() ; i++) {
				GameController.gameCharacter.get(i).setMoney(averageSum);
			}
			GameController.theGovernment.setMoney(averageSum+sum-averageSum*GameController.gameCharacter.size());
		}
	}
	
}
