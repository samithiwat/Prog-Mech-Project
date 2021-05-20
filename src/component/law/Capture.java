package component.law;

import exception.ExceedMinionInTileException;
import exception.OutOfActionException;
import gui.MainIsland;
import javafx.scene.paint.Color;
import logic.GameSetUp;
import update.PlayerPanelUpdate;

public class Capture extends InteractLawCard {

	public Capture() {
		super("Capture!",
				"The government can arrest one random minion from the map.(If there is no sub-minion, the government "
						+ "can arrest the header");
		this.img_path = "img/card/law/Capture.png";
		this.icon_img_path = "img/icon/JailIcon.png";
	}

	public void activateEffectCard() {

		MainIsland.overlayInteractMode("OneMinion", false, false);
		MainIsland.getMessage().setText("Select minion to capture. (ESC to cancle)");
		MainIsland.getMessageRoot().setVisible(true);
		PlayerPanelUpdate.setVisibleActivedLawPane(false);
		PlayerPanelUpdate.setShowMessage("Select minion to capture.", Color.web("0xFEFDE8"), Color.web("0xCCCCCC"), 120,
				1, 1000);
		GameSetUp.selectedIcon.clear();

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
						GameSetUp.selectedIcon.get(0).getMinion().jailed();
						PlayerPanelUpdate.setShowMessage("Successfully put this crime to jail.", COLOR_INFO,
								COLOR_STROKE_INFO, 90, 1, 2000);

					} catch (ExceedMinionInTileException e) {
						PlayerPanelUpdate.setShowMessage("Out of space to jail this crime.", COLOR_ERROR, 90, 2000);
					} catch (OutOfActionException e) {
						PlayerPanelUpdate.setShowMessage("I must wait another turn.", COLOR_ERROR, 100, 2000);
					}
					MainIsland.getMessageRoot().setVisible(false);
					PlayerPanelUpdate.setPanelVisible(true);
					GameSetUp.selectedTile.triggerOverlay();
					GameSetUp.selectedIcon.clear();
					GameSetUp.selectedTile = null;
					break;
				}

			}

		});
		selectMinion.start();
	}

}
