package update;

public class GameSettingUpdate {
	private static int nPlayer = 6;
	
	public static void setNPlayer(int nPlayer) {
		GameSettingUpdate.nPlayer = nPlayer;
	}
	
	public static int getNPlayer() {
		return nPlayer;
	}

}
