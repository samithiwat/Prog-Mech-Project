package component.law;

import exception.OutOfActionException;
import gui.MainIsland;
import logic.GameSetUp;
import update.PlayerPanelUpdate;

public class Pardon extends InteractLawCard {

	public Pardon() {
		super("Pardon", "Each turn the government can return a banished rat back to the owner.");
		this.img_path = "img/card/law/Pardon.png";
		this.icon_img_path = "img/icon/PardonIcon.png";
	}

	public void activateEffectCard() {
		MainIsland.setESC(false);
		MainIsland.getMessage().setText("Select minion to pardon. (ESC to cancle)");
		MainIsland.getMessageRoot().setVisible(true);
		PlayerPanelUpdate.setPanelVisible(false);
		GameSetUp.selectedIcon.clear();
		PlayerPanelUpdate.setShowMessage("Select minion to pardon", COLOR_INFO, COLOR_STROKE_INFO, 120, 1, 2000);
		Thread selectMinion = new Thread(() -> {
			while (true) {
				System.out.print("");
				if (GameSetUp.isCancel) {
					GameSetUp.isCancel = false;
					MainIsland.setESC(true);
					MainIsland.getMessageRoot().setVisible(false);
					PlayerPanelUpdate.setPanelVisible(true);
					break;
				}
				if (GameSetUp.selectedIcon.size() > 0) {
					try {
						GameSetUp.selectedIcon.get(0).getMinion().pardon();
						PlayerPanelUpdate.setShowMessage("Successfullt pardon this guy", COLOR_INFO, COLOR_STROKE_INFO,
								100, 1, 2000);
					} catch (OutOfActionException e) {
						EFFECT_ERROR.play();
						PlayerPanelUpdate.setShowMessage("I must wait for next turn.", COLOR_ERROR, 120, 2000);
					}
					MainIsland.getMessageRoot().setVisible(false);
					PlayerPanelUpdate.setPanelVisible(true);
					break;
				}
			}
		});
		selectMinion.start();
	}
}
