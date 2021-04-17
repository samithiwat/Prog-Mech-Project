package exception;

public class ExceedToBuyMinionException extends Exception{

	public ExceedToBuyMinionException(){
		super("You can buy only one minion per turn!");
	}
	
}
