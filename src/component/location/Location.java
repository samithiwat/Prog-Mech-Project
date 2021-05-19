package component.location;

import java.util.ArrayList;

import character.MainCharacter;
import component.Component;
import component.entity.Minion;

public class Location extends Component {
	private String description;
	private Minion possessedBy;
	protected MainCharacter owner;
	private ArrayList<Minion> minionOnLocation;
	protected double incomePerRound;
	protected double cost;
	private boolean isPossessable;

	public Location(String name, String description, int income, int cost) {
		super(name);
		this.description = description;
		this.incomePerRound = income;
		this.possessedBy = null;
		this.cost = cost * MainCharacter.M;
		this.minionOnLocation = new ArrayList<Minion>();
		this.owner = null;
		if (cost == 0) {
			this.isPossessable = true;
		} else {
			this.isPossessable = false;
		}
	}

	public void removeFromLocation(Minion minion) {
		int n = this.minionOnLocation.size();
		for(int i = 0 ; i < n ; i++) {
			if(this.minionOnLocation.get(i) == minion) {
				this.minionOnLocation.remove(i);
			}
		}
		if (minion == this.possessedBy) {
			minion.getPossessedBy().getPossessedArea().remove(this);
			if (this.minionOnLocation.size() > 0) {
				this.setPossesedBy(this.minionOnLocation.get(0));
				this.minionOnLocation.get(0).getPossessedBy().addPossessedLocation(this);
			} else {
				this.setPossesedBy(null);
			}
		}
	}

	public void addMinionToLocation(Minion minion) {
		if (this.getPossessedBy() == null) {
			this.setPossesedBy(minion);
		}
		this.minionOnLocation.add(minion);
	}

	// ------------------------------getter/setter--------------------------
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
		if (this.isPossessable) {
			this.possessedBy = minion;
			return true;
		}
		return false;
	}

	public ArrayList<Minion> getMinionOnLocation() {
		return minionOnLocation;
	}
	

	public double getIncomePerRound() {
		return incomePerRound;
	}

	public void setIncomePerRound(double incomePerRound) {
		this.incomePerRound = incomePerRound;
	}

	public String toString() {
		return "-----------------------------------------------------\n" + "Name: " + getName() + "\n" + "Description: "
				+ getDescription() + "\n" + "Minions: " + minionOnLocation + "\n"
				+ "-----------------------------------------------------";
	}
}
