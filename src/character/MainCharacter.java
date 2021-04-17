package character;

import java.util.ArrayList;

import component.entity.Minion;
import component.location.Location;
import component.weaponCard.WeaponCard;
import exception.ExceedToBuyMinionException;
import exception.FailToBuyMinionException;
import javafx.scene.media.AudioClip;
import javafx.scene.paint.Color;
import logic.GameController;
import logic.GameSetUp;
import update.AudioUpdate;

public abstract class MainCharacter {
	public final static int M = 1000000;
	private ArrayList<WeaponCard> weaponOnHand;
	private ArrayList<Minion> myEntity;
	private ArrayList<Location> possessedArea;
	private int goodPoint;
	private int money;
	private int income;
	private String name;
	private String desciption;
	protected Color color;
	protected AudioClip bgm;
	protected AudioClip selectBGM;
	private int lossPerTurn;
	private boolean isWin;
	private int num_Axe;
	private int num_Sword;
	private int num_Bow;
	private int num_Shield;
	private int num_Gun;

	public MainCharacter(String name, String description) {
		this.name = name;
		this.desciption = description;
		this.money = 7 * M;
		this.weaponOnHand = new ArrayList<WeaponCard>();
		this.myEntity = new ArrayList<Minion>();
		this.possessedArea = new ArrayList<Location>();
		this.goodPoint = 0;
		this.isWin = false;
		this.income = 0;
		this.lossPerTurn = 0;
		this.num_Axe = 0;
		this.num_Bow = 0;
		this.num_Gun = 0;
		this.num_Shield = 0;
		this.num_Sword = 0;
	}
	
	public void countWeaponCard() {
		this.num_Axe = 0;
		this.num_Bow = 0;
		this.num_Gun = 0;
		this.num_Shield = 0;
		this.num_Sword = 0;
		for(int i = 0 ; i < weaponOnHand.size() ; i++) {
			WeaponCard card = weaponOnHand.get(i);
			if(card.getName().equals("Axe")) {
				this.num_Axe++;
			}
			else if(card.getName().equals("Bow")) {
				this.num_Bow++;
			}
			else if(card.getName().equals("Gun")) {
				this.num_Gun++;
			}
			else if(card.getName().equals("Shield")) {
				this.num_Shield++;
			}
			else if(card.getName().equals("Sword")) {
				this.num_Sword++;
			}
		}
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
		for (int i = 0; i < this.myEntity.size(); i++) {
			if (this.myEntity.get(i) == minion) {
				this.myEntity.remove(i);
			}
		}
	}

	public void addPossessedLocation(Location location) {
		this.possessedArea.add(location);
	}

	public void gainIncome() {
		this.setMoney(this.getMoney()+this.income);
	}
	
	public int totalIncome() {
		int sum = 0;
		for(int i = 0 ; i< this.possessedArea.size() ; i++) {
			sum += this.possessedArea.get(i).getIncomePerRound();
		}
		this.income = sum;
		return sum;
	}

	public void buyMinion() throws FailToBuyMinionException,ExceedToBuyMinionException{
		if(GameSetUp.canBuyMinion) {
			if(GameSetUp.selectedTile.isSpawnable()) {
				money-=Minion.getCost();
				GameController.spawnMinion(new Minion(GameSetUp.thisTurn), GameSetUp.selectedTile);
				AudioUpdate.playCharacterSelectBGM(null, GameSetUp.thisTurn.bgm, GameSetUp.thisTurn.selectBGM);
				GameSetUp.canBuyMinion = false;				
			}
			else {
				throw new FailToBuyMinionException();
			}
		}
		else {
			throw new ExceedToBuyMinionException();
		}
	}
	
	public abstract int checkIsWin();
	// ----------------------getter/setter---------------------


	public ArrayList<WeaponCard> getWeaponHand() {
		return weaponOnHand;
	}

	public int getNum_Axe() {
		return num_Axe;
	}

	public int getNum_Sword() {
		return num_Sword;
	}

	public int getNum_Bow() {
		return num_Bow;
	}

	public int getNum_Shield() {
		return num_Shield;
	}

	public int getNum_Gun() {
		return num_Gun;
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
	
	public Color getColor() {
		return color;
	}
	
	public int getIncome() {
		return income;
	}
	
	public void setIncome(int income) {
		this.income = income;
	}
	
	public boolean isWin() {
		return isWin;
	}
	
	public int getLossPerTurn() {
		return lossPerTurn;
	}
	
	public void setLossPerTurn(int lossPerTurn) {
		this.lossPerTurn = lossPerTurn;
	}
	

////////////////////////////////////////////////////////////// FOR DEBUG ONLY ///////////////////////////////////////////////////////////////////



	public String toString() {
		return "-------------------------------------\n" + "Name: " + getName() + "\n" + "Description: "
				+ getDesciption() + "\n" + "GoodPoint: " + getGoodPoint() + "\n" + "Weapond on hand: " + getWeaponHand()
				+ "\n" + "Money: " + getMoney() + "\n" + "Minion" + getMyEntity()+"\n"
				+"-------------------------------------";
	}

////////////////////////////////////////////////////////////// END OF DEBUG ///////////////////////////////////////////////////////////////////

}
