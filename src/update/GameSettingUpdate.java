package update;

import gui.entity.GameSetting;

public class GameSettingUpdate {
	
	private static int nPlayer = 6;
	
	public void maxPlayerUpdate(GameSetting gs,boolean isIncrease) {
		
	}

	public static int getnPlayer() {
		return nPlayer;
	}

	public static void setnPlayer(int nPlayer) {
		GameSettingUpdate.nPlayer = nPlayer;
	}
	
}
