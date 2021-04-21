package update;

import java.util.ArrayList;

import character.MainCharacter;
import component.location.Plain;
import gui.MainIsland;
import gui.MapOverview;
import gui.entity.HexagonPane;
import gui.entity.MapGrid;
import gui.entity.PlayerPanel;
import gui.entity.StatusBar;
import gui.entity.StatusPane;
import gui.entity.TextTitle;
import gui.entity.TurnBar;
import gui.entity.TurnCharacterIcon;
import gui.overlay.HandOverlay;
import gui.overlay.PlayerList1;
import gui.overlay.PlayerList2;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import logic.GameController;
import logic.GameSetUp;

public class PlayerPanelUpdate {

// ------------------------------------------------------------ Update Crown In Turn Bar --------------------------------------------------------

	public static void updateTurnBar() {
		for (int i = 0; i < GameSettingUpdate.getNPlayer(); i++) {

			// -------------------------------- Find Current Turn Player --------------------------------

			if (GameSetUp.thisTurn == GameSetUp.gameCharacter.get(i)) {
				
				TurnCharacterIcon currentPlayerIcon = (TurnCharacterIcon) TurnBar.getIconPane().getChildren().get(i);
				TurnCharacterIcon peviousPlayerIcon = null;
				if (i > 0) {
					peviousPlayerIcon = (TurnCharacterIcon) TurnBar.getIconPane().getChildren().get(i - 1);
				} else {
					peviousPlayerIcon = (TurnCharacterIcon) TurnBar.getIconPane().getChildren()
							.get(GameSettingUpdate.getNPlayer() - 1);
				}

				// ------------------------------------- Update Crown --------------------------------------

				peviousPlayerIcon.setPlayerTurn(false);
				currentPlayerIcon.setPlayerTurn(true);
				break;
			}
		}
		
	}

// ---------------------------------------------------------- Update Player Status -------------------------------------------------------------

	public static void updateStatusPane() {
		MainCharacter character = GameSetUp.thisTurn;

		double money = character.getMoney() / MainCharacter.M;

		TextTitle moneyStatus = StatusPane.getMoney().getStatus();
		TextTitle minionStatus = StatusPane.getMinion().getStatus();
		TextTitle landStatus = StatusPane.getLand().getStatus();

		moneyStatus.setText("$" + money + " M");
		minionStatus.setText("" + character.getMyEntity().size());
		landStatus.setText("" + character.getPossessedArea().size());

	}

	public static void updateGoodnessPoint() {
		PlayerPanel.getGoodnessPoint().updatePoint(GameSetUp.thisTurn.getGoodPoint(), Color.WHITE);
	}

	public static void updateGovernmentPoint() {
		if (GameSetUp.theGovernment != null) {
			PlayerPanel.getGovernmentPoint().updatePoint(GameSetUp.governmentPoint, GameSetUp.theGovernment.getColor());
		} else {
			PlayerPanel.getGovernmentPoint().updatePoint(7, Color.WHITE);
		}
	}

// ---------------------------------------------------------- Toggle Grid -------------------------------------------------------------

	public static void toggleGridUpdate() {
		
		MapGrid.setEnable(!MapGrid.isEnable());
		
		String id;
		
		if(MapGrid.isEnable()) {
			id = "grid-release-style";
		}
		else {
			id = "grid-disable";
		}
		
		for (int i = 0; i < MapGrid.getGrids().size(); i++) {
			ArrayList<HexagonPane> column = MapGrid.getGrids().get(i);
			for (int j = 0; j < column.size(); j++) {
				column.get(j).setId(id);
			}
		}

	}
	
// ------------------------------------------- Highlight Tile --------------------------------------------
	
	public static void highlightSpawnableTile() {
		
		for (int i = 0; i < MapGrid.getGrids().size(); i++) {
			ArrayList<HexagonPane> column = MapGrid.getGrids().get(i);
			for (int j = 0; j < column.size(); j++) {
				if(column.get(j).isSpawnable()) {
					column.get(j).setId("grid-highlight-style");
				}
			}
		}
	}
	
	public static void highlightPlainTile() {
		for (int i = 0; i < MapGrid.getGrids().size(); i++) {
			ArrayList<HexagonPane> column = MapGrid.getGrids().get(i);
			for (int j = 0; j < column.size(); j++) {
				if(column.get(j).getLocationType() instanceof Plain) {
					column.get(j).setId("grid-highlight-style");
				}
			}
		}
	}
	
	public static void resetTile() {
		for (int i = 0; i < MapGrid.getGrids().size(); i++) {
			ArrayList<HexagonPane> column = MapGrid.getGrids().get(i);
			for (int j = 0; j < column.size(); j++) {
					column.get(j).setId("grid-release-style");
			}
		}
	}
	
// ------------------------------------------- Toggle Player Panel Mode ---------------------------------------------	
	
	public static void closePanel() {
		PlayerPanel.getEndTurn().setVisible(false);
		PlayerPanel.getGoodnessPoint().setVisible(false);
		PlayerPanel.getGovernmentPoint().setVisible(false);
		PlayerPanel.getHandsIcon().setVisible(false);
		PlayerPanel.getStatusPane().setVisible(false);
		PlayerPanel.getTurnBar().setVisible(false);
	}
	
	public static void openPanel() {
		PlayerPanel.getEndTurn().setVisible(true);
		PlayerPanel.getGoodnessPoint().setVisible(true);
		PlayerPanel.getGovernmentPoint().setVisible(true);
		PlayerPanel.getHandsIcon().setVisible(true);
		PlayerPanel.getStatusPane().setVisible(true);
		PlayerPanel.getTurnBar().setVisible(true);
	}
	
	
//---------------------------------------- Update HandOverlay -----------------------------------------
	
	public static void updateHandOverlay() {
		GameSetUp.thisTurn.countWeaponCard();
		for(int i = 0 ; i < MapOverview.allHandOverlay.size() ; i++) {
			HandOverlay handOverlay = MapOverview.allHandOverlay.get(i);
			handOverlay.getNum_slot1().setText("x"+GameSetUp.thisTurn.getNum_Sword());
			handOverlay.getNum_slot2().setText("x"+GameSetUp.thisTurn.getNum_Axe());
			handOverlay.getNum_slot3().setText("x"+GameSetUp.thisTurn.getNum_Shield());
			handOverlay.getNum_slot4().setText("x"+GameSetUp.thisTurn.getNum_Bow());
			handOverlay.getNum_slot5().setText("x"+GameSetUp.thisTurn.getNum_Gun());
			if(GameSetUp.thisTurn.getMoney() < MainCharacter.M) {
				handOverlay.getDrawCard().setOnMouseClicked((MouseEvent event) -> {
					MainIsland.setShowMessage("Not enough money!", Color.WHITE, 36, 3);
				});
			}
			else {
				handOverlay.getDrawCard().setOnMouseClicked((MouseEvent event) -> {
					if(GameSetUp.thisTurn.getMoney() >= 1*MainCharacter.M ) {
						GameSetUp.thisTurn.setMoney(GameSetUp.thisTurn.getMoney() - 1*MainCharacter.M);
						GameSetUp.thisTurn.addCardtoHand(GameSetUp.weaponDeck.drawCard());	
						updateHandOverlay();
					}
				});				
			}
		}
	}
	
	public static void updatePlayerList() {
		for(int i = 0 ; i < MapOverview.allPlayerList1.size() ; i++) {
			PlayerList1 playerList1 = MapOverview.allPlayerList1.get(i);
			for(int j = 0 ; j < (int)(playerList1.getAllText().size()/3) ; j++) {
				MainCharacter character = GameSetUp.gameCharacter.get(j);
				int k = j*3;
				playerList1.getAllText().get(k).setText(character.getMoney() / MainCharacter.M + "M");
				playerList1.getAllText().get(k+1).setText(character.getMyEntity().size() + "");
				playerList1.getAllText().get(k+2).setText(character.getArea() + "");
			}
		}
		for(int i = 0 ; i < MapOverview.allPlayerList2.size() ; i++) {
			PlayerList2 playerList2 = MapOverview.allPlayerList2.get(i);
			for(int j = 0 ; j < (int)(playerList2.getAllText().size()/3) ; j++) {
				MainCharacter character = GameSetUp.gameCharacter.get(j+3);
				int k = j*3;
				playerList2.getAllText().get(k).setText(character.getMoney() / MainCharacter.M + "M");
				playerList2.getAllText().get(k+1).setText(character.getMyEntity().size() + "");
				playerList2.getAllText().get(k+2).setText(character.getArea() + "");
			}
		}
	}

	
}
