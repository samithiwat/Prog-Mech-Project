package component.law;

import java.util.ArrayList;

import character.MainCharacter;
import logic.GameSetUp;
import update.GameSettingUpdate;

public class Sympathetic extends LawCard {
	
	public Sympathetic() {
		super("ChuayLeauKonJon", "The richest player has to pay the poorest player 2,000,000 coconuts. "
				+ "If there are more than one poorest player, the richest player has to pay 1,000,000 coconuts to each player");
		this.img_path = "img/card/law/SympatheticPoor.png";
	}

	public void activateEffectCard() {
		double max = Integer.MIN_VALUE;
		double min = Integer.MAX_VALUE;
		for (int i = 0; i < GameSettingUpdate.getNPlayer(); i++) {
			max = Math.max(max, GameSetUp.gameCharacter.get(i).getMoney());
			min = Math.min(min, GameSetUp.gameCharacter.get(i).getMoney());
		}
		ArrayList<MainCharacter> richestPlayers = new ArrayList<MainCharacter>();
		ArrayList<MainCharacter> poorestPlayers = new ArrayList<MainCharacter>();
		for (int i = 0; i < GameSettingUpdate.getNPlayer(); i++) {
			if (GameSetUp.gameCharacter.get(i).getMoney() == max) {
				richestPlayers.add(GameSetUp.gameCharacter.get(i));
			}
			if (GameSetUp.gameCharacter.get(i).getMoney() == min) {
				poorestPlayers.add(GameSetUp.gameCharacter.get(i));
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
