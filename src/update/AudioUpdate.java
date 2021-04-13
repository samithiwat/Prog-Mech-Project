package update;

import gui.MapOverview;
import javafx.scene.media.AudioClip;

public class AudioUpdate implements Updateable{
	
	public static void toMapOverview(AudioClip currentBGM) {
		if(currentBGM != null) {
			currentBGM.stop();
		}
		MapOverview.getBgm().play();
	}
	
}
