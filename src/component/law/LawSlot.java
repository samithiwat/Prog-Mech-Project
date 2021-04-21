package component.law;

import java.util.ArrayList;

import component.weaponCard.WeaponCard;

public class LawSlot {
	public static int N_SLOT = 2;
	
	private ArrayList<LawCard> slotCard;
	private ArrayList<WeaponCard> bannedWeapon;
	
	public LawSlot() {
		this.slotCard = new ArrayList<LawCard>();
		for(int i=0;i<N_SLOT;i++) {
			this.slotCard.add(null);			
		}
	}

	public boolean addLawToSlot(LawCard lawCard){
		if (this.slotCard.size() >= 2) {
			return false;
		}
		this.slotCard.add(lawCard);
		return true;
	}
	
	public void setLawAtSlot(int index, LawCard lawCard) {
		this.slotCard.set(index, lawCard);
	}

	public void remove(int index) {
		this.remove(index);
	}

	public void activateAllSlot() {
		for (int i = 0; i < this.slotCard.size(); i++) {
			if(this.slotCard.get(i)!=null) {
				this.slotCard.get(i).activateEffectCard();				
			}
		}
	}
	
	public int nSlot() {
		return slotCard.size();
	}
	
	public LawCard getSlot(int index) {
		return slotCard.get(index);
	}

	public ArrayList<WeaponCard> getBannedWeapon() {
		return bannedWeapon;
	}
	
}
