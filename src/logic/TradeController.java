package logic;

import java.util.ArrayList;
import java.util.Collections;

import character.MainCharacter;
import component.weaponCard.WeaponCard;
import gui.MainIsland;
import javafx.scene.paint.Color;
import update.FightOverlayUpdate;
import update.PlayerPanelUpdate;

// this class can be deleted.
public class TradeController {
	public static ArrayList<WeaponCard> trader_WeaponSlot = new ArrayList<WeaponCard>();
	public static ArrayList<WeaponCard> traded_WeaponSlot = new ArrayList<WeaponCard>();
	public static int trader_money = 0;
	public static int traded_money = 0;

	public static void trade(MainCharacter trader, MainCharacter traded) {
		if(traded_money > traded.getMoney() || trader_money > trader.getMoney()) {
			PlayerPanelUpdate.setShowMessage("Money is not enough!", Color.WHITE, 100, 3000);
			MainIsland.setShowMessage("Money is not enough!", Color.web("0xFEFDE8"), Color.web("0x89949B"),
					100, 1, 3000);
		}
		else {
			trader.getWeaponHand().addAll(traded_WeaponSlot);
			traded.getWeaponHand().addAll(trader_WeaponSlot);
			trader.setMoney(trader.getMoney() + traded_money - trader_money);
			traded.setMoney(traded.getMoney() - traded_money + trader_money);
			MainIsland.setShowMessage("Trade success!", Color.WHITE, 120, 3000);
			PlayerPanelUpdate.setShowMessage("Trade success!", Color.WHITE, 120, 3000);
		}
		if(GameSetUp.isFightTradeMode) {
			FightOverlayUpdate.invUpdate();			
		}
		traded_WeaponSlot.clear();
		trader_WeaponSlot.clear();
		trader_money = 0;
		traded_money = 0;
		GameSetUp.isFightTradeMode = false;
		traded.setFightTraded(true);
		traded.setTraded(true);	
	}

}
