package component.law;

import java.util.ArrayList;
import java.util.Collections;

public class LawDeck {
	private ArrayList<LawCard> lawDeck;

	public LawDeck() {
		this.lawDeck = new ArrayList<LawCard>();

	}

	public void setUpLawDeck() {
		Collections.addAll(lawDeck, new BanWeapon(), new Sympathetic(), new Capture(), new PunishBadGuy(),
				new GoodPower(), new MoneyPower(), new WeaponTax(), new MoveTax(), new TradeTax(), new MineTax(),
				new LandTax(), new FightTax(), new Pardon(), new RecallArmy(), new PoorPrestige(), new Unite(),
				new EncourageGoodGuy(), new SupportArmy(), new Equality());
	}

	// -----------getter/setter--------------------------
	public ArrayList<LawCard> getLawDeck() {
		return lawDeck;
	}

	public void setLawDeck(ArrayList<LawCard> lawDeck) {
		this.lawDeck = lawDeck;
	}

}
