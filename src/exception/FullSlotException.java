package exception;

public class FullSlotException extends Exception {

	public FullSlotException() {
		super("No slot left!");
	}

}
