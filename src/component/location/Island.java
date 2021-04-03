package component.location;

import java.util.ArrayList;

import component.entity.Minion;

public class Island extends Location {
	public static ArrayList<Minion> banishedMinion;
	public Island() {
		super("Island","Get isolated",0,0);
		banishedMinion = new ArrayList<Minion>();
	}
	 
	public void addToIsland(Minion minion) {
		this.banishedMinion.add(minion);
		while(minion.getMyMinion().size() > 0) {
			minion.getMyMinion().get(0).getPossessedBy();
		}
	}
	
	public void removeFromIsland(Minion minion) {
		minion.returnThisToOwner();
	}
	
	//----------------------getter/setter--------------------------
	
}
