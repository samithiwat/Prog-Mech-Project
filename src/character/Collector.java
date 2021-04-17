package character;

import java.util.ArrayList;

import component.entity.Minion;
import javafx.scene.paint.Color;
import logic.AudioLoader;

public class Collector extends MainCharacter {
	public Collector() {
		super("Lady Collector", "");
		this.color = Color.web("0x03728C");
		this.selectBGM = AudioLoader.ladySelectBGM;
	}

	public int checkIsWin() {
		boolean[] key = { true, true, true, true, true };
		int count = 0;
		for (int i = 0; i < this.getMyEntity().size(); i++) {
			for (int j = 0; j < this.getMyEntity().get(i).getMyMinion().size(); j++) {
				Minion minion = this.getMyEntity().get(i).getMyMinion().get(j);
				if (minion.getPossessedBy() instanceof RedFox && key[0]) {
					key[0] = false;
					count++;
				} else if (minion.getPossessedBy() instanceof BlackSkull && key[1]) {
					key[1] = false;
					count++;
				} else if (minion.getPossessedBy() instanceof Teewada && key[2]) {
					key[2] = false;
					count++;
				} else if (minion.getPossessedBy() instanceof Teewadee && key[3]) {
					key[3] = false;
					count++;
				} else if (minion.getPossessedBy() instanceof ThousandYear && key[4]) {
					key[4] = false;
					count++;
				}
			}
		}
		if (count >= 3) {
			this.setWin(true);
		} else {
			this.setWin(false);
		}
		return count;
	}
}
