package component.location;

import java.util.ArrayList;

import character.MainCharacter;
import component.entity.Minion;

public class Prison extends Location {
	public static final int PLEDGE = 7*MainCharacter.M;
	public static ArrayList<Minion> minionInPrison;
	public Prison() {
		super("Prison","Get arrested",0,0);
		this.minionInPrison = new ArrayList<Minion>();
	}
	
	public void removeMinion(int index) {
		this.minionInPrison.get(index).returnThisToOwner();
	}
	
	public void addToPrison(Minion minion) {
		this.minionInPrison.add(minion);
	}
	
}
