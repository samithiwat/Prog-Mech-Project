package component.location;

import character.MainCharacter;
import gui.MapOverview;
import gui.entity.LawCardIcon;
import gui.entity.LawCardSlot;
import gui.overlay.Government;
import javafx.application.Platform;
import logic.GameSetUp;
import update.PlayerPanelUpdate;

public class Council extends Location {
	public static int beginCycle;

	public Council() {
		super("Council", "Publish laws", 0, 0);
	}

	public void changeTheGovernment(MainCharacter character) {
		Platform.runLater(new Runnable() {
			
			@Override
			public void run() {
				unSelectedAll(GameSetUp.lawSlot.getSlot(0));
				unSelectedAll(GameSetUp.lawSlot.getSlot(1));				
			}
		});
		GameSetUp.lawSlot.setSlot(0, new LawCardIcon(null));
		GameSetUp.lawSlot.setSlot(1, new LawCardIcon(null));
		Platform.runLater(new Runnable() {

			@Override
			public void run() {
				updateActiveLaw();
			}
			
		});
		GameSetUp.theGovernment = character;
		beginCycle = GameSetUp.cycle;
	}

	public static int howLong() {
		return GameSetUp.cycle - beginCycle;
	}
	
	private void updateActiveLaw() {
		for (int i = 0; i < MapOverview.allGovernment.size(); i++) {
			Government overlay = MapOverview.allGovernment.get(i);
			overlay.updateActivedLaw();					
		}
		PlayerPanelUpdate.updateActivedLawPane();
	}
	
	private void unSelectedAll(LawCardIcon lawCardIcon) {
		LawCardSlot cardSlot = MapOverview.allGovernment.get(0).getCardSlot();
		int index = cardSlot.getChildren().indexOf(lawCardIcon);
		if (index >= 0) {
			for (int i = 0; i < MapOverview.allGovernment.size(); i++) {
				cardSlot = MapOverview.allGovernment.get(i).getCardSlot();
				LawCardIcon cardIcon = (LawCardIcon) cardSlot.getChildren().get(index);
				cardIcon.setSelected(false);
				cardIcon.setDisable(false);
				cardIcon.setId("law-card-unselected-style");
			}
		}
	}
}
