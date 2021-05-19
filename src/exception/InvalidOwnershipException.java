package exception;

public class InvalidOwnershipException extends Exception {

	public InvalidOwnershipException() {
		super("You're not this minion's owner!");
	}

}
