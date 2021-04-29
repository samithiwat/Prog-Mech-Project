package character;

import component.location.Prison;
import javafx.scene.image.ImageView;
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
		this.setImg_path("img/character/SirThousandYear.png");
		this.setPfp(new ImageView(ClassLoader.getSystemResource(IMG_PATH).toString()));
		this.winnerImg = new ImageView(ClassLoader.getSystemResource("img/character/SirThousandYearWinner.png").toString());
		this.objectiveInfo1 = "Government arrest";
		this.objectiveInfo2 = "Prisoner.";
		this.skill = "Get extra attact while attacking";
		this.nWinCount = 3;
	}

	public int checkIsWin() {
		if (Prison.minionInPrison.size() >= nWinCount) {
			this.setWin(true);
		} else {
//			GameSetUp.theGovernment.checkIsWin();
			this.setWin(false);
		}
		return Prison.minionInPrison.size();
	}
}
