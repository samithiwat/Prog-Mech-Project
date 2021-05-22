package update;

import character.MainCharacter;
import character.Teewada;
import character.Teewadee;
import component.entity.Minion;
import component.weaponCard.WeaponCard;
import gui.MapOverview;
import gui.entity.InvCard;
import gui.overlay.FightOverlay;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import logic.FightController;
import logic.GameSetUp;

public class FightOverlayUpdate {
	public static Minion challenger;
	public static Minion challenged;

	public static void updateSelectors() {
		if (GameSetUp.selectedIcon.get(0).getMinion().getPossessedBy().equals(GameSetUp.thisTurn)) {
			challenger = GameSetUp.selectedIcon.get(0).getMinion();
			challenged = GameSetUp.selectedIcon.get(1).getMinion();
		} else {
			challenger = GameSetUp.selectedIcon.get(1).getMinion();
			challenged = GameSetUp.selectedIcon.get(0).getMinion();
		}
	}

	public static void pfpUpdate() {
		for (int i = 0; i < MapOverview.allFightOverlay.size(); i++) {
			FightOverlay overlay = MapOverview.allFightOverlay.get(i);
			overlay.getchallenged_img().setImage(challenged.getPossessedBy().getPfp());
			overlay.getchallenger_img().setImage(challenger.getPossessedBy().getPfp());
			if (challenged.getPossessedBy().getName().equals("Sir Tewada")) {
				overlay.getChallenged_ult().setImage(Teewada.ultSkill);
				overlay.getChallenged_ult().setVisible(Teewada.warCry);
			} else if (challenged.getPossessedBy().getName().equals("Sir Tewadee")) {
				overlay.getChallenged_ult().setImage(Teewadee.ultSkill);
				overlay.getChallenged_ult().setVisible(Teewadee.warCry);
			}
			if (challenger.getPossessedBy().getName().equals("Sir Tewada")) {
				overlay.getChallenger_ult().setImage(Teewada.ultSkill);
				overlay.getChallenger_ult().setVisible(Teewada.warCry);
			} else if (challenger.getPossessedBy().getName().equals("Sir Tewadee")) {
				overlay.getChallenger_ult().setImage(Teewadee.ultSkill);
				overlay.getChallenger_ult().setVisible(Teewadee.warCry);
			}
		}
	}

	public static void invUpdate() {
		for (int i = 0; i < MapOverview.allFightOverlay.size(); i++) {
			FightOverlay overlay = MapOverview.allFightOverlay.get(i);
			for (int j = 0; j < overlay.challenger_weaponCard.size(); j++) {
				InvCard card = overlay.challenger_weaponCard.get(j);
				if (card.findCard(card.getKey()) == -1
						|| FightController.challenger_slot.size() >= challenger.getMyMinion().size() + 1) {
					card.setVisible(false);
				} else {
					card.setVisible(true);
				}
				for (int k = 0; k < GameSetUp.lawSlot.getBannedWeapon().size(); k++) {
					if (overlay.challenger_weaponCard.get(j).getCard().getName()
							.equals(GameSetUp.lawSlot.getBannedWeapon().get(k).getName())) {
						card.setVisible(false);
						break;
					}
				}
			}
			for (int j = 0; j < overlay.challenged_weaponCard.size(); j++) {
				InvCard card = overlay.challenged_weaponCard.get(j);
				if (card.findCard(card.getKey()) == -1
						|| FightController.challenged_slot.size() >= challenged.getMyMinion().size() + 1) {
					card.setVisible(false);
				} else {
					card.setVisible(true);
				}
				for (int k = 0; k < GameSetUp.lawSlot.getBannedWeapon().size(); k++) {
					if (overlay.challenger_weaponCard.get(j).getCard().getName()
							.equals(GameSetUp.lawSlot.getBannedWeapon().get(k).getName())) {
						card.setVisible(false);
						break;
					}
				}
			}
		}
	}

	public static void challengedofferUpdate() {
		for (int i = 0; i < MapOverview.allFightOverlay.size(); i++) {
			FightOverlay overlay = MapOverview.allFightOverlay.get(i);
			overlay.getChallenged_offer1().getChildren().clear();
			overlay.getChallenged_offer2().getChildren().clear();
			overlay.getChallenged_offer3().getChildren().clear();
			for (int j = 0; j < FightController.challenged_slot.size(); j++) {
				WeaponCard card = FightController.challenged_slot.get(j);
				ImageView card_img = new ImageView(ClassLoader.getSystemResource(card.getImg_path()).toString());
				card_img.setFitHeight(145);
				card_img.setFitWidth(97);
				card_img.setOnMouseClicked((MouseEvent event) -> {
					if (GameSetUp.gameLaw.taxWeapon) {
						for (int k = 0; k < GameSetUp.lawSlot.getTaxedWeapon().size(); k++) {
							if (card.getName().equals(GameSetUp.lawSlot.getTaxedWeapon().get(k).getName())) {
								challenged.getPossessedBy()
										.setMoney(challenged.getPossessedBy().getMoney() + 1 * MainCharacter.M);
							}
						}
					}
					challenged.getPossessedBy().getWeaponHand().add(card);
					FightController.challenged_slot.remove(card);
					challengedofferUpdate();
				});
				if (challenged.getPossessedBy().getName().equals("Council")) {
					card_img.setDisable(true);
				}
				if (j < 3) {
					if (j == 3) {
						overlay.getChallenged_offer1().add(overlay.getChallenged_page1_right(), 4, 0);
					}
					overlay.getChallenged_offer1().add(card_img, j + 1, 0);
				} else if (j >= 3 && j < 6) {
					if (j == 3) {
						overlay.getChallenged_offer2().add(overlay.getChallenged_page2_left(), 0, 0);
					}
					if (j == 6) {
						overlay.getChallenged_offer2().add(overlay.getChallenged_page2_right(), 4, 0);
					}
					overlay.getChallenged_offer2().add(card_img, j + 1 - 3, 0);
				} else if (j >= 6) {
					if (j == 6) {
						overlay.getChallenged_offer3().add(overlay.getChallenged_page3_left(), 0, 0);
					}
					overlay.getChallenged_offer3().add(card_img, j + 1 - 6, 0);
				}
			}
		}
		invUpdate();
	}

	public static void challengerofferUpdate() {
		for (int i = 0; i < MapOverview.allFightOverlay.size(); i++) {
			FightOverlay overlay = MapOverview.allFightOverlay.get(i);
			overlay.getChallenger_offer1().getChildren().clear();
			overlay.getChallenger_offer2().getChildren().clear();
			overlay.getChallenger_offer3().getChildren().clear();
			overlay.getChallenger_offer2().setVisible(false);
			overlay.getChallenger_offer3().setVisible(false);
			for (int j = 0; j < FightController.challenger_slot.size(); j++) {
				WeaponCard card = FightController.challenger_slot.get(j);
				ImageView card_img = new ImageView(ClassLoader.getSystemResource(card.getImg_path()).toString());
				card_img.setFitHeight(145);
				card_img.setFitWidth(97);
				card_img.setOnMouseClicked((MouseEvent event) -> {
					if (GameSetUp.gameLaw.taxWeapon) {
						for (int k = 0; k < GameSetUp.lawSlot.getTaxedWeapon().size(); k++) {
							if (card.getName().equals(GameSetUp.lawSlot.getTaxedWeapon().get(k).getName())) {
								challenger.getPossessedBy()
										.setMoney(challenger.getPossessedBy().getMoney() + 1 * MainCharacter.M);
							}
						}
					}
					challenger.getPossessedBy().getWeaponHand().add(card);
					FightController.challenger_slot.remove(card);
					challengerofferUpdate();
				});
				if (j < 3) {
					overlay.getChallenger_offer1().add(card_img, j + 1, 0);
				} else if (j >= 3 && j < 6) {
					if (j == 3) {
						overlay.getChallenger_offer2().setVisible(true);
						overlay.getChallenger_offer1().add(overlay.getChallenger_page1_right(), 4, 0);
						overlay.getChallenger_offer2().add(overlay.getChallenger_page2_left(), 0, 0);
					}
					overlay.getChallenger_offer2().add(card_img, j + 1 - 3, 0);
				} else if (j >= 6) {
					if (j == 6) {
						overlay.getChallenger_offer3().setVisible(true);
						overlay.getChallenger_offer2().add(overlay.getChallenger_page2_right(), 4, 0);
						overlay.getChallenger_offer3().add(overlay.getChallenger_page3_left(), 0, 0);
					}
					overlay.getChallenger_offer3().add(card_img, j + 1 - 6, 0);
				}
			}
		}
		invUpdate();
	}

	public static void acceptUpdate() {
		if (challenged.getPossessedBy().getName().equals("Council")) {
			FightOverlay.challenged_IsAccepted = true;
			for (int i = 0; i < MapOverview.allFightOverlay.size(); i++) {
				MapOverview.allFightOverlay.get(i).getchallenged_accept().setDisable(true);
			}
		}
		for (int i = 0; i < MapOverview.allFightOverlay.size(); i++) {
			FightOverlay overlay = MapOverview.allFightOverlay.get(i);
			if (FightOverlay.challenged_IsAccepted) {
				overlay.getchallenged_accept().setStyle("-fx-background-color : #279F2B;");
			} else {
				overlay.getchallenged_accept().setStyle("-fx-background-color : #C4C4C4;");
			}

			if (FightOverlay.challenger_IsAccepted) {
				overlay.getchallenger_accept().setStyle("-fx-background-color : #279F2B;");
			} else {
				overlay.getchallenger_accept().setStyle("-fx-background-color : #C4C4C4;");
			}
		}
	}
}
