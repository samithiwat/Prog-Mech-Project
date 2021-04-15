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
	
}
