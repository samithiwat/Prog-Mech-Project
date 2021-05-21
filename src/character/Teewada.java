package character;

import component.location.Council;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.media.AudioClip;
import javafx.scene.paint.Color;
import logic.AudioLoader;
import logic.GameSetUp;

public class Teewada extends MainCharacter {
	
	public static boolean warCry = true;
	public static Image ultSkill;
	public static AudioClip ultiSoundTrack = AudioLoader.ulti1Effect;

	public Teewada() {
		super("Sir Tewada", ""); 
		this.selectBGM = AudioLoader.sirTewadaSelectBGM;
		this.bgm = AudioLoader.sirTewadaBGM;
		this.color = Color.web("0xFEF67A");
		this.setImg_path("img/character/SirTewada.png");
		this.setPfp(new Image(ClassLoader.getSystemResource("img/character/SirTewada.png").toString()));
		this.winnerImg = new ImageView(ClassLoader.getSystemResource("img/character/SirTewadaWinner.png").toString());
		this.objectiveInfo1 = "Has government";
		this.objectiveInfo2 = "times in a row.";
		this.skill = "Power of goodness (can use only one time)";
		this.nWinCount = 7;
		ultSkill = new Image(ClassLoader.getSystemResource("img/sprites/SirTeewadaUlt.png").toString());
	}

	public int checkIsWin() {
		int howLong = Council.howLong();
		if (howLong >= nWinCount) {
			setWin(true);
			GameSetUp.isGameEnd = true;
		}
		return howLong;
	}
}
