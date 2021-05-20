package update;

import java.util.ArrayList;

import character.Dummy_Government;
import character.MainCharacter;
import component.location.Council;
import component.location.Plain;
import gui.MainIsland;
import gui.MapOverview;
import gui.PrisonIsland;
import gui.entity.ActivedLawPane;
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
import javafx.scene.layout.StackPane;
import javafx.scene.media.AudioClip;
import javafx.scene.paint.Color;
import logic.AudioLoader;
import logic.GameController;
import logic.GameSetUp;

public class PlayerPanelUpdate {

	public static ArrayList<ActivedLawPane> allActivedLawPanes = new ArrayList<ActivedLawPane>();

// ------------------------------------------------------------ Update Crown In Turn Bar --------------------------------------------------------

	public static void updateTurnBar() {
		for (int i = 0; i < GameSettingUpdate.getNPlayer(); i++) {

			// -------------------------------- Find Current Turn Player
			// --------------------------------

			if (GameSetUp.thisTurn == GameSetUp.gameCharacter.get(i)) {

				TurnCharacterIcon currentPlayerIcon = (TurnCharacterIcon) TurnBar.getIconPane().getChildren().get(i);
				TurnCharacterIcon peviousPlayerIcon = null;
				if (i > 0) {
					peviousPlayerIcon = (TurnCharacterIcon) TurnBar.getIconPane().getChildren().get(i - 1);
				} else {
					peviousPlayerIcon = (TurnCharacterIcon) TurnBar.getIconPane().getChildren()
							.get(GameSettingUpdate.getNPlayer() - 1);
				}

				// ------------------------------------- Update Crown
				// --------------------------------------

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
		minionStatus.setText("" + character.getMinionLeft());
		landStatus.setText("" + character.getArea());

	}

	public static void updateGoodnessPoint() {
		PlayerPanel.getGoodnessPoint().updatePoint(GameSetUp.thisTurn.getGoodPoint(), Color.WHITE);
	}

	public static void updateGovernmentPoint() {
		if (!(GameSetUp.theGovernment instanceof Dummy_Government)) {
			PlayerPanel.getGovernmentPoint().updatePoint(Council.howLong(), GameSetUp.theGovernment.getColor());
		} else {
			PlayerPanel.getGovernmentPoint().updatePoint(7, Color.WHITE);
		}
	}

// ----------------------------------------------------- Update Actived Law Pane ------------------------------------------------------

	public static void updateActivedLawPane() {
		for (int i = 0; i < allActivedLawPanes.size(); i++) {
			allActivedLawPanes.get(i).update();
		}
	}
	
	public static void setVisibleActivedLawPane(boolean isVisible) {
		for (int i = 0; i < allActivedLawPanes.size(); i++) {
			allActivedLawPanes.get(i).setVisible(isVisible);
		}
	}

// ---------------------------------------------------------- Toggle Grid -------------------------------------------------------------

	public static void toggleGridUpdate() {

		MapGrid.setEnable(!MapGrid.isEnable());

		String id;

		if (MapGrid.isEnable()) {
			id = "grid-release-style";
		} else {
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
				if (column.get(j).isSpawnable()) {
					column.get(j).setId("grid-highlight-style");
				}
			}
		}
	}

	public static void highlightPlainTile() {
		for (int i = 0; i < MapGrid.getGrids().size(); i++) {
			ArrayList<HexagonPane> column = MapGrid.getGrids().get(i);
			for (int j = 0; j < column.size(); j++) {
				if (column.get(j).getLocationType() instanceof Plain) {
					column.get(j).setId("grid-highlight-style");
				}
			}
		}
	}

	public static void resetTile() {
		String id;

		if (MapGrid.isEnable()) {
			id = "grid-release-style";
		} else {
			id = "grid-disable";
		}

		for (int i = 0; i < MapGrid.getGrids().size(); i++) {
			ArrayList<HexagonPane> column = MapGrid.getGrids().get(i);
			for (int j = 0; j < column.size(); j++) {
				column.get(j).setId(id);
				column.get(j).getLandInfoRoot().setVisible(false);
			}
		}
	}

// ------------------------------------------- Toggle Player Panel Mode ---------------------------------------------	

	public static void setPanelVisible(boolean isVisible) {
		PlayerPanel.getEndTurn().setVisible(isVisible);
		PlayerPanel.getGoodnessPoint().setVisible(isVisible);
		PlayerPanel.getGovernmentPoint().setVisible(isVisible);
		PlayerPanel.getHandsIcon().setVisible(isVisible);
		PlayerPanel.getStatusPane().setVisible(isVisible);
		PlayerPanel.getTurnBar().setVisible(isVisible);
		for (int i = 0; i < allActivedLawPanes.size(); i++) {
			allActivedLawPanes.get(i).setVisible(isVisible);
		}
	}

//---------------------------------------- Update HandOverlay -----------------------------------------

	public static void updateHandOverlay() {
		GameSetUp.thisTurn.countWeaponCard();
		for (int i = 0; i < MapOverview.allHandOverlay.size(); i++) {
			HandOverlay handOverlay = MapOverview.allHandOverlay.get(i);
			handOverlay.getNum_slot1().setText("x" + GameSetUp.thisTurn.getNum_Sword());
			handOverlay.getNum_slot2().setText("x" + GameSetUp.thisTurn.getNum_Axe());
			handOverlay.getNum_slot3().setText("x" + GameSetUp.thisTurn.getNum_Shield());
			handOverlay.getNum_slot4().setText("x" + GameSetUp.thisTurn.getNum_Bow());
			handOverlay.getNum_slot5().setText("x" + GameSetUp.thisTurn.getNum_Gun());
			if (GameSetUp.thisTurn.getMoney() < MainCharacter.M) {
				handOverlay.getDrawCard().setOnMouseClicked((MouseEvent event) -> {
					MainIsland.setShowMessage("Not enough money!", Color.WHITE, 120, 2000);
					setShowMessage("Not enough money!", Color.WHITE, 120, 2000);
				});
			} else {
				handOverlay.getDrawCard().setOnMouseClicked((MouseEvent event) -> {
					if (GameSetUp.thisTurn.getMoney() >= 1 * MainCharacter.M) {
						GameSetUp.thisTurn.setMoney(GameSetUp.thisTurn.getMoney() - 1 * MainCharacter.M);
						GameSetUp.thisTurn.addCardtoHand(GameSetUp.weaponDeck.drawCard());
						MainIsland.setShowMessage("You bought a card!", Color.WHITE, 120, 2000);
						setShowMessage("You bought a card!", Color.WHITE, 120, 2000);
						AudioClip effect = AudioLoader.successfulEffect;
						effect.play();
						GameSetUp.isDraw = false;
						updateHandOverlay();
					}
				});
			}
			if (GameSetUp.isDraw == false) {
				handOverlay.getDrawCard().setVisible(false);
			} else {
				handOverlay.getDrawCard().setVisible(true);
			}
		}
	}

	public static void updatePlayerList() {
		for (int i = 0; i < MapOverview.allPlayerList1.size(); i++) {
			PlayerList1 playerList1 = MapOverview.allPlayerList1.get(i);
			for (int j = 0; j < (int) (playerList1.getAllText().size() / 4); j++) {
				MainCharacter character = GameSetUp.gameCharacter.get(j);
				int k = j * 4;
				playerList1.getAllText().get(k).setText(character.getMoney() / MainCharacter.M + "M");
				playerList1.getAllText().get(k + 1).setText(character.getMinionLeft() + "");
				playerList1.getAllText().get(k + 2).setText(character.getArea() + "");
				playerList1.getAllText().get(k + 3).setText(character.getGoodPoint() + "");
			}
		}
		for (int i = 0; i < MapOverview.allPlayerList2.size(); i++) {
			PlayerList2 playerList2 = MapOverview.allPlayerList2.get(i);
			for (int j = 0; j < (int) (playerList2.getAllText().size() / 4); j++) {
				MainCharacter character = GameSetUp.gameCharacter.get(j + 3);
				int k = j * 4;
				playerList2.getAllText().get(k).setText(character.getMoney() / MainCharacter.M + "M");
				playerList2.getAllText().get(k + 1).setText(character.getMinionLeft() + "");
				playerList2.getAllText().get(k + 2).setText(character.getArea() + "");
				playerList2.getAllText().get(k + 3).setText(character.getGoodPoint() + "");
			}
		}
	}

// ------------------------------------------ Update Message -----------------------------------------------

	public static void setShowMessage(String message, Color color, int size, int duration) {
		MapOverview.setShowMessage(message, color, size, duration);
		MainIsland.setShowMessage(message, color, size, duration);
		PrisonIsland.setShowMessage(message, color, size, duration);
	}

	public static void setShowMessage(String message, Color color, Color strokeColor, int size, int strokeWidth,
			int duration) {
		MapOverview.setShowMessage(message, color, strokeColor, size, strokeWidth, duration);
		MainIsland.setShowMessage(message, color, strokeColor, size, strokeWidth, duration);
		PrisonIsland.setShowMessage(message, color, strokeColor, size, strokeWidth, duration);
	}

}
