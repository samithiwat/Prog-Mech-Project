package logic;

import java.util.ArrayList;
import java.util.Collections;

import character.MainCharacter;
import component.weaponCard.WeaponCard;
// this class can be deleted.
public class TradeController {
	public TradeController(MainCharacter trader, MainCharacter traded) {
		ArrayList<WeaponCard> trader_WeaponSlot = new ArrayList<WeaponCard>();
		ArrayList<WeaponCard> traded_WeaponSlot = new ArrayList<WeaponCard>();
		int trader_money = 0;
		int traded_money = 0;
//		update trading screen to logic
//		while(!accepted) {
//		}
		trader.getWeaponHand().addAll(traded_WeaponSlot);
		traded.getWeaponHand().addAll(trader_WeaponSlot);
		trader.setMoney(trader.getMoney() + traded_money - trader_money);
		traded.setMoney(traded.getMoney() - traded_money + trader_money);
	}
}
