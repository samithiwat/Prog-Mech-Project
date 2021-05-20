package logic;

import java.util.ArrayList;

import character.MainCharacter;
import component.entity.Minion;
import component.location.Council;
import component.weaponCard.WeaponCard;
import gui.MapOverview;
import gui.entity.MapGrid;
import gui.entity.TextTitle;
import gui.overlay.FightOverlay;
import javafx.scene.media.AudioClip;
import javafx.scene.paint.Color;
import update.FightOverlayUpdate;
import update.PlayerPanelUpdate;
import update.TradeOverlayUpdate;

public class FightController {
	public static ArrayList<WeaponCard> challenged_slot = new ArrayList<WeaponCard>();
	public static ArrayList<WeaponCard> challenger_slot = new ArrayList<WeaponCard>();
	public static boolean challenger_ult = false;
	public static boolean challenged_ult = false;
	
	
	public static void Fight(Minion challenger , Minion challenged) {
		//Each player choose their weapon card to add in these slots.
		Thread temp = new Thread(() -> {
			try {
				Thread.sleep(5000);				
			}catch(Exception e){
				System.out.println("error");
			}
			if(GameSetUp.isChallenging) {
				if(challenger_slot.size() >= 1) {
					try {
						Thread.sleep(1000);				
					}catch(Exception e){
						System.out.println("error");
					}
					challenged_slot.add(GameSetUp.weaponDeck.drawCard());
					GameSetUp.isFightOverlayOffersUpdate = true;
					try {
						Thread.sleep(1000);				
					}catch(Exception e){
						System.out.println("error");
					}
					challenged_slot.add(GameSetUp.weaponDeck.drawCard());
					GameSetUp.isFightOverlayOffersUpdate = true;
				}
			}
			int challenger_atkPoint = 0 , challenged_atkPoint = 0;
			MainCharacter challengerMCh = challenger.getPossessedBy();
			MainCharacter challengedMCh = challenged.getPossessedBy();
			GameLaw gameLaw = GameSetUp.gameLaw;
			if(gameLaw.goodPointAdvantage>0) {
				
				if(challengerMCh.getGoodPoint() > challengedMCh.getGoodPoint()) {
					challenger_atkPoint+=gameLaw.goodPointAdvantage;
				}
				else if(challengerMCh.getGoodPoint() < challengedMCh.getGoodPoint()) {
					challenged_atkPoint+=gameLaw.goodPointAdvantage;
				}
			}
			if(gameLaw.poorerAdvantage>0) {
				if(challengerMCh.getMoney() < challengedMCh.getMoney()) {
					challenger_atkPoint += gameLaw.poorerAdvantage;
				}
				else if(challengerMCh.getMoney() > challengedMCh.getMoney()) {
					challenged_atkPoint += gameLaw.poorerAdvantage;
				}
			}
			if(gameLaw.richerAdvantage>0) {
				if(challengerMCh.getMoney() < challengedMCh.getMoney()) {
					challenged_atkPoint += gameLaw.richerAdvantage;
				}
				else if(challengerMCh.getMoney() > challengedMCh.getMoney()) {
					challenger_atkPoint += gameLaw.richerAdvantage;
				}
			}
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
					Thread.sleep(1000);				
				}catch(Exception e){
					System.out.println("error");
				}
			}
			if(challenger.getPossessedBy().getName().equals("Ms.ThousandYear")) {
				challenger_atkPoint += 1;
			}
			if(challenged.getPossessedBy().getName().equals("Ms.Collector")) {
				challenger_atkPoint -= 1;
			}
			if(challenger_ult) {
				challenger_atkPoint += 999;
				challenger_isShield = false;
			}
			for(int j = 0 ; j < MapOverview.allFightOverlay.size() ; j++) {
				TextTitle t = MapOverview.allFightOverlay.get(j).getChallenger_outcome();
				if(challenger.getPossessedBy().getName().equals("Ms.ThousandYear")) {
					t.setText(t.getText() + " + 1" );
				}
				if(challenged.getPossessedBy().getName().equals("Ms.Collector")) {
					t.setText(t.getText() + " + -1" );
				}
				if(challenger_ult) {
					if(challenger_slot.size() == 0) {
						t.setText(t.getText() + "999" );						
					}
					else {
						t.setText(t.getText() + " + 999" );						
					}
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
					Thread.sleep(1000);				
				}catch(Exception e){
					System.out.println("error");
				}
			}
			if(challenged_ult) {
				challenged_atkPoint += 999;
				challenged_isShield = false;
			}
			for(int j = 0 ; j < MapOverview.allFightOverlay.size() ; j++) {
				TextTitle t = MapOverview.allFightOverlay.get(j).getChallenged_outcome();
				if(challenged_ult) {
					if(challenged_slot.size() == 0) {
						t.setText(t.getText() + "999" );						
					}
					else {
						t.setText(t.getText() + " + 999" );						
					}
				}
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
			if(GameSetUp.isChallenging == false) {
				if(!(challenged_ult||challenger_ult) && (challenged_isShield || challenger_isShield)) {
					PlayerPanelUpdate.setShowMessage("Shield", Color.WHITE, 120, 2000);
				}
				else if(challenger_atkPoint > challenged_atkPoint) {
					challenger.addMinion(challenged);
					AudioClip effect = AudioLoader.winEffect;
					effect.play();
					if(challenged.getOnLocation().getName().equals("SecretBase")) {
						challenged.getPossessedBy().getPossessedArea().remove(challenged.getOnLocation());
					}
					PlayerPanelUpdate.setShowMessage("You win", Color.WHITE, 120, 2000);
				}
				else if(challenger_atkPoint < challenged_atkPoint) {
					challenged.addMinion(challenger);
					AudioClip effect = AudioLoader.loseEffect;
					effect.play();
					if(challenger.getOnLocation().getName().equals("SecretBase")) {
						challenger.getPossessedBy().getPossessedArea().remove(challenger.getOnLocation());
					}
					PlayerPanelUpdate.setShowMessage("You lose", Color.WHITE, 120, 2000);
				}else {
					PlayerPanelUpdate.setShowMessage("Draw", Color.WHITE, 120, 2000);				
				}
				if(GameSetUp.ladyCollector!=null) {
					GameSetUp.ladyCollector.checkIsWin();					
				}
			}
			else if(GameSetUp.isChallenging == true && GameSetUp.theGovernment.getName().equals("Council")) {
				if(challenger_atkPoint > challenged_atkPoint) {
					Council council = (Council)MapGrid.councilTile.getLocationType();
					challenger.getOnLocation().removeFromLocation(challenger);
					council.addMinionToLocation(challenger);
					council.changeTheGovernment(challenger.getPossessedBy());
					AudioClip effect = AudioLoader.winEffect;
					effect.play();
					PlayerPanelUpdate.setShowMessage("Victory!", Color.WHITE, 120, 2000);
				}
				else {
					AudioClip effect = AudioLoader.loseEffect;
					effect.play();
					PlayerPanelUpdate.setShowMessage("Defeat", Color.WHITE, 120, 2000);
				}
			}
			else if(GameSetUp.isChallenging == true) {
				if(challenger_atkPoint > challenged_atkPoint) {
					Council council = (Council)MapGrid.councilTile.getLocationType();
					challenger.getOnLocation().removeFromLocation(challenger);
					//add minion to island
					council.getMinionOnLocation().remove(0);
					council.addMinionToLocation(challenger);
					council.changeTheGovernment(challenger.getPossessedBy());
					if(GameSetUp.sirTeewadee!=null) {
						GameSetUp.sirTeewadee.checkIsWin();						
					}
					AudioClip effect = AudioLoader.winEffect;
					effect.play();
					PlayerPanelUpdate.setShowMessage("Victory!", Color.WHITE, 120, 2000);
				}
				else {
					AudioClip effect = AudioLoader.loseEffect;
					effect.play();
					PlayerPanelUpdate.setShowMessage("Defeat", Color.WHITE, 120, 2000);
				}
			}
			challenged_ult = false;
			challenger_ult = false;
			TradeOverlayUpdate.traded = null;
			TradeOverlayUpdate.trader = null;
			GameSetUp.isChallenging = false;
			GameSetUp.selectedIcon.clear();
			challenged_slot.clear();
			challenger_slot.clear();
			FightOverlayUpdate.challenger = null;
			FightOverlayUpdate.challenged = null;
		});
		temp.start();
		try {
			temp.join();			
		}catch(Exception e){
			System.out.println("error");
		}
		for(int i = 0 ; i < MapOverview.allFightOverlay.size() ; i++) {
			FightOverlay overlay = MapOverview.allFightOverlay.get(i);
			overlay.getchallenged_accept().setDisable(false);
			overlay.getchallenger_accept().setDisable(false);
			overlay.triggerOverlay(0, 825, 1000);
			overlay.getChallenged_outcome().setText("");
			overlay.getChallenger_outcome().setText("");
		}

	}
	//-------------------------getter/setter-------------------------
	
}
