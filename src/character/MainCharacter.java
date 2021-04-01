package character;

import java.util.ArrayList;

import entity.Minion;
import weaponCard.WeaponCard;

public abstract class MainCharacter extends Character{
	public final static int M = 1000000;
	private ArrayList<WeaponCard> weaponOnHand;
	private ArrayList<ArrayList<Minion>> myEntity;
	private int money;
	public MainCharacter(String name, String description) {
		super(name,description);
		this.money = 7*M;
		this.weaponOnHand = new ArrayList<WeaponCard>();
		this.myEntity = new ArrayList<ArrayList<Minion>>();
	}
	public void addCardtoHand(WeaponCard card) {
		this.weaponOnHand.add(card);
	}
	public WeaponCard removeCardonHand(int index) {
		WeaponCard removedCard = this.weaponOnHand.get(index);
		this.weaponOnHand.remove(index);
		return removedCard;
	}
	
	public abstract boolean isWin();
	//----------------------getter/setter---------------------
	public ArrayList<WeaponCard> getWeaponHand() {
		return weaponOnHand;
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
	public ArrayList<ArrayList<Minion>> getMyEntity() {
		return myEntity;
	}
	public void setMyEntity(ArrayList<ArrayList<Minion>> myEntity) {
		this.myEntity = myEntity;
	}
	
	
	
}
