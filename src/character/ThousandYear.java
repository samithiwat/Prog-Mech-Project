package character;

import component.location.Prison;
import javafx.scene.paint.Color;
import logic.AudioLoader;
import logic.GameSetUp;

public class ThousandYear extends MainCharacter {
	public ThousandYear() {
		super("Sir Thousand Year", "");
		this.selectBGM = AudioLoader.sirThousandSelectBGM;
		this.bgm = AudioLoader.sirThousandBGM;
		this.color = Color.web("0xCC698D");
		this.setImg_path("img/character/SirThousandYear.png");
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
