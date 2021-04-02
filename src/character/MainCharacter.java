package character;

import java.util.ArrayList;

import component.entity.Minion;
import component.location.Location;
import component.weaponCard.WeaponCard;

public abstract class MainCharacter extends Character{
	public final static int M = 1000000;
	private ArrayList<WeaponCard> weaponOnHand;
	private ArrayList<Minion> myEntity;
	private ArrayList<Location> possessedArea;
	private int goodPoint;
	private int money;
	public MainCharacter(String name, String description) {
		super(name,description);
		this.money = 7*M;
		this.weaponOnHand = new ArrayList<WeaponCard>();
		this.myEntity = new ArrayList<Minion>();
		this.possessedArea = new ArrayList<Location>();
		this.goodPoint = 0;
	}
	public void addCardtoHand(WeaponCard card) {
		this.weaponOnHand.add(card);
	}
	public WeaponCard removeCardonHand(int index) {
		WeaponCard removedCard = this.weaponOnHand.get(index);
		this.weaponOnHand.remove(index);
		return removedCard;
	}
	public void addToMyEntity(Minion minion) {
		this.myEntity.add(minion);
	}
	public void removeFromMyEntity(Minion minion) {
		for(int i = 0 ; i < this.myEntity.size() ; i++) {
			if(this.myEntity.get(i) == minion) {
				this.myEntity.remove(i);
			}
		}
	}
	
	public void addPossessedLocation(Location location) {
		this.possessedArea.add(location);
	}
	
	public abstract boolean isWin();
	//----------------------getter/setter---------------------
	
	public ArrayList<WeaponCard> getWeaponHand() {
		return weaponOnHand;
	}
	public ArrayList<Location> getPossessedArea() {
		return possessedArea;
	}
	public void setPossessedArea(ArrayList<Location> possessedArea) {
		this.possessedArea = possessedArea;
	}
	public void setWeaponHand(ArrayList<WeaponCard> weaponOnHand) {
		this.weaponOnHand = weaponOnHand;
	}
	public ArrayList<WeaponCard> getWeaponOnHand() {
		return weaponOnHand;
	}
	public void setWeaponOnHand(ArrayList<WeaponCard> weaponOnHand) {
		this.weaponOnHand = weaponOnHand;
	}
	public int getMoney() {
		return money;
	}
	public void setMoney(int money) {
		this.money = Math.max(0, money);
	}
	public ArrayList<Minion> getMyEntity() {
		return myEntity;
	}
	public int getGoodPoint() {
		return goodPoint;
	}
	public void setGoodPoint(int goodPoint) {
		this.goodPoint = goodPoint;
	}
	
	
	
	
	
}
