package character;

import javafx.scene.paint.Color;
import logic.AudioLoader;
import logic.GameSetUp;

public class BlackSkull extends MainCharacter {
	
	public static final String IMG_PATH = "img/character/BlackSkull.png";
	public static final String IMG_PATH_MINION_IDLE = "img/character/BlackSkullMinionWalking.png";
	
	public static boolean prisonOutBreakSkill = true;

	public BlackSkull() {
		super("Black Skull", "");
		this.bgm = AudioLoader.blackSkullBGM;
		this.selectBGM = AudioLoader.blackSkullSelectBGM;
		this.color = Color.web("0x183F21");
		this.setImg_path("img/character/BlackSkull.png");
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
