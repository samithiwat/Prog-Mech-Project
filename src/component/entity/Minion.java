package component.entity;

import java.util.ArrayList;

import character.MainCharacter;

public class Minion implements moveable {
	private MainCharacter possessedBy;
	private ArrayList<Minion> myMinion;
	public Minion(MainCharacter possessedBy){
		this.possessedBy = possessedBy;
		myMinion = new ArrayList<Minion>();
	}
	
	public void addMinion(Minion minion) {
		this.myMinion.add(minion);
		addGroupMinion(minion);
		minion.possessedBy.removeFromMyEntity(minion);
	}
	
	public void removeMinion(int index) {
		this.myMinion.remove(index);
	}
	
	public void addGroupMinion(Minion minion) {
		while(minion.myMinion.size() > 0) {
			this.myMinion.add(minion.myMinion.get(0));
			minion.myMinion.remove(0);
		}
	}
	
	public void returnThisToOwner() {
		this.possessedBy.addToMyEntity(this);
	}
	//----------------------getter/setter--------------------------

	public MainCharacter getPossessedBy() {
		return possessedBy;
	}

	public void setPossessedBy(MainCharacter possessedBy) {
		this.possessedBy = possessedBy;
	}

	public ArrayList<Minion> getMyMinion() {
		return myMinion;
	}

	public void setMyMinion(ArrayList<Minion> myMinion) {
		this.myMinion = myMinion;
	}
	
}
// I'm not sure about this part :/