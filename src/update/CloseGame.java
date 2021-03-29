package update;

public class CloseGame {
	private static boolean isCloseGame = false;
	public static void setIsCloseGame(boolean isClose) {
		isCloseGame = isClose;
	}
	public static void update() {
		if(isCloseGame) {
			System.exit(1);
		}
	}
}