package component.law;

import logic.GameSetting;

public class PaSeeKarnKreunTee extends LawCard {
	public PaSeeKarnKreunTee() {
		super("PaSeeKarnKreunTee","A player requires to pay 1,000,000 coconuts each tile player moves");
	}
	public void activateEffectCard() {
		GameSetting.gameLaw.taxPerTile = true;
	}
}
