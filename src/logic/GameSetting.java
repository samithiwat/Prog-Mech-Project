package logic;

import java.util.ArrayList;

import character.MainCharacter;
import component.law.LawDeck;
import component.law.GameLaw;
import component.law.LawSlot;
import component.location.Council;
import component.location.Field;
import component.location.Forest;
import component.location.Location;
import component.location.Mine;
import component.location.Plain;
import component.location.SecretBase;
import component.location.Village;
import component.location.Water;
import component.weaponCard.Axe;
import component.weaponCard.Bow;
import component.weaponCard.Gun;
import component.weaponCard.RemovedDeck;
import component.weaponCard.Shield;
import component.weaponCard.Sword;
import component.weaponCard.WeaponDeck;

public class GameSetting {
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
	public static Location[][] map = new Location[10][11];
	public GameSetting() {
//----------------------------------Weapon Set Up---------------------------------------------------
		for(int i = 0 ; i < DUPLICATE ; i++) {
			weaponDeck.addCard(new Axe());
			weaponDeck.addCard(new Bow());
			weaponDeck.addCard(new Gun());
			weaponDeck.addCard(new Shield());
			weaponDeck.addCard(new Sword());
		}
//----------------------------------Add Law Card----------------------------------------------------
		lawDeck.setUpLawDeck();
//----------------------------------Choose Start Location-------------------------------------------
		String[] mapCode = {"50400000105","11100203201","02000001300","0402XXX0020","0031X6X1003","30001X03000"
				,"00401100410","10203020021","50004000205","00000000000"};
		for(int i = 0 ; i < 10 ; i++) {
			for(int j = 0 ; j < 11 ; j++) {
				char code = mapCode[i].charAt(j);
				if(code == '0') {
					map[i][j] = new Plain();
				}
				else if(code == '1') {
					map[i][j] = new Forest();
				}
				else if(code == '2') {
					map[i][j] = new Field();
				}
				else if(code == '3') {
					map[i][j] = new Village();
				}
				else if(code == '4') {
					map[i][j] = new Mine();
				}
				else if(code == '5') {
					map[i][j] = new SecretBase();
				}
				else if(code == '6') {
					map[i][j] = new Council();
				}
				else {
					map[i][j] = new Water();
				}
			}
		}
	}
}
