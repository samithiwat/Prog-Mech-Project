package component.law;

import java.util.ArrayList;
import java.util.Collections;

public class LawDeck {
	private ArrayList<LawCard> lawDeck;

	public LawDeck() {
		this.lawDeck = new ArrayList<LawCard>();

	}

	public void setUpLawDeck() {
		Collections.addAll(lawDeck, new BanArWut(), new ChuayLeauKonJon(), new JubKaoKuk(), new LongTodeKonShua(),
				new PaLangKonDee(), new PaLangYernTra(), new PaSeeArWut(), new PaSeeKarnKreunTee(), new PaSeeLaekKong(),
				new PaSeeMeung(), new PaSeeTeeDin(), new PaSeeTorSuu(), new RatTaBarnPatTinKrubBarn(),
				new ReakKurnKongKumRang(), new SakSeeKonJon(), new SaMakeeProngDong(), new SaNabSaNoonKonDee(),
				new SaNabSaNoonKongTub(), new TaoTeumTookKon());
	}

	// -----------getter/setter--------------------------
	public ArrayList<LawCard> getLawDeck() {
		return lawDeck;
	}

	public void setLawDeck(ArrayList<LawCard> lawDeck) {
		this.lawDeck = lawDeck;
	}

}
