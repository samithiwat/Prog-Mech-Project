package gui.entity;

import javafx.scene.layout.Pane;

public class MinionPane extends Pane {

	private final int[] posXList;
	private final int[] posYList;

	public MinionPane(int[] posXList, int[] posYList) {

		this.posXList = posXList;
		this.posYList = posYList;

	}

	public void setMinionAtPos(MenuIcon minionIcon, int pos) {

		minionIcon.setX(posXList[pos]);
		minionIcon.setY(posYList[pos]);
		getChildren().add(minionIcon);

	}

	public void setOneMinionSelectMode() {
		for (int i = 0; i < getChildren().size(); i++) {
			MinionIcon icon = (MinionIcon) getChildren().get(i);
			icon.selectOneMinionMode();

		}
	}

	public void setTwoMinionSelectMode() {
		for (int i = 0; i < getChildren().size(); i++) {
			MinionIcon icon = (MinionIcon) getChildren().get(i);
			icon.selectTwoMinionMode();

		}

	}

	public void setRansomMode() {
		for (int i = 0; i < getChildren().size(); i++) {
			MinionIcon icon = (MinionIcon) getChildren().get(i);
			icon.ransomMode();

		}
	}

	public void setPardonMode() {
		for (int i = 0; i < getChildren().size(); i++) {
			MinionIcon icon = (MinionIcon) getChildren().get(i);
			icon.pardonMode();

		}
	}
}
