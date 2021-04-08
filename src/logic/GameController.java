package logic;

import character.MainCharacter;

public class GameController {
	public GameController() {
		for(int i = 0 ; i < GameSetUp.gameCharacter.size() ; i++) {
			//choose start location
		}
		while(GameSetUp.isGameEnd) {
			for(int i = 0 ; i < GameSetUp.gameCharacter.size() ; i++) {
				MainCharacter character = GameSetUp.gameCharacter.get(i);
				character.gainIncome();
				if(GameSetUp.theGovernment == character ) {
					// remove/add lawcard
				}
				//what you do in a turn
				while(GameSetUp.isEndTurn) {
					
				}
				GameSetUp.gameLaw.setDefault();
				GameSetUp.lawSlot.activateAllSlot();
			}
		}
	}
}
