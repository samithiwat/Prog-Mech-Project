package component.law;

import logic.GameSetUp;

public class PaSeeKarnKreunTee extends LawCard {
	public PaSeeKarnKreunTee() {
		super("PaSeeKarnKreunTee","A player requires to pay 1,000,000 coconuts each tile player moves");
	}
	public void activateEffectCard() {
		GameSetUp.gameLaw.taxPerTile = true;
	}
}
