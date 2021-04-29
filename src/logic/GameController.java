package logic;

import character.MainCharacter;
import character.ThousandYear;
import component.entity.Minion;
import component.location.Ocean;
import component.location.Plain;
import component.location.Prison;
import gui.MainIsland;
import gui.entity.HexagonPane;
import javafx.scene.media.AudioClip;
import update.AudioUpdate;
import update.GameSettingUpdate;
import update.MainIslandUpdate;
import update.PlayerPanelUpdate;
import update.TradeOverlayUpdate;

public class GameController {
	public GameController() {
//-------------------------- Choose start minion location-----------------------------------

		MainIsland.dataInteractMode();
		GameSetUp.isHighlightSpawnable = true;

		for (int i = 0; i < GameSettingUpdate.getNPlayer(); i++) {

			GameSetUp.thisTurn = GameSetUp.gameCharacter.get(i);
			GameSetUp.isSelectMinionSpawn = true;

			if (GameSetUp.thisTurn instanceof ThousandYear) {

				GameSetUp.isHighlightSpawnable = false;
				GameSetUp.isHighlightPlain = true;
			}

// ------------------------	Wait Player Choose Spawn Location ---------------------------------

			while (true) {
				System.out.print("");

				if (GameSetUp.selectedTile != null) {
					if (GameSetUp.thisTurn instanceof ThousandYear) {

						if (GameSetUp.selectedTile.getLocationType() instanceof Plain) {

							GameSetUp.isHighlightSpawnable = true;
							GameSetUp.isHighlightPlain = false;
							break;

						} else {

							AudioClip effect = AudioLoader.errorSound;
							effect.play();
							GameSetUp.selectedTile = null;

						}
					} else if (GameSetUp.selectedTile.isSpawnable()) {

						break;

					} else {

						AudioClip effect = AudioLoader.errorSound;
						effect.play();
						GameSetUp.selectedTile = null;

					}
				}
			}

// --------------------------------------- After Player Choose Spawn Location -----------------------------------

			spawnMinion(GameSetUp.selectedTile);
			GameSetUp.isReset = true;
			GameSetUp.selectedTile = null;
		}

		GameSetUp.isCountDown = true;
		GameSetUp.countDownDuration = 3;

		while (true) {
			System.out.print("");
			if (!GameSetUp.isCountDown) {
				break;
			}
		}
		GameSetUp.isTurnChange = true;
		GameSetUp.isHighlightSpawnable = false;
		GameSetUp.isReset = true;
		MainIsland.overlayInteractMode("");
		MainIslandUpdate.setCenter();

//------------------------------------------------------------------------------------------
		while (!GameSetUp.isGameEnd) {
			for (int i = 0; i < GameSettingUpdate.getNPlayer(); i++) {
				GameSetUp.thisTurn = GameSetUp.gameCharacter.get(i);
				GameSetUp.isDraw = true;
				MainCharacter character = GameSetUp.thisTurn;
				GameSetUp.gameLaw.activateEachTurn(character);
				character.startNewTurn();

				if (GameSetUp.theGovernment == character) {
					// remove/add lawcard
					Prison.canCapture = true;
					Ocean.canPardon = true;
					GameSetUp.governmentPoint++;
				}
				// what you do in a turn

////////////////////////////////////////////////////////////////FOR DEBUG ONLY ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

				System.out
						.println("------------------------------- Current Turn --------------------------------------\n"
								+ GameSetUp.thisTurn);

////////////////////////////////////////////////////////////////END OF DEBUG/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////				

				TradeOverlayUpdate.resetIstraded();
				while (!GameSetUp.isEndTurn) {

					try {
						PlayerPanelUpdate.updateTurnBar();
						PlayerPanelUpdate.updateStatusPane();
						PlayerPanelUpdate.updateGoodnessPoint();
						PlayerPanelUpdate.updateGovernmentPoint();
					} catch (Exception e) {

					}

					if (GameSetUp.isGameEnd) {
						break;
					}

				}
				if (GameSetUp.isGameEnd) {
					break;
				}

				GameSetUp.lawSlot.activateAllSlot();
				GameSetUp.isEndTurn = false;
				GameSetUp.canBuyMinion = true;
				GameSetUp.isTurnChange = true;
				MainIslandUpdate.setCenter();
				// AudioUpdate.changeTurn(GameSetUp.thisTurn,);
			}
			GameSetUp.turn++;
			GameSetUp.gameLaw.activateEachCycle();
		}
		System.out.println("END!!!");
	}

//----------------------spawn minion-----------------	

	public static void spawnMinion(HexagonPane tile) {
		Minion minion = null;
		for (int i = 0; i < Minion.MAX_MINION; i++) {
			minion = GameSetUp.thisTurn.getMyEntity().get(i);
			if(minion.getOnLocation() == null) {
				break;
			}
		}
		if(minion!=null) {
			minion.setOnLocation(tile.getLocationType());
			minion.setPosX(tile.getColumn());
			minion.setPosY(tile.getRow());
			minion.setMoveLeft(0);
			tile.getLocationType().addMinionToLocation(minion);
		}

	}

}
