package component.location;

import java.util.ArrayList;

import character.MainCharacter;
import component.Component;
import component.entity.Minion;

public class Location extends Component {
	private String description;
	protected MainCharacter owner;
	private ArrayList<Minion> minionOnLocation;
	protected double incomePerRound;
	protected double cost;

	public Location(String name, String description, int income, int cost) {
		super(name);
		this.description = description;
		this.incomePerRound = income;
		this.cost = cost * MainCharacter.M;
		this.minionOnLocation = new ArrayList<Minion>();
		this.owner = null;
	}

	public void removeFromLocation(Minion minion) {
		int n = this.minionOnLocation.size();
		for (int i = 0; i < n; i++) {
			if (this.minionOnLocation.get(i) == minion) {
				this.minionOnLocation.remove(i);
				break;
			}
		}
	}

	public void addMinionToLocation(Minion minion) {
		this.minionOnLocation.add(minion);
	}

	// ------------------------------getter/setter--------------------------
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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

}
