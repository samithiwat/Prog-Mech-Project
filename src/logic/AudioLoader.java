package logic;

import javafx.scene.media.AudioClip;

public class AudioLoader {
	public static AudioClip menuThemeSong = new AudioClip(ClassLoader.getSystemResource("audio/MainMenuThemeSong1.wav").toString());
	
	public static void setBGMVolume(double volume) {
		menuThemeSong.setVolume(volume);
	}
	
}
