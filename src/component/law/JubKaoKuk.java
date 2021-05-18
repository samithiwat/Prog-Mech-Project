package component.law;

import exception.ExceedMinionInTileException;
import exception.OutOfActionException;
import gui.MainIsland;
import javafx.scene.paint.Color;
import logic.GameSetUp;
import update.PlayerPanelUpdate;

public class JubKaoKuk extends InteractLawCard {

	public JubKaoKuk() {
		super("Capture!",
				"The government can arrest one random minion from the map.(If there is no sub-minion, the government "
						+ "can arrest the header");
		this.img_path = "img/card/law/Capture.png";
		this.icon_img_path = "img/icon/JailIcon.png";
	}

	public void activateEffectCard() {
		// arrest a minion to the prison
		// code not finished

		MainIsland.overlayInteractMode("OneMinion");
		PlayerPanelUpdate.setShowMessage("Select minion to capture.", Color.web("0xFEFDE8"), Color.web("0xCCCCCC"), 120,
				1, 1000);
		GameSetUp.selectedIcon.clear();
		
		System.out.println(GameSetUp.selectedIcon);
		
		Thread selectMinion = new Thread(() -> {

			while (true) {
				System.out.print("");
				if (GameSetUp.selectedIcon.size() > 0) {
					// put minion to jail.
					try {
						GameSetUp.selectedIcon.get(0).getMinion().jailed();
						PlayerPanelUpdate.setShowMessage("Successfully put this crime to jail.", COLOR_INFO,
								COLOR_STROKE_INFO, 90, 1, 2000);
						
					} catch (ExceedMinionInTileException e) {
						PlayerPanelUpdate.setShowMessage("Out of space to jail this crime.", COLOR_ERROR, 90, 2000);
					} catch (OutOfActionException e) {
						PlayerPanelUpdate.setShowMessage("I must wait another turn.", COLOR_ERROR, 100, 2000);
					}
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
