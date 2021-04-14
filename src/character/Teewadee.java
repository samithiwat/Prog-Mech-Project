package character;

import component.location.Island;
import javafx.scene.paint.Color;

public class Teewadee extends MainCharacter {
	public static boolean warCry = true;

	public Teewadee() {
		super("Teewadee", "");
		this.color = Color.web("0xF75959");
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
