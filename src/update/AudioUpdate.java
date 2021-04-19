package update;

import gui.MapOverview;
import javafx.application.Platform;
import javafx.scene.media.AudioClip;
import javafx.util.Duration;
import logic.AudioLoader;
import logic.GameSetUp;

public class AudioUpdate implements Updateable {

	private static Thread playSelectBGM;

	private static int count = 0;

	public static void change(AudioClip currentBGM, AudioClip nextBGM) {
		if (currentBGM != null) {
			currentBGM.stop();
		}
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
