package character;

import component.location.Island;

public class Teewadee extends MainCharacter {
	public static boolean warCry = true;
	public Teewadee() {
		super("Teewadee","");
	}
	public int checkIsWin() {
		if(Island.banishedMinion.size() >= 6) {
			this.setWin(true);
		}
		else {
			this.setWin(false);
		}
		return Island.banishedMinion.size();
	}
}
