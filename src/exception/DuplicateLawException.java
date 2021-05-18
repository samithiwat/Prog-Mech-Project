package exception;

public class DuplicateLawException extends Exception {

	public DuplicateLawException() {
		super("This law is already actived");
	}

}
