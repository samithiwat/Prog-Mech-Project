package logic;

import java.util.ArrayList;

import character.MainCharacter;
import component.entity.Minion;
import component.law.LawDeck;
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
import gui.entity.HexagonPane;
import gui.entity.MapGrid;

public class GameSetUp {
	public static ArrayList<MainCharacter> gameCharacter = new ArrayList<MainCharacter>();
	public static GameLaw gameLaw = new GameLaw();
	public static WeaponDeck weaponDeck = new WeaponDeck();
	public static RemovedDeck removedDeck = new RemovedDeck();
	public static LawSlot lawSlot = new LawSlot();
	public static LawDeck lawDeck = new LawDeck();
	public static ArrayList<SecretBase> allsecretBases = new ArrayList<SecretBase>();
	private static final int DUPLICATE = 5;
	public static String turn;
	public static int cycle = 0;
	public static boolean isGameEnd = false;
	public static boolean isEndTurn;
	public static MainCharacter theGovernment = null;
	public static Location[][] map = new Location[9][11];
	public static MainCharacter thisTurn;
//	public static Minion selectedMinion = null;
	public static int governmentPoint = 0;
	public static HexagonPane selectedTile = null;

	public GameSetUp() {
		thisTurn = gameCharacter.get(0);
//----------------------------------Weapon Set Up---------------------------------------------------
		for (int i = 0; i < DUPLICATE; i++) {
			weaponDeck.addCard(new Axe());
			weaponDeck.addCard(new Bow());
			weaponDeck.addCard(new Gun());
			weaponDeck.addCard(new Shield());
			weaponDeck.addCard(new Sword());
		}
//----------------------------------Add Law Card----------------------------------------------------
		lawDeck.setUpLawDeck();
//----------------------------------Choose Start Location-------------------------------------------
		String[] mapCode = { "51400302105", "1310X001301", "04030X00230", "0001X6X1000", "0010XXX2X01", "200011000X0",
				"00401000430", "10302X3000X", "50004000305"};
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 11; j++) {
				char code = mapCode[i].charAt(j);
				if (code == '0') {
					map[i][j] = new Plain();
				} else if (code == '1') {
					map[i][j] = new Forest();
				} else if (code == '2') {
					map[i][j] = new Field();
				} else if (code == '3') {
					map[i][j] = new Village();
				} else if (code == '4') {
					map[i][j] = new Mine();
				} else if (code == '5') {
					map[i][j] = new SecretBase();
					allsecretBases.add((SecretBase) map[i][j]);
				} else if (code == '6') {
					map[i][j] = new Council();
				} else {
					map[i][j] = new Water();
				}
			}
		}

//////////////////////////////////////////////////////////////FOR DEBUG ONLY ///////////////////////////////////////////////////////////////////
		
//printMap();

//////////////////////////////////////////////////////////////END OF DEBUG ////////////////////////	`/////////////////////////////////////////////	
		
		setUpMapWithHexPane();
		
		SceneController.createGameScene();
	}

	private void setUpMapWithHexPane() {
		for (int i = 0; i < MapGrid.getGrids().size(); i++) {
			ArrayList<HexagonPane> column = MapGrid.getGrids().get(i);
			for (int j = 0; j < column.size(); j++) {
				column.get(i).setLocationType(map[i][j]);
			}
		}
	}
	
////////////////////////////////////////////////////////////// FOR DEBUG ONLY ///////////////////////////////////////////////////////////////////

	private void printMap() {
		for(int i=0;i<map.length;i++) {
			Location[] column = map[i];
			for(Location location : column) {
				System.out.println(location);
			}
		}
	}
	
////////////////////////////////////////////////////////////// END OF DEBUG  ////////////////////////////////////////////////////////////////////
	
}
