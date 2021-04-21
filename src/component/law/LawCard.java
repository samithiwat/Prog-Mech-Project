package component.law;

import component.Component;

public abstract class LawCard extends Component{
	private String cardName;
	private String effectCard;
	protected String img_path;
	public abstract void activateEffectCard();
	public LawCard(String name, String effectCard) {
		super(name);
		this.cardName = name;
		this.effectCard = effectCard;
	}

// ----------------------------------- Getter and Setter -----------------------------------
	
	public String getImg_path() {
		return img_path;
	} 
	
	public String getEffectCard() {
		return this.effectCard;
	}
	
}
