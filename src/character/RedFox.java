package character;

import gui.entity.GameSetting;
import javafx.scene.paint.Color;
import logic.GameSetUp;

public class RedFox extends MainCharacter {
	public RedFox() {
		super("Mr.RedFox","");
		this.color = Color.web("0xF58C4A");
		//this.bgm = AudioLoader.
		this.setMoney(11*M);
	}
	public int checkIsWin() {
		int count = 0;
		for(int i = 0 ; i < this.getPossessedArea().size() ; i++) {
			if(this.getPossessedArea().get(i).getName() == "Mine") {
				count += 1;
			}
		}
		if(count >= 3) {
			this.setWin(true);
		}
		else {
			this.setWin(false);
		}
		return count;
	}
}
