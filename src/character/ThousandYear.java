package character;

import component.location.Prison;

public class ThousandYear extends MainCharacter {
	public ThousandYear() {
		super("Ms.ThousandYear","");
	}
	public void checkIsWin() {
		if(Prison.minionInPrison.size() >= 3) {
			this.setWin(true);
		}
		else {
//			GameSetUp.theGovernment.checkIsWin();
			this.setWin(false);
		}
	}
}
