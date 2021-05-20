package component.law;

public abstract class InteractLawCard extends LawCard implements Interactable {

	protected String icon_img_path;

	public InteractLawCard(String name, String effectCard) {
		super(name, effectCard);
	}

	public String getIcon_img_path() {
		return icon_img_path;
	}

}
