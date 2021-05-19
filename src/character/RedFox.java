package character;

import gui.entity.GameSetting;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import logic.AudioLoader;
import logic.GameSetUp;

public class RedFox extends MainCharacter {
	
	public static final String IMG_PATH = "img/character/MrRedFox.png";
	public static final String IMG_PATH_MINION_IDLE = "img/character/FoxMinionIdle.png";
	
	public RedFox() {
		super("Mr.Red Fox","");
		this.color = Color.web("0xF58C4A");
		this.selectBGM = AudioLoader.mrFoxSelectBGM;
		this.setImg_path("img/character/MrRedFox.png");
		//this.bgm = AudioLoader.
		this.setMoney(11*M);
		this.setPfp(new Image(ClassLoader.getSystemResource(IMG_PATH).toString()));
		this.winnerImg = new ImageView(ClassLoader.getSystemResource("img/character/MrRedFoxWinner.png").toString());
		this.objectiveInfo1 = "Capture";
		this.objectiveInfo2 = "Mine.";
		this.skill = "Get extra money when start game";
		this.nWinCount = 3;
	}
	public int checkIsWin() {
		int count = 0;
		for(int i = 0 ; i < getPossessedArea().size() ; i++) {
			if(getPossessedArea().get(i).getName() == "Mine") {
				count += 1;
			}
		}
		if(count >= nWinCount) {
			setWin(true);
			GameSetUp.isGameEnd = true;
		}
		return count;
	}
}
