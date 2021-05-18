package logic;

import java.util.ArrayList;

import character.MainCharacter;
import component.law.LawCard;
import component.law.LongTodeKonShua;
import component.law.PaSeeMeung;
import component.location.Mine;
import component.weaponCard.WeaponCard;
import update.GameSettingUpdate;

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
	public int richerAdvantage;
	public int poorerAdvantage;
	public boolean setMoneyToAverage;
	public boolean supportArmy;
	
	public GameLaw() {
		this.setDefault();
	}
	
	public void setDefault() {
		this.poorerAdvantage = 0;
		this.richerAdvantage = 0;
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
		this.supportArmy = false;
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
		if(!this.supportArmy) {
			for(int i=0;i<GameSetUp.allsecretBases.size();i++) {
				GameSetUp.allsecretBases.get(i).setIncome(1);
			}

		}
		System.out.println("Tax :"+tax);
		character.setMoney(character.getMoney()-tax*MainCharacter.M);
		GameSetUp.theGovernment.setMoney(GameSetUp.theGovernment.getMoney() + tax*MainCharacter.M);
	}
	
	public void activateEachCycle() {
		if(this.setMoneyToAverage) {
			double sum = 0;
			for(int i = 0 ; i < GameSettingUpdate.getNPlayer() ; i++) {
				sum += GameSetUp.gameCharacter.get(i).getMoney();
			}
			double averageSum = 1*MainCharacter.M;
			while(averageSum*GameSettingUpdate.getNPlayer()  < sum) {
				averageSum += 500000;
			}
			for(int i = 0 ; i < GameSettingUpdate.getNPlayer()  ; i++) {
				GameSetUp.gameCharacter.get(i).setMoney(averageSum);
			}
			GameSetUp.theGovernment.setMoney(averageSum+Math.max(0,sum-averageSum*GameSetUp.gameCharacter.size()));
		}
	}
	
}

