package exception;

public class OutOfActionException extends Exception{

	public OutOfActionException() {
		super("You can't move anymore!");
	}
	
}
