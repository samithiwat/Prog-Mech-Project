package logic;

import javafx.scene.media.AudioClip;

public class AudioLoader {
	public static AudioClip menuThemeSong = new AudioClip(ClassLoader.getSystemResource("audio/MainMenuThemeSong1.wav").toString());
	public static AudioClip mouseEnterSound = new AudioClip(ClassLoader.getSystemResource("audio/beep1.mp3").toString());
	//public static AudioClip mouseEnterSound = new AudioClip(ClassLoader.getSystemResource("audio/beep2.mp3").toString());
	//public static AudioClip mouseEnterSound = new AudioClip(ClassLoader.getSystemResource("audio/beep3.mp3").toString());
	public static AudioClip quitSound = new AudioClip(ClassLoader.getSystemResource("audio/QuitEffect.mp3").toString());
	public static AudioClip PopSound = new AudioClip(ClassLoader.getSystemResource("audio/Pop.mp3").toString());
	public static AudioClip keyBoardTypingEffect = new AudioClip(ClassLoader.getSystemResource("audio/KeyboardTypingEffect.mp3").toString());
	public static AudioClip clickEffect = new AudioClip(ClassLoader.getSystemResource("audio/ClickEffect.mp3").toString());
	
	public static void setBGMVolume(double volume) {
		menuThemeSong.setVolume(volume);
	}
	
	public static void setEffectVolume(double volume) {
		mouseEnterSound.setVolume(volume);
	}
	
}