package gui.entity;

import component.law.Interactable;
import component.law.LawCard;
import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import logic.GameSetUp;
import update.Updateable;

public class ActivedLawPane extends StackPane implements Updateable {

	private HBox activedLawPane;
	private static final double HEIGHT = 200;

	public ActivedLawPane() {

// --------------------------------------------------------- Set Up ----------------------------------------------------------------------

		setAlignment(Pos.CENTER);
		setLayoutX(270);
		setLayoutY(660);
		setPrefHeight(HEIGHT);

// -------------------------------------------------------- Component --------------------------------------------------------------------

		activedLawPane = new HBox();
		activedLawPane.setSpacing(50);

// -------------------------------------------------------- Add Component ----------------------------------------------------------------

		getChildren().addAll(activedLawPane);
	}

	@Override
	public void update() {
		activedLawPane.getChildren().clear();
		setVisible(false);
		if (GameSetUp.thisTurn.equals(GameSetUp.theGovernment)) {

			for (int i = 0; i < GameSetUp.lawSlot.nSlot(); i++) {
				LawCard law = GameSetUp.lawSlot.getSlot(i).getLaw();
				if (law instanceof Interactable) {

					ActivedLawIcon activedLaw = new ActivedLawIcon(law);
					activedLawPane.getChildren().add(activedLaw);
					setVisible(true);

				}

			}

		}
	}

}
