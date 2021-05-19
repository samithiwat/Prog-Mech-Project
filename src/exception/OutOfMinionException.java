package exception;

public class OutOfMinionException extends Exception {

	public OutOfMinionException() {
		super("I don't have minion left.");
	}

}
