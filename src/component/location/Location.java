package component.location;

import java.util.ArrayList;

import character.MainCharacter;

public class Location {
	private String name;
	private String description;
	private MainCharacter possessedBy;
//	private ArrayList<E> *TODO:for group minion that is on the tile*
	private int incomePerRound;
	private int cost;
	private boolean isPossessable;
	public Location(String name, String description, int income, int cost) {
		this.name = name;
		this.description = description;
		this.incomePerRound = income*MainCharacter.M;
		this.possessedBy = null;
		this.cost = cost*MainCharacter.M;
		if(cost == 0) {
			this.isPossessable = true;
		}
		else {
			this.isPossessable = false;
		}
	}
	public void payIncome() {
		if(this.possessedBy != null) {
			this.possessedBy.setMoney(this.possessedBy.getMoney()+this.incomePerRound);
		}
	}
	
	
	// ------------------------------getter/setter--------------------------
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public MainCharacter getPossessedBy() {
		return possessedBy;
	}
	public boolean setPossesedBy(MainCharacter possessedBy) {
		if(this.isPossessable) {
			this.possessedBy = possessedBy;		
			return true;
		}
		return false;
	}
	public int getIncomePerRound() {
		return incomePerRound;
	}
	public void setIncomePerRound(int incomePerRound) {
		this.incomePerRound = incomePerRound;
	}
	public int getCost() {
		return cost;
	}
	public void setCost(int cost) {
		this.cost = cost;
	}
	
}
