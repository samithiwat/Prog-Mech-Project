package gui.entity;

import component.law.Interactable;
import component.law.LawCard;
import javafx.event.EventHandler;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.FontWeight;

public class ActivedLawIcon extends HBox implements Clickable {

	private ImageView icon;
	private TextTitle name;
	private LawCard law;
	
	private boolean isActive = true;

	public ActivedLawIcon(LawCard law) {
// ------------------------------------------- Set Up -------------------------------------------------------------

		this.law = law;
		setSpacing(20);
		interact();

// ----------------------------------------- Components -----------------------------------------------------------

		if (law instanceof Interactable) {

			icon = new ImageView(ClassLoader.getSystemResource(law.getImg_path()).toString());
			name = new TextTitle(law.getName(), Color.WHITE, FontWeight.BOLD, 36);

		} else {

			icon = new ImageView(ClassLoader.getSystemResource("img/card/BackCard.png").toString());
			name = new TextTitle("", Color.WHITE, FontWeight.BOLD, 36);
			isActive = false;
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
				law.activateEffectCard();
			}
		});
	}

	@Override
	public void triggerDisable() {
		setDisable(!isDisable());
	}

// ------------------------------------------------- Getter and Setter ---------------------------------------------------------------	

	public boolean isActive() {
		return this.isActive;
	}
	
}
