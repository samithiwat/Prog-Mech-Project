package gui.entity;

import component.entity.Minion;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Circle;

public class MinionList extends Tooltip {

	private final int RADIUS = 12;

	private HBox minionList;
	private Minion minion;

	public MinionList(Minion minion) {
		this.minion = minion;
		minionList = new HBox();
		minionList.setSpacing(20);
		setGraphic(minionList);
	}

	public void update() {
		minionList.getChildren().clear();

		Circle thisMinion = new Circle(RADIUS);
		thisMinion.setFill(minion.getPossessedBy().getColor());
		minionList.getChildren().add(thisMinion);

		for (int i = 0; i < minion.getMyMinion().size(); i++) {
			Circle symbol = new Circle(RADIUS);
			symbol.setFill(minion.getMyMinion().get(i).getPossessedBy().getColor());
			minionList.getChildren().add(symbol);
		}
	}

}
