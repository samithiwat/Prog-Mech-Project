package update;

import javafx.scene.media.AudioClip;
import logic.GameSetUp;

public class AudioUpdate {

	private static Thread playSelectBGM;

	public static void changeEnv(AudioClip nextBGM) {

		if (GameSetUp.currentEnvBGM != null) {
			GameSetUp.currentEnvBGM.stop();
		}
		
		GameSetUp.currentEnvBGM = nextBGM;
		
		if (nextBGM != null) {
			nextBGM.setCycleCount(AudioClip.INDEFINITE);
			nextBGM.play();
		}
	}
	
	public static void changeCharacter(AudioClip nextBGM) {

		if (GameSetUp.currentCharacterBGM != null) {
			GameSetUp.currentCharacterBGM.stop();
		}
		
		GameSetUp.currentCharacterBGM = nextBGM;
		
		if (nextBGM != null) {
			nextBGM.setCycleCount(AudioClip.INDEFINITE);
			nextBGM.play();
		}
	}

	public static void playCharacterSelectBGM(AudioClip bgm, AudioClip characterBGM, AudioClip selectBGM) {

		if (bgm != null) {
			bgm.stop();
		}

		if (characterBGM != null) {
			characterBGM.stop();
		}

		playSelectBGM = new Thread(() -> {
			selectBGM.play();
			while (true) {
				System.out.print("");
				if (!selectBGM.isPlaying()) {
					if (bgm != null) {
						bgm.setCycleCount(AudioClip.INDEFINITE);
						bgm.play();
					}
					if (characterBGM != null) {
						characterBGM.setCycleCount(AudioClip.INDEFINITE);
						characterBGM.play();
					}
					break;
				}
				if (playSelectBGM.isInterrupted()) {
					if (bgm != null) {
						bgm.setCycleCount(AudioClip.INDEFINITE);
						bgm.play();
					}
					if (characterBGM != null) {
						characterBGM.setCycleCount(AudioClip.INDEFINITE);
						characterBGM.play();
					}
					selectBGM.stop();
					break;
				}
			}
		});
		playSelectBGM.start();

	}

	public static Thread getPlaySelectBGM() {
		return playSelectBGM;
	}

}
