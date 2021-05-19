package logic;

import javafx.scene.media.AudioClip;

public class AudioLoader {
	public static AudioClip lobbyThemeSong = new AudioClip(ClassLoader.getSystemResource("audio/GameSettingMenuBGM.mp3").toString());
	public static AudioClip menuThemeSong = new AudioClip(ClassLoader.getSystemResource("audio/MainMenuThemeSong1.wav").toString());
	public static AudioClip mouseEnterSound = new AudioClip(ClassLoader.getSystemResource("audio/beep1.mp3").toString());
	public static AudioClip errorSound = new AudioClip(ClassLoader.getSystemResource("audio/Error.mp3").toString());
	public static AudioClip buySoundEffect = new AudioClip(ClassLoader.getSystemResource("audio/BuyEffect.mp3").toString());
	public static AudioClip countDownEffect1 = new AudioClip(ClassLoader.getSystemResource("audio/CountDownEffect1.mp3").toString());
	public static AudioClip countDownEffect2 = new AudioClip(ClassLoader.getSystemResource("audio/CountDownEffect2.mp3").toString());
	public static AudioClip combineEffect = new AudioClip(ClassLoader.getSystemResource("audio/CombineEffect.mp3").toString());
	public static AudioClip splitEffect = new AudioClip(ClassLoader.getSystemResource("audio/SplitEffect.mp3").toString());
	//public static AudioClip mouseEnterSound = new AudioClip(ClassLoader.getSystemResource("audio/beep2.mp3").toString());
	//public static AudioClip mouseEnterSound = new AudioClip(ClassLoader.getSystemResource("audio/beep3.mp3").toString());
	public static AudioClip transitionEffect = new AudioClip(ClassLoader.getSystemResource("audio/TransitionEffect.mp3").toString());
	public static AudioClip quitSound = new AudioClip(ClassLoader.getSystemResource("audio/QuitEffect.mp3").toString());
	public static AudioClip popSound = new AudioClip(ClassLoader.getSystemResource("audio/Pop.mp3").toString());
	public static AudioClip keyBoardTypingEffect = new AudioClip(ClassLoader.getSystemResource("audio/KeyboardTypingEffect.mp3").toString());
	public static AudioClip clickEffect = new AudioClip(ClassLoader.getSystemResource("audio/ClickEffect.mp3").toString());
	public static AudioClip mrFoxSelectBGM = new AudioClip(ClassLoader.getSystemResource("audio/MrFoxCharacterSelectBG.mp3").toString());
	public static AudioClip ladySelectBGM = new AudioClip(ClassLoader.getSystemResource("audio/LadyCharacterSelectBG.mp3").toString());
	public static AudioClip blackSkullSelectBGM = new AudioClip(ClassLoader.getSystemResource("audio/BlackSkullCharacterSelectBG.mp3").toString());
	public static AudioClip blackSkullBGM = new AudioClip(ClassLoader.getSystemResource("audio/BlackSkullBGM.mp3").toString());
	public static AudioClip sirThousandSelectBGM = new AudioClip(ClassLoader.getSystemResource("audio/SirThousandSelectSound.mp3").toString());
	public static AudioClip sirThousandBGM = new AudioClip(ClassLoader.getSystemResource("audio/SirThousandBGM.mp3").toString());
	public static AudioClip sirTewadaSelectBGM = new AudioClip(ClassLoader.getSystemResource("audio/SirTewaSelectSound.mp3").toString());
	public static AudioClip sirTewadeeSelectBGM = new AudioClip(ClassLoader.getSystemResource("audio/SirTewaSelectSound.mp3").toString());
	public static AudioClip beachBGM = new AudioClip(ClassLoader.getSystemResource("audio/BeachSoundEffect.mp3").toString());
	public static AudioClip addGoodPointEffect = new AudioClip(ClassLoader.getSystemResource("audio/AddGoodPointEffect.mp3").toString()); 
	public static AudioClip reduceGoodPointEffect = new AudioClip(ClassLoader.getSystemResource("audio/ReduceGoodPointEffect.mp3").toString()); 
	public static AudioClip successfulEffect = new AudioClip(ClassLoader.getSystemResource("audio/SuccessEffect.mp3").toString()); 
	
	public static void setBGMVolume(double volume) {
		menuThemeSong.setVolume(volume);
		lobbyThemeSong.setVolume(volume);
		mrFoxSelectBGM.setVolume(volume);
		ladySelectBGM.setVolume(volume);
		blackSkullSelectBGM.setVolume(volume);
		sirThousandSelectBGM.setVolume(volume);
		sirTewadaSelectBGM.setVolume(volume);
		sirTewadeeSelectBGM.setVolume(volume);
	}

	public static void setEffectVolume(double volume) {
		mouseEnterSound.setVolume(volume);
		popSound.setVolume(volume);
		quitSound.setVolume(volume);
		clickEffect.setVolume(volume);
		errorSound.setVolume(volume);
		transitionEffect.setVolume(volume);
	}
}