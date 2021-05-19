package component.law;

import exception.OutOfActionException;
import logic.GameSetUp;
import update.PlayerPanelUpdate;

public class Pardon extends InteractLawCard {

	public Pardon() {
		super("Pardon", "Each turn the government can return a banished rat back to the owner.");
		this.img_path = "img/card/law/Pardon.png";
		this.icon_img_path = "img/icon/PardonIcon.png";
	}

	public void activateEffectCard() {
		// haven't written banish zone
		GameSetUp.selectedIcon.clear();
		PlayerPanelUpdate.setShowMessage("Select minion to pardon.", COLOR_INFO, COLOR_STROKE_INFO, 120, 1, 2000);
		Thread selectMinion = new Thread(() -> {
			while (true) {
				System.out.print("");
				if (GameSetUp.selectedIcon.size() > 0) {
					try {
						GameSetUp.selectedIcon.get(0).getMinion().pardon();
						PlayerPanelUpdate.setShowMessage("Successfullt pardon this guy", COLOR_INFO, COLOR_STROKE_INFO,
								100, 1, 2000);
					} catch (OutOfActionException e) {
						EFFECT_ERROR.play();
						PlayerPanelUpdate.setShowMessage("I must wait for next turn.", COLOR_ERROR, 120, 2000);
					}
					break;
				}
			}
		});
		selectMinion.start();
	}
}
