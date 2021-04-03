package character;

import java.util.ArrayList;

import component.entity.Minion;

public class Collector  extends MainCharacter {
	public Collector() {
		super("Ms.Collector","");
	}
	
	public void checkIsWin() {
		boolean k1,k2,k3,k4,k5 = true;
		int count = 0;
		for(int i = 0 ; i < this.getMyEntity().size() ; i++) {
			for(int j = 0 ; j < this.getMyEntity().get(i).getMyMinion().size() ; j++) {
				Minion minion = this.getMyEntity().get(i).getMyMinion().get(j);
				if(minion.getPossessedBy() instanceof RedFox && k1) {
					k1 = false;
					count++;
				}
				else if(minion.getPossessedBy() instanceof BlackSkull && k2) {
					k2 = false;
					count++;
				}
				else if(minion.getPossessedBy() instanceof Teewada && k3) {
					k3 = false;
					count++;
				}
				else if(minion.getPossessedBy() instanceof Teewadee && k4) {
					k4 = false;
					count++;
				}
				else if(minion.getPossessedBy() instanceof ThousandYear && k5) {
					k5 = false;
					count++;
				}
			}
		}
		if(count >= 3) {
			this.setWin(true);
		}
		else {
			this.setWin(false);
		}
	}
}
