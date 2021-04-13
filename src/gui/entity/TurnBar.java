package gui.entity;

import java.util.ArrayList;

import character.*;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import logic.GameSetUp;
import update.GameSettingUpdate;

public class TurnBar extends StackPane implements Clickable {

	private static HBox iconPane;

	public TurnBar() {

		///////////////// FOR DEBUG ONLY /////////////////////

//		GameSetUp.gameCharacter = new ArrayList<MainCharacter>();
//		GameSetUp.gameCharacter.add(new RedFox());
//		GameSetUp.gameCharacter.add(new BlackSkull());
//		GameSetUp.gameCharacter.add(new Teewada());

		////////////////// END OF DEBUG ///////////////////////

// ------------------------------------------ Set Up Bar -----------------------------------------		

		setId("turnbar");
		setPrefWidth(720);
		setPrefHeight(140);
		setAlignment(Pos.CENTER_LEFT);

// ------------------------------------------ Set Up Player's Icon Pane -----------------------------------------	

		iconPane = new HBox();
		iconPane.setSpacing(40);
		iconPane.setPadding(new Insets(20, 50, 0, 50));
		iconPane.setId("turnbar");

		for (int i = 0; i < GameSetUp.gameCharacter.size(); i++) {
			TurnCharacterIcon characterIcon = new TurnCharacterIcon(RED_FOX_IMG, 44, 31);
			characterIcon.setVisible(false);
			iconPane.getChildren().add(characterIcon);
		}

		for (int i = 0; i < GameSettingUpdate.getNPlayer(); i++) {
			createCharacterIcon(GameSetUp.gameCharacter.get(i), i);
		}

// ----------------------------------------- Add Icon Pane to Bar ---------------------------------------------------

		getChildren().add(iconPane);

		interact();
	}

	private void createCharacterIcon(MainCharacter character, int index) {
////////////////////////////////////////////////////////////// FOR DEBUG ONLY ///////////////////////////////////////////////////////////////////
		
		System.out.println(character);
		
////////////////////////////////////////////////////////////// END OF DEBUG ///////////////////////////////////////////////////////////////////
		TurnCharacterIcon characterIcon = null;

		if (character instanceof RedFox) {
			characterIcon = new TurnCharacterIcon(RED_FOX_IMG, 44, 31);
		}

		if (character instanceof Collector) {
			characterIcon = new TurnCharacterIcon(LADY_COLLECTOR_IMG, 48, 43);
		}

		if (character instanceof BlackSkull) {
			characterIcon = new TurnCharacterIcon(BLACK_SKULL_IMG, 40, 25);
		}

		if (character instanceof ThousandYear) {
			characterIcon = new TurnCharacterIcon(SIR_THOUSAND_IMG, 28, 42);
		}

		if (character instanceof Teewada) {
			characterIcon = new TurnCharacterIcon(SIR_TEWADA, 48, 32);
		}

		if (character instanceof Teewadee) {
			characterIcon = new TurnCharacterIcon(SIR_TEWADEE, 57, 19);
		}

		iconPane.getChildren().set(index, characterIcon);

	}

	@Override
	public void interact() {
		setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				//((TurnCharacterIcon) iconPane.getChildren().get(0)).setPlayerTurn(true);
			}
		});
	}

	@Override
	public void triggerDisable() {
		setDisable(!isDisabled());
	}

	public static HBox getIconPane() {
		return iconPane;
	}
	
}
