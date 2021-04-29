package component.location;

public class SecretBase extends Location implements Incomeable{
	public SecretBase() {
		super("SecretBase","Give money",1,0);
	}
	
	public double getIncome() {
		return this.incomePerRound;
	}
	
	public void setIncome(double income) {
		this.incomePerRound = income;
	}
}
