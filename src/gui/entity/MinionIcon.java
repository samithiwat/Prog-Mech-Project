package gui.entity;

import component.entity.Minion;
import javafx.event.EventHandler;
import javafx.scene.effect.Bloom;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Effect;
import javafx.scene.effect.Glow;
import javafx.scene.effect.SepiaTone;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import logic.GameSetUp;

public class MinionIcon extends MenuIcon {

	public static final Effect SELECTED_EFFECT = new Bloom();
	
	private MinionList minionList;
	private Minion minion;
	private MinionIcon minionIcon = this;

	public MinionIcon(String img_path, int x, int y, Minion minion) {
		super(img_path, x, y);
		this.minion = minion;
		minionList = new MinionList(minion);
	}

	@Override
	public void interact() {
		setOnMouseEntered(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				setCursor(MOUSE_SELECT);
				EFFECT_MOUSE_ENTER.play();
				if(getEffect() == null) {
					setEffect(new DropShadow());					
				}
			}
		});

		setOnMouseExited(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				setCursor(MOUSE_NORMAL);
				if(getEffect() instanceof DropShadow) {
					setEffect(null);					
				}
			}
		});

		setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				
				if(event.getButton().equals(MouseButton.SECONDARY)) {
					
					if(!minionList.isShowing()) {
						minionList.update();
						minionList.show(minionIcon,event.getScreenX(),event.getScreenY());											
					}
					else {
						minionList.hide();						
					}
				}
				
			}
		});
	}
// ---------------------------------------- Set Minion Icon Mode -------------------------------------------------
	
	public void moveMode() {
		setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				
				if(event.getButton().equals(MouseButton.SECONDARY)) {
					
					if(!minionList.isShowing()) {
						minionList.update();
						minionList.show(minionIcon,event.getScreenX(),event.getScreenY());											
					}
					else {
						minionList.hide();						
					}
				}
				
			}
		});
	}
	
	public void selectMode() {
		setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				
				if(event.getButton().equals(MouseButton.PRIMARY)) {
					
					System.out.println("Before" + GameSetUp.selectedIcon);
					
					if(GameSetUp.selectedIcon.size() > 0) {
						if(!(minionIcon.equals(GameSetUp.selectedIcon.get(0)))) {
							GameSetUp.selectedIcon.add(minionIcon);
							minionIcon.setEffect(SELECTED_EFFECT);
						}
						else {
							GameSetUp.selectedIcon.remove(0);
							minionIcon.setEffect(null);
						}
					}
					else {
						GameSetUp.selectedIcon.add(minionIcon);
						minionIcon.setEffect(SELECTED_EFFECT);
					}
					
					System.out.println("After" + GameSetUp.selectedIcon);
					
				}
				
				if(event.getButton().equals(MouseButton.SECONDARY)) {
					
					if(!minionList.isShowing()) {
						minionList.update();
						minionList.show(minionIcon,event.getScreenX(),event.getScreenY());											
					}
					else {
						minionList.hide();						
					}
				}
				
			}
		});
	}
	
// ---------------------------------------- Getter and Setter -------------------------------------------------
	
	public MinionList getMinionList() {
		return minionList;
	}
	
	public Minion getMinion() {
		return this.minion;
	}
	
}
