package character;

import java.util.ArrayList;

import component.entity.Minion;
import component.location.Location;
import component.weaponCard.WeaponCard;

public abstract class MainCharacter{
	public final static int M = 1000000;
	private ArrayList<WeaponCard> weaponOnHand;
	private ArrayList<Minion> myEntity;
	private ArrayList<Location> possessedArea;
	private int goodPoint;
	private int money;
	private String name;
	private String desciption;
	private boolean isWin;
	public MainCharacter(String name, String description) {
		this.name = name;
		this.desciption = description;
		this.money = 7*M;
		this.weaponOnHand = new ArrayList<WeaponCard>();
		this.myEntity = new ArrayList<Minion>();
		this.possessedArea = new ArrayList<Location>();
		this.goodPoint = 0;
		this.isWin = false;
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
	
	public abstract void checkIsWin();
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
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDesciption() {
		return desciption;
	}
	public void setDesciption(String desciption) {
		this.desciption = desciption;
	}
	public void setMyEntity(ArrayList<Minion> myEntity) {
		this.myEntity = myEntity;
	}
	public void setWin(boolean isWin) {
		this.isWin = isWin;
	}
	
	
	
	
	
	
}
