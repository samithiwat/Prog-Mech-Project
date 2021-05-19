package gui.entity;

import component.law.BanWeapon;
import component.law.Sympathetic;
import component.law.Capture;
import component.law.PunishBadGuy;
import component.law.GoodPower;
import component.law.MoneyPower;
import component.law.PaSeeArWut;
import component.law.MoveTax;
import component.law.TradeTax;
import component.law.MineTax;
import component.law.LandTax;
import component.law.FightTax;
import component.law.Pardon;
import component.law.RecallArmy;
import component.law.Unite;
import component.law.EncourageGoodGuy;
import component.law.SupportArmy;
import component.law.PoorPrestige;
import component.law.Equality;
import gui.overlay.Government;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;

public class LawCardSlot extends GridPane {

	private static final int WIDTH = 800;
	private static final int HEIGHT = 650;
	private static final int IMG_WIDTH = 90;
	private static final int IMG_HEIGHT = 135;

	public LawCardSlot() {
// --------------------------------------------- Set Up Pane --------------------------------------------------------

		setId("law-overlay-bg");
		setPrefHeight(HEIGHT);
		setPrefWidth(WIDTH);
		setAlignment(Pos.CENTER);
		setPadding(new Insets(23, 75, 23, 75));
		setVgap(20);
		setHgap(50);

// -------------------------------------------- Set Up Slot --------------------------------------------------------------------
		LawCardIcon.setImgWidth(IMG_WIDTH);
		LawCardIcon.setImgHeight(IMG_HEIGHT);
		setUpSlot();
	}

	private void setUpSlot() {
		LawCardIcon banWeapon = new LawCardIcon(new BanWeapon());
		banWeapon.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				Government.setMode(0);
				StatusPane.triggerSelectWeapon();
			}

		});

		LawCardIcon capture = new LawCardIcon(new Capture());
		LawCardIcon encourageGoodGuy = new LawCardIcon(new EncourageGoodGuy());
		LawCardIcon equality = new LawCardIcon(new Equality());

		add(banWeapon, 0, 0);
		add(capture, 1, 0);
		add(encourageGoodGuy, 2, 0);
		add(equality, 3, 0);

		LawCardIcon fightTax = new LawCardIcon(new FightTax());
		LawCardIcon goodPower = new LawCardIcon(new GoodPower());
		LawCardIcon landTax = new LawCardIcon(new LandTax());
		LawCardIcon mineTax = new LawCardIcon(new MineTax());
		LawCardIcon moneyPower = new LawCardIcon(new MoneyPower());

		add(fightTax, 0, 1);
		add(goodPower, 1, 1);
		add(landTax, 2, 1);
		add(mineTax, 3, 1);
		add(moneyPower, 4, 1);

		LawCardIcon moveTax = new LawCardIcon(new MoveTax());
		LawCardIcon pardon = new LawCardIcon(new Pardon());
		LawCardIcon poorPrestige = new LawCardIcon(new PoorPrestige());
		LawCardIcon punishBadGuy = new LawCardIcon(new PunishBadGuy());
		LawCardIcon recallArmy = new LawCardIcon(new RecallArmy());

		add(moveTax, 0, 2);
		add(pardon, 1, 2);
		add(poorPrestige, 2, 2);
		add(punishBadGuy, 3, 2);
		add(recallArmy, 4, 2);

		LawCardIcon supportArmy = new LawCardIcon(new SupportArmy());
		LawCardIcon sympatheticPoor = new LawCardIcon(new Sympathetic());
		LawCardIcon tradeTax = new LawCardIcon(new TradeTax());
		LawCardIcon unite = new LawCardIcon(new Unite());
		LawCardIcon weaponTax = new LawCardIcon(new PaSeeArWut());
		weaponTax.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				Government.setMode(1);
				StatusPane.triggerSelectWeapon();
			}

		});

		add(supportArmy, 0, 3);
		add(sympatheticPoor, 1, 3);
		add(tradeTax, 2, 3);
		add(unite, 3, 3);
		add(weaponTax, 4, 3);
	}
}
