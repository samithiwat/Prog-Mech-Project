package logic;

import java.util.ArrayList;

import character.MainCharacter;
import character.ThousandYear;
import component.entity.Minion;
import component.location.Council;
import component.location.Location;
import component.location.Plain;
import component.location.Water;
import component.weaponCard.WeaponCard;
import components.character.GameCharacter;
import gui.MainIsland;
import gui.entity.HexagonPane;
import gui.entity.MapGrid;
import javafx.application.Platform;
import javafx.scene.media.AudioClip;
import update.GameSettingUpdate;
import update.HexTileUpdate;
import update.MainIslandUpdate;
import update.PlayerPanelUpdate;

public class GameController {
	public GameController() {
//--------------------------Choose start minion location-----------------------------------

		MainIsland.dataInteractMode();
		GameSetUp.isHighlightSpawnable = true;

		for (int i = 0; i < GameSettingUpdate.getNPlayer(); i++) {
			
			GameSetUp.thisTurn = GameSetUp.gameCharacter.get(i);
			
			while (true) {
				// empty
				System.out.println(GameSetUp.selectedTile);
				if (GameSetUp.selectedTile != null) {
					if (GameSetUp.gameCharacter.get(i) instanceof ThousandYear) {
						GameSetUp.isHighlightSpawnable = false;
						GameSetUp.isHighlightPlain = true;
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
//			System.out.println(GameSetUp.selectedTile);
			System.out.println("Spawn Minion");
			spawnMinion(new Minion(GameSetUp.gameCharacter.get(i)), GameSetUp.selectedTile);
			// HexTileUpdate.updateMinionIcon();
			GameSetUp.selectedTile = null;
		}
		GameSetUp.isEndTurn = true;
		GameSetUp.isHighlightSpawnable = false;
		MainIsland.overlayInteractMode();
		MainIslandUpdate.setCenter();
//------------------------------------------------------------------------------------------
		while (!GameSetUp.isGameEnd) {
			for (int i = 0; i < GameSettingUpdate.getNPlayer(); i++) {
				GameSetUp.thisTurn = GameSetUp.gameCharacter.get(i);
				MainCharacter character = GameSetUp.thisTurn;
				character.totalIncome();
				character.gainIncome();

				if (GameSetUp.theGovernment == character) {
					// remove/add lawcard
					GameSetUp.governmentPoint++;
				}
				// what you do in a turn

////////////////////////////////////////////////////////////////FOR DEBUG ONLY ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

				System.out
						.println("------------------------------- Current Turn --------------------------------------\n"
								+ GameSetUp.thisTurn);

////////////////////////////////////////////////////////////////END OF DEBUG/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////				

				while (!GameSetUp.isEndTurn) {

					try {
						PlayerPanelUpdate.updateTurnBar();
						PlayerPanelUpdate.updateStatusPane();
						PlayerPanelUpdate.updateGoodnessPoint();
						PlayerPanelUpdate.updateGovernmentPoint();
					} catch (Exception e) {

					}

//////////////////////////////////////////////////////////////// FOR DEBUG ONLY ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

//						System.out.println("Current Turn :" + GameSetUp.thisTurn);

//////////////////////////////////////////////////////////////// END OF DEBUG/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

				}
				GameSetUp.gameLaw.setDefault();
				GameSetUp.lawSlot.activateAllSlot();
				// GameSetUp.isEndTurn = false;
			}
		}
	}

//----------------------spawn minion-----------------	
	public void spawnMinion(Minion minion, HexagonPane tile) {
		minion.setOnLocation(tile.getLocationType());
		minion.setPosX(tile.getRow());
		minion.setPosY(tile.getColumn());
		tile.getLocationType().addMinonToLocation(minion);
	}

//----------------------Fight/Trade--------------------------	

//	public void Fight(Minion challenger, Minion defender) {
//		ArrayList<WeaponCard> challenger_slot = new ArrayList<WeaponCard>();
//		ArrayList<WeaponCard> defender_slot = new ArrayList<WeaponCard>();
//		// Each player choose their weapon card to add in these slots.
//		int challenger_atkPoint = 0, defender_atkPoint = 0;
//		for (int i = 0; i < challenger_slot.size(); i++) {
//			int randomized_atkPoint = challenger_slot.get(i).rand_attack();
//			// update the randomized atk to the screen
//			challenger_atkPoint += randomized_atkPoint;
//		}
//		for (int i = 0; i < defender_slot.size(); i++) {
//			int randomized_atkPoint = defender_slot.get(i).rand_attack();
//			// update the randomized atk to the screen
//			defender_atkPoint += randomized_atkPoint;
//		}
//
//		if (challenger_atkPoint > defender_atkPoint) {
////			this.winner = challenger.getPossessedBy();
//			challenger.addMinion(defender);
//		} else if (challenger_atkPoint < defender_atkPoint) {
////			this.winner = defender.getPossessedBy();
//			defender.addMinion(challenger);
//		}
//	}
//
//	public void Fight(Minion challenger, Location defender) {
//		ArrayList<WeaponCard> challenger_slot = new ArrayList<WeaponCard>();
//		ArrayList<WeaponCard> defender_slot = new ArrayList<WeaponCard>();
//		// Each player choose their weapon card to add in these slots.
//		defender_slot.add(GameSetUp.weaponDeck.drawCard());
//		defender_slot.add(GameSetUp.weaponDeck.drawCard());
//		int challenger_atkPoint = 0, defender_atkPoint = 0;
//		for (int i = 0; i < challenger_slot.size(); i++) {
//			int randomized_atkPoint = challenger_slot.get(i).rand_attack();
//			// update the randomized atk to the screen
//			challenger_atkPoint += randomized_atkPoint;
//		}
//		for (int i = 0; i < defender_slot.size(); i++) {
//			int randomized_atkPoint = defender_slot.get(i).rand_attack();
//			// update the randomized atk to the screen
//			defender_atkPoint += randomized_atkPoint;
//		}
//
//		if (challenger_atkPoint > defender_atkPoint) {
////			this.winner = challenger.getPossessedBy();
////			challenger.addMinion(defender);
//		} else if (challenger_atkPoint < defender_atkPoint) {
////			this.winner = defender.getPossessedBy();
////			defender.addMinion(challenger);
//		}
//	}
//
//	public void Trade(MainCharacter trader, MainCharacter traded) {
//		ArrayList<WeaponCard> trader_WeaponSlot = new ArrayList<WeaponCard>();
//		ArrayList<WeaponCard> traded_WeaponSlot = new ArrayList<WeaponCard>();
//		int trader_money = 0;
//		int traded_money = 0;
////		update trading screen to logic
////		while(!accepted) {
////		}
//		trader.getWeaponHand().addAll(traded_WeaponSlot);
//		traded.getWeaponHand().addAll(trader_WeaponSlot);
//		trader.setMoney(trader.getMoney() + traded_money - trader_money);
//		traded.setMoney(traded.getMoney() - traded_money + trader_money);
//	}
}
