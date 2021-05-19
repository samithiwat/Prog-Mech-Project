package character;

import component.location.Prison;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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
		this.setPfp(new Image(ClassLoader.getSystemResource("img/character/SirThousandYear.png").toString()));
		this.winnerImg = new ImageView(ClassLoader.getSystemResource("img/character/SirThousandYearWinner.png").toString());
		this.objectiveInfo1 = "Government arrest";
		this.objectiveInfo2 = "Prisoner.";
		this.skill = "Get extra attact while attacking";
		this.nWinCount = 3;
	}

	public int checkIsWin() {
		if (Prison.minionInPrison.size() >= nWinCount) {
			GameSetUp.isGameEnd = true;
			setWin(true);
		}
		return Prison.minionInPrison.size();
	}
}
