package logic;

import java.util.ArrayList;

import character.MainCharacter;
import component.entity.Minion;
import component.location.Location;
import component.weaponCard.WeaponCard;
// this class can be deleted
public class FightController {
	public Minion winner;
	public FightController(Minion challenger , Minion defender) {
		ArrayList<WeaponCard> challenger_slot = new ArrayList<WeaponCard>();
		ArrayList<WeaponCard> defender_slot = new ArrayList<WeaponCard>();
		//Each player choose their weapon card to add in these slots.
		int challenger_atkPoint = 0 , defender_atkPoint = 0;
		for(int i = 0 ; i < challenger_slot.size() ; i++) {
			int randomized_atkPoint = challenger_slot.get(i).rand_attack();
			//update the randomized atk to the screen
			challenger_atkPoint += randomized_atkPoint;
		}
		for(int i = 0 ; i < defender_slot.size() ; i++) {
			int randomized_atkPoint = defender_slot.get(i).rand_attack();
			//update the randomized atk to the screen
			defender_atkPoint += randomized_atkPoint;
		}
		if(challenger.getPossessedBy().getName().equals("Ms.ThousandYear")) {
			challenger_atkPoint += 1;
		}
		if(defender.getPossessedBy().getName().equals("Ms.Collector")) {
			challenger_atkPoint -= 1;
		}
		if(challenger_atkPoint > defender_atkPoint) {
			this.winner = challenger;
			challenger.addMinion(defender);
		}
		else if(challenger_atkPoint < defender_atkPoint) {
			this.winner = defender;
			defender.addMinion(challenger);
		}
	}
	public FightController(Minion challenger, Location defender) {
		ArrayList<WeaponCard> challenger_slot = new ArrayList<WeaponCard>();
		ArrayList<WeaponCard> defender_slot = new ArrayList<WeaponCard>();
		//Each player choose their weapon card to add in these slots.
		defender_slot.add(GameSetUp.weaponDeck.drawCard());
		defender_slot.add(GameSetUp.weaponDeck.drawCard());
		int challenger_atkPoint = 0 , defender_atkPoint = 0;
		for(int i = 0 ; i < challenger_slot.size() ; i++) {
			int randomized_atkPoint = challenger_slot.get(i).rand_attack();
			//update the randomized atk to the screen
			challenger_atkPoint += randomized_atkPoint;
		}
		for(int i = 0 ; i < defender_slot.size() ; i++) {
			int randomized_atkPoint = defender_slot.get(i).rand_attack();
			//update the randomized atk to the screen
			defender_atkPoint += randomized_atkPoint;
		}
		if(challenger_atkPoint > defender_atkPoint) {
			this.winner = challenger;
//			challenger.addMinion(defender);
		}
		else if(challenger_atkPoint < defender_atkPoint) {
			this.winner = null;
//			defender.addMinion(challenger);
		}
	}
	//-------------------------getter/setter-------------------------
	public String getWinner() {
		if(winner == null) {
			return "You Lose";
		}
		return winner.getPossessedBy().getName();
	}
}
