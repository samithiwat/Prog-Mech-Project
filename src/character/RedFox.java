package character;

public class RedFox extends MainCharacter {
	public RedFox() {
		super("Mr.RedFox","");
		this.setMoney(11*M);
		
	}
	public void checkIsWin() {
		int count = 0;
		for(int i = 0 ; i < this.getPossessedArea().size() ; i++) {
			if(this.getPossessedArea().get(i).getName() == "Mine") {
				count += 1;
			}
		}
		if(count >= 3) {
			this.setWin(true);
		}
		else {
			this.setWin(false);
		}
	}
}
