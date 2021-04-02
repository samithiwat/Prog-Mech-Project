package entity;

import java.util.ArrayList;

public class Minion implements moveable {
	private String possessedBy;
	private ArrayList<Minion> myMinion;
	public Minion(String possessedBy){
		this.possessedBy = possessedBy;
		myMinion = new ArrayList<Minion>();
	}
	
	public void addMinion(Minion minion) {
		this.myMinion.add(minion);
		addGroupMinion(minion);
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
}
// I'm not sure about this part :/