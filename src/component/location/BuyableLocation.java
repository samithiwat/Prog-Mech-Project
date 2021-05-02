package component.location;

import character.MainCharacter;

public abstract class BuyableLocation extends Location implements Buyable, Incomeable {

	public BuyableLocation(String name, String description, int income, int cost) {
		super(name, description, income, cost);
	}

	@Override
	public double getIncome() {
		return this.incomePerRound;
	}

	public double getCost() {
		return this.cost;
	}

	public MainCharacter getOwner() {
		return this.owner;
	}

	public void setOwner(MainCharacter owner) {
		this.owner = owner;
	}

}
