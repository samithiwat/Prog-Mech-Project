package exception;

public class WaterTileException extends Exception {

	public WaterTileException() {
		super("Can't move to water tile!");
	}
}
