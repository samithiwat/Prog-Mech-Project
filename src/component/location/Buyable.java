package component.location;

import character.MainCharacter;

public interface Buyable {

	public double getCost();

	public MainCharacter getOwner();

	public void setOwner(MainCharacter owner);
}
