package character;

import component.location.Council;
import logic.GameSetUp;

public class Teewada extends MainCharacter {
	public static boolean warCry = true;
	public Teewada() {
		super("Teewada","");
	}
	public void checkIsWin() {
		int howLong = Council.howLong();
		if(howLong >= 7) {
			this.setWin(true);
//			GameSetUp.theGovernment.setWin(true);
		}
		else {
			this.setWin(false);
//			GameSetUp.theGovernment.checkIsWin();
		}
	}
}
