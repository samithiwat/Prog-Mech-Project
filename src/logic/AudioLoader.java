package logic;

import javafx.scene.media.AudioClip;

public class AudioLoader {
	public static AudioClip lobbyThemeSong = new AudioClip(ClassLoader.getSystemResource("audio/GameSettingMenuBGM.mp3").toString());
	public static AudioClip menuThemeSong = new AudioClip(ClassLoader.getSystemResource("audio/MainMenuThemeSong1.wav").toString());
	public static AudioClip mouseEnterSound = new AudioClip(ClassLoader.getSystemResource("audio/beep1.mp3").toString());
	//public static AudioClip mouseEnterSound = new AudioClip(ClassLoader.getSystemResource("audio/beep2.mp3").toString());
	//public static AudioClip mouseEnterSound = new AudioClip(ClassLoader.getSystemResource("audio/beep3.mp3").toString());
	public static AudioClip quitSound = new AudioClip(ClassLoader.getSystemResource("audio/QuitEffect.mp3").toString());
	public static AudioClip popSound = new AudioClip(ClassLoader.getSystemResource("audio/Pop.mp3").toString());
	public static AudioClip keyBoardTypingEffect = new AudioClip(ClassLoader.getSystemResource("audio/KeyboardTypingEffect.mp3").toString());
	public static AudioClip clickEffect = new AudioClip(ClassLoader.getSystemResource("audio/ClickEffect.mp3").toString());
	public static AudioClip mrFoxSelectBGM = new AudioClip(ClassLoader.getSystemResource("audio/MrFoxCharacterSelectBG.mp3").toString());
	public static AudioClip ladySelectBGM = new AudioClip(ClassLoader.getSystemResource("audio/LadyCharacterSelectBG.mp3").toString());
	public static AudioClip blackSkullSelectBGM = new AudioClip(ClassLoader.getSystemResource("audio/BlackSkullCharacterSelectBG.mp3").toString());
	public static AudioClip sirThousandSelectBGM = new AudioClip(ClassLoader.getSystemResource("audio/MrFoxCharacterSelectBG.mp3").toString());
	public static AudioClip sirTewadaSelectBGM = new AudioClip(ClassLoader.getSystemResource("audio/MrFoxCharacterSelectBG.mp3").toString());
	public static AudioClip sirTewadeeSelectBGM = new AudioClip(ClassLoader.getSystemResource("audio/MrFoxCharacterSelectBG.mp3").toString());
	
	public static void setBGMVolume(double volume) {
		menuThemeSong.setVolume(volume);
		lobbyThemeSong.setVolume(volume);
	}
	 
	public static void setEffectVolume(double volume) {
		mouseEnterSound.setVolume(volume);
		popSound.setVolume(volume);
		quitSound.setVolume(volume);
		clickEffect.setVolume(volume);
	}
}