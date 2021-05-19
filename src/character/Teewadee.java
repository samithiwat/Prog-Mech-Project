package character;

import component.location.Ocean;
import component.weaponCard.Gun;
import javafx.animation.Animation;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import logic.AudioLoader;
import logic.GameSetUp;
import sprites.AnimationSprites;

public class Teewadee extends MainCharacter {

	public static boolean warCry = true;
	public static Image ultSkill;

	public Teewadee() {
		super("Sir Tewadee", "");
		this.selectBGM = AudioLoader.sirTewadeeSelectBGM;
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
