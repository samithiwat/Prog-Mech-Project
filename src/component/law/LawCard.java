package component.law;

public abstract class LawCard {
	private String cardName;
	private String effectCard;
	public abstract void activateEffectCard();
	public LawCard(String name, String effectCard) {
		this.cardName = name;
		this.effectCard = effectCard;
	}
}
