package update;

import java.util.ArrayList;

import gui.GameLobbyMenu;
import gui.entity.CharacterSetting;
import gui.entity.GameSetting;
import gui.entity.TextTitle;

public class GameSettingUpdate {
	private static int nPlayer = 6;

	public static void maxPlayerUpdate(boolean isIncrease) {
		if (isIncrease) {
			if (nPlayer == 6) {
				nPlayer = 3;
			} else {
				nPlayer++;
			}
		} else {
			if (nPlayer == 3) {
				nPlayer = 6;
			} else {
				nPlayer--;
			}
		}
		TextTitle maxPlayerNumText = GameSetting.getTexts().get(0);
		maxPlayerNumText.setText("" + nPlayer);
		updatePlayerBox();
	}

	public static void updatePlayerBox() {
		ArrayList<CharacterSetting> cBoxes = GameLobbyMenu.getCBoxes();
		CharacterSelectUpdate.startUpdate();
		switch (nPlayer) {
		case 6:
			cBoxes.get(3).setVisible(true);
			cBoxes.get(4).setVisible(true);
			cBoxes.get(5).setVisible(true);
			break;

		case 5:
			cBoxes.get(3).setVisible(true);
			cBoxes.get(4).setVisible(true);
			cBoxes.get(5).setVisible(false);
			break;

		case 4:
			cBoxes.get(3).setVisible(true);
			cBoxes.get(4).setVisible(false);
			cBoxes.get(5).setVisible(false);
			break;

		case 3:
			cBoxes.get(3).setVisible(false);
			cBoxes.get(4).setVisible(false);
			cBoxes.get(5).setVisible(false);
			break;
		}
	}

	public static void setNPlayer(int nPlayer) {
		GameSettingUpdate.nPlayer = nPlayer;
	}

	public static int getNPlayer() {
		return nPlayer;
	}

}
