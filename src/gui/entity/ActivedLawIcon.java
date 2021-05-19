package gui.entity;

import component.law.InteractLawCard;
import component.law.Interactable;
import component.law.LawCard;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.FontWeight;

public class ActivedLawIcon extends VBox implements Clickable {

	private ImageView icon;
	private TextTitle name;
	private LawCard law;

	private boolean isInteractable = true;

	public ActivedLawIcon(LawCard law) {
// ------------------------------------------- Set Up -------------------------------------------------------------

		this.law = law;
		setSpacing(20);
		setAlignment(Pos.CENTER);
		interact();

// ----------------------------------------- Components -----------------------------------------------------------

		if (law instanceof Interactable) {

			InteractLawCard interactLaw = (InteractLawCard) law;

			icon = new ImageView(ClassLoader.getSystemResource(interactLaw.getIcon_img_path()).toString());
			name = new TextTitle(interactLaw.getName(), Color.WHITE, FontWeight.BOLD, 36);

		} else {

			icon = new ImageView(ClassLoader.getSystemResource("img/card/CardBack.png").toString());
			name = new TextTitle("", Color.WHITE, FontWeight.BOLD, 36);
			isInteractable = false;
			setVisible(false);

		}

// ---------------------------------------- Add Component ---------------------------------------------------------

		getChildren().addAll(icon, name);
	}

	@Override
	public void interact() {
		setOnMouseEntered(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				EFFECT_MOUSE_ENTER.play();
				DropShadow dropShadow = new DropShadow();
				icon.setEffect(dropShadow);
				name.setEffect(dropShadow);
			}

		});

		setOnMouseExited(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				icon.setEffect(null);
				name.setEffect(null);
			}
		});

		setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				EFFECT_MOUSE_CLICK.play();
				law.activateEffectCard();
			}
		});
	}

	@Override
	public void triggerDisable() {
		setDisable(!isDisable());
	}

// ------------------------------------------------- Getter and Setter ---------------------------------------------------------------	

	public boolean isInteractable() {
		return this.isInteractable;
	}

}
