package gui.entity;

import component.law.BanArWut;
import component.law.ChuayLeauKonJon;
import component.law.JubKaoKuk;
import component.law.LongTodeKonShua;
import component.law.PaLangKonDee;
import component.law.PaLangYernTra;
import component.law.PaSeeArWut;
import component.law.PaSeeKarnKreunTee;
import component.law.PaSeeLaekKong;
import component.law.PaSeeMeung;
import component.law.PaSeeTeeDin;
import component.law.PaSeeTorSuu;
import component.law.RatTaBarnPatTinKrubBarn;
import component.law.ReakKurnKongKumRang;
import component.law.SaMakeeProngDong;
import component.law.SaNabSaNoonKonDee;
import component.law.SaNabSaNoonKongTub;
import component.law.SakSeeKonJon;
import component.law.TaoTeumTookKon;
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
		LawCardIcon banWeapon = new LawCardIcon(new BanArWut());
		banWeapon.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				Government.setMode(0);
				StatusPane.triggerSelectWeapon();
			}

		});

		LawCardIcon capture = new LawCardIcon(new JubKaoKuk());
		LawCardIcon encourageGoodGuy = new LawCardIcon(new SaNabSaNoonKonDee());
		LawCardIcon equality = new LawCardIcon(new TaoTeumTookKon());

		add(banWeapon, 0, 0);
		add(capture, 1, 0);
		add(encourageGoodGuy, 2, 0);
		add(equality, 3, 0);

		LawCardIcon fightTax = new LawCardIcon(new PaSeeTorSuu());
		LawCardIcon goodPower = new LawCardIcon(new PaLangKonDee());
		LawCardIcon landTax = new LawCardIcon(new PaSeeTeeDin());
		LawCardIcon mineTax = new LawCardIcon(new PaSeeMeung());
		LawCardIcon moneyPower = new LawCardIcon(new PaLangYernTra());

		add(fightTax, 0, 1);
		add(goodPower, 1, 1);
		add(landTax, 2, 1);
		add(mineTax, 3, 1);
		add(moneyPower, 4, 1);

		LawCardIcon moveTax = new LawCardIcon(new PaSeeKarnKreunTee());
		LawCardIcon pardon = new LawCardIcon(new RatTaBarnPatTinKrubBarn());
		LawCardIcon poorPrestige = new LawCardIcon(new SakSeeKonJon());
		LawCardIcon punishBadGuy = new LawCardIcon(new LongTodeKonShua());
		LawCardIcon recallArmy = new LawCardIcon(new ReakKurnKongKumRang());

		add(moveTax, 0, 2);
		add(pardon, 1, 2);
		add(poorPrestige, 2, 2);
		add(punishBadGuy, 3, 2);
		add(recallArmy, 4, 2);

		LawCardIcon supportArmy = new LawCardIcon(new SaNabSaNoonKongTub());
		LawCardIcon sympatheticPoor = new LawCardIcon(new ChuayLeauKonJon());
		LawCardIcon tradeTax = new LawCardIcon(new PaSeeLaekKong());
		LawCardIcon unite = new LawCardIcon(new SaMakeeProngDong());
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
