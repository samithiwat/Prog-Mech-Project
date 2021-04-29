package character;

import component.location.Ocean;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import logic.AudioLoader;

public class Teewadee extends MainCharacter {
	
	public static final String IMG_PATH = "img/character/SirTewadee.png";
	public static final String IMG_PATH_MINION_IDLE = "img/character/SirTewadeeMinionIdle.png";
	
	public static boolean warCry = true;

	public Teewadee() {
		super("Sir Tewadee", "");
		this.selectBGM = AudioLoader.sirTewadeeSelectBGM;
		this.color = Color.web("0xF75959");
		this.setImg_path("img/character/SirTewadee.png");
		this.setPfp(new ImageView(ClassLoader.getSystemResource(IMG_PATH).toString()));
		this.winnerImg = new ImageView(ClassLoader.getSystemResource("img/character/SirTewadeeWinner.png").toString());
		this.objectiveInfo1 = "Has total";
		this.objectiveInfo2 = "exiled government in the ocean.";
		this.skill = "Power of goodness (can use only one time)";
		this.nWinCount = 6;
	}

	public int checkIsWin() {
		if (Ocean.banishedMinion.size() >= nWinCount) {
			this.setWin(true);
		} else {
			this.setWin(false);
		}
		return Ocean.banishedMinion.size();
	}
}
