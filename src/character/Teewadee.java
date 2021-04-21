package character;

import component.location.Island;
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
	}

	public int checkIsWin() {
		if (Island.banishedMinion.size() >= 6) {
			this.setWin(true);
		} else {
			this.setWin(false);
		}
		return Island.banishedMinion.size();
	}
}
