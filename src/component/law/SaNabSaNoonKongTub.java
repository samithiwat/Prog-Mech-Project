package component.law;

import logic.GameSetUp;

public class SaNabSaNoonKongTub extends LawCard{
	
	public static final String IMG_PATH = "img/card/law/SupportArmy.png";
	
	public SaNabSaNoonKongTub() {
		super("SaNabSaNoonKongTub","Increase the money player can get from secret base by 3,000,000 coconuts");
		this.img_path = "img/card/law/SupportArmy.png";
	}
	public void activateEffectCard() {
		// wait when secretbase location is done.
		GameSetUp.gameLaw.supportArmy = true;
		for(int i=0;i<GameSetUp.allsecretBases.size();i++) {
			GameSetUp.allsecretBases.get(i).setIncome(4);
		}
	}
}
