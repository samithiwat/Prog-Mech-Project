package component.location;

import java.util.ArrayList;

import character.MainCharacter;
import component.entity.Minion;

public class Prison extends Location {
	public static final double PLEDGE = 7 * MainCharacter.M;
	public static ArrayList<Minion> minionInPrison;
	public static boolean canCapture = true;

	public Prison() {
		super("Prison", "Get arrested", 0, 0);
		this.minionInPrison = new ArrayList<Minion>();
	}

	public static void removeMinion(int index) {
		minionInPrison.get(index).returnThisToOwner();
	}

	public static void addToPrison(Minion minion) {
		minionInPrison.add(minion);
	}

}
