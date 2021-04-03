package component.law;

import java.util.ArrayList;

import character.MainCharacter;
import logic.GameSetting;

public class ChuayLeauKonJon extends LawCard {
	public ChuayLeauKonJon() {
		super("ChuayLeauKonJon", "The richest player has to pay the poorest player 2,000,000 coconuts. "
				+ "If there are more than one poorest player, the richest player has to pay 1,000,000 coconuts to each player");
	}

	public void activateEffectCard() {
		int max = Integer.MIN_VALUE;
		int min = Integer.MAX_VALUE;
		for (int i = 0; i < GameSetting.gameCharacter.size(); i++) {
			max = Math.max(max, GameSetting.gameCharacter.get(i).getMoney());
			min = Math.min(min, GameSetting.gameCharacter.get(i).getMoney());
		}
		ArrayList<MainCharacter> richestPlayers = new ArrayList<MainCharacter>();
		ArrayList<MainCharacter> poorestPlayers = new ArrayList<MainCharacter>();
		for (int i = 0; i < GameSetting.gameCharacter.size(); i++) {
			if (GameSetting.gameCharacter.get(i).getMoney() == max) {
				richestPlayers.add(GameSetting.gameCharacter.get(i));
			}
			if (GameSetting.gameCharacter.get(i).getMoney() == min) {
				poorestPlayers.add(GameSetting.gameCharacter.get(i));
			}
		}
		for (int i = 0; i < poorestPlayers.size(); i++) {
			for (int j = 0; j < richestPlayers.size(); j++) {
				richestPlayers.get(j).setMoney(richestPlayers.get(j).getMoney() - 1 * MainCharacter.M);
				poorestPlayers.get(i).setMoney(poorestPlayers.get(i).getMoney() + 1 * MainCharacter.M);
			}
		}
		if (poorestPlayers.size() == 1) {
			for (int j = 0; j < richestPlayers.size(); j++) {
				richestPlayers.get(j).setMoney(richestPlayers.get(j).getMoney() - 1 * MainCharacter.M);
				poorestPlayers.get(0).setMoney(poorestPlayers.get(0).getMoney() + 1 * MainCharacter.M);
			}
		}
	}
}
