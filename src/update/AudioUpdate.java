package update;

import gui.MapOverview;
import javafx.scene.media.AudioClip;
import javafx.util.Duration;

public class AudioUpdate implements Updateable{
	
	public static void toMapOverview(AudioClip currentBGM) {
		if(currentBGM != null) {
			currentBGM.stop();
		}
		MapOverview.getBgm().setCycleCount(AudioClip.INDEFINITE);
		MapOverview.getBgm().play();		
	}
	
	public static void changeScene(AudioClip currentBGM, AudioClip nextSceneBGM) {
		if(currentBGM!=null) {
			currentBGM.stop();
		}
		if(nextSceneBGM!=null) {
			nextSceneBGM.setCycleCount(AudioClip.INDEFINITE);
		nextSceneBGM.play();
		}
	}
	
}
