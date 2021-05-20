package character;

import component.entity.Minion;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import logic.AudioLoader;
import logic.GameSetUp;

public class Collector extends MainCharacter {

	public Collector() {
		super("Lady Collector", "");
		this.color = Color.web("0x03728C");
		this.selectBGM = AudioLoader.ladySelectBGM;
		this.setImg_path("img/character/LadyCollector.png");
		this.setPfp(new Image(ClassLoader.getSystemResource("img/character/LadyCollector.png").toString()));
		this.winnerImg = new ImageView(
				ClassLoader.getSystemResource("img/character/LadyCollectorWinner.png").toString());
		this.objectiveInfo1 = "Capture";
		this.objectiveInfo2 = "Minion.";
		this.skill = "Get extra attact while defencing";
		this.nWinCount = 3;
	}

	public int checkIsWin() {
		boolean[] key = { true, true, true, true, true };
		int count = 0;
		for (int i = 0; i < getMyEntity().size(); i++) {
			for (int j = 0; j < getMyEntity().get(i).getMyMinion().size(); j++) {
				Minion minion = getMyEntity().get(i).getMyMinion().get(j);
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
		if (count >= nWinCount) {
			setWin(true);
			GameSetUp.isGameEnd = true;
		}
		return count;
	}
}
