package gui.entity;

import exception.ExceedToBuyMinionException;
import exception.FailToBuyMinionException;
import gui.MainIsland;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import logic.GameSetUp;

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
		
		buyMinion.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				
				MainIsland.dataInteractMode();
				MainIsland.getInfoRoot().setAlignment(Pos.BOTTOM_CENTER);
				MainIsland.getInfo().setFontBold(48);
				MainIsland.getInfo().setFill(Color.web("0x393E46"));
				MainIsland.getInfo().setText("Select spawn location of your minion");
				GameSetUp.isHighlightSpawnable = true;
				GameSetUp.selectedTile = null;
				
				Thread confirmBuy = new Thread(()->{
					while(true) {
						System.out.print("");
						if(GameSetUp.selectedTile != null) {
							GameSetUp.isReset = true;
							GameSetUp.isHighlightSpawnable = false;
							MainIsland.overlayInteractMode();
							try {
								GameSetUp.thisTurn.buyMinion();
								MainIsland.overlayInteractMode();
							} catch (FailToBuyMinionException e) {
								EFFECT_ERROR.play();
								MainIsland.setShowMessage("Fail to buy a minion!", Color.web("E04B4B"), 90, 3000);
							}
							catch(ExceedToBuyMinionException e) {
								EFFECT_ERROR.play();
								MainIsland.setShowMessage("You can buy only 1 minion per turn!", Color.web("E04B4B"), 90, 3000);
							}
							GameSetUp.selectedTile = null;
							break;
						}
				}
					System.out.println();
				});
				confirmBuy.start();
				
			}
		});

		buyLand = new MenuItem("Buy Land");
		buyLand.setGraphic(new ImageView(ClassLoader.getSystemResource("img/icon/House1.png").toString()));
		buyLand.setVisible(false);

		combine = new MenuItem("Combine");
		combine.setGraphic(new ImageView(ClassLoader.getSystemResource("img/icon/GroupIcon.png").toString()));
		combine.setVisible(false);

		split = new MenuItem("Split");
		split.setGraphic(new ImageView(ClassLoader.getSystemResource("img/icon/ScissorIcon.png").toString()));
		split.setVisible(false);

		MenuItem cancle = new MenuItem("Cancle");		
//		cancle.setGraphic(new ImageView(ClassLoader.getSystemResource("img/icon/CancleIcon.png").toString()));
		
		confirm = new MenuItem("Confirm");
		confirm.setVisible(false);
//		setId("player-action-menu-style");

		getItems().addAll(buyMinion, buyLand, combine, split,confirm, cancle);
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
