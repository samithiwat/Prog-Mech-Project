package gui.entity;

import javafx.scene.layout.Pane;

public class MinionPane extends Pane{

	private final int[] posXList;
	private final int[] posYList;
	
	public MinionPane(int[] posXList,int[] posYList) {
		
		this.posXList = posXList;
		this.posYList = posYList;
		
	}
	
	public void setMinionAtPos(MenuIcon minionIcon,int pos) {
	
		minionIcon.setX(posXList[pos]);
		minionIcon.setY(posYList[pos]);
		getChildren().add(minionIcon);
		
	}
	
}
