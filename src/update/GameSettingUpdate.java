package update;

import java.util.ArrayList;

import gui.GameSettingMenu;
import gui.entity.CharacterBox;
import gui.entity.GameSetting;
import gui.entity.TextTitle;
import logic.SceneController;

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
		ArrayList<CharacterBox> cBoxes = GameSettingMenu.getCBoxes();
		switch(nPlayer) {
		case 6 :
			cBoxes.get(0).setVisible(true);
			cBoxes.get(1).setVisible(true);
			cBoxes.get(2).setVisible(true);
			break;
		
		case 5 :
			cBoxes.get(0).setVisible(true);
			cBoxes.get(1).setVisible(true);
			cBoxes.get(2).setVisible(false);
			break;
		
		case 4 :
			cBoxes.get(0).setVisible(true);
			cBoxes.get(1).setVisible(false);
			cBoxes.get(2).setVisible(false);
			break;
		
		case 3 :
			cBoxes.get(0).setVisible(false);
			cBoxes.get(1).setVisible(false);
			cBoxes.get(2).setVisible(false);
			break;
		}
		
//		for(int i = 0 ; i < cBoxes.size() ; i++) {
//			if(nPlayer-3 <= i) {
//				cBoxes.get(i).setVisible(false);
//			}
//			else {
//				cBoxes.get(i).setVisible(true);
//			}
//		}
		//SceneController.setScene((new GameSettingMenu()).getScene());
		
	}
	
	public static void selectCharacterUpdate(int id) {
		
	}

	public static void setNPlayer(int nPlayer) {
		GameSettingUpdate.nPlayer = nPlayer;
	}

	public static int getNPlayer() {
		return nPlayer;
	}

}
