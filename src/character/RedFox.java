package character;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.media.AudioClip;
import javafx.scene.paint.Color;
import logic.AudioLoader;
import logic.GameSetUp;

public class RedFox extends MainCharacter {

	public RedFox() {
		super("Mr.Red Fox", "");
		this.color = Color.web("0xF58C4A");
		this.selectBGM = AudioLoader.mrFoxSelectBGM;
		this.bgm = AudioLoader.mrFoxBGM;
		this.setImg_path("img/character/MrRedFox.png");
		this.setMoney(11 * M);
		this.setPfp(new Image(ClassLoader.getSystemResource("img/character/MrRedFox.png").toString()));
		this.winnerImg = new ImageView(ClassLoader.getSystemResource("img/character/MrRedFoxWinner.png").toString());
		this.objectiveInfo1 = "Capture";
		this.objectiveInfo2 = "Mine.";
		this.skill = "Get extra money when start game";
		this.nWinCount = 3;

	}

	public int checkIsWin() {
		int count = 0;
		for (int i = 0; i < getPossessedArea().size(); i++) {
			if (getPossessedArea().get(i).getName() == "Mine") {
				count += 1;
			}
		}
		if (count >= nWinCount) {
			setWin(true);
			GameSetUp.isGameEnd = true;
		}
		return count;
	}
}
