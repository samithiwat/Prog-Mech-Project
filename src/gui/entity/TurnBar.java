package gui.entity;

import character.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import logic.GameSetUp;

public class TurnBar extends StackPane implements Clickable{

	HBox iconPane;

	public TurnBar() {

		setId("turnbar");
		
		iconPane = new HBox();
		iconPane.setSpacing(40);

		for (int i = 0; i < GameSetUp.gameCharacter.size(); i++) {
			createCharacterIcon(GameSetUp.gameCharacter.get(i));
		}
		
		getChildren().add(iconPane);
		
		interact();
	}
	
	private void createCharacterIcon(MainCharacter character) {
		TurnCharacterIcon characterIcon = null;
		
		if(character instanceof RedFox) {
			characterIcon = new TurnCharacterIcon(RED_FOX_IMG,44,31);
		}
		
		if(character instanceof Collector) {
			characterIcon = new TurnCharacterIcon(LADY_COLLECTOR_IMG,48,43);
		}	
			
		if(character instanceof BlackSkull) {
			characterIcon = new TurnCharacterIcon(BLACK_SKULL_IMG,40,15);
		}
			
		if(character instanceof ThousandYear) {
			characterIcon = new TurnCharacterIcon(SIR_THOUSAND_IMG,28,42);
		}
		
		if(character instanceof ThousandYear) {
			characterIcon = new TurnCharacterIcon(SIR_TEWADA,48,16);
		}


		if(character instanceof ThousandYear) {
			characterIcon = new TurnCharacterIcon(SIR_TEWADEE,57,19);
		}	

		iconPane.getChildren().add(characterIcon);
		
	}

	@Override
	public void interact() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void triggerDisable() {
		setDisable(!isDisabled());
	}
}
