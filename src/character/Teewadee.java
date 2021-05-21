package character;

import component.location.Ocean;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.media.AudioClip;
import javafx.scene.paint.Color;
import logic.AudioLoader;
import logic.GameSetUp;

public class Teewadee extends MainCharacter {

	public static boolean warCry = true;
	public static Image ultSkill;
	public static AudioClip ultiSoundTrack = AudioLoader.ulti2Effect;

	public Teewadee() {
		super("Sir Tewadee", "");
		this.selectBGM = AudioLoader.sirTewadeeSelectBGM;
		this.bgm= AudioLoader.sirTewadeeBGM;
		this.color = Color.web("0xF75959");
		this.setImg_path("img/character/SirTewadee.png");
		this.setPfp(new Image(ClassLoader.getSystemResource("img/character/SirTewadee.png").toString()));
		this.winnerImg = new ImageView(ClassLoader.getSystemResource("img/character/SirTewadeeWinner.png").toString());
		this.objectiveInfo1 = "Has total";
		this.objectiveInfo2 = "exiled government in the ocean.";
		this.skill = "Power of goodness (can use only one time)";
		this.nWinCount = 6;
		ultSkill = new Image(ClassLoader.getSystemResource("img/sprites/SirTeewadeeUlt.png").toString());

	}

	public int checkIsWin() {
		if (Ocean.banishedMinion.size() >= nWinCount) {
			setWin(true);
			GameSetUp.isGameEnd = true;
		}
		return Ocean.banishedMinion.size();
	}
}
