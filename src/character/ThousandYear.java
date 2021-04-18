package character;

import component.location.Prison;
import javafx.scene.paint.Color;
import logic.AudioLoader;
import logic.GameSetUp;

public class ThousandYear extends MainCharacter {
	
	public static final String IMG_PATH = "img/character/SirThousandYear.png";
	public static final String IMG_PATH_MINION_IDLE = "img/character/SirThousandMinionIdle.png";
	
	public ThousandYear() {
		super("Sir Thousand Year", "");
		this.selectBGM = AudioLoader.sirThousandSelectBGM;
		this.bgm = AudioLoader.sirThousandBGM;
		this.color = Color.web("0xCC698D");
	}

	public int checkIsWin() {
		if (Prison.minionInPrison.size() >= 3) {
			this.setWin(true);
		} else {
//			GameSetUp.theGovernment.checkIsWin();
			this.setWin(false);
		}
		return Prison.minionInPrison.size();
	}
}
