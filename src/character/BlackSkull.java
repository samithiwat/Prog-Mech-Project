package character;

import javafx.scene.paint.Color;
import logic.AudioLoader;
import logic.GameSetUp;

public class BlackSkull extends MainCharacter {
	public static boolean prisonOutBreakSkill = true;

	public BlackSkull() {
		super("Mr.BlackSkull", "");
		this.bgm = AudioLoader.blackSkullBGM;
		this.color = Color.web("0x183F21");
	}

	public int checkIsWin() {
		int count = 0;
		for (int i = 0; i < GameSetUp.allsecretBases.size(); i++) {
			if (GameSetUp.allsecretBases.get(i).getPossessedBy() != null) {
				count++;
			}
		}
		if (count >= 4) {
			this.setWin(true);
		} else {
			this.setWin(false);
		}
		return count;
	}
}
