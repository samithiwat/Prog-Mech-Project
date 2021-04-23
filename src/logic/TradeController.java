package logic;

import java.util.ArrayList;
import java.util.Collections;

import character.MainCharacter;
import component.weaponCard.WeaponCard;
import update.FightOverlayUpdate;

// this class can be deleted.
public class TradeController {
	public static ArrayList<WeaponCard> trader_WeaponSlot = new ArrayList<WeaponCard>();
	public static ArrayList<WeaponCard> traded_WeaponSlot = new ArrayList<WeaponCard>();
	public static int trader_money = 0;
	public static int traded_money = 0;

	public static void trade(MainCharacter trader, MainCharacter traded) {
		trader.getWeaponHand().addAll(traded_WeaponSlot);
		traded.getWeaponHand().addAll(trader_WeaponSlot);
		trader.setMoney(trader.getMoney() + traded_money - trader_money);
		traded.setMoney(traded.getMoney() - traded_money + trader_money);
		FightOverlayUpdate.invUpdate();
		traded_WeaponSlot.clear();
		trader_WeaponSlot.clear();
		trader_money = 0;
		traded_money = 0;
		GameSetUp.isFightTradeMode = false;
		traded.setFightTraded(true);
		traded.setTraded(true);
	}

}
