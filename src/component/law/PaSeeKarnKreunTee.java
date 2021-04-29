package component.law;

import character.MainCharacter;
import logic.GameSetUp;

public class PaSeeKarnKreunTee extends LawCard {

	public static final int FEE = 1 * MainCharacter.M;
	public static final String IMG_PATH = "img/card/law/MoveTax.png";

	public PaSeeKarnKreunTee() {
		super("PaSeeKarnKreunTee", "A player requires to pay 1,000,000 coconuts each tile player moves");
		this.img_path = "img/card/law/MoveTax.png";
	}

	public void activateEffectCard() {
		GameSetUp.gameLaw.taxPerTile = true;
	}
}
