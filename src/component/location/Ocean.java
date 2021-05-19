package component.location;

import java.util.ArrayList;

import component.entity.Minion;

public class Ocean extends Location {
	public static ArrayList<Minion> banishedMinion;
	public static boolean canPardon = true;

	public Ocean() {
		super("Island", "Get isolated", 0, 0);
		banishedMinion = new ArrayList<Minion>();
	}

	public static void addToOcean(Minion minion) {
		banishedMinion.add(minion);
		while (minion.getMyMinion().size() > 0) {
			minion.getMyMinion().get(0).getPossessedBy();
		}
	}

	public static void removeFromOcean(Minion minion) {
		minion.returnThisToOwner();
	}

}
