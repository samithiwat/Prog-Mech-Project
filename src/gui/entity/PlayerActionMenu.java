package gui.entity;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.image.ImageView;

public class PlayerActionMenu extends ContextMenu implements Clickable {

	private static final int WIDTH = 300;

	private MenuItem buyMinion;
	private MenuItem buyLand;
	private MenuItem combine;
	private MenuItem split;
	private MenuItem confirm;

	public PlayerActionMenu() {
		buyMinion = new MenuItem("Buy Minion");
		buyMinion.setGraphic(new ImageView(ClassLoader.getSystemResource("img/icon/GoldIngot.png").toString()));

		buyLand = new MenuItem("Buy Land");
		buyLand.setGraphic(new ImageView(ClassLoader.getSystemResource("img/icon/House1.png").toString()));

		combine = new MenuItem("Combine");
		combine.setGraphic(new ImageView(ClassLoader.getSystemResource("img/icon/GroupIcon.png").toString()));

		split = new MenuItem("Split");
		split.setGraphic(new ImageView(ClassLoader.getSystemResource("img/icon/ScissorIcon.png").toString()));

		MenuItem cancle = new MenuItem("Cancle");		
//		cancle.setGraphic(new ImageView(ClassLoader.getSystemResource("img/icon/CancleIcon.png").toString()));
		
		confirm = new MenuItem("Confirm");
//		setId("player-action-menu-style");

		getItems().addAll(buyMinion, buyLand, combine, split, cancle,confirm);
	}

	@Override
	public void interact() {
		// Empty
	}

	@Override
	public void triggerDisable() {
		// Empty
	}
	
// ------------------------------------------------ Getter and Setter ------------------------------------------------------------	

	public MenuItem getBuyMinion() {
		return buyMinion;
	}

	public MenuItem getBuyLand() {
		return buyLand;
	}

	public MenuItem getCombine() {
		return combine;
	}

	public MenuItem getSplit() {
		return split;
	}

	public MenuItem getConfirm() {
		return confirm;
	}

}
