package component.location;

import java.util.ArrayList;

import character.MainCharacter;
import component.entity.Minion;

public class Location {
	private String name;
	private String description;
	private Minion possessedBy;
//	private ArrayList<E> *TODO:for group minion that is on the tile*
	private ArrayList<Minion> minionOnLocation;
	private int incomePerRound;
	private int cost; 
	private boolean isPossessable;
	public Location(String name, String description, int income, int cost) {
		this.name = name;
		this.description = description;
		this.incomePerRound = income*MainCharacter.M;
		this.possessedBy = null;
		this.cost = cost*MainCharacter.M;
		this.minionOnLocation = new ArrayList<Minion>();
		if(cost == 0) {
			this.isPossessable = true;
		}
		else {
			this.isPossessable = false;
		}
	}
	public void payIncome() {
		if(this.possessedBy != null && this.isPossessable) {
			this.possessedBy.getPossessedBy().setMoney(this.possessedBy.getPossessedBy().getMoney()+this.incomePerRound);
		}
	}
	
	public void removeFromLocation(Minion minion) {
		for(int i = 0 ; i < this.minionOnLocation.size() ; i++) {
			if(this.minionOnLocation.get(i) == minion) {
				this.minionOnLocation.remove(i);
			}
		}
		if(minion == this.possessedBy) {
			minion.getPossessedBy().getPossessedArea().remove(this);//im not sure that this remove will work.
			if(this.minionOnLocation.size() > 0) {
				this.setPossesedBy(this.minionOnLocation.get(0));
				this.minionOnLocation.get(0).getPossessedBy().addPossessedLocation(this);
			}
			else {
				this.setPossesedBy(null);
			}
		}
	}
	
	public void addMinonToLocation(Minion minion) {
		if(this.getPossessedBy() == null) {
			this.setPossesedBy(minion);
		}
		this.minionOnLocation.add(minion);
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
	public Minion getPossessedBy() {
		return possessedBy;
	}
	public boolean setPossesedBy(Minion minion) {
		if(this.isPossessable) {
			this.possessedBy = minion;		
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
	
////////////////////////////////////////////////////////////// FOR DEBUG ONLY ///////////////////////////////////////////////////////////////////
	public String toString() {
		return "-----------------------------------------------------\n"
				+"Name: "+getName()+"\n"+
				"Description: "+getDescription()+"\n"
				+"-----------------------------------------------------";
	}
////////////////////////////////////////////////////////////// END OF DEBUG /////////////////////////////////////////////////////////////////////
	
}
