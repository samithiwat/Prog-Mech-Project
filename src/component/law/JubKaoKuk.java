package component.law;

import gui.MainIsland;
import javafx.scene.paint.Color;
import update.PlayerPanelUpdate;

public class JubKaoKuk extends LawCard implements Interactable{
	
	public static final String IMG_PATH = "img/card/law/Capture.png";
	
	public JubKaoKuk() {
		super("JubKaoKuk","The government can arrest one random minion from the map.(If there is no sub-minion, the government "
				+ "can arrest the header");
		this.img_path = "img/card/law/Capture.png";
	}
	public void activateEffectCard() {
		//arrest a minion to the prison 
		// code not finished
		
		MainIsland.overlayInteractMode();
		PlayerPanelUpdate.setShowMessage("Select minion to capture.", Color.web("0xFEFDE8"),Color.web("0xCCCCCC"), 120,1, 1000);
		
		
	}
}
