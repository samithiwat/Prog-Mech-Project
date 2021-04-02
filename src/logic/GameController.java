package logic;

import java.util.ArrayList;

import character.MainCharacter;
import component.law.LawDeck;
import component.law.GameLaw;
import component.law.LawSlot;
import component.weaponCard.Axe;
import component.weaponCard.Bow;
import component.weaponCard.Gun;
import component.weaponCard.RemovedDeck;
import component.weaponCard.Shield;
import component.weaponCard.Sword;
import component.weaponCard.WeaponDeck;

public class GameController {
	public static ArrayList<MainCharacter> gameCharacter = new ArrayList<MainCharacter>();
	public static GameLaw gameLaw = new GameLaw();
	public static WeaponDeck weaponDeck = new WeaponDeck() ;
	public static RemovedDeck removedDeck = new RemovedDeck();
	public static LawSlot lawSlot = new LawSlot();
	public static LawDeck lawDeck = new LawDeck();
	private static final int DUPLICATE = 5 ;
	public static String turn;
	public static int cycle = 0;
	public static boolean isGameEnd = false;
	public static boolean isEndTurn;
	public static MainCharacter theGovernment = null;
	public static void main(String[] args) {
//----------------------------------Weapon Set Up---------------------------------------------------
		for(int i = 0 ; i < DUPLICATE ; i++) {
			weaponDeck.addCard(new Axe());
			weaponDeck.addCard(new Bow());
			weaponDeck.addCard(new Gun());
			weaponDeck.addCard(new Shield());
			weaponDeck.addCard(new Sword());
		}
//----------------------------------Add Law Card----------------------------------------------------
		
//----------------------------------Choose Start Location-------------------------------------------
		for(int i = 0 ; i < gameCharacter.size(); i++) {
			
		}
//-----------------------------------Turn Base-----------------------------------------------------
		while(!isGameEnd) {
			for(int i = 0 ; i < gameCharacter.size() ; i++) {
				isEndTurn = false;
				while(!isEndTurn) {
					//----------------------------What happen in one turn--------------------------
				}
			}
		}
	}
}
