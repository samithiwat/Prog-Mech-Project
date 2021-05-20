package component.law;

import component.Component;

public abstract class LawCard extends Component {
	private String cardName;
	private String effectCard;
	protected String img_path;

	public abstract void activateEffectCard();

	public LawCard(String name, String effectCard) {
		super(name);
		this.cardName = name;
		this.effectCard = effectCard;
	}

// ------------------------------------- Equal Method --------------------------------------

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		LawCard other = (LawCard) obj;
		if (cardName == null) {
			if (other.cardName != null)
				return false;
		} else if (!cardName.equals(other.cardName))
			return false;
		if (effectCard == null) {
			if (other.effectCard != null)
				return false;
		} else if (!effectCard.equals(other.effectCard))
			return false;
		return true;
	}

// ----------------------------------- Getter and Setter -----------------------------------

	public String getImg_path() {
		return img_path;
	}

	public String getEffectCard() {
		return this.effectCard;
	}

}
