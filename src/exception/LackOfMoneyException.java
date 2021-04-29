package exception;

public class LackOfMoneyException extends Exception{
	
	public LackOfMoneyException() {
		super("You not have enough money.");
	}

}
