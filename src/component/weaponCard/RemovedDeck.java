package component.weaponCard;

import java.util.ArrayList;

public class RemovedDeck {
	private ArrayList<WeaponCard> removedCard;
	public RemovedDeck() {
		this.removedCard = new ArrayList<WeaponCard>();
	}
	public void returnAllToDeck(WeaponDeck deck) {
		while(this.removedCard.size() > 0) {
			deck.addCard(this.removedCard.get(0));
			this.removedCard.remove(0);
		}
	}
	
	public void addToRemovedDeck(WeaponCard removedCard) {
		this.removedCard.add(removedCard);
	}
	public ArrayList<WeaponCard> getRemovedCard() {
		return removedCard;
	}
	public void setRemovedCard(ArrayList<WeaponCard> removedCard) {
		this.removedCard = removedCard;
	}
	
}
