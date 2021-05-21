package logic;

import character.MainCharacter;
import character.ThousandYear;
import component.entity.Minion;
import component.location.Ocean;
import component.location.Plain;
import component.location.Prison;
import gui.MainIsland;
import gui.entity.HexagonPane;
import javafx.application.Platform;
import javafx.scene.media.AudioClip;
import update.AudioUpdate;
import update.GameSettingUpdate;
import update.MainIslandUpdate;
import update.PlayerPanelUpdate;
import update.TradeOverlayUpdate;

public class GameController {
	public GameController() {
//-------------------------- Choose start minion location-----------------------------------

		MainIsland.dataInteractMode("Select location to spawn your minion.", false, false);
		GameSetUp.isHighlightSpawnable = true;
		AudioUpdate.changeEnv(AudioLoader.selectSound);
		
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

		GameSetUp.thisTurn = GameSetUp.gameCharacter.get(0);
		GameSetUp.isTurnChange = true;
		GameSetUp.isHighlightSpawnable = false;
		GameSetUp.isReset = true;
		MainIsland.overlayInteractMode("", true, true);
		MainIslandUpdate.setCenter();

//------------------------------------------------------------------------------------------
		while (!GameSetUp.isGameEnd) {
			for (int i = 0; i < GameSettingUpdate.getNPlayer(); i++) {
				GameSetUp.thisTurn = GameSetUp.gameCharacter.get(i);
//				AudioUpdate.change(GameSetUp.thisTurn.getBgm());
				GameSetUp.isDraw = true;
				MainCharacter character = GameSetUp.thisTurn;
				GameSetUp.gameLaw.activateEachTurn(character);
				character.startNewTurn();

				if (GameSetUp.theGovernment == character) {
					// remove/add lawcard
					Prison.canCapture = true;
					Ocean.canPardon = true;
				}
				// what you do in a turn			

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
				if (GameSetUp.sirTewada != null) {
					GameSetUp.sirTewada.checkIsWin();
				}
				GameSetUp.lawSlot.activateAllSlot();
				GameSetUp.isEndTurn = false;
				GameSetUp.canBuyMinion = true;
				GameSetUp.isTurnChange = true;
				MainIslandUpdate.setCenter();
			}
			GameSetUp.cycle++;
			GameSetUp.gameLaw.activateEachCycle();

		}
		Platform.runLater(new Runnable() {

			@Override
			public void run() {
				SceneController.endScene(getWinner());
			}
		});

	}

//----------------------spawn minion-----------------	

	public static void spawnMinion(HexagonPane tile) {
		Minion minion = null;
		for (int i = 0; i < Minion.MAX_MINION; i++) {
			minion = GameSetUp.thisTurn.getMyEntity().get(i);
			if (minion.getOnLocation() == null) {
				break;
			}
		}
		if (minion != null) {
			minion.setOnLocation(tile.getLocationType());
			minion.setPosX(tile.getColumn());
			minion.setPosY(tile.getRow());
			minion.setMoveLeft(0);
			tile.getLocationType().addMinionToLocation(minion);
		}

	}

	private MainCharacter getWinner() {
		for (MainCharacter player : GameSetUp.gameCharacter) {
			if (player.isWin()) {
				return player;
			}
		}
		return null;
	}
}
