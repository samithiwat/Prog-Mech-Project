package logic;

import java.util.ArrayList;

import character.MainCharacter;
import component.entity.Minion;
import component.location.Location;
import component.weaponCard.WeaponCard;
import gui.MainIsland;
import gui.MapOverview;
import gui.entity.TextTitle;
import gui.overlay.FightOverlay;
import javafx.scene.paint.Color;
import update.FightOverlayUpdate;
import update.PlayerPanelUpdate;

public class FightController {
	public static ArrayList<WeaponCard> challenged_slot = new ArrayList<WeaponCard>();
	public static ArrayList<WeaponCard> challenger_slot = new ArrayList<WeaponCard>();
	
	
	public static void Fight(Minion challenger , Minion challenged) {
		//Each player choose their weapon card to add in these slots.
		Thread temp = new Thread(() -> {
			int challenger_atkPoint = 0 , challenged_atkPoint = 0;
			boolean challenger_isShield = false;
			for(int i = 0 ; i < challenger_slot.size() ; i++) {
				int randomized_atkPoint = challenger_slot.get(i).rand_attack();
				//update the randomized atk to the screen
				challenger_atkPoint += randomized_atkPoint;
				for(int j = 0 ; j < MapOverview.allFightOverlay.size() ; j++) {
					TextTitle t = MapOverview.allFightOverlay.get(j).getChallenger_outcome();
					if(i == 0 && randomized_atkPoint == 0) {
						t.setText("DEF");
						challenger_isShield = true;
					}
					else if( i == 0) {
						t.setText(randomized_atkPoint + "");
					}
					else if(randomized_atkPoint == 0) {
						t.setText(t.getText() + " + " + "DEF");	
						challenger_isShield = true;
					}
					else {
						t.setText(t.getText() + " + " + randomized_atkPoint);						
					}
				}
				try {
					Thread.sleep(500);				
				}catch(Exception e){
					System.out.println("error");
				}
			}
			for(int j = 0 ; j < MapOverview.allFightOverlay.size() ; j++) {
				TextTitle t = MapOverview.allFightOverlay.get(j).getChallenger_outcome();
				if(challenger.getPossessedBy().getName().equals("Ms.ThousandYear")) {
					challenger_atkPoint += 1;
					t.setText(t.getText() + " + 1" );
				}
				if(challenged.getPossessedBy().getName().equals("Ms.Collector")) {
					challenger_atkPoint -= 1;
					t.setText(t.getText() + " + -1" );
				}
				if(challenger_isShield) {
					t.setText(t.getText() + " = " + "DEF");
				}
				else {
					t.setText(t.getText() + " = " + challenger_atkPoint);					
				}
			}
			try {
				Thread.sleep(2000);				
			}catch(Exception e){
				System.out.println("error");
			}
			boolean challenged_isShield = false;
			for(int i = 0 ; i < challenged_slot.size() ; i++) {
				int randomized_atkPoint = challenged_slot.get(i).rand_attack();
				//update the randomized atk to the screen
				challenged_atkPoint += randomized_atkPoint;
				for(int j = 0 ; j < MapOverview.allFightOverlay.size() ; j++) {
					TextTitle t = MapOverview.allFightOverlay.get(j).getChallenged_outcome();
					if(i == 0 && randomized_atkPoint == 0) {
						t.setText("DEF");
						challenged_isShield = true;
					}
					else if( i == 0) {
						t.setText(randomized_atkPoint + "");
					}
					else if(randomized_atkPoint == 0) {
						t.setText(t.getText() + " + " + "DEF");	
						challenged_isShield = true;
					}
					else {
						t.setText(t.getText() + " + " + randomized_atkPoint);						
					}
				}
				try {
					Thread.sleep(500);				
				}catch(Exception e){
					System.out.println("error");
				}
			}
			
			for(int j = 0 ; j < MapOverview.allFightOverlay.size() ; j++) {
				TextTitle t = MapOverview.allFightOverlay.get(j).getChallenged_outcome();
				if(challenged_isShield) {
					t.setText(t.getText() + " = " + "DEF");
				}
				else {
					t.setText(t.getText() + " = " + challenged_atkPoint);					
				}
			}
			try {
				Thread.sleep(2000);				
			}catch(Exception e){
				System.out.println("error");
			}
			if(challenged_isShield || challenger_isShield) {
				MainIsland.setShowMessage("Shield", Color.WHITE, 120, 2000);
				PlayerPanelUpdate.setShowMessage("Shield", Color.WHITE, 120, 2000);
			}
			else if(challenger_atkPoint > challenged_atkPoint) {
				challenger.addMinion(challenged);
				MainIsland.setShowMessage("You win", Color.WHITE, 120, 2000);
				PlayerPanelUpdate.setShowMessage("You win", Color.WHITE, 120, 2000);
			}
			else if(challenger_atkPoint < challenged_atkPoint) {
				challenged.addMinion(challenger);
				MainIsland.setShowMessage("You lose", Color.WHITE, 120, 2000);
				PlayerPanelUpdate.setShowMessage("You lose", Color.WHITE, 120, 2000);
			}else {
				MainIsland.setShowMessage("Draw", Color.WHITE, 120, 2000);
				PlayerPanelUpdate.setShowMessage("Draw", Color.WHITE, 120, 2000);
			}
		});
		temp.start();
		try {
			temp.join();			
		}catch(Exception e){
			System.out.println("error");
		}
		for(int i = 0 ; i < MapOverview.allFightOverlay.size() ; i++) {
			FightOverlay overlay = MapOverview.allFightOverlay.get(i);
			overlay.triggerOverlay(0, 825, 1000);
			overlay.getChallenged_outcome().setText("");
			overlay.getChallenger_outcome().setText("");
		}

	}
	public static boolean Fight(Minion challenger, Location challenged) {
		//Each player choose their weapon card to add in these slots.
		challenged_slot.add(GameSetUp.weaponDeck.drawCard());
		challenged_slot.add(GameSetUp.weaponDeck.drawCard());
		int challenger_atkPoint = 0 , challenged_atkPoint = 0;
		for(int i = 0 ; i < challenger_slot.size() ; i++) {
			int randomized_atkPoint = challenger_slot.get(i).rand_attack();
			//update the randomized atk to the screen
			challenger_atkPoint += randomized_atkPoint;
		}
		for(int i = 0 ; i < challenged_slot.size() ; i++) {
			int randomized_atkPoint = challenged_slot.get(i).rand_attack();
			//update the randomized atk to the screen
			challenged_atkPoint += randomized_atkPoint;
		}
		if(challenger_atkPoint > challenged_atkPoint) {
			return true;
		}
		return false;
	}
	//-------------------------getter/setter-------------------------
	
}
