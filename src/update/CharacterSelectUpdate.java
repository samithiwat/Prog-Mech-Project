package update;

import java.util.ArrayList;

import character.BlackSkull;
import character.Collector;
import character.RedFox;
import character.Teewada;
import character.Teewadee;
import character.ThousandYear;
import gui.GameLobbyMenu;
import gui.entity.CharacterCard;
import gui.entity.CharacterSetting;
import gui.entity.TextTitle;
import gui.overlay.CharacterInfo;
import javafx.application.Platform;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.media.AudioClip;
import javafx.scene.paint.Color;
import logic.AudioLoader;
import logic.GameController;

public class CharacterSelectUpdate implements Updateable {

	private static Thread t;
	private static CharacterSetting cBox;
	private static ImageView portraits;
	private static ArrayList<CharacterCard> cc = new ArrayList<CharacterCard>();
	private static ArrayList<TextTitle> overlayTexts = new ArrayList<TextTitle>();
	private static ArrayList<TextTitle> overlayInfoTexts = new ArrayList<TextTitle>();

// ------------------------------------------------ Getter and Setter ------------------------------------------------------------

	public static CharacterSetting getcBox() {
		return cBox;
	}

	public static ArrayList<TextTitle> getOverlayInfoTexts() {
		return overlayInfoTexts;
	}

	public static void setOverlayInfoTexts(ArrayList<TextTitle> overlayInfoTexts) {
		CharacterSelectUpdate.overlayInfoTexts = overlayInfoTexts;
	}

	public static void setcBox(CharacterSetting cBox) {
		CharacterSelectUpdate.cBox = cBox;
	}

	public static ArrayList<CharacterCard> getCc() {
		return cc;
	}

	public static ArrayList<TextTitle> getOverlayTexts() {
		return overlayTexts;
	}

// ------------------------------------------------ Set BGM When Mouse Enter -------------------------------------------------------

	public static void mouseEnteredUpdate(CharacterCard cc) {
		cc.setCursor(MOUSE_SELECT);
		cc.setId("character-card-hold");
		GameLobbyMenu.getBGM().stop();
		cc.getSoundEffect().play();
		t = new Thread(() -> {
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				cc.getSoundEffect().stop();
				GameLobbyMenu.getBGM().play();
			}
			Platform.runLater(new Runnable() {

				@Override
				public void run() {
					cc.getSoundEffect().stop();
					GameLobbyMenu.getBGM().play();
				}
			});
		});
		t.start();
	}

	public static void mouseExitedUpdate(CharacterCard cc) {
		cc.setId("character-card");
		cc.setCursor(MOUSE_NORMAL);
		t.interrupt();
	}

// ------------------------------------------ Set Disable Duration When Click Close Icon For Prevent Sound Bug and Selection Bug ----------------------------------------------

	public static void closeUpdate() {
		for (int i = 0; i < 6; i++) {
			cc.get(i).setDisable(true);
			GameLobbyMenu.getCBoxes().get(i).getButtons().get(1).setDisable(true);
			if(!GameLobbyMenu.getCBoxes().get(i).isReady()) {
				GameLobbyMenu.getCBoxes().get(i).setDisable(true);
			}
		}
		Thread t = new Thread(() -> {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {

			}
			Platform.runLater(new Runnable() {

				@Override
				public void run() {
					for (int i = 0; i < 6; i++) {
						cc.get(i).setDisable(false);
						GameLobbyMenu.getCBoxes().get(i).getButtons().get(1).setDisable(false);
						if(!GameLobbyMenu.getCBoxes().get(i).isReady()) {
							GameLobbyMenu.getCBoxes().get(i).setDisable(false);
						}
					}
				}
			});
		});
		t.start();
	}

// ------------------------------------------ Update Selected Character to Overlay and GameController ------------------------------------------

	public static void selectCharacterUpdate(int id) {
		System.out.println("id : " + id);
//		cc.get(id).setDisable(true);
//		cc.get(id).getSelectedText().setVisible(true);
//		overlayTexts.get(id).setFill(Color.web("0xA5A5A5"));

// ------------------------------------------ Update Change Character ----------------------------------------------------------------		

		if (cBox.isSelected()) {
			System.out.println("Enable : " + GameController.gameCharacter.get(cBox.getCBoxId()).getName());
			switch (GameController.gameCharacter.get(cBox.getCBoxId()).getName()) {
			case "Mr.RedFox":
				cc.get(0).setSelected(false);
				break;
			case "Ms.Collector":
				cc.get(1).setSelected(false);
				break;
			case "Mr.BlackSkull":
				cc.get(2).setSelected(false);
				break;
			case "Ms.ThousandYear":
				cc.get(3).setSelected(false);
				break;
			case "Teewada":
				cc.get(4).setSelected(false);
				break;
			case "Teewadee":
				cc.get(5).setSelected(false);
				break;
			}
		}

// ------------------------------------------------------- Update Status of Character Is He Selected ----------------------------------------
		
		cBox.setSelected(true);
		cc.get(id).setSelected(true);

		setPortraits(id);
		
// ------------------------------------------------------ Add Selected Character to Array in GameController ----------------------------------
		
		switch (id) {
		case 0:
			GameController.gameCharacter.set(cBox.getCBoxId(), new RedFox());
			GameLobbyMenu.getOverlay1().triggerOverlay(0, 825, 1000);
			break;
		case 1:
			GameController.gameCharacter.set(cBox.getCBoxId(), new Collector());
			GameLobbyMenu.getOverlay1().triggerOverlay(0, 825, 1000);
			break;
		case 2:
			GameController.gameCharacter.set(cBox.getCBoxId(), new BlackSkull());
			GameLobbyMenu.getOverlay1().triggerOverlay(0, 825, 1000);
			break;
		case 3:
			GameController.gameCharacter.set(cBox.getCBoxId(), new ThousandYear());
			GameLobbyMenu.getOverlay2().triggerOverlay(0, 825, 1000);
			break;
		case 4:
			GameController.gameCharacter.set(cBox.getCBoxId(), new Teewada());
			GameLobbyMenu.getOverlay2().triggerOverlay(0, 825, 1000);
			break;
		case 5:
			GameController.gameCharacter.set(cBox.getCBoxId(), new Teewadee());
			GameLobbyMenu.getOverlay2().triggerOverlay(0, 825, 1000);
			break;
		}
		closeUpdate();
		System.out.println("characterCard : " + cc);
		//System.out.println("gameCharacter : " + GameController.gameCharacter);
	}

// --------------------------------------------------------------- Update When Click Ready Button ---------------------------------------------------

	public static void readyUpdate() {
		if (cBox.isSelected()) {
			cBox.getButtons().get(0).setVisible(false);
			cBox.getButtons().get(1).setVisible(true);
			cBox.getButtons().get(2).setDisable(true);
			cBox.getBgGroup().setDisable(true);
			//cBox.setDisable(true);
			cBox.setReady(true);
		} else {
			AudioClip effect = AudioLoader.errorSound;
			effect.play();
		}
	}
	
// --------------------------------------------------------------- Update When Click Unready Button ------------------------------------------------
	
	public static void unreadyUpdate() {
		cBox.getButtons().get(0).setVisible(true);
		cBox.getButtons().get(1).setVisible(false);
		cBox.getButtons().get(2).setDisable(false);
		cBox.getBgGroup().setDisable(false);
		cBox.setDisable(false);
		cBox.setReady(false);
	}

// --------------------------------------------------------------- Update When Trigger Overlay ------------------------------------------------------

	public static void overlayUpdate() {
		for (int i = 0; i < 6; i++) {
			if (cc.get(i).isSelected()) {
				System.out.println("Disable "+ cc.get(i).toString());
				cc.get(i).setDisable(true);
				cc.get(i).getSelectedText().setVisible(true);
				overlayTexts.get(i).setFill(Color.web("0xA5A5A5"));
			}
			else {
				cc.get(i).setDisable(false);
				cc.get(i).getSelectedText().setVisible(false);
				overlayTexts.get(i).setFill(Color.WHITE);
			}
		}
	}
// --------------------------------------------------------------- Trigger Character Information Overlay ---------------------------------------------
	
	public static void rightClickUpdate(int id) {
		AudioClip effect = AudioLoader.transitionEffect;
		effect.play();
		for(int i = 0;i < 6; i++) {
			cc.get(i).setDisable(true);
		}
		
		switch(id) {
		case 0 :
			overlayInfoTexts.get(0).setText("Capture 3 Mine");
			overlayInfoTexts.get(0).setX(431);
			overlayInfoTexts.get(1).setText("Get extra money when start game");
			overlayInfoTexts.get(1).setX(221);
			CharacterInfo.getBg2().setFill(Color.web("0xF58C4A"));
			break;
		case 1 :
			overlayInfoTexts.get(0).setText("Capture 3 Minion");
			overlayInfoTexts.get(0).setX(410);
			overlayInfoTexts.get(1).setText("Get extra attact while defencing");
			overlayInfoTexts.get(1).setX(256);
			CharacterInfo.getBg2().setFill(Color.web("0x0296AF"));
			break;
		case 2 :
			overlayInfoTexts.get(0).setText("Has anyone stand on 4 secret base");
			overlayInfoTexts.get(0).setX(216);
			overlayInfoTexts.get(1).setText("Prison break (can use only one time)");
			overlayInfoTexts.get(1).setX(193);
			CharacterInfo.getBg2().setFill(Color.web("0x04AD8F"));
			break;
		case 3 :
			overlayInfoTexts.get(0).setText("Government arrest 3 prisoner");
			overlayInfoTexts.get(0).setX(269);
			overlayInfoTexts.get(1).setText("Get extra attact while attacking");
			overlayInfoTexts.get(1).setX(256);
			CharacterInfo.getBg2().setFill(Color.web("0xB76389"));
			break;
		case 4 :
			overlayInfoTexts.get(0).setText("Has government 7 times in a row");
			overlayInfoTexts.get(0).setX(235);
			overlayInfoTexts.get(1).setText("Power of goodness (can use only one time)");
			overlayInfoTexts.get(1).setX(121);
			CharacterInfo.getBg2().setFill(Color.web("0xFAB24E"));
			break;
		case 5 :
			overlayInfoTexts.get(0).setText("Has total 7 exiled government in the ocean");
			overlayInfoTexts.get(0).setX(125);
			overlayInfoTexts.get(1).setText("Power of goodness (can use only one time)");
			overlayInfoTexts.get(1).setX(121);
			CharacterInfo.getBg2().setFill(Color.web("0xE04B4B"));
			break;
		}
		
		GameLobbyMenu.getCharacterInfo().triggerOverlay(0, 825, 1000);
	}
	
// --------------------------------------------------------------- Set Portraits For selected Character ---------------------------------------------

	private static void setPortraits(int id) {
		ArrayList<TextTitle> texts = cBox.getTexts();

		switch (id) {
		case 0:
			// --------------------------------------------------- Set Text -------------------------------------------------------------
			texts.get(0).setVisible(false);
			texts.get(1).setText("Mr.Red Fox");
			texts.get(1).setX(47);
			// --------------------------------------------------- Set Character Image --------------------------------------------------
			portraits = cBox.getPortraits();
			portraits.setImage(new Image(ClassLoader.getSystemResource("img/character/MrRedFox.png").toString()));
			portraits.setVisible(true);
			portraits.setX(65);
			portraits.setY(45);
			portraits.setFitWidth(90);
			portraits.setFitHeight(105);

//			cBox.getBgGroup().getChildren().add(portraits);

			cBox.getBg().get(0).setId("character-box-selected");
			cBox.getBg().get(1).setVisible(true);
			break;
		case 1:
			// --------------------------------------------------- Set Text -------------------------------------------------------------
			texts.get(0).setVisible(false);
			texts.get(1).setText("Lady Collector");
			texts.get(1).setX(26);
			// --------------------------------------------------- Set Character Image --------------------------------------------------
			portraits = cBox.getPortraits();
			portraits.setImage(
					new Image(ClassLoader.getSystemResource("img/character/LadyCollectorFull.png").toString()));
			portraits.setVisible(true);
//			portraits = new ImageView(ClassLoader.getSystemResource("img/character/LadyCollectorFull.png").toString());
			portraits.setX(50);
			portraits.setY(45);
			portraits.setFitWidth(120);
			portraits.setFitHeight(105);

//			cBox.getBgGroup().getChildren().add(portraits);

			cBox.getBg().get(0).setId("character-box-selected");
			cBox.getBg().get(1).setVisible(true);
			break;

		case 2:
			// --------------------------------------------------- Set Text
			// -------------------------------------------------------------
			texts.get(0).setVisible(false);
			texts.get(1).setText("Black Skull");
			texts.get(1).setX(46);
			// --------------------------------------------------- Set Character Image
			// --------------------------------------------------
			portraits = cBox.getPortraits();
			portraits.setImage(new Image(ClassLoader.getSystemResource("img/character/BlackSkull.png").toString()));
			portraits.setVisible(true);
//			portraits = new ImageView(ClassLoader.getSystemResource("img/character/BlackSkull.png").toString());
			portraits.setX(65);
			portraits.setY(45);
			portraits.setFitWidth(90);
			portraits.setFitHeight(105);

//			cBox.getBgGroup().getChildren().add(portraits);

			cBox.getBg().get(0).setId("character-box-selected");
			cBox.getBg().get(1).setVisible(true);
			break;
		case 3:
			// --------------------------------------------------- Set Text
			// -------------------------------------------------------------
			texts.get(0).setVisible(false);
			texts.get(1).setText("Sir Thousand Year");
			texts.get(1).setX(8);
			// --------------------------------------------------- Set Character Image
			// --------------------------------------------------
			portraits = cBox.getPortraits();
			portraits
					.setImage(new Image(ClassLoader.getSystemResource("img/character/SirThousandYear.png").toString()));
			portraits.setVisible(true);
//			portraits = new ImageView(ClassLoader.getSystemResource("img/character/SirThousandYear.png").toString());
			portraits.setX(65);
			portraits.setY(45);
			portraits.setFitWidth(90);
			portraits.setFitHeight(105);

//			cBox.getBgGroup().getChildren().add(portraits);

			cBox.getBg().get(0).setId("character-box-selected");
			cBox.getBg().get(1).setVisible(true);
			break;
		case 4:
			// --------------------------------------------------- Set Text
			// -------------------------------------------------------------
			texts.get(0).setVisible(false);
			texts.get(1).setText("Sir Tewada");
			texts.get(1).setX(48);
			// --------------------------------------------------- Set Character Image
			// --------------------------------------------------
			portraits = cBox.getPortraits();
			portraits.setImage(new Image(ClassLoader.getSystemResource("img/character/SirTewada.png").toString()));
			portraits.setVisible(true);
//			portraits = new ImageView(ClassLoader.getSystemResource("img/character/SirTewada.png").toString());
			portraits.setX(65);
			portraits.setY(45);
			portraits.setFitWidth(90);
			portraits.setFitHeight(105);

//			cBox.getBgGroup().getChildren().add(portraits);

			cBox.getBg().get(0).setId("character-box-selected");
			cBox.getBg().get(1).setVisible(true);
			break;
		case 5:
			// --------------------------------------------------- Set Text
			// -------------------------------------------------------------
			texts.get(0).setVisible(false);
			texts.get(1).setText("Sir Tewadee");
			texts.get(1).setX(41);
			// --------------------------------------------------- Set Character Image
			// --------------------------------------------------
			portraits = cBox.getPortraits();
			portraits.setImage(new Image(ClassLoader.getSystemResource("img/character/SirTewadee.png").toString()));
			portraits.setVisible(true);
//			portraits = new ImageView(ClassLoader.getSystemResource("img/character/SirTewadee.png").toString());
			portraits.setX(65);
			portraits.setY(45);
			portraits.setFitWidth(90);
			portraits.setFitHeight(105);

//			cBox.getBgGroup().getChildren().add(portraits);

			cBox.getBg().get(0).setId("character-box-selected");
			cBox.getBg().get(1).setVisible(true);
			break;
		}

	}

}
