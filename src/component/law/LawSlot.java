package component.law;

import java.util.ArrayList;

public class LawSlot {
	private ArrayList<LawCard> slotCard;
	public LawSlot() {
		this.slotCard = new ArrayList<LawCard>();
	}
	public boolean addLawToSlot(LawCard lawCard) {
		if(this.slotCard.size() >= 2) {
			return false;
		}
		this.slotCard.add(lawCard);
		return true;
	}
	public void remove(int index) {
		this.remove(index);
	}
	public void activateAllSlot() {
		for(int i = 0 ; i < this.slotCard.size() ; i++) {
			this.slotCard.get(i).activateEffectCard();
		}
	}
}
