package component.law;

import logic.GameSetUp;

public class PaSeeKarnKreunTee extends LawCard {
	
	public static final String IMG_PATH = "img/card/MoveTax.png";
	
	public PaSeeKarnKreunTee() {
		super("PaSeeKarnKreunTee","A player requires to pay 1,000,000 coconuts each tile player moves");
		this.img_path = "img/card/MoveTax.png";
	}
	public void activateEffectCard() {
		GameSetUp.gameLaw.taxPerTile = true;
	}
}
