package character;

import logic.GameSetUp;

public class BlackSkull extends MainCharacter{
	private static boolean prisonOutBreakSkill = true;
	public BlackSkull() {
		super("Mr.BlackSkull","");
	}
	public void checkIsWin() {
		int count = 0;
		for(int i = 0 ; i < GameSetUp.allsecretBases.size() ; i++) {
			if(GameSetUp.allsecretBases.get(i).getPossessedBy() != null) {
				count++;
			}
		}
		if(count >= 4) {
			this.setWin(true);
		}
		else {
			this.setWin(false);
		}
	}
}
