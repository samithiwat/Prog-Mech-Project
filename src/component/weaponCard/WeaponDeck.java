package component.weaponCard;

import java.util.ArrayList;
import java.util.Random;

public class WeaponDeck {
	private ArrayList<WeaponCard> weaponDeck;
	public WeaponDeck() {
		weaponDeck = new ArrayList<WeaponCard>();
	}
	public void addCard(WeaponCard card) {
		this.weaponDeck.add(card);
	}
	
	public WeaponCard drawCard() {
		Random rand = new Random();
		int deckSize = this.weaponDeck.size();
		int drawIndex = rand.nextInt(deckSize);
		WeaponCard draw = this.weaponDeck.get(drawIndex);
		this.weaponDeck.remove(drawIndex);
		return draw;
	}
}
